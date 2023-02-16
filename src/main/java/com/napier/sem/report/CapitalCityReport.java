package com.napier.sem.report;

public class CapitalCityReport extends BaseReport {
    public String country;

    public CapitalCityReport(String name, String population, String country) {
        super(name, population);
        this.country = country;
    }
}
