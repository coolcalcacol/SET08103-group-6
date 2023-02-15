package com.napier.sem;

import com.napier.sem.database.Cities;
import com.napier.sem.database.DB;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Initiate database Class
        DB Database = new DB("localhost", "33060", "world", "root", "group6Pass");
        try {
            // Connect to database
            Database.connect();

            // Get population report
            List<List<String>> e = Cities.getAllCities(Database);
            for (List<String> row : e) {
                System.out.println(row);
            } 
        } catch (Exception e) {
            // On error, print error message
            System.out.println(e.getMessage());
        } finally {
            // Disconnect from database
            Database.disconnect();
        }

    }
}
