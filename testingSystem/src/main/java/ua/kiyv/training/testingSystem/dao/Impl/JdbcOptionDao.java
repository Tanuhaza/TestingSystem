package ua.kiyv.training.testingSystem.dao.Impl;


import ua.kiyv.training.testingSystem.connection.DaoConnection;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.OptionDao;
import ua.kiyv.training.testingSystem.dao.mapper.OptionMapper;
import ua.kiyv.training.testingSystem.model.entity.Option;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanya on 02.01.2018.
 */
public class JdbcOptionDao implements OptionDao {
    @Override
    public void create(Option option) {
        String sqlStatement = "INSERT INTO options  (optionText, score, isCorrect, comment, " +
                            " question_id) VALUES (?,?,?,?,?)";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, option.getOptionText());
            statement.setInt(2, option.getScore());
            statement.setBoolean(3, option.isCorrect());
            statement.setString(4, option.getComment());
            statement.setInt(5, option.getQuestionId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Creating option failed: no rows affected.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new DaoException("Creating option failed: no id obtained.");
            }
            Integer id = generatedKeys.getInt(1);
            option.setId(id);
            generatedKeys.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't create option", e);
        }
    }


    @Override
    public Option findById(int id) {
        String sqlStatement = "SELECT * FROM options WHERE id = ?";
        Option option;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new DaoException("option with id " + id + " doesn't exist");
            }
            OptionMapper optionMapper = new OptionMapper();
            option = optionMapper.extractFromResultSet(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get option", e);
        }
        return option;
    }

    @Override
    public List<Option> findAll(){
        String sqlStatement = "SELECT * FROM options";
        Option option;
        List<Option> options = new ArrayList<>();
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            OptionMapper optionMapper=new OptionMapper();
            while (resultSet.next()) {
                option = optionMapper.extractFromResultSet(resultSet);
                options.add(option);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get all options", e);
        }
        return options;
    }

    @Override
    public void update(Option option) {
        String sqlStatement = "UPDATE options SET optionText = ?, score = ?, isCorrect = ?,comment = ?" +
                            " question_id=?  WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, option.getOptionText());
            statement.setInt(2, option.getScore());
            statement.setBoolean(3, option.isCorrect());
            statement.setString(4, option.getComment());
            statement.setInt(5, option.getQuestionId());
            statement.setInt(6, option.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Updating option failed: no rows affected.");
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't update option", e);
        }
    }

    @Override
    public void delete(Option option) {
        String sqlStatement = "DELETE FROM options WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, option.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Deleting option failed: no rows affected.");
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't delete option", e);
        }
    }
}
