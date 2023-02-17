package com.napier.sem;

import com.napier.sem.database.Cities;
import com.napier.sem.database.Countries;
import com.napier.sem.database.DB;
import com.napier.sem.report.CityReport;
import com.napier.sem.report.CountryReport;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Initiate database Class
        DB db = new DB("localhost", "33060", "world", "root", "group6Pass");

        // Create a scanner to read user input where required
        Scanner userInput = new Scanner(System.in);
        try {
            // Connect to database
            db.connect();

            // All the countries in the world organised by largest population to smallest.
            System.out.print("1. Computing all countries in the world organised by largest population to smallest");
            printWithDelays("...\n", 200);
            List<CountryReport> countries = Countries.getAllCountriesByPopulation(db, -1);


            // All the countries in a continent organised by largest population to smallest.
            System.out.print("2. Computing all the countries in a continent organised by largest population to smallest");
            printWithDelays("...\n", 200);
            HashMap<String, List<CountryReport>> countriesInContinent = Countries.getCountriesInContinentByPopulation(db, -1);

            // All the countries in a region organised by largest population to smallest.
            System.out.print("3. Computing all the countries in a region organised by largest population to smallest");
            printWithDelays("...\n", 200);
            HashMap<String, List<CountryReport>> countriesInRegion = Countries.getCountriesInRegionByPopulation(db, -1);

            // The top N populated countries in the world where N is provided by the user.
            System.out.print("Please enter the number of countries you would like to see: ");
            int n = userInput.nextInt();
            System.out.print("4. Computing the top " + n + " populated countries in the world");
            printWithDelays("...\n", 200);
            List<CountryReport> topNCountries = Countries.getAllCountriesByPopulation(db, n);

            // The top N populated countries in a continent where N is provided by the user.
            System.out.print("5. Computing the top " + n + " populated countries in a continent");
            printWithDelays("...\n", 200);
            HashMap<String, List<CountryReport>> topNCountriesInContinent = Countries.getCountriesInContinentByPopulation(db, n);

            // The top N populated countries in a region where N is provided by the user.
            System.out.print("6. Computing the top " + n + " populated countries in a region");
            printWithDelays("...\n", 200);
            HashMap<String, List<CountryReport>> topNCountriesInRegion = Countries.getCountriesInRegionByPopulation(db, n);

            // All the cities in the world organised by largest population to smallest.
            System.out.print("7. Computing all the cities in the world organised by largest population to smallest");
            printWithDelays("...\n", 200);
            List<CityReport> cities = Cities.getAllCitiesByPopulation(db, -1);

            // All the cities in a continent organised by largest population to smallest.
            System.out.print("8. Computing all the cities in a continent organised by largest population to smallest");
            printWithDelays("...\n", 200);
            HashMap<String, List<CityReport>> citiesInContinent = Cities.getCitiesInContinentByPopulation(db, -1);

            // All the cities in a region organised by largest population to smallest.
            System.out.print("9. Computing all the cities in a region organised by largest population to smallest");
            printWithDelays("...\n", 200);
            HashMap<String, List<CityReport>> citiesInRegion = Cities.getCitiesInRegionByPopulation(db, -1);

            // All the cities in a country organised by largest population to smallest.
            System.out.print("10. Computing all the cities in a country organised by largest population to smallest");
            printWithDelays("...\n", 200);
            HashMap<String, List<CityReport>> citiesInCountry = Cities.getCitiesInCountryByPopulation(db, -1);

            // All the cities in a district organised by largest population to smallest.
            System.out.print("11. Computing all the cities in a district organised by largest population to smallest");
            printWithDelays("...\n", 200);
            HashMap<String, List<CityReport>> citiesInDistrict = Cities.getCitiesInDistrictByPopulation(db, -1);

            // The top N populated cities in the world where N is provided by the user.
            System.out.print("12. Computing the top " + n + " populated cities in the world");
            printWithDelays("...\n", 200);
            List<CityReport> topNCities = Cities.getAllCitiesByPopulation(db, n);

            // The top N populated cities in a continent where N is provided by the user.
            System.out.print("13. Computing the top " + n + " populated cities in a continent");
            printWithDelays("...\n", 200);
            HashMap<String, List<CityReport>> topNCitiesInContinent = Cities.getCitiesInContinentByPopulation(db, n);

            // The top N populated cities in a region where N is provided by the user.
            System.out.print("14. Computing the top " + n + " populated cities in a region");
            printWithDelays("...\n", 200);
            HashMap<String, List<CityReport>> topNCitiesInRegion = Cities.getCitiesInRegionByPopulation(db, n);

            // The top N populated cities in a country where N is provided by the user.
            System.out.print("15. Computing the top " + n + " populated cities in a country");
            printWithDelays("...\n", 200);
            HashMap<String, List<CityReport>> topNCitiesInCountry = Cities.getCitiesInCountryByPopulation(db, n);

            // The top N populated cities in a district where N is provided by the user.
            System.out.print("16. Computing the top " + n + " populated cities in a district");
            printWithDelays("...\n", 200);
            HashMap<String, List<CityReport>> topNCitiesInDistrict = Cities.getCitiesInDistrictByPopulation(db, n);


            //TODO: Determine what output method we are to use for all reports



            //// OUTPUT SECTION ////

            // All the countries in the world organised by largest population to smallest.
            System.out.println("\n\n\nAll the countries in the world organised by largest population to smallest.");
            for (CountryReport report : countries) System.out.println(report.name + " - " + report.population);

            // All the countries in a continent organised by largest population to smallest.
            System.out.println("\n\n\nAll the countries in a continent organised by largest population to smallest.");
            for (String continent : countriesInContinent.keySet()) {
                System.out.println("\n\n\nContinent: " + continent);
                for (CountryReport report : countriesInContinent.get(continent)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // All the countries in a region organised by largest population to smallest.
            System.out.println("\n\n\nAll the countries in a region organised by largest population to smallest.");
            for (String region : countriesInRegion.keySet()) {
                System.out.println("\n\n\nRegion: " + region);
                for (CountryReport report : countriesInRegion.get(region)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // The top N populated countries in the world where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated countries in the world");
            for (CountryReport report : topNCountries) System.out.println(report.name + " - " + report.population);

            // The top N populated countries in a continent where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated countries in a continent");
            for (String continent : topNCountriesInContinent.keySet()) {
                System.out.println("\n\n\nContinent: " + continent);
                for (CountryReport report : topNCountriesInContinent.get(continent)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // The top N populated countries in a region where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated countries in a region");
            for (String region : topNCountriesInRegion.keySet()) {
                System.out.println("\n\n\nRegion: " + region);
                for (CountryReport report : topNCountriesInRegion.get(region)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // All the cities in the world organised by largest population to smallest.
            System.out.println("\n\n\nAll the cities in the world organised by largest population to smallest.");
            for (CityReport report : cities) System.out.println(report.name + " - " + report.population);

            // All the cities in a continent organised by largest population to smallest.
            System.out.println("\n\n\nAll the cities in a continent organised by largest population to smallest.");
            for (String continent : citiesInContinent.keySet()) {
                System.out.println("\n\n\nContinent: " + continent);
                for (CityReport report : citiesInContinent.get(continent)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // All the cities in a region organised by largest population to smallest.
            System.out.println("\n\n\nAll the cities in a region organised by largest population to smallest.");
            for (String region : citiesInRegion.keySet()) {
                System.out.println("\n\n\nRegion: " + region);
                for (CityReport report : citiesInRegion.get(region)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // All the cities in a country organised by largest population to smallest.
            System.out.println("\n\n\nAll the cities in a country organised by largest population to smallest.");
            for (String country : citiesInCountry.keySet()) {
                System.out.println("\n\n\nCountry: " + country);
                for (CityReport report : citiesInCountry.get(country)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // All the cities in a district organised by largest population to smallest.
            System.out.println("\n\n\nAll the cities in a district organised by largest population to smallest.");
            for (String district : citiesInDistrict.keySet()) {
                System.out.println("\n\n\nDistrict: " + district);
                for (CityReport report : citiesInDistrict.get(district)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // The top N populated cities in the world where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated cities in the world");
            for (CityReport report : topNCities) System.out.println(report.name + " - " + report.population);

            // The top N populated cities in a continent where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated cities in a continent");
            for (String continent : topNCitiesInContinent.keySet()) {
                System.out.println("\n\n\nContinent: " + continent);
                for (CityReport report : topNCitiesInContinent.get(continent)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // The top N populated cities in a region where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated cities in a region");
            for (String region : topNCitiesInRegion.keySet()) {
                System.out.println("\n\n\nRegion: " + region);
                for (CityReport report : topNCitiesInRegion.get(region)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // The top N populated cities in a country where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated cities in a country");
            for (String country : topNCitiesInCountry.keySet()) {
                System.out.println("\n\n\nCountry: " + country);
                for (CityReport report : topNCitiesInCountry.get(country)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // The top N populated cities in a district where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated cities in a district");
            for (String district : topNCitiesInDistrict.keySet()) {
                System.out.println("\n\n\nDistrict: " + district);
                for (CityReport report : topNCitiesInDistrict.get(district)) System.out.println("\t" + report.name + " - " + report.population);
            }

        } catch (Exception e) {
            // On error, print error message
            System.out.println(e.getMessage());
        } finally {
            // Disconnect from database
            db.disconnect();
        }

    }

    private static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printWithDelays(String data, int delay) {
        for (char ch : data.toCharArray()) {
            System.out.print(ch);
            wait(delay);
        }
    }
}
