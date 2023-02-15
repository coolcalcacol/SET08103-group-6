package com.napier.sem.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Countries {

    // Ideally this would be using a Country class, but we haven't got that far yet
    private static final List<List<String>> cache = new ArrayList<>();

    public static List<List<String>> getAllCountries(DB db) throws SQLException {
        if (!cache.isEmpty()) return cache;
        System.out.println("Cache empty, loading from database");

        List<List<String>> countries = new ArrayList<>();

        String query = "SELECT * FROM country";

        try (ResultSet results = db.runQuery(query)) {

            if (results == null) return countries;

            while (results.next()) {
                List<String> country = new ArrayList<>();

                country.add(results.getString("Code"));
                country.add(results.getString("Name"));
                country.add(results.getString("Continent"));
                country.add(results.getString("Region"));
                country.add(results.getString("SurfaceArea"));
                country.add(results.getString("IndepYear"));
                country.add(results.getString("Population"));
                country.add(results.getString("LifeExpectancy"));
                country.add(results.getString("GNP"));
                country.add(results.getString("GNPOld"));
                country.add(results.getString("LocalName"));
                country.add(results.getString("GovernmentForm"));
                country.add(results.getString("HeadOfState"));
                country.add(results.getString("Capital"));
                country.add(results.getString("Code2"));

                countries.add(country);
            }
        }
        cache.clear();
        cache.addAll(countries);

        return countries;
    }

}
