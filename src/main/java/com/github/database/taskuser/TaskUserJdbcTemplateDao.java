package com.github.database.taskuser;

import com.github.database.mapper.TemplateTaskUserMapper;
import com.github.database.mapper.TemplateUserMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class TaskUserJdbcTemplateDao {

    private static final String BY_TASK_ID = "select * from task_user where task_id = ?";
    private static final String BY_USER_ID = "select * from task_user where user_id = ?";
    private static final String SAVE = "INSERT INTO task_user (task_id, user_id) VALUES ((SELECT id FROM tasks WHERE id = ?),(SELECT id FROM users WHERE id = ?))";
    private JdbcTemplate jdbcTemplate;

    public TaskUserJdbcTemplateDao(org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(int task_id, int user_id) {
        jdbcTemplate.update(SAVE, task_id, user_id);
    }

    public TaskUser getByTaskId(int task_id) {
        return jdbcTemplate.queryForObject(BY_TASK_ID, new TemplateTaskUserMapper(), task_id);

    }

    public TaskUser getByUserId(int user_id) {
        return jdbcTemplate.queryForObject(BY_USER_ID, new TemplateTaskUserMapper(), user_id);
    }
}
