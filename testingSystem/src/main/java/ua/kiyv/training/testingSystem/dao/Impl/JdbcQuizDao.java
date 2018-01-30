package ua.kiyv.training.testingSystem.dao.Impl;

import org.apache.log4j.Logger;
import ua.kiyv.training.testingSystem.connection.DaoConnection;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.QuizDao;
import ua.kiyv.training.testingSystem.dao.mapper.QuizMapper;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.Quiz;
import ua.kiyv.training.testingSystem.utils.constants.LoggerMessages;
import ua.kiyv.training.testingSystem.utils.constants.MessageKeys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanya on 15.01.2018.
 *
 * Implementation of quiz dao, which works with MySql using jdbc
 */

public class JdbcQuizDao implements QuizDao {

    JdbcQuizDao(){}

    private static final Logger logger = Logger.getLogger(JdbcQuizDao.class);

    @Override
    public void create(Quiz quiz) {
        String sqlStatement = "INSERT INTO test (name,topic_id) VALUES (?, ? )";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, quiz.getName());
            statement.setInt(2, quiz.getTopicId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException(MessageKeys.WRONG_TEST_DB_CREATING_NO_ROWS_AFFECTED);
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new DaoException(MessageKeys.WRONG_TEST_DB_NO_ID_OBTAINED);
            }
            Integer id = generatedKeys.getInt(1);
            quiz.setId(id);
            generatedKeys.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_CREATE_NEW_TEST + quiz.toString());
            throw new DaoException(ex, MessageKeys.WRONG_TEST_DB_CAN_NOT_CREATE);
        }
    }

    @Override
    public Quiz findById(int id) {
        String sqlStatement = "SELECT * FROM test WHERE id = ?";
        Quiz quiz;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new DaoException(MessageKeys.WRONG_TEST_DB_NO_ID_EXIST);
            }
            QuizMapper quizMapper = new QuizMapper();
            quiz = quizMapper.extractFromResultSet(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_TEST_BY_ID + id);
            throw new DaoException(ex, MessageKeys.WRONG_TEST_DB_CAN_NOT_GET);
        }
        return quiz;
    }

    @Override
    public List<Quiz> findAll() {
        String sqlStatement = "SELECT * FROM test";
        Quiz quiz;
        List<Quiz> quizzes = new ArrayList<>();
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            QuizMapper quizMapper =new QuizMapper();
            while (resultSet.next()) {
                quiz = quizMapper.extractFromResultSet(resultSet);
                quizzes.add(quiz);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_ALL_TESTS);
            throw new DaoException(ex, MessageKeys.WRONG_TEST_DB_CAN_NOT_GET_ALL_TESTS);
        }
        return quizzes;
    }

    @Override
    public void update(Quiz quiz) {
        String sqlStatement = "UPDATE test SET name = ?, topic_id = ?  WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, quiz.getName());
            statement.setInt(2, quiz.getTopicId());
            statement.setInt(3, quiz.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException(MessageKeys.WRONG_TEST_DB_UPDATING_NO_ROWS_AFFECTED);
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_UPDATE_TEST + quiz.toString());
            throw new DaoException(ex, MessageKeys.WRONG_TEST_DB_CAN_NOT_UPDATE);
        }
    }

    @Override
    public void delete(Quiz quiz) {
        String sqlStatement = "DELETE FROM test WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, quiz.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException(MessageKeys.WRONG_TEST_DB_DELETING_NO_ROWS_AFFECTED);
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_DELETE_TEST + quiz.getId());
            throw new DaoException(ex, MessageKeys.WRONG_TEST_DB_CAN_NOT_DELETE);
        }
    }

    @Override
    public List<Integer> getAssociatedQuestionsIDByQuizID(int id) {
        String sqlStatement = "SELECT * FROM question_test WHERE test_Id = ?";
        List<Integer> associatedQuestionsId = new ArrayList<>();
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                associatedQuestionsId.add(resultSet.getInt("question_Id"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_ASSOSIATED_QUESTIONS_BY_TEST_ID + id);
            throw new DaoException(ex, MessageKeys.WRONG_TEST_DB_CAN_NOT_GET_ASSOSIATED_QUESTIONS);
        }
        return associatedQuestionsId;
    }


    @Override
    public List<Integer> getAssociatedQuestionsIDByQuizIDWithLimitPerPage(int id,int startFrom, int quantity) {
        String sqlStatement = "SELECT * FROM question_test WHERE test_Id = ? limit ?,?";
        List<Integer> associatedQuestionsId = new ArrayList<>();
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.setInt(2, startFrom);
            statement.setInt(3, quantity);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                associatedQuestionsId.add(resultSet.getInt("question_Id"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_ASSOSIATED_QUESTIONS_BY_TEST_ID + id);
            throw new DaoException(ex, MessageKeys.WRONG_TEST_DB_CAN_NOT_GET_ASSOSIATED_QUESTIONS);
        }
        return associatedQuestionsId;
    }

    @Override
    public int countAllQuestionByQuizId(int id) {
        String sqlStatement = "SELECT COUNT(question_id) AS total_count FROM question_test WHERE test_Id= ? ";
        int totalNumberOfQuestionsByTestId;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new DaoException(MessageKeys.WRONG_TEST_DB_CAN_NOT_GET_ALL_TESTS);
            }
            totalNumberOfQuestionsByTestId = resultSet.getInt("total_count");
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_ALL_TESTS);
            throw new DaoException(ex, MessageKeys.WRONG_TEST_DB_CAN_NOT_GET_ALL_TESTS);
        }
        return  totalNumberOfQuestionsByTestId;
    }

    @Override
    public List<Quiz> getAssosiatedQuizzesByTopicId(int id) {
        String sqlStatement = "SELECT * FROM test WHERE topic_id = ?";
        List<Quiz> quizzes =new ArrayList<>();
        Quiz quiz;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            QuizMapper quizMapper = new QuizMapper();
            while (resultSet.next()) {
                quiz = quizMapper.extractFromResultSet(resultSet);
                quizzes.add(quiz);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_FIND_ASSOSIATED_TESTS_BY_TOPIC_ID + id);
            throw new DaoException(ex, MessageKeys.WRONG_TEST_DB_CAN_NOT_GET_ASSOSIATED_TEST_BY_TOPIC);
        }
        return quizzes;
    }

    @Override
    public boolean associate(Quiz quiz, Question question) {
        String sqlStatement = "INSERT INTO test  (question_id, test_id, topic_id ) VALUES (?,?,?)";
        boolean isCreated;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, question.getId());
            statement.setInt(2, quiz.getId());
            statement.setInt(3, quiz.getTopicId());

            isCreated = statement.executeUpdate() != 0;
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_ASSOSIATE_TEST_WITH_QUESTION +" testId " + quiz.getId() + " questionId  " + question.getId());
            throw new DaoException(ex, MessageKeys.WRONG_TEST_DB_CAN_NOT_ASSOSIATE_TESTS_WITH_QUESTIONS);
        }
        return isCreated;
    }
}
