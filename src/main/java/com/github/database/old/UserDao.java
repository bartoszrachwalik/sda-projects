package com.github.database.old;

import com.github.database.user.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;

class UserDao {

    private static final String BY_LOGIN = "select * from users where login = '%s'";
    private static final String FIND_ALL = "select * from users";
    private static final String SAVE = "insert into users(login,password) values('%s','%s')";
    private Connection con;
    private UserMapper userMapper;

    public UserDao(Connection connection, UserMapper userMapper) {
        this.con = connection;
        this.userMapper = userMapper;
    }

    User getByLogin(String login) throws SQLException {
        ResultSet resultSet = con.createStatement().executeQuery(format(BY_LOGIN, login));
        return userMapper.getSingle(resultSet);
    }

    List<User> findAll() throws SQLException {
        ResultSet resultSet = con.createStatement().executeQuery(FIND_ALL);
        return userMapper.getList(resultSet);
    }

    void save(String login, String password) throws SQLException {
        con.createStatement().executeUpdate(format(SAVE, login, password));
    }

}
