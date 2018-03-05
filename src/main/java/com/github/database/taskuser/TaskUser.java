package com.github.database.taskuser;

public class TaskUser {

    private long task_id;
    private long user_id;

    public TaskUser(long task_id, long user_id) {
        this.task_id = task_id;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "TaskUser{" +
                "task_id=" + task_id +
                ", user_id=" + user_id +
                '}';
    }

    public long getTask_id() {
        return task_id;
    }

    public long getUser_id() {
        return user_id;
    }
}
