package com.napier.sem.report.extended;

import com.napier.sem.report.CapitalCityReport;

public class ExtendedCapitalCityReport extends CapitalCityReport {
    public String continent;
    public String region;

    public ExtendedCapitalCityReport(String name, String population, String country, String continent, String region) {
        super(name, population, country);
        this.continent = continent;
        this.region = region;
    }
}
