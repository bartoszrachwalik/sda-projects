package com.github.database.user;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;

class UserDao {

    private static final String BY_LOGIN = "select * from users where login = '%s'";
    private static final String FIND_ALL = "select * from users";
    private static final String SAVE = "insert into users(login,password) values('%s','%s')";
    private static final String DELETE = "deleteById from users where id = %s";
    private DataSource dataSource;
    private UserMapper userMapper = new UserMapper();

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User getByLogin(String login) throws SQLException {
        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery(format(BY_LOGIN, login));
        return userMapper.getSingle(resultSet);
    }

    public List<User> findAll() throws SQLException {
        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery(FIND_ALL);
        return userMapper.getList(resultSet);
    }

    public void save(String login, String password) throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate(format(SAVE, login, password));
    }

    public void deleteById(Long id) throws SQLException {
        dataSource.getConnection().createStatement().executeUpdate(format(DELETE, id));
    }
}
