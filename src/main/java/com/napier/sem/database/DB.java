package com.napier.sem.database;

import java.sql.*;


public class DB {
    private static Connection connection = null;

    // This will need to be changed to the correct database in future
    private static final String url = "localhost:3306";

    public static void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + url + "/world?useUnicode=true&characterEncoding=UTF-8", "root", "");
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database");
            System.out.println(e.getMessage());
        }
    }

    public static void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static ResultSet runQuery(String query) {
        try {
            var statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
