package ua.kiyv.training.testingSystem.dao.mapper;

import ua.kiyv.training.testingSystem.model.Assessment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Tanya on 04.01.2018.
 */
public class AssessmentMapper implements ObjectMapper<Assessment> {

    @Override
    public Assessment extractFromResultSet(ResultSet rs) throws SQLException {
            return new Assessment.Builder()
                .setId(rs.getInt("id") )
                .setUserId( rs.getInt("users_id") )
                .setDataPassed( rs.getDate("dataPassed") )
                .setTopicId(rs.getInt("topic_id"))
                .setTotalScore(rs.getInt("totalScore"))
                .build();
    }

    @Override
    public Assessment makeUnique(Map<Integer, Assessment> cache, Assessment teacher) {
        return null;
    }
}
