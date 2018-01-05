package ua.kiyv.training.testingSystem.dao.Impl;

import ua.kiyv.training.testingSystem.connection.DaoConnection;
import ua.kiyv.training.testingSystem.connection.Jdbc.JdbcTransactionHelper;
import ua.kiyv.training.testingSystem.dao.AssessmentDao;
import ua.kiyv.training.testingSystem.dao.DaoException;
import ua.kiyv.training.testingSystem.dao.mapper.AssessmentMapper;
import ua.kiyv.training.testingSystem.model.Assessment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanya on 02.01.2018.
 */
public class JdbcAssessmentDao implements AssessmentDao {

    @Override
    public void create(Assessment assessment) {
        String sqlStatement = "INSERT INTO assessment (users_id,topic_id, totalScore) VALUES (?, ?, ? )";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, assessment.getUserId());
            statement.setInt(2, assessment.getTopicId());
            statement.setInt(3, assessment.getTotalScore());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Creating assessment failed: no rows affected.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new DaoException("Creating assesment failed: no id obtained.");
            }
            Integer id = generatedKeys.getInt(1);
            assessment.setId(id);
            generatedKeys.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't create assesment", e);
        }
    }

    @Override
    public Assessment findById(int id){
    String sqlStatement = "SELECT * FROM assessment WHERE id = ?";
        Assessment assessment;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
        PreparedStatement statement = connection.prepareStatement(sqlStatement);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next()) {
        throw new DaoException("Assessment with id " + id + " doesn't exist");
        }
        AssessmentMapper  assessmentMapper=new AssessmentMapper();
        assessment = assessmentMapper.extractFromResultSet(resultSet);
        resultSet.close();
        statement.close();
        } catch (SQLException e) {
        throw new DaoException("Can't get assessment", e);
        }
        return assessment;
        }

    @Override
    public List<Assessment> findAll() {
        String sqlStatement = "SELECT * FROM assessment";
        List<Assessment> assessments = new ArrayList<>();
        Assessment assessment;
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            AssessmentMapper assessmentMapper = new AssessmentMapper();
            while (resultSet.next()) {
                assessment = assessmentMapper.extractFromResultSet(resultSet);
                assessments.add(assessment);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't get all users.", e);
        }
        return assessments;
    }

    @Override
    public void update(Assessment assessment) {
        String sqlStatement = "UPDATE assessment SET users_id = ?, topic_id = ?, totalScore = ? WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, assessment.getUserId());
            statement.setInt(2, assessment.getTopicId());
            statement.setInt(3, assessment.getTotalScore());
            statement.setInt(4, assessment.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Updating assessment failed: no rows affected.");
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't update assessment", e);
        }
    }

    @Override
    public void delete(Assessment assessment) {
        String sqlStatement = "DELETE FROM assessment WHERE id = ?";
        try (DaoConnection connection = JdbcTransactionHelper.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, assessment.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Deleting assessment failed: no rows affected.");
            }
            statement.close();
        } catch (SQLException e) {
            throw new DaoException("Can't delete assessment", e);
        }
    }

    }

