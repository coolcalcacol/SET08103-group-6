package com.napier.sem.report;

public class PopulationReport extends BaseReport{
    public String country;
    public String continent;
    public String region;
    public PopulationReport(String region, String continent, String country,String name, String population) {
        super(name, population);
        this.country = country;
        this.continent = continent;
        this.region = region;
    }
}
