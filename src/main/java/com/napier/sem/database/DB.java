package com.napier.sem.database;

import java.sql.*;

/**
 * Utility class for connecting to the database.
 */
public class DB {
    private Connection connection = null;

    private final String url;
    private final String username;
    private final String password;

    /**
     * Creates a new database connection.
     * @param IP The IP address of the database server.
     * @param port The port of the database server.
     * @param databaseName The name of the database to connect to.
     * @param username The username to connect with.
     * @param password The password to connect with.
     */
    public DB(String IP, String port, String databaseName, String username, String password){
        this.url = "jdbc:mysql://" + IP + ":" + port + "/" + databaseName + "?useUnicode=true&characterEncoding=UTF-8";
        this.username = username;
        this.password = password;
    }

    /**
     * Connects to the database.
     */
    public boolean connect() {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Disconnects from the database.
     */
    public boolean disconnect() {
        try {
            if(this.connection != null)
                this.connection.close();
            else throw new SQLException("Connection is null");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Gets the connection to the database.
     * @return The connection to the database.
     */
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Runs a query on the database.
     * @param query The query to run.
     * @return The results of the query.
     */
    public ResultSet runQuery(String query) throws SQLException {
        Statement statement = this.connection.createStatement();
        return statement.executeQuery(query);
    }
}
