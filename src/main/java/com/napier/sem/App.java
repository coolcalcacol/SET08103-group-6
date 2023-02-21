package com.napier.sem;

import com.napier.sem.database.Cities;
import com.napier.sem.database.Countries;
import com.napier.sem.database.DB;
import com.napier.sem.report.CapitalCityReport;
import com.napier.sem.report.CityReport;
import com.napier.sem.report.CountryReport;
import com.napier.sem.report.PopulationReport;

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

            // Get user input for a value for N
            System.out.print("Please enter a value for N: ");
            int n = userInput.nextInt();

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

            // All the capital cities in the world organised by largest population to smallest.
            System.out.print("17. Computing all the capital cities in the world organised by largest population to smallest");
            printWithDelays("...\n", 200);
            List<CapitalCityReport> capitalCities = Cities.getCapitalCitiesByPopulation(db, -1);

            // All the capital cities in a continent organised by largest population to smallest.
            System.out.print("18. Computing all the capital cities in a continent organised by largest population to smallest");
            printWithDelays("...\n", 200);
            HashMap<String, List<CapitalCityReport>> capitalCitiesInContinent = Cities.getCapitalCitiesInContinentByPopulation(db, -1);

            // All the capital cities in a region organised by largest population to smallest.
            System.out.print("19. Computing all the capital cities in a region organised by largest population to smallest");
            printWithDelays("...\n", 200);
            HashMap<String, List<CapitalCityReport>> capitalCitiesInRegion = Cities.getCapitalCitiesInRegionByPopulation(db, -1);

            // The top N populated capital cities in the world where N is provided by the user.
            System.out.print("20. Computing the top " + n + " populated capital cities in the world");
            printWithDelays("...\n", 200);
            List<CapitalCityReport> topNCapitalCities = Cities.getCapitalCitiesByPopulation(db, n);

            // The top N populated capital cities in a continent where N is provided by the user.
            System.out.print("21. Computing the top " + n + " populated capital cities in a continent");
            printWithDelays("...\n", 200);
            HashMap<String, List<CapitalCityReport>> topNCapitalCitiesInContinent = Cities.getCapitalCitiesInContinentByPopulation(db, n);

            // The top N populated capital cities in a region where N is provided by the user.
            System.out.print("22. Computing the top " + n + " populated capital cities in a region");
            printWithDelays("...\n", 200);
            HashMap<String, List<CapitalCityReport>> topNCapitalCitiesInRegion = Cities.getCapitalCitiesInRegionByPopulation(db, n);

            // The population of people, people living in cities, and people not living in cities in each continent.
            System.out.print("23. Computing the population of people, people living in cities, and people not living in cities in each continent");
            printWithDelays("...\n", 200);
            HashMap<String, PopulationReport> populationInContinent = Cities.getPopulationByContinent(db);

            // The population of people, people living in cities, and people not living in cities in each region.
            System.out.print("24. Computing the population of people, people living in cities, and people not living in cities in each region");
            printWithDelays("...\n", 200);
            HashMap<String, PopulationReport> populationInRegion = Cities.getPopulationByRegion(db);

            // The population of people, people living in cities, and people not living in cities in each country.
            System.out.print("25. Computing the population of people, people living in cities, and people not living in cities in each country");
            printWithDelays("...\n", 200);
            HashMap<String, PopulationReport> populationInCountry = Cities.getPopulationByCountry(db);

            //TODO: Determine what output method we are to use for all reports



            //// OUTPUT SECTION ////

            // All the countries in the world organised by largest population to smallest.
            System.out.println("\n\n\nAll the countries in the world organised by largest population to smallest.");
            for (CountryReport report : countries) System.out.println(report.name + " - " + report.population);

            // All the countries in a continent organised by largest population to smallest.
            System.out.println("\n\n\nAll the countries in a continent organised by largest population to smallest.");
            for (String continent : countriesInContinent.keySet()) {
                System.out.println("\nContinent: " + continent);
                for (CountryReport report : countriesInContinent.get(continent)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // All the countries in a region organised by largest population to smallest.
            System.out.println("\n\n\nAll the countries in a region organised by largest population to smallest.");
            for (String region : countriesInRegion.keySet()) {
                System.out.println("\nRegion: " + region);
                for (CountryReport report : countriesInRegion.get(region)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // The top N populated countries in the world where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated countries in the world");
            for (CountryReport report : topNCountries) System.out.println(report.name + " - " + report.population);

            // The top N populated countries in a continent where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated countries in a continent");
            for (String continent : topNCountriesInContinent.keySet()) {
                System.out.println("\nContinent: " + continent);
                for (CountryReport report : topNCountriesInContinent.get(continent)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // The top N populated countries in a region where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated countries in a region");
            for (String region : topNCountriesInRegion.keySet()) {
                System.out.println("\nRegion: " + region);
                for (CountryReport report : topNCountriesInRegion.get(region)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // All the cities in the world organised by largest population to smallest.
            System.out.println("\n\n\nAll the cities in the world organised by largest population to smallest.");
            for (CityReport report : cities) System.out.println(report.name + " - " + report.population);

            // All the cities in a continent organised by largest population to smallest.
            System.out.println("\n\n\nAll the cities in a continent organised by largest population to smallest.");
            for (String continent : citiesInContinent.keySet()) {
                System.out.println("\nContinent: " + continent);
                for (CityReport report : citiesInContinent.get(continent)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // All the cities in a region organised by largest population to smallest.
            System.out.println("\n\n\nAll the cities in a region organised by largest population to smallest.");
            for (String region : citiesInRegion.keySet()) {
                System.out.println("\nRegion: " + region);
                for (CityReport report : citiesInRegion.get(region)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // All the cities in a country organised by largest population to smallest.
            System.out.println("\n\n\nAll the cities in a country organised by largest population to smallest.");
            for (String country : citiesInCountry.keySet()) {
                System.out.println("\nCountry: " + country);
                for (CityReport report : citiesInCountry.get(country)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // All the cities in a district organised by largest population to smallest.
            System.out.println("\n\n\nAll the cities in a district organised by largest population to smallest.");
            for (String district : citiesInDistrict.keySet()) {
                System.out.println("\nDistrict: " + district);
                for (CityReport report : citiesInDistrict.get(district)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // The top N populated cities in the world where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated cities in the world");
            for (CityReport report : topNCities) System.out.println(report.name + " - " + report.population);

            // The top N populated cities in a continent where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated cities in a continent");
            for (String continent : topNCitiesInContinent.keySet()) {
                System.out.println("\nContinent: " + continent);
                for (CityReport report : topNCitiesInContinent.get(continent)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // The top N populated cities in a region where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated cities in a region");
            for (String region : topNCitiesInRegion.keySet()) {
                System.out.println("\nRegion: " + region);
                for (CityReport report : topNCitiesInRegion.get(region)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // The top N populated cities in a country where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated cities in a country");
            for (String country : topNCitiesInCountry.keySet()) {
                System.out.println("\nCountry: " + country);
                for (CityReport report : topNCitiesInCountry.get(country)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // The top N populated cities in a district where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated cities in a district");
            for (String district : topNCitiesInDistrict.keySet()) {
                System.out.println("\nDistrict: " + district);
                for (CityReport report : topNCitiesInDistrict.get(district)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // All the capital cities in the world organised by largest population to smallest.
            System.out.println("\n\n\nAll the capital cities in the world organised by largest population to smallest.");
            for (CapitalCityReport report : capitalCities) System.out.println(report.name + " - " + report.population);

            // All the capital cities in a continent organised by largest population to smallest.
            System.out.println("\n\n\nAll the capital cities in a continent organised by largest population to smallest.");
            for (String continent : capitalCitiesInContinent.keySet()) {
                System.out.println("\nContinent: " + continent);
                for (CapitalCityReport report : capitalCitiesInContinent.get(continent)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // All the capital cities in a region organised by largest population to smallest.
            System.out.println("\n\n\nAll the capital cities in a region organised by largest population to smallest.");
            for (String region : capitalCitiesInRegion.keySet()) {
                System.out.println("\nRegion: " + region);
                for (CapitalCityReport report : capitalCitiesInRegion.get(region)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // The top N populated capital cities in the world where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated capital cities in the world");
            for (CapitalCityReport report : topNCapitalCities) System.out.println(report.name + " - " + report.population);

            // The top N populated capital cities in a continent where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated capital cities in a continent");
            for (String continent : topNCapitalCitiesInContinent.keySet()) {
                System.out.println("\nContinent: " + continent);
                for (CapitalCityReport report : topNCapitalCitiesInContinent.get(continent)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // The top N populated capital cities in a region where N is provided by the user.
            System.out.println("\n\n\nThe top " + n + " populated capital cities in a region");
            for (String region : topNCapitalCitiesInRegion.keySet()) {
                System.out.println("\nRegion: " + region);
                for (CapitalCityReport report : topNCapitalCitiesInRegion.get(region)) System.out.println("\t" + report.name + " - " + report.population);
            }

            // The population of people, people living in cities, and people not living in cities in each continent.
            System.out.println("\n\n\nThe population of people, people living in cities, and people not living in cities in each continent");
            for (String continent : populationInContinent.keySet()) {
                System.out.println("\nContinent: " + continent);
                System.out.println("\tPopulation: " + populationInContinent.get(continent).population);
                System.out.println("\tPopulation living in cities: " + populationInContinent.get(continent).populationInCities);
                System.out.println("\tPopulation not living in cities: " + populationInContinent.get(continent).populationOutCities);
            }

            // The population of people, people living in cities, and people not living in cities in each region.
            System.out.println("\n\n\nThe population of people, people living in cities, and people not living in cities in each region");
            for (String region : populationInRegion.keySet()) {
                System.out.println("\nRegion: " + region);
                System.out.println("\tPopulation: " + populationInRegion.get(region).population);
                System.out.println("\tPopulation living in cities: " + populationInRegion.get(region).populationInCities);
                System.out.println("\tPopulation not living in cities: " + populationInRegion.get(region).populationOutCities);
            }

            // The population of people, people living in cities, and people not living in cities in each country.
            System.out.println("\n\n\nThe population of people, people living in cities, and people not living in cities in each country");
            for (String country : populationInCountry.keySet()) {
                System.out.println("\nCountry: " + country);
                System.out.println("\tPopulation: " + populationInCountry.get(country).population);
                System.out.println("\tPopulation living in cities: " + populationInCountry.get(country).populationInCities);
                System.out.println("\tPopulation not living in cities: " + populationInCountry.get(country).populationOutCities);
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
//            wait(delay);
        }
    }
}
