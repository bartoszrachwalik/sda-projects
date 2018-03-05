package com.github.database.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

import static java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;

public class DBConfig {

    private static final int MAX_TOTAL = 20;
    private static final String INIT_SQL = "select 1;";
    private static final String UPDATE_SQL = "insert into users(login,password) VALUES ('newlogin','newpassword')";
    private static final String SELECT_SQL = "SELECT * FROM users";

    public static void connection(String url, String login, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        try (Connection con = DriverManager.getConnection(url, login, password)) {
            con.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);
            con.setAutoCommit(false);
            con.createStatement().executeUpdate(UPDATE_SQL);
            selectallusersandprint(con);
            con.rollback();
        }
    }

    private static void selectallusersandprint(Connection con) throws SQLException {
        ResultSet resultSet = con.createStatement().executeQuery(SELECT_SQL);
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }
    }
}
