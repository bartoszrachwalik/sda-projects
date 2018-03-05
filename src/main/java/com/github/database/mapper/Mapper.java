package com.github.database.mapper;

import com.github.database.tasks.Task;
import com.github.database.taskuser.TaskUser;
import com.github.database.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mapper {

    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String TASK_ID = "task_id";
    private static final String USER_ID = "user_id";
    private static final String NAME = "name";

    public User mapUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt(ID), resultSet.getString(LOGIN), resultSet.getString(PASSWORD));
    }

    public Task mapTask(ResultSet resultSet) throws SQLException {
        return new Task(resultSet.getInt(ID), resultSet.getString(NAME));
    }

    public TaskUser mapTaskUser(ResultSet resultSet) throws SQLException {
        return new TaskUser(resultSet.getInt(TASK_ID), resultSet.getInt(USER_ID));
    }
}
