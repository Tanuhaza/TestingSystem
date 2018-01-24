package ua.kiyv.training.testingSystem.dao.mapper;

import ua.kiyv.training.testingSystem.model.entity.Option;
import ua.kiyv.training.testingSystem.model.entity.Test;
import ua.kiyv.training.testingSystem.model.entity.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Tanya on 14.01.2018.
 */
public class TestMapper implements ObjectMapper<Test> {
    @Override
    public Test extractFromResultSet(ResultSet rs) throws SQLException {
        return new Test.Builder()
                .setId(rs.getInt("id") )
                .setName (rs.getString("name") )
                .setTopicId( rs.getInt("topic_id") )
                .build();
    }

}
