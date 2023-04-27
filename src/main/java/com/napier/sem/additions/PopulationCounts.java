package com.napier.sem.additions;

import com.napier.sem.database.DB;

import java.sql.ResultSet;

public class PopulationCounts {
    /**
     * Parses an SQL query result set and returns population.
     * @param db the database connection
     * @param query the SQL query to execute
     * @return number of population for the query
     */
    public static Long parseResults(DB db, String query) {
        long population = 0L;
        try (ResultSet results = db.runQuery(query)) {
            while(results.next()) {
                population += results.getLong("Population");
            }
        } catch (Exception e) {
            return null;
        }
        return population;
    }

    /**
     * Determines the population of the world.
     * @param db the database connection
     * @return the population of the world
     */
    public static Long getPopulationOfWorld(DB db) {
        String query = "SELECT SUM(Population) AS Population FROM country";
        return parseResults(db, query);
    }

    /**
     * Determines the population of a continent.
     * @param db the database connection
     * @param continent the continent to determine the population of
     * @return the population of the continent
     */
    public static Long getPopulationOfContinent(DB db, String continent) {
        String query = "SELECT SUM(Population) AS Population FROM country WHERE Continent = '" + continent + "'";
        return parseResults(db, query);
    }

    /**
     * Determines the population of a region.
     * @param db the database connection
     * @param region the region to determine the population of
     * @return the population of the region
     */
    public static Long getPopulationOfRegion(DB db, String region) {
        String query = "SELECT SUM(Population) AS Population FROM country WHERE Region = '" + region + "'";
        return parseResults(db, query);
    }

    /**
     * Determines the population of a country.
     * @param db the database connection
     * @param country the country to determine the population of
     * @return the population of the country
     */
    public static Long getPopulationOfCountry(DB db, String country) {
        String query = "SELECT SUM(Population) AS Population FROM country WHERE Name = '" + country + "'";
        return parseResults(db, query);
    }

    /**
     * Determines the population of a district.
     * @param db the database connection
     * @param district the district to determine the population of
     * @return the population of the district
     */
    public static Long getPopulationOfDistrict(DB db, String district) {
        String query = "SELECT SUM(Population) AS Population FROM city WHERE District = '" + district + "'";
        return parseResults(db, query);
    }

    /**
     * Determines the population of a city.
     * @param db the database connection
     * @param city the city to determine the population of
     * @return the population of the city
     */
    public static Long getPopulationOfCity(DB db, String city) {
        String query = "SELECT SUM(Population) AS Population FROM city WHERE Name = '" + city + "'";
        return parseResults(db, query);
    }

}
