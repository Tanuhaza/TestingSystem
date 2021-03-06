package ua.kiyv.training.testingSystem.dao.mapper;

import ua.kiyv.training.testingSystem.model.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {


    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return new User.Builder()
                .setId(rs.getInt("id") )
                .setFirstName( rs.getString("firstName") )
                .setLastName( rs.getString("lastName") )
                .setLogin(rs.getString("login"))
                .setPassword(rs.getString("password"))
                .setEmail(rs.getString("email"))
                .setRole(User.Role.valueOf(rs.getString("role")))
                .build();
    }
}

