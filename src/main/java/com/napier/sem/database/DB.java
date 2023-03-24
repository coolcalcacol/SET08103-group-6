package com.napier.sem.database;

import java.sql.*;


public class DB {
    private Connection connection = null;

    private final String url;
    private final String username;
    private final String password;
    public DB(String IP, String port, String databaseName, String username, String password){
        this.url = "jdbc:mysql://" + IP + ":" + port + "/" + databaseName + "?useUnicode=true&characterEncoding=UTF-8";
        this.username = username;
        this.password = password;
    }

    // This will need to be changed to the correct database in future

    public void connect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database");
        }
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Database failed to disconnect");
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet runQuery(String query) {
        try {
            var statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("DB#runQuery failed: " + e.getMessage());
        }
        return null;
    }
}
