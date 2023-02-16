package com.napier.sem.database;

import com.napier.sem.report.CityReport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cities {

    private static List<CityReport> parseResults(DB db, String query) throws SQLException {
        List<CityReport> countries = new ArrayList<>();

        try (ResultSet results = db.runQuery(query)) {
            if (results == null) return countries;
            while (results.next()) {
                CityReport report = new CityReport(
                        results.getString("Name"),
                        results.getString("Population"),
                        results.getString("District"),
                        results.getString("Country")
                );
                countries.add(report);
            }
        }
        return countries;
    }
}
