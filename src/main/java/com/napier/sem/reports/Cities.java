package com.napier.sem.reports;

import com.napier.sem.Helpers;
import com.napier.sem.database.DB;
import com.napier.sem.report.CityReport;
import com.napier.sem.report.extended.ExtendedCityReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Utility class for retrieving city-related reports from the database.
 */
public class Cities {
    private static final String baseSelect = "SELECT city.Name, city.Population, city.District, country.Name AS Country, country.Continent, country.Region";
    private static final String baseFrom = " FROM city INNER JOIN country ON country.Code=city.CountryCode ";

    /**
     * Parses an SQL query result set and returns a list of {@link ExtendedCityReport} objects.
     * @param db the database connection
     * @param query the SQL query to execute
     * @return a list of extended city reports
     */
    public static List<ExtendedCityReport> parseResults(DB db, String query) {
        List<ExtendedCityReport> cities = new ArrayList<>();

        try (ResultSet results = db.runQuery(query)) {
            while (results.next()) {
                ExtendedCityReport report = new ExtendedCityReport(
                        results.getString("Name"),
                        results.getString("Population"),
                        results.getString("District"),
                        results.getString("Country"),
                        results.getString("Continent"),
                        results.getString("Region")
                );
                cities.add(report);
            }
        } catch (SQLException e) {
            return null;
        }

        return cities;
    }



    /**
     * Retrieves a list of {@link CityReport} objects for all cities in the database, sorted by population.
     * @param db the database connection
     * @param limit the maximum number of results to return, or 0 for no limit
     * @return a list of city reports
     */
    public static List<CityReport> getAllCitiesByPopulation(DB db, int limit) {
        String query = baseSelect + baseFrom + "ORDER BY city.Population DESC";
        if(limit > 0) query += " LIMIT " + limit;
        List<ExtendedCityReport> results = parseResults(db, query);
        return new ArrayList<>(results);
    }

    /**
     * Retrieves a map of {@link CityReport} objects, grouped by continent and sorted by population.
     * @param db the database connection
     * @param limit the maximum number of results to return per continent, or 0 for no limit
     * @return a map of city reports grouped by continent
     */
    public static HashMap<String, List<CityReport>> getCitiesInContinentByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() OVER (PARTITION BY country.Continent ORDER BY city.Population DESC) AS continent_rank" +
                baseFrom + "ORDER BY country.Continent, city.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE continent_rank <= " + limit;

        return Helpers.getReportsSplitByAccessorOfTypeT(parseResults(db, query), "continent");
    }

    /**
     * Retrieves a map of {@link CityReport} objects, grouped by region and sorted by population.
     * @param db the database connection
     * @param limit the maximum number of results to return per region, or 0 for no limit
     * @return a map of city reports grouped by region
     */
    public static HashMap<String, List<CityReport>> getCitiesInRegionByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() OVER (PARTITION BY country.Region ORDER BY city.Population DESC) AS region_rank" +
                baseFrom + "ORDER BY country.Region, city.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE region_rank <= " + limit;

        return Helpers.getReportsSplitByAccessorOfTypeT(parseResults(db, query), "region");
    }

    /**
     * Retrieves a map of {@link CityReport} objects, grouped by country and sorted by population.
     * @param db the database connection
     * @param limit the maximum number of results to return per country, or 0 for no limit
     * @return a map of city reports grouped by country
     */
    public static HashMap<String, List<CityReport>> getCitiesInCountryByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() OVER (PARTITION BY country.Name ORDER BY city.Population DESC) AS country_rank" +
                baseFrom + "ORDER BY Country, city.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE country_rank <= " + limit;

        return Helpers.getReportsSplitByAccessorOfTypeT(parseResults(db, query), "country");
    }

    /**
     * Returns a map of cities in each district, sorted by population.
     * @param db The database object to use.
     * @param limit The maximum number of results to return. If 0 or negative, returns all results.
     * @return A map of cities in each district, sorted by population.
     */
    public static HashMap<String, List<CityReport>> getCitiesInDistrictByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() OVER (PARTITION BY city.District ORDER BY city.Population DESC) AS district_rank" +
                baseFrom + "ORDER BY city.District, city.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE district_rank <= " + limit;

        return Helpers.getReportsSplitByAccessorOfTypeT(parseResults(db, query), "district");
    }
}
