package com.github.database.taskuser;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskUserPrepStateDao {

    private static final String BY_TASK_ID = "select * from task_user where task_id = ?";
    private static final String BY_USER_ID = "select * from task_user where user_id = ?";
    private static final String SAVE = "INSERT INTO task_user (task_id, user_id) VALUES ((SELECT id FROM tasks WHERE id = ?),(SELECT id FROM users WHERE id = ?))";
    private DataSource dataSource;
    private TaskUserMapper taskUserMapper = new TaskUserMapper();

    public TaskUserPrepStateDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(int task_id, int user_id) throws SQLException {
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SAVE);
        preparedStatement.setInt(1, task_id);
        preparedStatement.setInt(2, user_id);
        preparedStatement.executeUpdate();
    }

    public TaskUser getByTaskId(int task_id) throws SQLException {
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(BY_TASK_ID);
        preparedStatement.setInt(1, task_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return taskUserMapper.getSingle(resultSet);
    }

    public TaskUser getByUserId(int user_id) throws SQLException {
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(BY_USER_ID);
        preparedStatement.setInt(1, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return taskUserMapper.getSingle(resultSet);
    }
}
