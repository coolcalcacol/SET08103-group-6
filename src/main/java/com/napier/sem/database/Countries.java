package com.napier.sem.database;

import com.napier.sem.report.CountryReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Countries {
    private static final String baseSelect = "SELECT country.Name, country.Population, country.Code, country.Continent, country.Region, city.name AS Capital";
    private static final String baseFrom = " FROM country INNER JOIN city ON country.Capital=city.ID WHERE country.Capital IS NOT NULL ";

    public static List<CountryReport> parseResults(DB db, String query) {
        List<CountryReport> countries = new ArrayList<>();

        try (ResultSet results = db.runQuery(query)) {
            if (results == null) return countries;
            while (results.next()) {
                CountryReport report = new CountryReport(
                        results.getString("Name"),
                        results.getString("Population"),
                        results.getString("Code"),
                        results.getString("Continent"),
                        results.getString("Region"),
                        results.getString("Capital")
                );
                countries.add(report);
            }
        } catch (SQLException e) {
            System.out.println("Countries#parseResults failed: " + e.getMessage());
        }
        return countries;
    }
    public static List<CountryReport> getAllCountriesByPopulation(DB db, int limit) {
        String query = baseSelect + baseFrom + "ORDER BY country.Population DESC";
        if(limit > 0) query += " LIMIT " + limit;
        return parseResults(db, query);
    }

    public static HashMap<String, List<CountryReport>> getCountriesInContinentByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() over (PARTITION BY Continent order by Population DESC) AS continent_rank" +
                baseFrom + "ORDER BY country.Continent, country.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE continent_rank <= " + limit;

        List<CountryReport> results = parseResults(db, query);
        HashMap<String, List<CountryReport>> continentMap = new HashMap<>();
        for (CountryReport report : results) {
            if (!continentMap.containsKey(report.continent)) {
                continentMap.put(report.continent, new ArrayList<>());
            }
            continentMap.get(report.continent).add(report);
        }
        return continentMap;
    }

    public static HashMap<String, List<CountryReport>> getCountriesInRegionByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() over (PARTITION BY Region ORDER BY Population DESC) AS region_rank" +
                baseFrom + "ORDER BY country.Region, country.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE region_rank <= " + limit;
        List<CountryReport> results = parseResults(db, query);
        HashMap<String, List<CountryReport>> regionMap = new HashMap<>();
        for (CountryReport report : results) {
            if (!regionMap.containsKey(report.region)) {
                regionMap.put(report.region, new ArrayList<>());
            }
            regionMap.get(report.region).add(report);
        }
        return regionMap;
    }
}
