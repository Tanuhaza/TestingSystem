package ua.kiyv.training.testingSystem.dao.mapper;

import ua.kiyv.training.testingSystem.model.entity.Option;

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
                .setOptionText( rs.getString("optionText") )
                .setScore( rs.getInt("score") )
                .setCorrect(rs.getBoolean("isCorrect"))
                .setComment(rs.getString("comment"))
                .setQuestionId(rs.getInt("question_id"))
                .build();
          return option;
    }

}
