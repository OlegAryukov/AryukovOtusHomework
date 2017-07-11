package ru.aryukov;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by oaryukov on 10.07.2017.
 */
public class ConnectionHelper {

    public Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        Connection connection =
                DriverManager.getConnection("jdbc:postgresql://localhost:5432/otus", "postgres", "500NSczx");
        connection.setAutoCommit(false);

        return connection;
    }
}
