package com.napier.sem.report.extended;

import com.napier.sem.report.CityReport;

public class ExtendedCityReport extends CityReport {
    public String continent;
    public String region;

    public ExtendedCityReport(String name, String population, String district, String country, String continent, String region) {
        super(name, population, district, country);
        this.continent = continent;
        this.region = region;
    }
}
