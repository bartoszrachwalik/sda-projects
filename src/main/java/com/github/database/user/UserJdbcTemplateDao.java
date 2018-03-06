package com.github.database.user;

import com.github.database.mapper.TemplateUserMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class UserJdbcTemplateDao {

    private static final String BY_LOGIN = "select * from users where login = ?";
    private static final String FIND_ALL = "select * from users";
    private static final String SAVE = "insert into users(login,password) values(?,?)";
    private JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getByLogin(String login) throws SQLException {
        return jdbcTemplate.queryForObject(BY_LOGIN, new TemplateUserMapper(), login);
    }

    public List<User> findAll() throws SQLException {
        List<User> userList = jdbcTemplate.query(FIND_ALL, new TemplateUserMapper());
        return userList;
    }

    public void save(String login, String password) throws SQLException {
        jdbcTemplate.update(SAVE, login, password);
    }
}

