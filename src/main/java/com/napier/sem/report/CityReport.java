package com.napier.sem.report;

public class CityReport extends BaseReport {
    public String district;
    public String country;
    public CityReport(String name, String population, String district, String country) {
        super(name, population);
        this.district = district;
        this.country = country;
    }
}
