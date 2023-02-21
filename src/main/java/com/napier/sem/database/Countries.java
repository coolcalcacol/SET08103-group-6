package com.napier.sem.database;

import com.napier.sem.report.CountryReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Countries {
    private static List<CountryReport> parseResults(DB db, String query) throws SQLException {
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
        }
        return countries;
    }
    public static List<CountryReport> getAllCountriesByPopulation(DB db, int limit) throws SQLException {
        String query = "SELECT * FROM country ORDER BY Population DESC";
        if(limit > 0) query += " LIMIT " + limit;
        return parseResults(db, query);
    }

    public static HashMap<String, List<CountryReport>> getCountriesInContinentByPopulation(DB db, int limit) throws SQLException {
        String query = "SELECT Name, Population, Code, Continent, Region, Capital, row_number() over (partition by Continent order by Population desc) as continent_rank " +
                "FROM country " +
                "ORDER BY Continent, Population DESC";
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

    public static HashMap<String, List<CountryReport>> getCountriesInRegionByPopulation(DB db, int limit) throws SQLException {
        String query = "SELECT Name, Population, Code, Continent, Region, Capital, row_number() over (partition by Region order by Population desc) as region_rank " +
                "FROM country " +
                "ORDER BY Region, Population DESC";
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
