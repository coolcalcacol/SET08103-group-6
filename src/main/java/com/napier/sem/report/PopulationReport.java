package com.napier.sem.report;

public class PopulationReport extends BaseReport{
    public String populationInCities;
    public String populationOutCities;
    public PopulationReport(String name, String population, String populationInCities, String populationOutCities) {
        super(name, population);
        this.populationInCities = populationInCities;
        this.populationOutCities = populationOutCities;
    }
}
