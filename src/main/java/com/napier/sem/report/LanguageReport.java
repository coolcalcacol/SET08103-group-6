package com.napier.sem.report;

import com.napier.sem.report.base.BaseReport;

public class LanguageReport extends BaseReport {
    public LanguageReport(String name, String population) {
        super(name, population);
    }

    @Override
    public String getHeader() {
        return String.format("| %-35s | %-15s |", "Language", "Population") + "\n" +
                "|-------------------------------------|-----------------|\n";
    }

    @Override
    public String getRow() {
        return String.format("| %-35s | %-15s |", this.name, this.population);
    }
}
