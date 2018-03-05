package com.github.database.old;

import com.github.database.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class UserMapper {

    private static final int ID = 1;
    private static final int LOGIN = 2;
    private static final int PASSWORD = 3;

    User getSingle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next())
            throw new NoSuchElementException();
        else
            return new User(resultSet.getInt(ID), resultSet.getString(LOGIN), resultSet.getString(PASSWORD));
    }

    List<User> getList(ResultSet resultSet) throws SQLException {
        List<User> usersList = new ArrayList<>();
        while (resultSet.next()) {
            usersList.add(new User(resultSet.getInt(ID), resultSet.getString(LOGIN), resultSet.getString(PASSWORD)));
        }
        return usersList;
    }
}