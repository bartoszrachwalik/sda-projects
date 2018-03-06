package com.github.database.user;

import com.github.database.mapper.TemplateUserMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserJdbcTemplateDao {

    private static final String BY_LOGIN = "select * from users where login = ?";
    private static final String FIND_ALL = "select * from users";
    private static final String SAVE = "insert into users(login,password) values(?,?)";
    private static final String DELETE = "DELETE FROM USERS WHERE id = ?";
    private JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getByLogin(String login) {
        return jdbcTemplate.queryForObject(BY_LOGIN, new TemplateUserMapper(), login);
    }

    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, new TemplateUserMapper());
    }

    public void save(String login, String password) {
        jdbcTemplate.update(SAVE, login, password);
    }

    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE, id);
    }
}

