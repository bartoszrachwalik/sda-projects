package com.github.database.taskuser;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.String.format;

public class TaskUserDao {

    private static final String BY_TASK_ID = "select * from task_user where task_id = '%s'";
    private static final String BY_USER_ID = "select * from task_user where user_id = '%s'";
    private static final String SAVE = "INSERT INTO task_user (task_id, user_id) VALUES ((SELECT id FROM tasks WHERE id = '%s'),(SELECT id FROM users WHERE id = '%s'))";
    private DataSource dataSource;
    private TaskUserMapper taskUserMapper = new TaskUserMapper();

    public TaskUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(int task_id, int user_id) throws SQLException {
        dataSource.getConnection().createStatement().executeQuery(format(SAVE, task_id, user_id));
    }

    public TaskUser getByTaskId(int task_id) throws SQLException {
        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery(format(BY_TASK_ID, task_id));
        return taskUserMapper.getSingle(resultSet);
    }

    public TaskUser getByUserId(int user_id) throws SQLException {
        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery(format(BY_USER_ID, user_id));
        return taskUserMapper.getSingle(resultSet);
    }
}
