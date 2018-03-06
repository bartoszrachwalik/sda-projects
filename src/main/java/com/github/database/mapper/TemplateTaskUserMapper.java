package com.github.database.mapper;

import com.github.database.taskuser.TaskUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TemplateTaskUserMapper implements RowMapper<TaskUser> {

    @Override
    public TaskUser mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new TaskUser(resultSet.getInt("task_id"), resultSet.getInt("user_id"));
    }
}