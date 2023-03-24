package com.napier.sem;

import com.napier.sem.database.Cities;
import com.napier.sem.database.Countries;
import com.napier.sem.database.CountryLanguage;
import com.napier.sem.database.DB;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static DB db;
    @BeforeAll
    static void init() {
        db = new DB("localhost", "33060", "world", "root", "group6Pass");

        db.connect();
    }

    @AfterAll
    static void cleanUp() {
        db.disconnect();
    }

    @Test
    void basicTest() {
        assertEquals(1, 1);
    }

    @Test
    void getDbConnectionReturnConnection() {
        assertNotNull(db.getConnection());
    }

    @Test
    void testRunQuery() {
        String query = "SELECT * FROM city LIMIT 1";
        assertNotNull(db.runQuery(query));
    }

    @Test
    void testRunQueryWithNull() {
        String query = "SELECT city.ID2 FROM city LIMIT 1";
        assertNull(db.runQuery(query));
    }

    @Test
    void fakeDbConnection() {
        DB db2 = new DB("localhost", "33061", "world", "root", "group6Pass");
        db2.connect();
    }

    @Test
    void testDisconnectWithoutConnection() {
        DB db2 = new DB("localhost", "33060", "world", "root", "group6Pass");
        db2.disconnect();
    }

    @Test
    void countriesClassTest() {
        new Countries();
    }
    @Test
    void testCountriesByPopulation() {
        Countries.getAllCountriesByPopulation(db, 1);
    }

    @Test
    void testCountriesByPopulationWithNoLimit() {
        Countries.getAllCountriesByPopulation(db, 0);
    }

    @Test
    void testContinentCountriesByPopulation() {
        Countries.getCountriesInContinentByPopulation(db, 1);
    }

    @Test
    void testContinentCountriesByPopulationWithNoLimit() {
        Countries.getCountriesInContinentByPopulation(db, 0);
    }

    @Test
    void testRegionCountriesByPopulation() {
        Countries.getCountriesInRegionByPopulation(db, 1);
    }

    @Test
    void testRegionCountriesByPopulationWithNoLimit() {
        Countries.getCountriesInRegionByPopulation(db, 0);
    }

    @Test
    void testInvalidQuery() {
        assertTrue(Countries.parseResults(db, "SELECT * FROM city2").isEmpty());
    }

    @Test
    void testCitiesClass() {
        new Cities();
    }

    @Test
    void testCitiesByPopulation() {
        Cities.getAllCitiesByPopulation(db, 1);
    }

    @Test
    void testCitiesByPopulationWithNoLimit() {
        Cities.getAllCitiesByPopulation(db, 0);
    }

    @Test
    void testCitiesInContinentByPopulation() {
        Cities.getCitiesInContinentByPopulation(db, 1);
    }

    @Test
    void testCitiesInContinentByPopulationWithNoLimit() {
        Cities.getCitiesInContinentByPopulation(db, 0);
    }

    @Test
    void testCitiesInRegionByPopulation() {
        Cities.getCitiesInRegionByPopulation(db, 1);
    }

    @Test
    void testCitiesInRegionByPopulationWithNoLimit() {
        Cities.getCitiesInRegionByPopulation(db, 0);
    }

    @Test
    void testCitiesInCountryByPopulation() {
        Cities.getCitiesInCountryByPopulation(db, 1);
    }

    @Test
    void testCitiesInCountryByPopulationWithNoLimit() {
        Cities.getCitiesInCountryByPopulation(db, 0);
    }

    @Test
    void testCitiesInDistrictByPopulation() {
        Cities.getCitiesInDistrictByPopulation(db, 1);
    }

    @Test
    void testCitiesInDistrictByPopulationWithNoLimit() {
        Cities.getCitiesInDistrictByPopulation(db, 0);
    }

    @Test
    void testCapitalCitiesByPopulation() {
        Cities.getCapitalCitiesByPopulation(db, 1);
    }

    @Test
    void testCapitalCitiesByPopulationWithNoLimit() {
        Cities.getCapitalCitiesByPopulation(db, 0);
    }

    @Test
    void testCapitalCitiesInContinentByPopulation() {
        Cities.getCapitalCitiesInContinentByPopulation(db, 1);
    }

    @Test
    void testCapitalCitiesInContinentByPopulationWithNoLimit() {
        Cities.getCapitalCitiesInContinentByPopulation(db, 0);
    }

    @Test
    void testCapitalCitiesInRegionByPopulation() {
        Cities.getCapitalCitiesInRegionByPopulation(db, 1);
    }

    @Test
    void testCapitalCitiesInRegionByPopulationWithNoLimit() {
        Cities.getCapitalCitiesInRegionByPopulation(db, 0);
    }

    @Test
    void testCitiesPopulationByContinent() {
        Cities.getPopulationByContinent(db);
    }

    @Test
    void testCitiesPopulationByRegion() {
        Cities.getPopulationByRegion(db);
    }

    @Test
    void testCitiesPopulationByCountry() {
        Cities.getPopulationByCountry(db);
    }

    @Test
    void testParseExtendedCityResults() {
        assertTrue(Cities.parseExtendedCityResults(db, "SELECT ID2 FROM city LIMIT 1").isEmpty());
    }

    @Test
    void testParseCapitalCityResults() {
        assertTrue(Cities.parseCapitalCityResults(db, "SELECT ID2 FROM city LIMIT 1").isEmpty());
    }

    @Test
    void testPopulationResults() {
        assertTrue(Cities.parsePopulationResults(db, "SELECT ID2 FROM city LIMIT 1").isEmpty());
    }

    @Test
    void testCountryLanguageClass() {
        new CountryLanguage();
    }


    @Test
    void testMainApp() {
        // TODO: MAIN METHOD NOT BEING TESTED DUE TO IMMINENT REFACTOR OF DRIVER CODE. TESTS WILL BE WRITTEN AFTER REFACTOR.
        // App.main("0");
    }
}
