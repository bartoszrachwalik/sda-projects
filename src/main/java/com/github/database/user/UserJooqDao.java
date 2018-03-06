package com.github.database.user;

import com.github.database.utils.Filter;
import com.github.database.utils.Page;
import com.todo.db.tables.Users;
import com.todo.db.tables.records.UsersRecord;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SelectWithTiesAfterOffsetStep;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import static com.todo.db.tables.Users.USERS;

public class UserJooqDao {

    private static Map<String, Field> nameToField;

    static {
        nameToField = new HashMap<>();
        nameToField.put("id", USERS.ID);
        nameToField.put("login", USERS.LOGIN);
        nameToField.put("createdAt", USERS.CREATED_AT);
    }

    private final DSLContext dsl;

    public UserJooqDao(DSLContext dsl) {
        this.dsl = dsl;
    }

    public User getByLogin(String login) {
        return dsl.select().from(USERS).where(USERS.LOGIN.eq(login)).fetchAnyInto(User.class);
    }

    public List<User> findAll(Page page) {
        return dsl.select().from(USERS).limit(page.getSize()).offset(page.getOffset()).fetchInto(User.class);

    }

    //findAll with filter
    public List<User> findAll(Page page, List<Filter> filter) {
        List<Condition> where = new ArrayList<>();
        filter.stream().map(f -> nameToField.get(f.getField()).eq(f.getValue()));
        SelectWithTiesAfterOffsetStep<UsersRecord> limit = dsl.selectFrom(USERS)
                .where(where)
                .offset(page.getOffset())
                .limit(page.getSize());
        return limit.fetchInto(User.class);
    }

    public void save(User user) {
        dsl.newRecord(USERS, user).store();
        //alternative
//        dsl.insertInto(USERS).columns(USERS.LOGIN, USERS.PASSWORD).values(user.getLogin(), user.getPassword()).execute();
    }

    public void deleteById(int id) {
        dsl.deleteFrom(USERS).where(USERS.ID.eq(id)).execute();
    }
}
