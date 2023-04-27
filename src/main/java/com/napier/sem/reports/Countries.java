package com.napier.sem.reports;

import com.napier.sem.Helpers;
import com.napier.sem.database.DB;
import com.napier.sem.report.CountryReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Utility class for retrieving country-related reports from the database.
 */
public class Countries {
    private static final String baseSelect = "SELECT country.Name, country.Population, country.Code, country.Continent, country.Region, city.name AS Capital";
    private static final String baseFrom = " FROM country INNER JOIN city ON country.Capital=city.ID WHERE country.Capital IS NOT NULL ";

    /**
     * Parses the results of a query into a list of {@link CountryReport} objects.
     * @param db The database to run the query on.
     * @param query The query to run.
     * @return A list of {@link CountryReport} objects.
     */
    public static List<CountryReport> parseResults(DB db, String query) {
        List<CountryReport> countries = new ArrayList<>();

        try (ResultSet results = db.runQuery(query)) {
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
            return null;
        }

        return countries;
    }

    /**
     * Gets all countries in the database, sorted by population.
     * @param db The database to run the query on.
     * @param limit The maximum number of results to return. If 0, all results will be returned.
     * @return A list of {@link CountryReport} objects.
     */
    public static List<CountryReport> getAllCountriesByPopulation(DB db, int limit) {
        String query = baseSelect + baseFrom + "ORDER BY country.Population DESC";
        if(limit > 0) query += " LIMIT " + limit;
        return parseResults(db, query);
    }

    /**
     * Gets all countries in the database, sorted by population and grouped by continent.
     * @param db The database to run the query on.
     * @param limit The maximum number of results to return per continent. If 0, all results will be returned.
     * @return A HashMap of continent names to lists of {@link CountryReport} objects.
     */
    public static HashMap<String, List<CountryReport>> getCountriesInContinentByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() over (PARTITION BY Continent order by Population DESC) AS continent_rank" +
                baseFrom + "ORDER BY country.Continent, country.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE continent_rank <= " + limit;

        return Helpers.getReportsSplitByAccessorOfTypeT(parseResults(db, query), "continent");
    }

    /**
     * Gets all countries in the database, sorted by population and grouped by region.
     * @param db The database to run the query on.
     * @param limit The maximum number of results to return per region. If 0, all results will be returned.
     * @return A HashMap of region names to lists of {@link CountryReport} objects.
     */
    public static HashMap<String, List<CountryReport>> getCountriesInRegionByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() over (PARTITION BY Region ORDER BY Population DESC) AS region_rank" +
                baseFrom + "ORDER BY country.Region, country.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE region_rank <= " + limit;

        return Helpers.getReportsSplitByAccessorOfTypeT(parseResults(db, query), "region");
    }
}
