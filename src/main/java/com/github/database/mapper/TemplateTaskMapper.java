package com.github.database.mapper;

import com.github.database.tasks.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TemplateTaskMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet resultSet, int rowNUm) throws SQLException {
        return new Task(resultSet.getInt("id"), resultSet.getString("name"));
    }
}
