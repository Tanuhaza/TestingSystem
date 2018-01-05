package ua.kiyv.training.testingSystem.dao.mapper;

import ua.kiyv.training.testingSystem.model.Option;
import ua.kiyv.training.testingSystem.model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static com.sun.corba.se.impl.util.RepositoryId.cache;

/**
 * Created by Tanya on 04.01.2018.
 */
public class QuestionMapper implements ObjectMapper<Question> {

    @Override
    public Question extractFromResultSet(ResultSet rs) throws SQLException {
        return new Question.Builder()
                .setId(rs.getInt("id") )
                .setQuestionText( rs.getString("question") )
                .setCreationalDate( rs.getDate("creationDate") )
                .setTopicId(rs.getInt("topic_id") )
                .build();
    }


    @Override
    public Question makeUnique(Map<Integer, Question> cache, Question teacher) {
        return null;
    }

}
