package com.napier.sem.report;

import com.napier.sem.report.base.BaseReport;

public class PopulationReport extends BaseReport {
    public String populationInCities;
    public String populationOutCities;
    public PopulationReport(String name, String population, String populationInCities, String populationOutCities) {
        super(name, population);
        this.populationInCities = populationInCities;
        this.populationOutCities = populationOutCities;
    }

    @Override
    public String getHeader() {
        return String.format("| %-35s | %-15s | %-15s | %-15s |", "Name", "Population", "Population in Cities", "Population out Cities") + "\n" +
                "|-------------------------------------|-----------------|-----------------|-----------------|\n";
    }

    @Override
    public String getRow() {
        return String.format("| %-35s | %-15s | %-15s | %-15s |", this.name, this.population, this.populationInCities, this.populationOutCities);
    }
}
