package com.napier.sem.report.base;

import com.napier.sem.report.interfaces.IReport;

public abstract class BaseReport implements IReport {
    public String name;
    public String population;

    public BaseReport(String name, String population) {
        this.name = name;
        this.population = population;
    }
}


