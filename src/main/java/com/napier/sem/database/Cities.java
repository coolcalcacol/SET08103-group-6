package com.napier.sem.database;

import com.napier.sem.report.CapitalCityReport;
import com.napier.sem.report.CityReport;
import com.napier.sem.report.PopulationReport;
import com.napier.sem.report.extended.ExtendedCapitalCityReport;
import com.napier.sem.report.extended.ExtendedCityReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cities {
    private static final String baseSelect = "SELECT city.Name, city.Population, city.District, country.Name AS Country, country.Continent, country.Region";
    private static final String baseFrom = " FROM city INNER JOIN country ON country.Code=city.CountryCode ";
    private static final String baseWhere = "WHERE city.ID=country.Capital ";

    public static List<ExtendedCityReport> parseExtendedCityResults(DB db, String query) {
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
        } catch (SQLException e) {
            System.out.println("Cities#parseExtendedCityResults failed: " + e.getMessage());
        }
        return countries;
    }

    public static List<ExtendedCapitalCityReport> parseCapitalCityResults(DB db, String query) {
        List<ExtendedCapitalCityReport> countries = new ArrayList<>();

        try (ResultSet results = db.runQuery(query)) {
            if (results == null) return countries;
            while (results.next()) {
                ExtendedCapitalCityReport report = new ExtendedCapitalCityReport(
                        results.getString("Name"),
                        results.getString("Population"),
                        results.getString("Country"),
                        results.getString("Continent"),
                        results.getString("Region")
                );
                countries.add(report);
            }
        } catch (SQLException e) {
            System.out.println("Cities#parseCapitalCityResults failed: " + e.getMessage());
        }
        return countries;
    }

    public static List<PopulationReport> parsePopulationResults(DB db, String query) {
        List<PopulationReport> countries = new ArrayList<>();

        try (ResultSet results = db.runQuery(query)) {
            if (results == null) return countries;
            while (results.next()) {
                double percentInCity = Math.round((results.getLong("PopulationInCities") * 1D) / results.getLong("Population") * 100);
                double percentNotInCity = Math.round((results.getLong("Population") - results.getLong("PopulationInCities")) * 1D / results.getLong("Population") * 100);
                PopulationReport report = new PopulationReport(
                        results.getString("Name"),
                        results.getString("Population"),
                        percentInCity + "%",
                        percentNotInCity + "%"
                );
                countries.add(report);
            }
        } catch (SQLException e) {
            System.out.println("Cities#parsePopulationResults failed: " + e.getMessage());
        }
        return countries;
    }

    public static List<CityReport> getAllCitiesByPopulation(DB db, int limit) {
        String query = baseSelect + baseFrom + "ORDER BY city.Population DESC";
        if(limit > 0) query += " LIMIT " + limit;
        return new ArrayList<>(parseExtendedCityResults(db, query));
    }

    public static HashMap<String, List<CityReport>> getCitiesInContinentByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() OVER (PARTITION BY Continent ORDER BY city.Population DESC) AS continent_rank" +
                baseFrom + "ORDER BY country.Continent, city.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE continent_rank <= " + limit;

        List<ExtendedCityReport> results = parseExtendedCityResults(db, query);
        HashMap<String, List<CityReport>> continentMap = new HashMap<>();
        for (ExtendedCityReport report : results) {
            if (!continentMap.containsKey(report.continent)) {
                continentMap.put(report.continent, new ArrayList<>());
            }
            continentMap.get(report.continent).add(report);
        }
        return continentMap;
    }

    public static HashMap<String, List<CityReport>> getCitiesInRegionByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() OVER (PARTITION BY Region ORDER BY city.Population DESC) AS region_rank" +
                baseFrom + "ORDER BY country.Region, city.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE region_rank <= " + limit;

        List<ExtendedCityReport> results = parseExtendedCityResults(db, query);
        HashMap<String, List<CityReport>> regionMap = new HashMap<>();
        for (ExtendedCityReport report : results) {
            if (!regionMap.containsKey(report.region)) {
                regionMap.put(report.region, new ArrayList<>());
            }
            regionMap.get(report.region).add(report);
        }
        return regionMap;
    }

    public static HashMap<String, List<CityReport>> getCitiesInCountryByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() OVER (PARTITION BY country.Name ORDER BY city.Population DESC) AS country_rank" +
                baseFrom + "ORDER BY Country, city.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE country_rank <= " + limit;

        List<ExtendedCityReport> results = parseExtendedCityResults(db, query);
        HashMap<String, List<CityReport>> countryMap = new HashMap<>();
        for (CityReport report : results) {
            if (!countryMap.containsKey(report.country)) {
                countryMap.put(report.country, new ArrayList<>());
            }
            countryMap.get(report.country).add(report);
        }
        return countryMap;
    }

    public static HashMap<String, List<CityReport>> getCitiesInDistrictByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() OVER (PARTITION BY District ORDER BY city.Population DESC) AS district_rank" +
                baseFrom + "ORDER BY city.District, city.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE district_rank <= " + limit;

        List<ExtendedCityReport> results = parseExtendedCityResults(db, query);
        HashMap<String, List<CityReport>> districtMap = new HashMap<>();
        for (CityReport report : results) {
            if (!districtMap.containsKey(report.district)) {
                districtMap.put(report.district, new ArrayList<>());
            }
            districtMap.get(report.district).add(report);
        }
        return districtMap;
    }

    public static List<CapitalCityReport> getCapitalCitiesByPopulation(DB db, int limit) {
        String query = baseSelect + baseFrom + baseWhere + "ORDER BY city.Population DESC"; 
        if(limit > 0) query += " LIMIT " + limit;
        
        return new ArrayList<>(parseCapitalCityResults(db, query));
    }

    public static HashMap<String, List<CapitalCityReport>> getCapitalCitiesInContinentByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() OVER (PARTITION BY Continent ORDER BY city.Population DESC) AS continent_rank" +
                baseFrom + baseWhere +
                "ORDER BY country.Continent, city.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE continent_rank <= " + limit;

        List<ExtendedCapitalCityReport> results = parseCapitalCityResults(db, query);
        HashMap<String, List<CapitalCityReport>> continentMap = new HashMap<>();
        for (ExtendedCapitalCityReport report : results) {
            if (!continentMap.containsKey(report.continent)) {
                continentMap.put(report.continent, new ArrayList<>());
            }
            continentMap.get(report.continent).add(report);
        }
        return continentMap;
    }

    public static HashMap<String, List<CapitalCityReport>> getCapitalCitiesInRegionByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() OVER (PARTITION BY country.Region ORDER BY city.Population DESC) AS region_rank " +
                baseFrom + baseWhere +
                "ORDER BY country.Region, city.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE region_rank <= " + limit;

        List<ExtendedCapitalCityReport> results = parseCapitalCityResults(db, query);
        HashMap<String, List<CapitalCityReport>> regionMap = new HashMap<>();
        for (ExtendedCapitalCityReport report : results) {
            if (!regionMap.containsKey(report.region)) {
                regionMap.put(report.region, new ArrayList<>());
            }
            regionMap.get(report.region).add(report);
        }
        return regionMap;
    }

    public static HashMap<String, PopulationReport> getPopulationByContinent(DB db) {
        String query = "SELECT country.Continent AS Name, SUM(DISTINCT country.Population) AS Population, SUM(city.Population) AS PopulationInCities " +
                baseFrom + "GROUP BY country.Continent " + "ORDER BY Population DESC";

        List<PopulationReport> results = parsePopulationResults(db, query);
        HashMap<String, PopulationReport> continentMap = new HashMap<>();
        for (PopulationReport report : results) {
            continentMap.put(report.name, report);
        }
        return continentMap;
    }

    public static HashMap<String, PopulationReport> getPopulationByRegion(DB db) {
        String query = "SELECT country.Region AS Name, SUM(DISTINCT country.Population) AS Population, SUM(city.Population) AS PopulationInCities " +
                baseFrom + "GROUP BY country.Region " + "ORDER BY Population DESC";

        List<PopulationReport> results = parsePopulationResults(db, query);
        HashMap<String, PopulationReport> regionMap = new HashMap<>();
        for (PopulationReport report : results) {
            regionMap.put(report.name, report);
        }
        return regionMap;
    }

    public static HashMap<String, PopulationReport> getPopulationByCountry(DB db) {
        String query = "SELECT country.Name AS Name, SUM(DISTINCT country.Population) AS Population, SUM(city.Population) AS PopulationInCities " +
                baseFrom + "GROUP BY country.Name " + "ORDER BY Population DESC";

        List<PopulationReport> results = parsePopulationResults(db, query);
        HashMap<String, PopulationReport> countryMap = new HashMap<>();
        for (PopulationReport report : results) {
            countryMap.put(report.name, report);
        }
        return countryMap;
    }
}
