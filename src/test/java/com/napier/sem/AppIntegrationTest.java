package com.napier.sem;

import com.napier.sem.additions.LanguagesSpoken;
import com.napier.sem.additions.PopulationCounts;
import com.napier.sem.database.*;

import com.napier.sem.reports.*;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static DB db;
    @BeforeAll
    static void init() {
        db = new DB("localhost", "33060", "world", "root", "group6Pass");

        assertTrue(db.connect());
    }

    @AfterAll
    static void cleanUp() {
       assertTrue(db.disconnect());
    }

    // DB
    @Test
    void getDbConnectionReturnConnection() {
        assertNotNull(db.getConnection());
    }

    @Test
    void testRunQuery() throws SQLException {
        String query = "SELECT * FROM city LIMIT 1";
        assertNotNull(db.runQuery(query));
    }

    @Test
    void testRunQueryWithException() {
        String query = "SELECT city.ID2 FROM city LIMIT 1";
        assertThrows(SQLException.class, () -> db.runQuery(query));
    }

    @Test
    void fakeDbConnection() {
        DB db2 = new DB("localhost", "33061", "world", "root", "group6Pass");
        assertFalse(db2.connect());
    }

    @Test
    void testDisconnectWithoutConnection() {
        DB db2 = new DB("localhost", "33060", "world", "root", "group6Pass");
        assertFalse(db2.disconnect());
    }

    // Additions
    @Test
    void testAdditionsClasses() {
        new LanguagesSpoken();
        new PopulationCounts();
    }

    // LanguagesSpoken
    @Test
    void testLanguagesSpokenByCountry() {
        assertFalse(LanguagesSpoken.getLanguagesSpokenByCountry(db).isEmpty());
    }

    // PopulationCounts
    @Test
    void testPopulationOfWorld() {
        assertNotNull(PopulationCounts.getPopulationOfWorld(db));
    }

    @Test
    void testPopulationOfContinent() {
        assertNotNull(PopulationCounts.getPopulationOfContinent(db, "Europe"));
    }

    @Test
    void testPopulationOfRegion() {
        assertNotNull(PopulationCounts.getPopulationOfRegion(db, "British Islands"));
    }

    @Test
    void testPopulationOfCountry() {
        assertNotNull(PopulationCounts.getPopulationOfCountry(db, "United Kingdom"));
    }

    @Test
    void testPopulationOfDistrict() {
        assertNotNull(PopulationCounts.getPopulationOfDistrict(db, "Scotland"));
    }

    @Test
    void testPopulationOfCity() {
        assertNotNull(PopulationCounts.getPopulationOfCity(db, "Edinburgh"));
    }

    // Test Each parseResults() with invalid queries

    @Test
    void testParseLanguagesSpokenResults() {
        assertNull(LanguagesSpoken.parseResults(db, "SELECT ID2 FROM city LIMIT 1"));
    }

    @Test
    void testParsePopulationCountsResults() {
        assertNull(PopulationCounts.parseResults(db, "SELECT ID2 FROM city LIMIT 1"));
    }

    // Reports
    @Test
    void testReportsClasses() {
        new CapitalCities();
        new Cities();
        new Countries();
        new Population();
    }

    // Capital Cities
    @Test
    void testCapitalCitiesByPopulation() {
        assertFalse(CapitalCities.getCapitalCitiesByPopulation(db, 1).isEmpty());
    }

    @Test
    void testCapitalCitiesByPopulationWithNoLimit() {
        assertFalse(CapitalCities.getCapitalCitiesByPopulation(db, 0).isEmpty());
    }

    @Test
    void testCapitalCitiesInContinentByPopulation() {
        assertFalse(CapitalCities.getCapitalCitiesInContinentByPopulation(db, 1).isEmpty());
    }

    @Test
    void testCapitalCitiesInContinentByPopulationWithNoLimit() {
        assertFalse(CapitalCities.getCapitalCitiesInContinentByPopulation(db, 0).isEmpty());
    }

    @Test
    void testCapitalCitiesInRegionByPopulation() {
        assertFalse(CapitalCities.getCapitalCitiesInContinentByPopulation(db, 1).isEmpty());
    }

    @Test
    void testCapitalCitiesInRegionByPopulationWithNoLimit() {
        assertFalse(CapitalCities.getCapitalCitiesInRegionByPopulation(db, 0).isEmpty());
    }

    // Cities
    @Test
    void testCitiesByPopulation() {
        assertFalse(Cities.getAllCitiesByPopulation(db, 1).isEmpty());
    }

    @Test
    void testCitiesByPopulationWithNoLimit() {
        assertFalse(Cities.getAllCitiesByPopulation(db, 0).isEmpty());
    }

    @Test
    void testCitiesInContinentByPopulation() {
        assertFalse(Cities.getCitiesInContinentByPopulation(db, 1).isEmpty());
    }

    @Test
    void testCitiesInContinentByPopulationWithNoLimit() {
        assertFalse(Cities.getCitiesInContinentByPopulation(db, 0).isEmpty());
    }

    @Test
    void testCitiesInRegionByPopulation() {
        assertFalse(Cities.getCitiesInRegionByPopulation(db, 1).isEmpty());
    }

    @Test
    void testCitiesInRegionByPopulationWithNoLimit() {
        assertFalse(Cities.getCitiesInRegionByPopulation(db, 0).isEmpty());
    }

    @Test
    void testCitiesInCountryByPopulation() {
        assertFalse(Cities.getCitiesInCountryByPopulation(db, 1).isEmpty());
    }

    @Test
    void testCitiesInCountryByPopulationWithNoLimit() {
        assertFalse(Cities.getCitiesInCountryByPopulation(db, 0).isEmpty());
    }

    @Test
    void testCitiesInDistrictByPopulation() {
        assertFalse(Cities.getCitiesInCountryByPopulation(db, 1).isEmpty());
    }

    @Test
    void testCitiesInDistrictByPopulationWithNoLimit() {
        assertFalse(Cities.getCitiesInCountryByPopulation(db, 0).isEmpty());
    }


    // Countries
    @Test
    void testCountriesByPopulation() {
        assertFalse(Countries.getAllCountriesByPopulation(db, 1).isEmpty());
    }

    @Test
    void testCountriesByPopulationWithNoLimit() {
        assertFalse(Countries.getAllCountriesByPopulation(db, 0).isEmpty());
    }

    @Test
    void testContinentCountriesByPopulation() {
        assertFalse(Countries.getCountriesInContinentByPopulation(db, 1).isEmpty());
    }

    @Test
    void testContinentCountriesByPopulationWithNoLimit() {
        assertFalse(Countries.getCountriesInContinentByPopulation(db, 0).isEmpty());
    }

    @Test
    void testRegionCountriesByPopulation() {
        assertFalse(Countries.getCountriesInRegionByPopulation(db, 1).isEmpty());
    }

    @Test
    void testRegionCountriesByPopulationWithNoLimit() {
        assertFalse(Countries.getCountriesInRegionByPopulation(db, 0).isEmpty());
    }

    // Population
    @Test
    void testCitiesPopulationByContinent() {
        assertFalse(Population.getPopulationByContinent(db).isEmpty());
    }

    @Test
    void testCitiesPopulationByRegion() {
        assertFalse(Population.getPopulationByRegion(db).isEmpty());
    }

    @Test
    void testCitiesPopulationByCountry() {
        assertFalse(Population.getPopulationByCountry(db).isEmpty());
    }


    // Test Each parseResults() with invalid queries
    @Test
    void testParseCapitalCityResults() {
        assertNull(CapitalCities.parseResults(db, "SELECT ID2 FROM city LIMIT 1"));
    }

    @Test
    void testParseExtendedCityResults() {
        assertNull(Cities.parseResults(db, "SELECT ID2 FROM city LIMIT 1"));
    }

    @Test
    void testParseCountryResults() {
        assertNull(Countries.parseResults(db, "SELECT ID2 FROM country LIMIT 1"));
    }

    @Test
    void testParsePopulationResults() {
        assertNull(Population.parseResults(db, "SELECT ID2 FROM city LIMIT 1"));
    }

    // App
    @Test
    void testApp() {
        App.runReports(db, 1, 0);
    }
}
