package com.napier.sem;

import com.napier.sem.report.BaseReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Helpers {
    /**
     * Retrieves a list of {@link T} objects for all X in the database.
     * @param results result of the database query
     * @return a map of area to T reports
     */
    public static <T extends BaseReport> HashMap<String, T> getReportOfTypeT(List<T> results) {
        HashMap<String, T> areaMap = new HashMap<>();
        for (T report : results) {
            areaMap.put(report.name, report);
        }
        return areaMap;
    }

    /**
     * Retrieves a list of {@link T} objects for all X in the database.
     * @param results result of the database query
     * @param accessor the accessor to use to get the area name
     * @return a map of area to T reports
     * @param <T> the type of extended report
     * @param <R> the type of report to cast to
     */
    public static <T extends BaseReport, R extends T> HashMap<String, List<T>> getReportsSplitByAccessorOfTypeT(List<R> results, String accessor) {
        HashMap<String, List<T>> areaMap = new HashMap<>();
        for (R report : results) {
            if (!areaMap.containsKey(accessor))
                areaMap.put(accessor, new ArrayList<>());
            areaMap.get(accessor).add(report);
        }
        return areaMap;
    }
}
