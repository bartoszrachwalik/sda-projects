package com.github.database.mapper;

import com.github.database.user.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TemplateUserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new User(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"));
    }
}
