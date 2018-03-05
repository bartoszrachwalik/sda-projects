package com.github.database.user;

import com.github.database.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class UserMapper {

    private Mapper mapper = new Mapper();

    User getSingle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next())
            throw new NoSuchElementException();
        else
            return mapper.mapUser(resultSet);
    }

    List<User> getList(ResultSet resultSet) throws SQLException {
        List<User> usersList = new ArrayList<>();
        while (resultSet.next()) {
            usersList.add(mapper.mapUser(resultSet));
        }
        return usersList;
    }
}