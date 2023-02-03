package com.napier.sem.report;

public class CountryReport extends BaseReport {
    public String code;
    public String continent;
    public String Region;
    public String capital;

    public CountryReport(String capital, String Region, String continent, String code, String name, String population) {
        super(name, population);
        this.code = code;
        this.continent = continent;
        this.Region = Region;
        this.capital = capital;
    }
}
