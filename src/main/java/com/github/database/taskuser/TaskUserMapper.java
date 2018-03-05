package com.github.database.taskuser;

import com.github.database.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class TaskUserMapper {

    private Mapper mapper = new Mapper();

    TaskUser getSingle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next())
            throw new NoSuchElementException();
        else
            return mapper.mapTaskUser(resultSet);
    }

    List<TaskUser> getList(ResultSet resultSet) throws SQLException {
        List<TaskUser> taskList = new ArrayList<>();
        while (resultSet.next()) {
            taskList.add(mapper.mapTaskUser(resultSet));
        }
        return taskList;
    }
}
