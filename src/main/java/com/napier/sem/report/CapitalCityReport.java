package com.napier.sem.report;

import com.napier.sem.report.base.BaseReport;

public class CapitalCityReport extends BaseReport {
    public String country;

    public CapitalCityReport(String name, String population, String country) {
        super(name, population);
        this.country = country;
    }

    @Override
    public String getHeader() {
        return String.format("| %-35s | %-15s | %-45s |", "Name", "Population", "Country") + "\n" +
                "|-------------------------------------|-----------------|-----------------------------------------------|\n";
    }

    @Override
    public String getRow() {
        return String.format("| %-35s | %-15d | %-45s |", this.name, Integer.valueOf(this.population), this.country);
    }
}
