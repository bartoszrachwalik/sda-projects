package com.github.database.tasks;

import com.github.database.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class TaskMapper {

    private Mapper mapper = new Mapper();

    Task getSingle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next())
            throw new NoSuchElementException();
        else
            return mapper.mapTask(resultSet);
    }

    List<Task> getList(ResultSet resultSet) throws SQLException {
        List<Task> taskList = new ArrayList<>();
        while (resultSet.next()) {
            taskList.add(mapper.mapTask(resultSet));
        }
        return taskList;
    }
}
