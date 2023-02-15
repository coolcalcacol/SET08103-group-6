package com.napier.sem.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryLanguage {

    // Ideally this would be using a HashMap<String, Country> but we haven't got that far yet
    private static final List<List<String>> cache = new ArrayList<>();

    public static List<List<String>> getAllCountryLangs(DB db) throws SQLException {
        if (!cache.isEmpty()) return cache;
        System.out.println("Cache empty, loading from database");

        List<List<String>> countryLangs = new ArrayList<>();

        String query = "SELECT * FROM countrylanguage";

        try (ResultSet results = db.runQuery(query)) {

            if (results == null) return countryLangs;

            while (results.next()) {
                List<String> countryLang = new ArrayList<>();

                countryLang.add(results.getString("CountryCode"));
                countryLang.add(results.getString("Language"));
                countryLang.add(results.getString("IsOfficial"));
                countryLang.add(results.getString("Percentage"));

                countryLangs.add(countryLang);
            }
        }
        cache.clear();
        cache.addAll(countryLangs);

        return countryLangs;
    }

}
