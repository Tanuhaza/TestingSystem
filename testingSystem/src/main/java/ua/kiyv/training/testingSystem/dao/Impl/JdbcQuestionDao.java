package ua.kiyv.training.testingSystem.dao.Impl;

import ua.kiyv.training.testingSystem.connection.DaoConnection;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.QuestionDao;
import ua.kiyv.training.testingSystem.dao.mapper.QuestionMapper;
import ua.kiyv.training.testingSystem.model.entity.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanya on 02.01.2018.
 */
public class JdbcQuestionDao implements QuestionDao {
    @Override
    public void create(Question question) {
            String sqlStatement = "INSERT INTO questions (question,topic_Id) VALUES (?, ?)";
            try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sqlStatement,
                        Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, question.getQuestionText());
                statement.setInt(2, question.getTopicId());
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new DaoException("Creating question failed: no rows affected.");
                }
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (!generatedKeys.next()) {
                    throw new DaoException("Creating question failed: no id obtained.");
                }
                Integer id = generatedKeys.getInt(1);
                question.setId(id);
                generatedKeys.close();
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't create question", e);
            }

    }

    @Override
    public Question findById(int id) {
        String sqlStatement = "SELECT * FROM questions WHERE id = ?";
        Question question;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new DaoException("Question with id " + id + " doesn't exist");
            }
            QuestionMapper questionMapper = new QuestionMapper();
            question = questionMapper.extractFromResultSet(resultSet);

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get question", e);
        }
        return question;
    }

    @Override
    public List<Question> findAll() {
        String sqlStatement = "SELECT * FROM questions";
        Question question;
        List<Question> questions = new ArrayList<>();
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            QuestionMapper questionMapper=new QuestionMapper();
            while (resultSet.next()) {
                question = questionMapper.extractFromResultSet(resultSet);
                questions.add(question);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get all questions", e);
        }
        return questions;
    }

    @Override
    public void update(Question question) {
        String sqlStatement = "UPDATE questions SET question = ?, topic_id = ? WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, question.getQuestionText());
            statement.setInt(2, question.getTopicId());
            statement.setInt(3, question.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Updating question failed: no rows affected.");
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't update question", e);
        }

    }

    @Override
    public void delete(Question question) {
        String sqlStatement = "DELETE FROM questions WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, question.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Deleting question failed: no rows affected.");
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't delete question", e);
        }

    }
}
