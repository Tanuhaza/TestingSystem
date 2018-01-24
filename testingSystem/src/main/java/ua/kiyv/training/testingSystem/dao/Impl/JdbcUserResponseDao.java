package ua.kiyv.training.testingSystem.dao.Impl;

import ua.kiyv.training.testingSystem.connection.DaoConnection;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.UserResponseDao;
import ua.kiyv.training.testingSystem.dao.mapper.UserResponseMapper;
import ua.kiyv.training.testingSystem.model.entity.Test;
import ua.kiyv.training.testingSystem.model.entity.UserResponse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanya on 14.01.2018.
 */
public class JdbcUserResponseDao implements UserResponseDao {

        @Override
        public void create(UserResponse userResponse) {
            String sqlStatement = "INSERT INTO user_response (user_id,topic_id, test_id,question_id,options_id, totalScore, passedTimes) VALUES (?, ?, ?, ?, ?, ?,?)";
            try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sqlStatement);
                statement.setInt(1, userResponse.getUserId());
                statement.setInt(2, userResponse.getTopicId());
                statement.setInt(3, userResponse.getTestId());
                statement.setInt(4, userResponse.getQuestionId());
                statement.setInt(5, userResponse.getOptionId());
                statement.setInt(6, userResponse.getTotalScore());
                statement.setInt(7, userResponse.getPassedTimes());

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new DaoException("Creating userResponse failed: no rows affected.");
                }

                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't create userResponse", e);
            }
        }

    @Override
    public UserResponse findById(int id) {
        return null;
    }

    @Override
        public List<UserResponse> findByUserId(int id){
            String sqlStatement = "SELECT * FROM user_response WHERE user_id = ?";
            List<UserResponse> userResponses = new ArrayList<>();
            UserResponse userResponse;
            try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sqlStatement);
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery(sqlStatement);
                UserResponseMapper userResponseMapper = new UserResponseMapper();
                while (resultSet.next()) {
                    userResponse = userResponseMapper.extractFromResultSet(resultSet);
                    userResponses.add(userResponse);
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't get userResponse", e);
            }
            return userResponses;
        }

        @Override
        public List<UserResponse> findAll() {
            String sqlStatement = "SELECT * FROM user_response";
            List<UserResponse> userResponses = new ArrayList<>();
            UserResponse userResponse;
            try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlStatement);
                UserResponseMapper userResponseMapper = new UserResponseMapper();
                while (resultSet.next()) {
                    userResponse = userResponseMapper.extractFromResultSet(resultSet);
                    userResponses.add(userResponse);
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't get all users.", e);
            }
            return userResponses;
        }

    @Override
        public void update(UserResponse userResponse) {
            String sqlStatement = "UPDATE user_response SET user_id = ?, topic_id = ?, test_id=?, question_id=?, options_id=?, totalScore = ?,passedTimes=?" +
                                          " WHERE (user_id = ? and test_id=? and passedTimes=? and options_id=?)";
            try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sqlStatement);
                statement.setInt(1, userResponse.getUserId());
                statement.setInt(2, userResponse.getTopicId());
                statement.setInt(3, userResponse.getTestId());
                statement.setInt(4, userResponse.getQuestionId());
                statement.setInt(5, userResponse.getOptionId());
                statement.setInt(6, userResponse.getTotalScore());
                statement.setInt(7, userResponse.getPassedTimes());
                statement.setInt(8, userResponse.getUserId());
                statement.setInt(9, userResponse.getTestId());
                statement.setInt(10, userResponse.getPassedTimes());
                statement.setInt(11, userResponse.getOptionId());
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new DaoException("Updating UserResponse failed: no rows affected.");
                }
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't update UserResponse", e);
            }
        }

        @Override
        public void delete(UserResponse userResponse) {
            String sqlStatement = "DELETE FROM user_response WHERE user_id = ? and test_id=? and passedTimes=?";
            try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sqlStatement);
                statement.setInt(1, userResponse.getUserId());
                statement.setInt(2, userResponse.getTestId());
                statement.setInt(3, userResponse.getPassedTimes());
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new DaoException("Deleting userResponse failed: no rows affected.");
                }
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't delete userResponse", e);
            }
        }

    @Override
    public void deleteByPassedTimes(int userId, int testId, int passedTimes) {
        String sqlStatement = "DELETE FROM user_response WHERE user_id = ? and test_id=? and passedTimes=?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, userId);
            statement.setInt(2, testId);
            statement.setInt(3, passedTimes);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Deleting userResponse failed: no rows affected.");
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't delete userResponse", e);
        }
    }

    @Override
    public List<Integer> getPassedTestsIdByUserId(int userId){
        String sqlStatement = "SELECT DISTINCT test_id,passedTimes FROM user_response WHERE user_id = ?";
        int testId;
        List<Integer> passedTestsId = new ArrayList<>();
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                testId =resultSet.getInt("test_id");
                passedTestsId.add(testId);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get testId", e);
        }
        return passedTestsId;
    }


    @Override
    public List<UserResponse> getUserResponseByUserAndTestId(int userId,int testId){
        String sqlStatement = "SELECT * FROM user_response WHERE (user_id = ? and test_id=?)";
        UserResponse UserResponse;
        List<UserResponse> userResponses = new ArrayList<>();
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, userId);
            statement.setInt(2, testId);
            ResultSet resultSet = statement.executeQuery();
            UserResponseMapper UserResponseMapper = new UserResponseMapper();
            while (resultSet.next()) {
                UserResponse = UserResponseMapper.extractFromResultSet(resultSet);
                userResponses.add(UserResponse);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get userResponse", e);
        }
        return userResponses;
    }

    @Override
    public List<Integer> getPassedTimes(int userId, int testId){
            String sqlStatement = "SELECT DISTINCT passedTimes FROM user_response WHERE (user_id = ? and test_id=?)";
            List<Integer> passedTimes=new ArrayList<>();
            int num;
            try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sqlStatement);
                statement.setInt(1, userId);
                statement.setInt(2, testId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    num =resultSet.getInt("passedTimes");
                    passedTimes.add(num);
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                throw new DaoException("Can't get column passedTimes", e);
            }

        return passedTimes;
    }
}



