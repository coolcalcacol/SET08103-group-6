package com.napier.sem.reports;

import com.napier.sem.Helpers;
import com.napier.sem.database.DB;
import com.napier.sem.report.PopulationReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Population {
    private static final String baseSQL = "SELECT country.%1$s AS Name, SUM(DISTINCT country.Population) AS Population, SUM(city.Population) AS PopulationInCities " +
            "FROM city INNER JOIN country ON country.Code=city.CountryCode " +
            "GROUP BY country.%1$s " +
            "ORDER BY Population DESC";


    /**
     * Parses an SQL query result set and returns a list of {@link PopulationReport} objects.
     * @param db the database connection
     * @param query the SQL query to execute
     * @return a list of population reports
     */
    public static List<PopulationReport> parseResults(DB db, String query) {
        List<PopulationReport> populationReports = new ArrayList<>();

        try (ResultSet results = db.runQuery(query)) {
            while (results.next()) {
                double percentInCity = Math.round((results.getLong("PopulationInCities") * 1D) / results.getLong("Population") * 100);
                double percentNotInCity = Math.round((results.getLong("Population") - results.getLong("PopulationInCities")) * 1D / results.getLong("Population") * 100);
                PopulationReport report = new PopulationReport(
                        results.getString("Name"),
                        results.getString("Population"),
                        percentInCity + "%",
                        percentNotInCity + "%"
                );
                populationReports.add(report);
            }
        } catch (SQLException e) {
            return null;
        }

        return populationReports;
    }

    /**
     * Retrieves the population of each continent and returns it as a map of continent name to population report.
     * @param db the database to query
     * @return a map of continent name to population report
     */
    public static HashMap<String, PopulationReport> getPopulationByContinent(DB db) {
        String query = String.format(baseSQL, "Continent");
        return Helpers.getReportOfTypeT(parseResults(db, query));
    }

    /**
     * Retrieves the population of each region and returns it as a map of region name to population report.
     * @param db the database to query
     * @return a map of region name to population report
     */
    public static HashMap<String, PopulationReport> getPopulationByRegion(DB db) {
        String query = String.format(baseSQL, "Region");
        return Helpers.getReportOfTypeT(parseResults(db, query));
    }

    /**
     * Retrieves the population of each country and returns it as a map of country name to population report.
     * @param db the database to query
     * @return a map of country name to population report
     */
    public static HashMap<String, PopulationReport> getPopulationByCountry(DB db) {
        String query = String.format(baseSQL, "Name");
        return Helpers.getReportOfTypeT(parseResults(db, query));
    }


}
