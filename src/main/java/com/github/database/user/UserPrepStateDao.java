package com.github.database.user;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserPrepStateDao {

    private static final String BY_LOGIN = "select * from users where login = ?";
    private static final String FIND_ALL = "select * from users";
    private static final String SAVE = "insert into users(login,password) values(?,?)";
    private static final String DELETE = "delete from users where id = ?";
    private DataSource dataSource;
    private UserMapper userMapper = new UserMapper();

    public UserPrepStateDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User getByLogin(String login) throws SQLException {
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(BY_LOGIN);
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        return userMapper.getSingle(resultSet);
    }

    List<User> findAll() throws SQLException {
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(FIND_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        return userMapper.getList(resultSet);
    }

    void save(String login, String password) throws SQLException {
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SAVE);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();
    }

    public void deleteById(Long id) throws SQLException {
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(DELETE);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }
}
