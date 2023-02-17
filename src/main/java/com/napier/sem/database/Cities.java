package com.napier.sem.database;

import com.napier.sem.report.CityReport;
import com.napier.sem.report.ExtendedCityReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cities {

    private static List<ExtendedCityReport> parseResults(DB db, String query) throws SQLException {
        List<ExtendedCityReport> countries = new ArrayList<>();

        try (ResultSet results = db.runQuery(query)) {
            if (results == null) return countries;
            while (results.next()) {
                ExtendedCityReport report = new ExtendedCityReport(
                        results.getString("Name"),
                        results.getString("Population"),
                        results.getString("District"),
                        results.getString("Country"),
                        results.getString("Continent"),
                        results.getString("Region")
                );
                countries.add(report);
            }
        }
        return countries;
    }

    public static List<CityReport> getAllCitiesByPopulation(DB db, int limit) throws SQLException {
        String query = "SELECT city.Name, city.Population, city.District, country.Name AS Country, country.Continent, country.Region " +
                "FROM city " +
                "INNER JOIN country ON country.Code=city.CountryCode " +
                "ORDER BY Population DESC";
        if(limit > 0) query += " LIMIT " + limit;
        return new ArrayList<>(parseResults(db, query));
    }

    public static HashMap<String, List<CityReport>> getCitiesInContinentByPopulation(DB db, int limit) throws SQLException {
        String query = "SELECT city.Name, city.Population, city.District, country.Name AS Country, country.Continent, country.Region, row_number() over (partition by Continent order by Population desc) as continent_rank " +
                "FROM city " +
                "INNER JOIN country ON country.Code=city.CountryCode " +
                "ORDER BY Continent, Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE continent_rank <= " + limit;

        List<ExtendedCityReport> results = parseResults(db, query);
        HashMap<String, List<CityReport>> continentMap = new HashMap<>();
        for (ExtendedCityReport report : results) {
            if (!continentMap.containsKey(report.continent)) {
                continentMap.put(report.continent, new ArrayList<>());
            }
            continentMap.get(report.continent).add(report);
        }
        return continentMap;
    }

    public static HashMap<String, List<CityReport>> getCitiesInRegionByPopulation(DB db, int limit) throws SQLException {
        String query = "SELECT city.Name, city.Population, city.District, country.Name AS Country, country.Continent, country.Region, row_number() over (partition by Region order by Population desc) as region_rank " +
                "FROM city " +
                "INNER JOIN country ON country.Code=city.CountryCode " +
                "ORDER BY Region, Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE region_rank <= " + limit;

        List<ExtendedCityReport> results = parseResults(db, query);
        HashMap<String, List<CityReport>> regionMap = new HashMap<>();
        for (ExtendedCityReport report : results) {
            if (!regionMap.containsKey(report.region)) {
                regionMap.put(report.region, new ArrayList<>());
            }
            regionMap.get(report.region).add(report);
        }
        return regionMap;
    }

    public static HashMap<String, List<CityReport>> getCitiesInCountryByPopulation(DB db, int limit) throws SQLException {
        String query = "SELECT city.Name, city.Population, city.District, country.Name AS Country, country.Continent, country.Region, row_number() over (partition by Region order by Population desc) as country_rank " +
                "FROM city " +
                "INNER JOIN country ON country.Code=city.CountryCode " +
                "ORDER BY Region, Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE country_rank <= " + limit;

        List<ExtendedCityReport> results = parseResults(db, query);
        HashMap<String, List<CityReport>> countryMap = new HashMap<>();
        for (CityReport report : results) {
            if (!countryMap.containsKey(report.country)) {
                countryMap.put(report.country, new ArrayList<>());
            }
            countryMap.get(report.country).add(report);
        }
        return countryMap;
    }

    public static HashMap<String, List<CityReport>> getCitiesInDistrictByPopulation(DB db, int limit) throws SQLException {
        String query = "SELECT city.Name, city.Population, city.District, country.Name AS Country, country.Continent, country.Region, row_number() over (partition by District order by Population desc) as district_rank " +
                "FROM city " +
                "INNER JOIN country ON country.Code=city.CountryCode " +
                "ORDER BY District, Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE district_rank <= " + limit;

        List<ExtendedCityReport> results = parseResults(db, query);
        HashMap<String, List<CityReport>> districtMap = new HashMap<>();
        for (CityReport report : results) {
            if (!districtMap.containsKey(report.district)) {
                districtMap.put(report.district, new ArrayList<>());
            }
            districtMap.get(report.district).add(report);
        }
        return districtMap;
    }
}
