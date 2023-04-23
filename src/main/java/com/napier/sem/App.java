package com.napier.sem;

import com.napier.sem.additions.LanguagesSpoken;
import com.napier.sem.additions.PopulationCounts;
import com.napier.sem.database.*;
import com.napier.sem.report.*;
import com.napier.sem.reports.CapitalCities;
import com.napier.sem.reports.Cities;
import com.napier.sem.reports.Countries;
import com.napier.sem.reports.Population;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

//TODO: Look into making the Cities and Countries class methods all non static
public class App {
    public static void main(String[] args) {
        // Initiate database Class
        DB db = new DB("localhost", "33060", "world", "root", "group6Pass");

        // Create a scanner to read user input where required
        Scanner userInput = new Scanner(System.in);

        // Connect to database
        if(!db.connect()) {
            System.out.println("Failed to connect to database");
            return;
        }

        // Get user input for a value for N
        System.out.print("Please enter a value for N: ");
        int n = userInput.nextInt();

        // Run reports
        runReports(db, n, 200);

        // Disconnect from database
        if(!db.disconnect()) {
            System.out.println("Error disconnecting from database");
        }

    }

    public static void runReports(DB db, int n, int ms) {
        // All the countries in the world organised by largest population to smallest.
        System.out.print("1. Computing all countries in the world organised by largest population to smallest");
        printWithDelays(ms);
        List<CountryReport> countries = Countries.getAllCountriesByPopulation(db, -1);


        // All the countries in a continent organised by largest population to smallest.
        System.out.print("2. Computing all the countries in a continent organised by largest population to smallest");
        printWithDelays(ms);
        HashMap<String, List<CountryReport>> countriesInContinent = Countries.getCountriesInContinentByPopulation(db, -1);

        // All the countries in a region organised by largest population to smallest.
        System.out.print("3. Computing all the countries in a region organised by largest population to smallest");
        printWithDelays(ms);
        HashMap<String, List<CountryReport>> countriesInRegion = Countries.getCountriesInRegionByPopulation(db, -1);

        // The top N populated countries in the world where N is provided by the user.
        System.out.print("4. Computing the top " + n + " populated countries in the world");
        printWithDelays(ms);
        List<CountryReport> topNCountries = Countries.getAllCountriesByPopulation(db, n);

        // The top N populated countries in a continent where N is provided by the user.
        System.out.print("5. Computing the top " + n + " populated countries in a continent");
        printWithDelays(ms);
        HashMap<String, List<CountryReport>> topNCountriesInContinent = Countries.getCountriesInContinentByPopulation(db, n);

        // The top N populated countries in a region where N is provided by the user.
        System.out.print("6. Computing the top " + n + " populated countries in a region");
        printWithDelays(ms);
        HashMap<String, List<CountryReport>> topNCountriesInRegion = Countries.getCountriesInRegionByPopulation(db, n);

        // All the cities in the world organised by largest population to smallest.
        System.out.print("7. Computing all the cities in the world organised by largest population to smallest");
        printWithDelays(ms);
        List<CityReport> cities = Cities.getAllCitiesByPopulation(db, -1);

        // All the cities in a continent organised by largest population to smallest.
        System.out.print("8. Computing all the cities in a continent organised by largest population to smallest");
        printWithDelays(ms);
        HashMap<String, List<CityReport>> citiesInContinent = Cities.getCitiesInContinentByPopulation(db, -1);

        // All the cities in a region organised by largest population to smallest.
        System.out.print("9. Computing all the cities in a region organised by largest population to smallest");
        printWithDelays(ms);
        HashMap<String, List<CityReport>> citiesInRegion = Cities.getCitiesInRegionByPopulation(db, -1);

        // All the cities in a country organised by largest population to smallest.
        System.out.print("10. Computing all the cities in a country organised by largest population to smallest");
        printWithDelays(ms);
        HashMap<String, List<CityReport>> citiesInCountry = Cities.getCitiesInCountryByPopulation(db, -1);

        // All the cities in a district organised by largest population to smallest.
        System.out.print("11. Computing all the cities in a district organised by largest population to smallest");
        printWithDelays(ms);
        HashMap<String, List<CityReport>> citiesInDistrict = Cities.getCitiesInDistrictByPopulation(db, -1);

        // The top N populated cities in the world where N is provided by the user.
        System.out.print("12. Computing the top " + n + " populated cities in the world");
        printWithDelays(ms);
        List<CityReport> topNCities = Cities.getAllCitiesByPopulation(db, n);

        // The top N populated cities in a continent where N is provided by the user.
        System.out.print("13. Computing the top " + n + " populated cities in a continent");
        printWithDelays(ms);
        HashMap<String, List<CityReport>> topNCitiesInContinent = Cities.getCitiesInContinentByPopulation(db, n);

        // The top N populated cities in a region where N is provided by the user.
        System.out.print("14. Computing the top " + n + " populated cities in a region");
        printWithDelays(ms);
        HashMap<String, List<CityReport>> topNCitiesInRegion = Cities.getCitiesInRegionByPopulation(db, n);

        // The top N populated cities in a country where N is provided by the user.
        System.out.print("15. Computing the top " + n + " populated cities in a country");
        printWithDelays(ms);
        HashMap<String, List<CityReport>> topNCitiesInCountry = Cities.getCitiesInCountryByPopulation(db, n);

        // The top N populated cities in a district where N is provided by the user.
        System.out.print("16. Computing the top " + n + " populated cities in a district");
        printWithDelays(ms);
        HashMap<String, List<CityReport>> topNCitiesInDistrict = Cities.getCitiesInDistrictByPopulation(db, n);

        // All the capital cities in the world organised by largest population to smallest.
        System.out.print("17. Computing all the capital cities in the world organised by largest population to smallest");
        printWithDelays(ms);
        List<CapitalCityReport> capitalCities = CapitalCities.getCapitalCitiesByPopulation(db, -1);

        // All the capital cities in a continent organised by largest population to smallest.
        System.out.print("18. Computing all the capital cities in a continent organised by largest population to smallest");
        printWithDelays(ms);
        HashMap<String, List<CapitalCityReport>> capitalCitiesInContinent = CapitalCities.getCapitalCitiesInContinentByPopulation(db, -1);

        // All the capital cities in a region organised by largest population to smallest.
        System.out.print("19. Computing all the capital cities in a region organised by largest population to smallest");
        printWithDelays(ms);
        HashMap<String, List<CapitalCityReport>> capitalCitiesInRegion = CapitalCities.getCapitalCitiesInRegionByPopulation(db, -1);

        // The top N populated capital cities in the world where N is provided by the user.
        System.out.print("20. Computing the top " + n + " populated capital cities in the world");
        printWithDelays(ms);
        List<CapitalCityReport> topNCapitalCities = CapitalCities.getCapitalCitiesByPopulation(db, n);

        // The top N populated capital cities in a continent where N is provided by the user.
        System.out.print("21. Computing the top " + n + " populated capital cities in a continent");
        printWithDelays(ms);
        HashMap<String, List<CapitalCityReport>> topNCapitalCitiesInContinent = CapitalCities.getCapitalCitiesInContinentByPopulation(db, n);

        // The top N populated capital cities in a region where N is provided by the user.
        System.out.print("22. Computing the top " + n + " populated capital cities in a region");
        printWithDelays(ms);
        HashMap<String, List<CapitalCityReport>> topNCapitalCitiesInRegion = CapitalCities.getCapitalCitiesInRegionByPopulation(db, n);

        // The population of people, people living in cities, and people not living in cities in each continent.
        System.out.print("23. Computing the population of people, people living in cities, and people not living in cities in each continent");
        printWithDelays(ms);
        HashMap<String, PopulationReport> populationInContinent = Population.getPopulationByContinent(db);

        // The population of people, people living in cities, and people not living in cities in each region.
        System.out.print("24. Computing the population of people, people living in cities, and people not living in cities in each region");
        printWithDelays(ms);
        HashMap<String, PopulationReport> populationInRegion = Population.getPopulationByRegion(db);

        // The population of people, people living in cities, and people not living in cities in each country.
        System.out.print("25. Computing the population of people, people living in cities, and people not living in cities in each country");
        printWithDelays(ms);
        HashMap<String, PopulationReport> populationInCountry = Population.getPopulationByCountry(db);

        // View population of specific areas
        System.out.print("26. View population of specific areas");
        printWithDelays(ms);
        Long worldPopulation = PopulationCounts.getPopulationOfWorld(db);
        Long continentPopulation = PopulationCounts.getPopulationOfContinent(db, "Europe");
        Long regionPopulation = PopulationCounts.getPopulationOfRegion(db, "British Islands");
        Long countryPopulation = PopulationCounts.getPopulationOfCountry(db, "United Kingdom");
        Long districtPopulation = PopulationCounts.getPopulationOfDistrict(db, "Scotland");
        Long cityPopulation = PopulationCounts.getPopulationOfCity(db, "Edinburgh");


        // Number of people who speak each language
        System.out.print("27. Computing the number of people who speak each language");
        printWithDelays(ms);
        List<LanguageReport> languages = LanguagesSpoken.getLanguagesSpokenByCountry(db);


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
    }

    private static void printWithDelays(int ms) {
        for (char ch : "...\n".toCharArray()) {
            System.out.print(ch);
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
