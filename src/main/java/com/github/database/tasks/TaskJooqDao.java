package com.github.database.tasks;

import org.jooq.DSLContext;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static com.todo.db.tables.Tasks.TASKS;
import static java.time.LocalDateTime.now;

public class TaskJooqDao {

    private final DSLContext dsl;

    public TaskJooqDao(DSLContext dslContext) {
        this.dsl = dslContext;
    }

    public Task getByName(String name) {
        return dsl.select().from(TASKS).where(TASKS.NAME.eq(name)).fetchAnyInto(Task.class);
    }

    public void save(String name) {
        dsl.newRecord(TASKS, name).store();
    }

    public void markAsDone(int userId, int taskId) {
        dsl.update(TASKS).set(TASKS.COMPLETED_BY, userId).set(TASKS.COMPLETED_AT, LocalDateTime.now()).where(TASKS.ID.eq(5)).execute();
    }

    public void removeCompleted() throws SQLException {
        dsl.deleteFrom(TASKS).where(TASKS.COMPLETED_BY.isNotNull()).execute();
    }
}
