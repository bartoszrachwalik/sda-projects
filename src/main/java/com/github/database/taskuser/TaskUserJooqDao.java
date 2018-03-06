package com.github.database.taskuser;

import org.jooq.DSLContext;

import javax.sql.DataSource;

import static com.todo.db.tables.TaskUser.TASK_USER;

public class TaskUserJooqDao {

    private final DSLContext dsl;
    private static final String BY_TASK_ID = "select * from task_user where task_id = '%s'";
    private static final String BY_USER_ID = "select * from task_user where user_id = '%s'";
    private static final String SAVE = "INSERT INTO task_user (task_id, user_id) VALUES ((SELECT id FROM tasks WHERE id = '%s'),(SELECT id FROM users WHERE id = '%s'))";
    private TaskUserMapper taskUserMapper = new TaskUserMapper();

    public TaskUserJooqDao(DSLContext dsl) {
        this.dsl = dsl;
    }

    public void save(TaskUser taskUser) {
        dsl.newRecord(TASK_USER, taskUser).store();
    }

    public TaskUser getByTaskId(int task_id) {
        return dsl.select().from(TASK_USER).where(TASK_USER.TASK_ID.eq(task_id)).fetchAnyInto(TaskUser.class);

    }

    public TaskUser getByUserId(int user_id) {
        return dsl.select().from(TASK_USER).where(TASK_USER.USER_ID.eq(user_id)).fetchAnyInto(TaskUser.class);

    }
}
