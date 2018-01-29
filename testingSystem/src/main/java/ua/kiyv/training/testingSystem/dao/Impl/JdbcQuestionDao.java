package ua.kiyv.training.testingSystem.dao.Impl;

import org.apache.log4j.Logger;
import ua.kiyv.training.testingSystem.connection.DaoConnection;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.connection.TransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.QuestionDao;
import ua.kiyv.training.testingSystem.dao.mapper.OptionMapper;
import ua.kiyv.training.testingSystem.dao.mapper.QuestionMapper;
import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.Test;
import ua.kiyv.training.testingSystem.model.entity.Topic;
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
 * Implementation of question dao, which works with MySql using jdbc
 */

public class JdbcQuestionDao implements QuestionDao {

    JdbcQuestionDao(){}

    private static final Logger logger = Logger.getLogger(JdbcQuestionDao.class);

    @Override
    public void create(Question question) {
            String sqlStatement = "INSERT INTO question (question,topic_Id) VALUES (?, ?)";
            try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sqlStatement,
                        Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, question.getQuestionText());
                statement.setInt(2, question.getTopicId());
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new DaoException(MessageKeys.WRONG_QUESTION_DB_CREATING_NO_ROWS_AFFECTED);
                }
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (!generatedKeys.next()) {
                    throw new DaoException(MessageKeys.WRONG_QUESTION_DB_NO_ID_OBTAINED);
                }
                Integer id = generatedKeys.getInt(1);
                question.setId(id);
                generatedKeys.close();
                statement.close();
            } catch (SQLException ex) {
                logger.error(LoggerMessages.ERROR_CREATE_NEW_QUESTION + question.toString());
                throw new DaoException(ex, MessageKeys.WRONG_QUESTION_DB_CAN_NOT_CREATE);
            }

    }

    @Override
    public Question findById(int id) {
        String sqlStatement = "SELECT * FROM question WHERE id = ?";
        Question question;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new DaoException(MessageKeys.WRONG_QUESTION_DB_NO_ID_EXIST);
            }
            QuestionMapper questionMapper = new QuestionMapper();
            question = questionMapper.extractFromResultSet(resultSet);

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_QUESTION_BY_ID + id);
            throw new DaoException(ex, MessageKeys.WRONG_QUESTION_DB_CAN_NOT_GET);
        }
        return question;
    }

    @Override
    public List<Question> findAll() {
        String sqlStatement = "SELECT * FROM question";
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
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_ALL_QUESTIONS);
            throw new DaoException(ex, MessageKeys.WRONG_QUESTION_DB_CAN_NOT_GET_ALL_QUESTIONS);
        }
        return questions;
    }

    @Override
    public void update(Question question) {
        String sqlStatement = "UPDATE question SET question = ?, topic_id = ? WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, question.getQuestionText());
            statement.setInt(2, question.getTopicId());
            statement.setInt(3, question.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException(MessageKeys.WRONG_QUESTION_DB_UPDATING_NO_ROWS_AFFECTED);
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_UPDATE_QUESTION + question.toString());
            throw new DaoException(ex, MessageKeys.WRONG_QUESTION_DB_CAN_NOT_UPDATE);
        }

    }

    @Override
    public void delete(Question question) {
        String sqlStatement = "DELETE FROM question WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, question.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException(MessageKeys.WRONG_QUESTION_DB_DELETING_NO_ROWS_AFFECTED);
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_DELETE_QUESTION + question.getId());
            throw new DaoException(ex, MessageKeys.WRONG_QUESTION_DB_CAN_NOT_DELETE);
        }
    }

    @Override
    public List<Question> getAssosiatedQuestionByTopicId(int id) {
        String sqlStatement = "SELECT * FROM question WHERE topic_id = ?";
        List<Question> questions=new ArrayList<>();
        Question question;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            QuestionMapper questionMapper = new QuestionMapper();
            while (resultSet.next()) {
                question = questionMapper.extractFromResultSet(resultSet);
                questions.add(question);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_ASSOSIATED_QUESTIONS_BY_TOPIC_ID + id );
            throw new DaoException(ex, MessageKeys.WRONG_QUESTION_DB_CAN_NOT_GET_ASSOSIATED_QUESTIONS_BY_TOPIC_ID);
        }
        return questions;
    }

    @Override
    public List<Option> getAssociatedOptionsByQuestionID( int id) {
        String sqlStatement = "SELECT * FROM options WHERE question_Id = ?";
        List<Option> associatedOptions = new ArrayList<>();
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            OptionMapper optionMapper = new OptionMapper();
            while (resultSet.next()) {
                Option option = optionMapper.extractFromResultSet(resultSet);
                associatedOptions.add(option);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_FIND_ASSOSIATED_OPTIONS_BY_QUESTION_ID + id);
            throw new DaoException(ex, MessageKeys.WRONG_QUESTION_DB_CAN_NOT_GET_ASSOSIATED_OPTIONS_BY_QUESTION_ID );
        }
        return associatedOptions;
    }


    @Override
    public List<Integer> getAssociatedTestsIDByQuestionID(int id) {
        String sqlStatement = "SELECT * FROM question_test WHERE question_Id = ?";
        List<Integer> associatedTestsId = new ArrayList<>();
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                associatedTestsId.add(resultSet.getInt("test_Id"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_FIND_ASSOSIATED_TESTS_BY_QUESTION_ID + id);
            throw new DaoException(ex, MessageKeys.WRONG_QUESTION_DB_CAN_NOT_GET_ASSOSIATED_TESTS_BY_QUESTION_ID);
        }
        return associatedTestsId;
    }

}
