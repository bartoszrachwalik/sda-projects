package com.github.database.tasks;

import com.github.database.mapper.TemplateTaskMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class TaskJdbcTemplateDao {

    private static final String INSERT = "insert into tasks (name) values (?)";
    private static final String MARK_COMPLETED = "update tasks set completed_by = ?, "
            + "completed_at=now() where id = ?";
    private static final String REMOVE_COMPLETED = "delete from tasks where completed_by is not null";
    private static final String BY_NAME = "select * from tasks where name = ?";
    private JdbcTemplate jdbcTemplate;

    public TaskJdbcTemplateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Task getByName(String name) throws SQLException {
        return jdbcTemplate.queryForObject(BY_NAME, new TemplateTaskMapper(), name);
    }

    public void save(String name) throws SQLException {
        jdbcTemplate.update(INSERT,name);
    }

    public void markAsDone(int userId, int taskId) throws SQLException {
        jdbcTemplate.query(MARK_COMPLETED, new TemplateTaskMapper(), userId, taskId);
    }

    public void removeCompleted() throws SQLException {
        jdbcTemplate.query(REMOVE_COMPLETED, new TemplateTaskMapper());
    }
}
