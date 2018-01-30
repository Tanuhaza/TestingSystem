package ua.kiyv.training.testingSystem.dao.mapper;

import ua.kiyv.training.testingSystem.model.entity.Quiz;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Tanya on 14.01.2018.
 */
public class QuizMapper implements ObjectMapper<Quiz> {
    @Override
    public Quiz extractFromResultSet(ResultSet rs) throws SQLException {
        return new Quiz.Builder()
                .setId(rs.getInt("id") )
                .setName (rs.getString("name") )
                .setTopicId( rs.getInt("topic_id") )
                .build();
    }

}
