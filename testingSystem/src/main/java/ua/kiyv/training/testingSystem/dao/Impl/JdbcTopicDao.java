package ua.kiyv.training.testingSystem.dao.Impl;

import org.apache.log4j.Logger;
import ua.kiyv.training.testingSystem.connection.DaoConnection;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.TopicDao;
import ua.kiyv.training.testingSystem.dao.mapper.TopicMapper;
import ua.kiyv.training.testingSystem.model.entity.Question;
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
 */

/**
 * Implementation of topic response dao, which works with MySql using jdbc
 */
public class JdbcTopicDao implements TopicDao {
    JdbcTopicDao(){}

    private static final Logger logger = Logger.getLogger(JdbcTopicDao.class);

    @Override
    public void create(Topic topic) {
        String sqlStatement = "INSERT INTO topic (title,info) VALUES (?, ? )";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, topic.getTitle());
            statement.setString(2, topic.getInfo());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException(MessageKeys.WRONG_TOPIC_DB_CREATING_NO_ROWS_AFFECTED);
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new DaoException(MessageKeys.WRONG_TOPIC_DB_NO_ID_OBTAINED);
            }
            Integer id = generatedKeys.getInt(1);
            topic.setId(id);
            generatedKeys.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_CREATE_NEW_TOPIC + topic.toString());
            throw new DaoException(ex, MessageKeys.WRONG_TOPIC_DB_CAN_NOT_CREATE);
        }
    }

    @Override
    public Topic findById(int id) {
        String sqlStatement = "SELECT * FROM topic WHERE id = ?";
        Topic topic;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new DaoException(MessageKeys.WRONG_TOPIC_DB_NO_ID_EXIST);
            }
            TopicMapper topicMapper = new TopicMapper();
            topic = topicMapper.extractFromResultSet(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_TOPIC_BY_ID + id);
            throw new DaoException(ex, MessageKeys.WRONG_TOPIC_DB_CAN_NOT_GET);
        }
        return topic;
    }

    @Override
    public List<Topic> findAll() {
        String sqlStatement = "SELECT * FROM topic";
        Topic topic;
        List<Topic> topics = new ArrayList<>();
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            TopicMapper topicMapper=new TopicMapper();
            while (resultSet.next()) {
                topic=topicMapper.extractFromResultSet(resultSet);
                topics.add(topic);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_FIND_ALL_TOPICS);
            throw new DaoException(ex, MessageKeys.WRONG_TOPIC_DB_CAN_NOT_GET_ALL_TOPICS);
        }
        return topics;
    }

    @Override
    public void update(Topic topic) {
        String sqlStatement = "UPDATE topic SET title = ?, info = ?  WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, topic.getTitle());
            statement.setString(2, topic.getInfo());
            statement.setInt(3, topic.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException(MessageKeys.WRONG_TOPIC_DB_UPDATING_NO_ROWS_AFFECTED);
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_UPDATE_TOPIC + topic.toString());
            throw new DaoException(ex, MessageKeys.WRONG_TOPIC_DB_CAN_NOT_UPDATE);
        }
    }

    @Override
    public void delete(Topic topic) {
        String sqlStatement = "DELETE FROM topic WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, topic.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException(MessageKeys.WRONG_TOPIC_DB_DELETING_NO_ROWS_AFFECTED);
            }
            statement.close();
        } catch (SQLException ex) {
            logger.error(LoggerMessages.ERROR_DELETE_TOPIC + topic.getId());
            throw new DaoException(ex, MessageKeys.WRONG_TOPIC_DB_CAN_NOT_DELETE);
        }
    }

    @Override
    public List<Question> getAssosiatedQuestions(int id) {
        return null;
    }
}
