package com.napier.sem;

import com.napier.sem.report.base.BaseReport;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
            try {
                Field field = report.getClass().getField(accessor);
                String reportToAdd = (String) field.get(report);
                if (!areaMap.containsKey(reportToAdd))
                    areaMap.put(reportToAdd, new ArrayList<>());
                areaMap.get(reportToAdd).add(report);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                System.out.println("Could not access field " + accessor + " in report " + report.getClass().getName());
                return null;
            }
        }
        return areaMap;
    }

    /**
     * Prints a message with a delay between each character.
     * @param ms The delay between each character in milliseconds.
     */
    public static void printWithDelays(int ms) {
        for (char ch : "...\n".toCharArray()) {
            System.out.print(ch);
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public static void clearReports() {
        File fileDirectory = new File("./reports");
        if (!fileDirectory.exists())
            return;

        for (File file : Objects.requireNonNull(fileDirectory.listFiles())) {
            if (file.isDirectory()) {
                for (File report : Objects.requireNonNull(file.listFiles())) {
                    report.delete();
                }
            }
            file.delete();
        }
    }

    /**
     * Generates a report from a list of {@link T} objects.
     * @param results The list of {@link T} objects to generate a report from.
     * @param title The title of the report.
     * @return The generated report.
     */
    public static <T extends BaseReport> String generateReport(List<T> results, String title) {
        StringBuilder report = new StringBuilder();
        report.append(title);
        report.append("\n==============\n");
        report.append(results.get(0).getHeader());

        for (T result : results) {
            report.append(result.getRow());
            report.append("\n");
        }

        return report.toString();
    }

    public static String generateBasicReport(String title, String header, String row) {
        return title +
                "\n==============\n" +
                header +
                row +
                "\n";
    }

    /**
     * Writes a report to a file.
     * @param reportType The type of report to write.
     * @param fileName The name of the file to write to.
     * @param report The report to write.
     */
    public static void writeReport(String reportType, String fileName, String report) {
        final String regex = "\\\\|\\/";
        String sanitizedReportType = reportType.replaceAll(regex, "_");
        String sanitizedFileName = fileName.replaceAll(regex, "_");
        File fileDirectory = new File("./reports");
        if (!fileDirectory.exists())
            fileDirectory.mkdir();

        File reportDirectory = new File("./reports/" + sanitizedReportType);
        if (!reportDirectory.exists())
            reportDirectory.mkdir();

        try {
            File reportFile = new File("./reports/" + sanitizedReportType + "/" + sanitizedFileName + ".md");
            BufferedWriter reportWriter = new BufferedWriter(new FileWriter(reportFile));
            reportWriter.write(report);
            reportWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to write report "+ reportType + ": " + e.getMessage());
        }
    }
}
