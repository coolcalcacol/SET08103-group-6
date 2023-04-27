package com.napier.sem.report;

import com.napier.sem.report.base.BaseReport;

public class CityReport extends BaseReport {
    public String district;
    public String country;
    public CityReport(String name, String population, String district, String country) {
        super(name, population);
        this.district = district;
        this.country = country;
    }

    @Override
    public String getHeader() {
        return String.format("| %-35s | %-15s | %-15s | %-15s |", "Name", "Population", "Country", "District") + "\n" +
                "|-------------------------------------|-----------------|-----------------|-----------------|\n";
    }

    @Override
    public String getRow() {
        return String.format("| %-35s | %-15s | %-15s | %-15s |", this.name, this.population, this.country,this.district);
    }
}
