package ua.kiyv.training.testingSystem.dao.Impl;

import ua.kiyv.training.testingSystem.connection.DaoConnection;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.TopicDao;
import ua.kiyv.training.testingSystem.dao.mapper.TopicMapper;
import ua.kiyv.training.testingSystem.dao.mapper.UserMapper;
import ua.kiyv.training.testingSystem.model.Topic;
import ua.kiyv.training.testingSystem.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanya on 02.01.2018.
 */
public class JdbcTopicDao implements TopicDao {
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
                throw new DaoException("Creating user failed: no rows affected.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new DaoException("Creating user failed: no id obtained.");
            }
            Integer id = generatedKeys.getInt(1);
            topic.setId(id);
            generatedKeys.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't create user", e);
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
                throw new DaoException("Topic with id " + id + " doesn't exist");
            }
            TopicMapper topicMapper = new TopicMapper();
            topic = topicMapper.extractFromResultSet(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get topic", e);
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
        } catch (SQLException e) {
            throw new DaoException("Can't get all users.", e);
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
                throw new DaoException("Updating topic failed: no rows affected.");
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't update topic", e);
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
                throw new DaoException("Deleting topic failed: no rows affected.");
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't delete topic", e);
        }

    }
}
