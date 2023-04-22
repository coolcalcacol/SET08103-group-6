package com.napier.sem.reports;

import com.napier.sem.Helpers;
import com.napier.sem.database.DB;
import com.napier.sem.report.CapitalCityReport;
import com.napier.sem.report.extended.ExtendedCapitalCityReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CapitalCities {
    private static final String baseSelect = "SELECT city.Name, city.Population, city.District, country.Name AS Country, country.Continent, country.Region";
    private static final String baseFrom = " FROM city INNER JOIN country ON country.Code=city.CountryCode ";
    private static final String baseWhere = "WHERE city.ID=country.Capital ";

    /**
     * Parses an SQL query result set and returns a list of {@link ExtendedCapitalCityReport} objects.
     * @param db the database connection
     * @param query the SQL query to execute
     * @return a list of extended capital city reports
     */
    public static List<ExtendedCapitalCityReport> parseResults(DB db, String query) {
        List<ExtendedCapitalCityReport> capitalCities = new ArrayList<>();

        try (ResultSet results = db.runQuery(query)) {
            while (results.next()) {
                ExtendedCapitalCityReport report = new ExtendedCapitalCityReport(
                        results.getString("Name"),
                        results.getString("Population"),
                        results.getString("Country"),
                        results.getString("Continent"),
                        results.getString("Region")
                );
                capitalCities.add(report);
            }
        } catch (SQLException e) {
            return null;
        }

        return capitalCities;
    }

    /**
     * Returns a list of capital cities, sorted by population.
     * @param db The database object to use.
     * @param limit The maximum number of results to return. If 0 or negative, returns all results.
     * @return A list of capital cities, sorted by population.
     */
    public static List<CapitalCityReport> getCapitalCitiesByPopulation(DB db, int limit) {
        String query = baseSelect + baseFrom + baseWhere + "ORDER BY city.Population DESC";
        if(limit > 0) query += " LIMIT " + limit;

        List<ExtendedCapitalCityReport> results = parseResults(db, query);
        return new ArrayList<>(results);
    }

    /**
     * Returns a map of capital cities in each continent, sorted by population.
     * @param db The database object to use.
     * @param limit The maximum number of results to return. If 0 or negative, returns all results.
     * @return A map of capital cities in each continent, sorted by population.
     */
    public static HashMap<String, List<CapitalCityReport>> getCapitalCitiesInContinentByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() OVER (PARTITION BY country.Continent ORDER BY city.Population DESC) AS continent_rank" +
                baseFrom + baseWhere +
                "ORDER BY country.Continent, city.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE continent_rank <= " + limit;

        return Helpers.getReportsSplitByAccessorOfTypeT(parseResults(db, query), "continent");
    }

    /**
     * Returns a map of capital cities in each region, sorted by population.
     * @param db The database object to use.
     * @param limit The maximum number of results to return. If 0 or negative, returns all results.
     * @return A map of capital cities in each region, sorted by population.
     */
    public static HashMap<String, List<CapitalCityReport>> getCapitalCitiesInRegionByPopulation(DB db, int limit) {
        String query = baseSelect + ", ROW_NUMBER() OVER (PARTITION BY country.Region ORDER BY city.Population DESC) AS region_rank " +
                baseFrom + baseWhere +
                "ORDER BY country.Region, city.Population DESC";
        if(limit > 0) query = "SELECT * FROM (" + query + ") AS T WHERE region_rank <= " + limit;

        return Helpers.getReportsSplitByAccessorOfTypeT(parseResults(db, query), "region");
    }
}
