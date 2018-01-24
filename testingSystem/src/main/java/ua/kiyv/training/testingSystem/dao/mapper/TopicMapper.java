package ua.kiyv.training.testingSystem.dao.mapper;

import ua.kiyv.training.testingSystem.model.entity.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Tanya on 04.01.2018.
 */
public class TopicMapper implements ObjectMapper<Topic> {

    @Override
    public Topic extractFromResultSet(ResultSet rs) throws SQLException {
       return new Topic.Builder()
                .setId(rs.getInt("id") )
                .setTitle( rs.getString("title") )
                .setInfo( rs.getString("info") )
                .build();
    }

}
