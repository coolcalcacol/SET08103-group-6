package com.napier.sem.report;

public class CapitalCityReport extends BaseReport {
    public String country;

    public CapitalCityReport(String country, String name, String population) {
        super(name, population);
        this.country = country;
    }
}
