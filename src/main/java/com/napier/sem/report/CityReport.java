package com.napier.sem.report;

public class CityReport extends BaseReport {
    public String district;
    public String country;
    public CityReport(String country, String district, String name, String population) {
        super(name, population);
        this.country = country;
        this.district = district;
    }
}
