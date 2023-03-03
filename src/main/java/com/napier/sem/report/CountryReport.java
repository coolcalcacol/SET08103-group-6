package com.napier.sem.report;

public class CountryReport extends BaseReport {
    public String code;
    public String continent;
    public String region;
    public String capital;

    public CountryReport(String name, String population, String code, String continent, String region, String capital) {
        super(name, population);
        this.code = code;
        this.continent = continent;
        this.region = region;
        this.capital = capital;
    }
}
