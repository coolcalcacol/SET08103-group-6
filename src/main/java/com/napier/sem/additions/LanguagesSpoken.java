package com.napier.sem.additions;

import com.napier.sem.database.DB;
import com.napier.sem.report.LanguageReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LanguagesSpoken {
    /**
     * Parses an SQL query result set and returns populations.
     * @param db the database connection
     * @param query the SQL query to execute
     * @return List of population to language for the query
     */
    public static List<LanguageReport> parseResults(DB db, String query) {
        List<LanguageReport> population = new ArrayList<>();

        try (ResultSet results = db.runQuery(query)) {
            while (results.next()) {
                LanguageReport languageReport = new LanguageReport(results.getString("Language"), results.getString("Population"));
                population.add(languageReport);
            }
        } catch (SQLException e) {
            return null;
        }
        return population;
    }

    /**
     * Determines the total number of people who speak each language.
     * @param db the database connection
     * @return List of population to language for the query
     */
    public static List<LanguageReport> getLanguagesSpokenByCountry(DB db) {
        String query = "SELECT countrylanguage.Language AS Language, SUM(countrylanguage.Percentage / 100 * country.Population) AS Population FROM countrylanguage JOIN country ON countrylanguage.CountryCode = country.Code GROUP BY countrylanguage.Language ORDER BY Population DESC";
        return parseResults(db, query);
    }
}
