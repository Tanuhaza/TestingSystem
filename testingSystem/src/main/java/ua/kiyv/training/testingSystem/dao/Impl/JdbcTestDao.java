package ua.kiyv.training.testingSystem.dao.Impl;

import ua.kiyv.training.testingSystem.connection.DaoConnection;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.TestDao;
import ua.kiyv.training.testingSystem.dao.mapper.QuestionMapper;
import ua.kiyv.training.testingSystem.dao.mapper.TestMapper;
import ua.kiyv.training.testingSystem.dao.mapper.TopicMapper;
import ua.kiyv.training.testingSystem.model.entity.Question;
import ua.kiyv.training.testingSystem.model.entity.Test;
import ua.kiyv.training.testingSystem.model.entity.Topic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanya on 15.01.2018.
 */
public class JdbcTestDao implements TestDao {
    @Override
    public void create(Test test) {
        String sqlStatement = "INSERT INTO test (name,topic_id) VALUES (?, ? )";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, test.getName());
            statement.setInt(2, test.getTopicId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Creating test failed: no rows affected.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new DaoException("Creating test failed: no id obtained.");
            }
            Integer id = generatedKeys.getInt(1);
            test.setId(id);
            generatedKeys.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't create test", e);
        }
    }

    @Override
    public Test findById(int id) {
        String sqlStatement = "SELECT * FROM test WHERE id = ?";
        Test test;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new DaoException("Test with id " + id + " doesn't exist");
            }
            TestMapper testMapper = new TestMapper();
            test = testMapper.extractFromResultSet(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get test", e);
        }
        return test;
    }

    @Override
    public List<Test> findAll() {
        String sqlStatement = "SELECT * FROM test";
        Test test;
        List<Test> tests = new ArrayList<>();
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            TestMapper testMapper=new TestMapper();
            while (resultSet.next()) {
                test=testMapper.extractFromResultSet(resultSet);
                tests.add(test);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get all tests.", e);
        }
        return tests;
    }

    @Override
    public void update(Test test) {
        String sqlStatement = "UPDATE test SET name = ?, topic_id = ?  WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setString(1, test.getName());
            statement.setInt(2, test.getTopicId());
            statement.setInt(3, test.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Updating test failed: no rows affected.");
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't update test", e);
        }
    }

    @Override
    public void delete(Test test) {
        String sqlStatement = "DELETE FROM test WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, test.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Deleting test failed: no rows affected.");
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't delete test", e);
        }
    }

    @Override
    public List<Integer> getAssociatedQuestionsIDByTestID(int id) {
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
        } catch (SQLException e) {
            throw new DaoException("Can't get associated questions", e);
        }
        return associatedQuestionsId;
    }

    @Override
    public List<Test> getAssosiatedTestsByTopicId(int id) {
        String sqlStatement = "SELECT * FROM test WHERE topic_id = ?";
        List<Test> tests=new ArrayList<>();
        Test test;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            TestMapper testMapper = new TestMapper();
            while (resultSet.next()) {
                test = testMapper.extractFromResultSet(resultSet);
                tests.add(test);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get test", e);
        }
        return tests;
    }

    @Override
    public boolean associate(Test test,Question question) {
        String sqlStatement = "INSERT INTO test  (question_id, test_id, topic_id ) VALUES (?,?,?)";
        boolean isCreated;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, question.getId());
            statement.setInt(2, test.getId());
            statement.setInt(3, test.getTopicId());

            isCreated = statement.executeUpdate() != 0;
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't assosiate test with question", e);
        }
        return isCreated;
    }
}
