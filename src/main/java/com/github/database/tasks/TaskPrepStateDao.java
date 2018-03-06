package com.github.database.tasks;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskPrepStateDao {

    private static final String INSERT = "insert into tasks (name) values (?)";
    private static final String MARK_COMPLETED = "update tasks set completed_by = ?, "
            + "completed_at=now() where id = ?";
    private static final String REMOVE_COMPLETED = "delete from tasks where completed_by is not null";
    private static final String BY_NAME = "select * from tasks where name = ?";

    private DataSource dataSource;
    private TaskMapper taskMapper = new TaskMapper();

    public TaskPrepStateDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Task getByName(String name) throws SQLException {
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(BY_NAME);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        return taskMapper.getSingle(resultSet);
    }

    public void save(String name) throws SQLException {
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(INSERT);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
    }

    public void markAsDone(int userId, int taskId) throws SQLException {
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(MARK_COMPLETED);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, taskId);
        preparedStatement.executeUpdate();
    }

    public void removeCompleted() throws SQLException {
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(REMOVE_COMPLETED);
        preparedStatement.executeUpdate();
    }
}