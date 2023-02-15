package com.napier.sem.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cities {

    // Ideally this would be using a City class, but we haven't got that far yet
    private static final List<List<String>> cache = new ArrayList<>();

    public static List<List<String>> getAllCities(DB db) throws SQLException {
        if (!cache.isEmpty()) return cache;
        System.out.println("Cache empty, loading from database");

        List<List<String>> cities = new ArrayList<>();

        String query = "SELECT * FROM city";

        try (ResultSet results = db.runQuery(query)) {

            if (results == null) return cities;

            while (results.next()) {
                List<String> city = new ArrayList<>();

                city.add(results.getString("ID"));
                city.add(results.getString("Name"));
                city.add(results.getString("CountryCode"));
                city.add(results.getString("District"));
                city.add(results.getString("Population"));

                cities.add(city);
            }
        }
        cache.clear();
        cache.addAll(cities);

        return cities;
    }

}
