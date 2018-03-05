package com.github.database.tasks;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.String.format;

public class TaskDao {
    private static final String INSERT = "insert into tasks (name) values ('%s')";
    private static final String MARK_COMPLETED = "update tasks set completed_by = '%s', completed_at=now() where id = '%s'";
    private static final String REMOVE_COMPLETED = "delete from tasks where completed_by is not null";
    private static final String BY_NAME = "select * from tasks where name = '%s'";
    private DataSource dataSource;
    private TaskMapper taskMapper = new TaskMapper();

    public TaskDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Task getByName(String name) throws SQLException {
        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery(format(BY_NAME, name));
        return taskMapper.getSingle(resultSet);
    }

    public void save(String name) throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate(format(INSERT, name));
    }

    public void markAsDone(int userId, int taskId) throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate(format(MARK_COMPLETED, 2, 1));
    }

    public void removeCompleted() throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate(REMOVE_COMPLETED);
    }
}
