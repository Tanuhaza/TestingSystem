package ua.kiyv.training.testingSystem.dao.mapper;

import ua.kiyv.training.testingSystem.model.Option;
import ua.kiyv.training.testingSystem.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Tanya on 04.01.2018.
 */
public class OptionMapper implements ObjectMapper<Option> {

    @Override
    public Option extractFromResultSet(ResultSet rs) throws SQLException {
        Option option =new Option.Builder()
                .setId(rs.getInt("id") )
                .setOptionText( rs.getString("option") )
                .setScore( rs.getInt("score") )
                .setCorrect(rs.getBoolean("isCorrect"))
                .setComment(rs.getString("comment"))
                .setAssesmentId(rs.getInt("assessment_id"))
                .setQuestionId(rs.getInt("questions_id"))
                .build();
        if (rs.getInt("assessment_id") == 0)
            option.setAssesmentId(null);
          return option;
    }

    @Override
    public Option makeUnique(Map<Integer, Option> cache, Option teacher) {
        return null;
    }
}
