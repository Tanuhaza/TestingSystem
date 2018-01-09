package ua.kiyv.training.testingSystem.dao.Impl;

import ua.kiyv.training.testingSystem.connection.DaoConnection;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.UserDao;
import ua.kiyv.training.testingSystem.dao.mapper.UserMapper;
import ua.kiyv.training.testingSystem.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanya on 02.01.2018.
 */
public class JdbcUserDao implements UserDao {

    JdbcUserDao(){}

    @Override
    public void create(User user) {
        String sqlStatement = "INSERT INTO users (firstName, lastName,login,password, email, role,superiorId) VALUES (?, ?, ?, ?, ?, ?,?)";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getRole().toString());
            statement.setObject(7, user.getSuperiorId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Creating user failed: no rows affected.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new DaoException("Creating user failed: no id obtained.");
            }
            Integer id = generatedKeys.getInt(1);
            user.setId(id);
            generatedKeys.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't create user", e);
        }
    }

    @Override
    public User findById(int id) {
        String sqlStatement = "SELECT * FROM users WHERE id = ?";
        User user;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new DaoException("User with id " + id + " doesn't exist");
            }
            UserMapper userMapper=new UserMapper();
            user=userMapper.extractFromResultSet(resultSet);
            if (resultSet.wasNull()) {user.setSuperiorId (null);}

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get user", e);
        }
        return user;
    }

    @Override
    public User findByLogin(String login) {
        String sqlStatement = "SELECT id, firstName, lastName,login, password, email, role, superiorId "
                               + "FROM users WHERE login=?";
        User user;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new DaoException("User with login " + login + " doesn't exist");
            }
                UserMapper userMapper=new UserMapper();
                user=userMapper.extractFromResultSet(resultSet);
                if (resultSet.wasNull()) {user.setSuperiorId(null);}

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get user", e);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        String sqlStatement = "SELECT * FROM users";
        User user;
        List<User> users = new ArrayList<>();
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            UserMapper userMapper=new UserMapper();
            while (resultSet.next()) {
                user=userMapper.extractFromResultSet(resultSet);
                if (resultSet.wasNull()) {user.setSuperiorId(null);}
                users.add(user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get all users.", e);
        }
        return users;
    }

    @Override
    public void update(User user) {
        String sqlStatement = "UPDATE users SET firstName = ?, lastName = ?, " +
                "login=?,password=?, email = ?,role = ?, superiorId = ? WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getRole().toString());
            statement.setInt(7, user.getSuperiorId());
            statement.setInt(8, user.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Updating user failed: no rows affected.");
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't update user", e);
        }

    }

    @Override
    public void delete(User user) {
        String sqlStatement = "DELETE FROM users WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, user.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Deleting user failed: no rows affected.");
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't delete user", e);
        }

    }

    @Override
    public List<User> findAllSubordinatesOf(User user) {
        String sqlStatement = "SELECT * FROM users WHERE superiorId = ?";
        List<User> subordinates = new ArrayList<>();
        User subordinate;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (!resultSet.next()) {
                throw new DaoException("Subordinators of  " + user + " don't exist");
            }
            resultSet.previous();
            while (resultSet.next()) {
                subordinate=userMapper.extractFromResultSet(resultSet);
                subordinates.add(subordinate);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get all subordinates of user with id " + user.getId(), e);
        }
        return subordinates;
    }

    @Override
    public List<User> findByRole(User.Role role) {
        String sqlStatement = "SELECT * FROM users WHERE role = ?";
        List<User> users = new ArrayList<>();
        User user;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1,role.toString());
            ResultSet resultSet = statement.executeQuery();
            UserMapper userMapper=new UserMapper();
            if (!resultSet.next()) {
                throw new DaoException("Users for role  " + role + " don't exist");
            }
            resultSet.previous();
            while (resultSet.next()) {
                user = userMapper.extractFromResultSet(resultSet);
                if (resultSet.wasNull())
                    user.setSuperiorId(null);

                users.add(user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get users by role", e);
        }
        return users;
    }
}
