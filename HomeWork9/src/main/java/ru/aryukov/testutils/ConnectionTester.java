package ru.aryukov.testutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by oaryukov on 08.06.2017.
 */
public class ConnectionTester {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/otus";
    static final String USER = "postgres";
    static final String PASS = "500NSczx";
    public static Connection getConnection(){

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Not found driver");
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection is filed");
            e.printStackTrace();
        }
        return connection;
    }

    public static void testConnection(){
        Connection connection = getConnection();
        try {
            System.out.println("Connected to: " + connection.getMetaData().getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

