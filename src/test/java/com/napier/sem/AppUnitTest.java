package com.napier.sem;

import com.napier.sem.report.*;
import com.napier.sem.report.extended.ExtendedCapitalCityReport;
import com.napier.sem.report.extended.ExtendedCityReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppUnitTest {
    // Abstract base report
    @Test
    void baseReportTest() {
        BaseReport report = new BaseReport("name", "population") {};
        assertEquals("name", report.name);
        assertEquals("population", report.population);
    }

    // Basic report types

    @Test
    void capitalCityReportTest() {
        CapitalCityReport report = new CapitalCityReport("name", "population", "country");
        assertEquals("name", report.name);
        assertEquals("population", report.population);
        assertEquals("country", report.country);
    }

    @Test
    void cityReportTest() {
        CityReport report = new CityReport("name", "population", "district", "country");
        assertEquals("name", report.name);
        assertEquals("population", report.population);
        assertEquals("district", report.district);
        assertEquals("country", report.country);
    }

    @Test
    void countryReportTest() {
        CountryReport report = new CountryReport("name", "population", "code", "continent", "region", "capital");
        assertEquals("name", report.name);
        assertEquals("population", report.population);
        assertEquals("code", report.code);
        assertEquals("continent", report.continent);
        assertEquals("region", report.region);
        assertEquals("capital", report.capital);
    }

    @Test
    void languageReportTest() {
        LanguageReport report = new LanguageReport("name", "population");
        assertEquals("name", report.name);
        assertEquals("population", report.population);
    }

    @Test
    void populationReportTest() {
        PopulationReport report = new PopulationReport("name", "population", "populationInCities", "populationOutCities");
        assertEquals("name", report.name);
        assertEquals("population", report.population);
        assertEquals("populationInCities", report.populationInCities);
        assertEquals("populationOutCities", report.populationOutCities);
    }

    // Extended report types
    @Test
    void extendedCapitalCityReportTest() {
        ExtendedCapitalCityReport report = new ExtendedCapitalCityReport("name", "population", "country", "continent", "region");
        assertEquals("name", report.name);
        assertEquals("population", report.population);
        assertEquals("country", report.country);
        assertEquals("continent", report.continent);
        assertEquals("region", report.region);
    }

    @Test
    void extendedCityReportTest() {
        ExtendedCityReport report = new ExtendedCityReport("name", "population", "district", "country", "continent", "region");
        assertEquals("name", report.name);
        assertEquals("population", report.population);
        assertEquals("district", report.district);
        assertEquals("country", report.country);
        assertEquals("continent", report.continent);
        assertEquals("region", report.region);
    }
}
