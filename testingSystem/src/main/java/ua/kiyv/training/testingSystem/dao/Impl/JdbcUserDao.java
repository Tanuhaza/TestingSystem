package ua.kiyv.training.testingSystem.dao.Impl;

import org.apache.log4j.Logger;
import ua.kiyv.training.testingSystem.connection.DaoConnection;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.UserDao;
import ua.kiyv.training.testingSystem.dao.mapper.UserMapper;
import ua.kiyv.training.testingSystem.model.entity.User;
import ua.kiyv.training.testingSystem.utils.constants.LoggerMessages;
import ua.kiyv.training.testingSystem.utils.constants.MessageKeys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanya on 02.01.2018.
 *
 * Implementation of user dao, which works with MySql using jdbc
 */

public class JdbcUserDao implements UserDao {

    JdbcUserDao() {
    }

    private static final Logger logger = Logger.getLogger(JdbcUserDao.class);

    @Override
    public void create(User user) {
        String sqlStatement = "INSERT INTO user (firstName, lastName,login,password, email, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getRole().toString());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException(MessageKeys.WRONG_USER_DB_CREATING_NO_ROWS_AFFECTED);
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new DaoException(MessageKeys.WRONG_USER_DB_NO_ID_OBTAINED);
            }
            Integer id = generatedKeys.getInt(1);
            user.setId(id);
            generatedKeys.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_CREATE_NEW_USER + user.toString());
            throw new DaoException(ex, MessageKeys.WRONG_USER_DB_CAN_NOT_CREATE);
        }
    }

    @Override
    public User findById(int id) {
        String sqlStatement = "SELECT * FROM user WHERE id = ?";
        User user;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new DaoException(MessageKeys.WRONG_USER_DB_NO_ID_EXIST);
            }
            UserMapper userMapper = new UserMapper();
            user = userMapper.extractFromResultSet(resultSet);

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_USER_BY_ID + id);
            throw new DaoException(ex, MessageKeys.WRONG_USER_DB_CAN_NOT_GET);
        }
        return user;
    }

    @Override
    public User findByLogin(String login) {
        String sqlStatement = "SELECT id, firstName, lastName,login, password, email, role "
                + "FROM user WHERE login=?";
        User user;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new DaoException(MessageKeys.WRONG_USER_DB_NO_LOGIN_EXIST);
            }
            UserMapper userMapper = new UserMapper();
            user = userMapper.extractFromResultSet(resultSet);

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_USER_BY_LOGIN + login);
            throw new DaoException(ex, MessageKeys.WRONG_USER_DB_CAN_NOT_GET);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        String sqlStatement = "SELECT * FROM user";
        User user;
        List<User> users = new ArrayList<>();
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            UserMapper userMapper = new UserMapper();
            while (resultSet.next()) {
                user = userMapper.extractFromResultSet(resultSet);
                users.add(user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_ALL_USERS);
            throw new DaoException(ex, MessageKeys.WRONG_USER_DB_CAN_NOT_GET_ALL_USERS);
        }
        return users;
    }

    @Override
    public int countAllUsers() {
        String sqlStatement = "SELECT COUNT(id) AS total_count FROM user ";
        int totalNumberOfUsers;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new DaoException(MessageKeys.WRONG_USER_DB_CAN_NOT_GET_ALL_USERS);
            }
            totalNumberOfUsers = resultSet.getInt("total_count");
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_ALL_USERS);
            throw new DaoException(ex, MessageKeys.WRONG_USER_DB_CAN_NOT_GET_ALL_USERS);
        }
        return totalNumberOfUsers;
    }

    @Override
    public List<User> getAllWithLimitPerPage(int startFrom, int quantity) {
        String sqlStatement = "SELECT * FROM user order by lastName desc limit ?,?";
        User user;
        List<User> users = new ArrayList<>();
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, startFrom);
            statement.setInt(2, quantity);
            ResultSet resultSet = statement.executeQuery();
            UserMapper userMapper = new UserMapper();
            while (resultSet.next()) {
                user = userMapper.extractFromResultSet(resultSet);
                users.add(user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_ALL_USERS);
            throw new DaoException(ex, MessageKeys.WRONG_USER_DB_CAN_NOT_GET_ALL_USERS);
        }
        return users;
    }

    @Override
    public void update(User user) {
        String sqlStatement = "UPDATE user SET firstName = ?, lastName = ?, " +
                "login=?,password=?, email = ?,role = ?, WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getRole().toString());
            statement.setInt(7, user.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException(MessageKeys.WRONG_USER_DB_UPDATING_NO_ROWS_AFFECTED);
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_UPDATE_USER + user.toString());
            throw new DaoException(ex, MessageKeys.WRONG_USER_DB_CAN_NOT_UPDATE);
        }
    }

    @Override
    public void delete(User user) {
        String sqlStatement = "DELETE FROM user WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, user.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException(MessageKeys.WRONG_USER_DB_DELETING_NO_ROWS_AFFECTED);
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_DELETE_USER + user.getId());
            throw new DaoException(ex, MessageKeys.WRONG_USER_DB_CAN_NOT_DELETE);
        }
    }

    @Override
    public List<User> findByRole(User.Role role) {
        String sqlStatement = "SELECT * FROM user WHERE role = ?";
        List<User> users = new ArrayList<>();
        User user;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, role.toString());
            ResultSet resultSet = statement.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (!resultSet.next()) {
                throw new DaoException(MessageKeys.WRONG_USER_DB_ROLE);
            }
            resultSet.previous();
            while (resultSet.next()) {
                user = userMapper.extractFromResultSet(resultSet);
                users.add(user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_ROLE + role);
            throw new DaoException(ex, MessageKeys.WRONG_USER_DB_CAN_NOT_GET);
        }
        return users;
    }
}
