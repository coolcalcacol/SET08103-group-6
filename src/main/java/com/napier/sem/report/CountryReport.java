package com.napier.sem.report;

import com.napier.sem.report.base.BaseReport;

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

    @Override
    public String getHeader() {
        return String.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |", "Country Code", "Name", "Population", "Continent", "Region", "Capital") + "\n" +
                "|-----------------|-----------------|-----------------|-----------------|-----------------|-----------------|\n";
    }

    @Override
    public String getRow() {
        return String.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |", this.code, this.name, this.population, this.continent, this.region, this.capital);
    }
}
