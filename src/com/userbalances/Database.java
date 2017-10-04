package com.userbalances;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;

import java.io.IOException;
import java.sql.SQLException;

public class Database {
    private static JdbcPooledConnectionSource connectionSource;

    public static JdbcPooledConnectionSource getConnectionSource() {
        if (connectionSource == null) {
            try {
                connectionSource = new JdbcPooledConnectionSource(
                        "jdbc:mysql://localhost:3306/user_balances", "root", "");
            } catch(SQLException e) {
                throw new RuntimeException("exception", e);
            }
        }
        return connectionSource;
    }

    public static void close() throws IOException {
        try {
            connectionSource.close();
        } catch(IOException e) {
            throw new RuntimeException("exception", e);
        }
    }
}
