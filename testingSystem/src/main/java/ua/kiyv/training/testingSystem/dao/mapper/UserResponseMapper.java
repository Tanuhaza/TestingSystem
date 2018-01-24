package ua.kiyv.training.testingSystem.dao.mapper;

import ua.kiyv.training.testingSystem.model.entity.UserResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Tanya on 14.01.2018.
 */
public class UserResponseMapper implements ObjectMapper<UserResponse> {

    @Override
    public UserResponse extractFromResultSet(ResultSet rs) throws SQLException {
        return new UserResponse.Builder()
                .setUserId(rs.getInt("user_id"))
                .setTopicId(rs.getInt("topic_id"))
                .setTestId(rs.getInt("test_id"))
                .setQuestionId(rs.getInt("question_id"))
                .setOptionId(rs.getInt("options_id"))
                .setTotalScore(rs.getInt("totalScore"))
                .setDataPassed(rs.getDate("dataPassed"))
                .setPassedTimes(rs.getInt("passedTimes"))
                .build();
    }

}

