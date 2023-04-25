package com.napier.sem;

import com.napier.sem.additions.LanguagesSpoken;
import com.napier.sem.additions.PopulationCounts;
import com.napier.sem.database.*;
import com.napier.sem.report.*;
import com.napier.sem.reports.CapitalCities;
import com.napier.sem.reports.Cities;
import com.napier.sem.reports.Countries;
import com.napier.sem.reports.Population;

import java.util.*;
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

        // clear existing reports
        Helpers.clearReports();

        // Get user input for a value for N
        System.out.print("Please enter a value for N: ");
        int n = userInput.nextInt();

        // Run reports
        runReports(db, n, 10);

        // Disconnect from database
        if(!db.disconnect()) {
            System.out.println("Error disconnecting from database");
        }

    }

    public static void runReports(DB db, int n, int ms) {
        // All the countries in the world organised by largest population to smallest.
        System.out.print("1. Computing all countries in the world organised by largest population to smallest");
        Helpers.printWithDelays(ms);
        List<CountryReport> countries = Countries.getAllCountriesByPopulation(db, -1);
        String report1 = Helpers.generateReport(countries, "All Countries in the World by Population");
        Helpers.writeReport("1", "total", report1);

        // All the countries in a continent organised by largest population to smallest.
        System.out.print("2. Computing all the countries in a continent organised by largest population to smallest");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CountryReport>> countriesInContinent = Countries.getCountriesInContinentByPopulation(db, -1);
        for (String continent : countriesInContinent.keySet()) {
            String report2 = Helpers.generateReport(countriesInContinent.get(continent), "All Countries in continent \"" + continent + "\" by Population");
            Helpers.writeReport("2", continent, report2);
        }

        // All the countries in a region organised by largest population to smallest.
        System.out.print("3. Computing all the countries in a region organised by largest population to smallest");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CountryReport>> countriesInRegion = Countries.getCountriesInRegionByPopulation(db, -1);
        for (String region : countriesInRegion.keySet()) {
            String report3 = Helpers.generateReport(countriesInRegion.get(region), "All Countries in region \"" + region + "\" by Population");
            Helpers.writeReport("3", region, report3);
        }

        // The top N populated countries in the world where N is provided by the user.
        System.out.print("4. Computing the top " + n + " populated countries in the world");
        Helpers.printWithDelays(ms);
        List<CountryReport> topNCountries = Countries.getAllCountriesByPopulation(db, n);
        String report4 = Helpers.generateReport(topNCountries, "Top " + n + " Countries in the World by Population");
        Helpers.writeReport("4", "total", report4);

        // The top N populated countries in a continent where N is provided by the user.
        System.out.print("5. Computing the top " + n + " populated countries in a continent");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CountryReport>> topNCountriesInContinent = Countries.getCountriesInContinentByPopulation(db, n);
        for (String continent : topNCountriesInContinent.keySet()) {
            String report5 = Helpers.generateReport(topNCountriesInContinent.get(continent), "Top " + n + " Countries in continent \"" + continent + "\" by Population");
            Helpers.writeReport("5", continent, report5);
        }

        // The top N populated countries in a region where N is provided by the user.
        System.out.print("6. Computing the top " + n + " populated countries in a region");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CountryReport>> topNCountriesInRegion = Countries.getCountriesInRegionByPopulation(db, n);
        for (String region : topNCountriesInRegion.keySet()) {
            String report6 = Helpers.generateReport(topNCountriesInRegion.get(region), "Top " + n + " Countries in region \"" + region + "\" by Population");
            Helpers.writeReport("6", region, report6);
        }

        // All the cities in the world organised by largest population to smallest.
        System.out.print("7. Computing all the cities in the world organised by largest population to smallest");
        Helpers.printWithDelays(ms);
        List<CityReport> cities = Cities.getAllCitiesByPopulation(db, -1);
        String report7 = Helpers.generateReport(cities, "All Cities in the World by Population");
        Helpers.writeReport("7", "total", report7);

        // All the cities in a continent organised by largest population to smallest.
        System.out.print("8. Computing all the cities in a continent organised by largest population to smallest");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CityReport>> citiesInContinent = Cities.getCitiesInContinentByPopulation(db, -1);
        for (String continent : citiesInContinent.keySet()) {
            String report8 = Helpers.generateReport(citiesInContinent.get(continent), "All Cities in continent \"" + continent + "\" by Population");
            Helpers.writeReport("8", continent, report8);
        }

        // All the cities in a region organised by largest population to smallest.
        System.out.print("9. Computing all the cities in a region organised by largest population to smallest");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CityReport>> citiesInRegion = Cities.getCitiesInRegionByPopulation(db, -1);
        for (String region : citiesInRegion.keySet()) {
            String report9 = Helpers.generateReport(citiesInRegion.get(region), "All Cities in region \"" + region + "\" by Population");
            Helpers.writeReport("9", region, report9);
        }

        // All the cities in a country organised by largest population to smallest.
        System.out.print("10. Computing all the cities in a country organised by largest population to smallest");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CityReport>> citiesInCountry = Cities.getCitiesInCountryByPopulation(db, -1);
        for (String country : citiesInCountry.keySet()) {
            String report10 = Helpers.generateReport(citiesInCountry.get(country), "All Cities in country \"" + country + "\" by Population");
            Helpers.writeReport("10", country, report10);
        }

        // All the cities in a district organised by largest population to smallest.
        System.out.print("11. Computing all the cities in a district organised by largest population to smallest");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CityReport>> citiesInDistrict = Cities.getCitiesInDistrictByPopulation(db, -1);
        for (String district : citiesInDistrict.keySet()) {
            String report11 = Helpers.generateReport(citiesInDistrict.get(district), "All Cities in district \"" + district + "\" by Population");
            Helpers.writeReport("11", district, report11);
        }

        // The top N populated cities in the world where N is provided by the user.
        System.out.print("12. Computing the top " + n + " populated cities in the world");
        Helpers.printWithDelays(ms);
        List<CityReport> topNCities = Cities.getAllCitiesByPopulation(db, n);
        String report12 = Helpers.generateReport(topNCities, "Top " + n + " Cities in the World by Population");
        Helpers.writeReport("12", "total", report12);

        // The top N populated cities in a continent where N is provided by the user.
        System.out.print("13. Computing the top " + n + " populated cities in a continent");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CityReport>> topNCitiesInContinent = Cities.getCitiesInContinentByPopulation(db, n);
        for (String continent : topNCitiesInContinent.keySet()) {
            String report13 = Helpers.generateReport(topNCitiesInContinent.get(continent), "Top " + n + " Cities in continent \"" + continent + "\" by Population");
            Helpers.writeReport("13", continent, report13);
        }

        // The top N populated cities in a region where N is provided by the user.
        System.out.print("14. Computing the top " + n + " populated cities in a region");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CityReport>> topNCitiesInRegion = Cities.getCitiesInRegionByPopulation(db, n);
        for (String region : topNCitiesInRegion.keySet()) {
            String report14 = Helpers.generateReport(topNCitiesInRegion.get(region), "Top " + n + " Cities in region \"" + region + "\" by Population");
            Helpers.writeReport("14", region, report14);
        }

        // The top N populated cities in a country where N is provided by the user.
        System.out.print("15. Computing the top " + n + " populated cities in a country");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CityReport>> topNCitiesInCountry = Cities.getCitiesInCountryByPopulation(db, n);
        for (String country : topNCitiesInCountry.keySet()) {
            String report15 = Helpers.generateReport(topNCitiesInCountry.get(country), "Top " + n + " Cities in country \"" + country + "\" by Population");
            Helpers.writeReport("15", country, report15);
        }

        // The top N populated cities in a district where N is provided by the user.
        System.out.print("16. Computing the top " + n + " populated cities in a district");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CityReport>> topNCitiesInDistrict = Cities.getCitiesInDistrictByPopulation(db, n);
        for (String district : topNCitiesInDistrict.keySet()) {
            String report16 = Helpers.generateReport(topNCitiesInDistrict.get(district), "Top " + n + " Cities in district \"" + district + "\" by Population");
            Helpers.writeReport("16", district, report16);
        }

        // All the capital cities in the world organised by largest population to smallest.
        System.out.print("17. Computing all the capital cities in the world organised by largest population to smallest");
        Helpers.printWithDelays(ms);
        List<CapitalCityReport> capitalCities = CapitalCities.getCapitalCitiesByPopulation(db, -1);
        String report17 = Helpers.generateReport(capitalCities, "All Capital Cities in the World by Population");
        Helpers.writeReport("17", "total", report17);

        // All the capital cities in a continent organised by largest population to smallest.
        System.out.print("18. Computing all the capital cities in a continent organised by largest population to smallest");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CapitalCityReport>> capitalCitiesInContinent = CapitalCities.getCapitalCitiesInContinentByPopulation(db, -1);
        for (String continent : capitalCitiesInContinent.keySet()) {
            String report18 = Helpers.generateReport(capitalCitiesInContinent.get(continent), "All Capital Cities in continent \"" + continent + "\" by Population");
            Helpers.writeReport("18", continent, report18);
        }

        // All the capital cities in a region organised by largest population to smallest.
        System.out.print("19. Computing all the capital cities in a region organised by largest population to smallest");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CapitalCityReport>> capitalCitiesInRegion = CapitalCities.getCapitalCitiesInRegionByPopulation(db, -1);
        for (String region : capitalCitiesInRegion.keySet()) {
            String report19 = Helpers.generateReport(capitalCitiesInRegion.get(region), "All Capital Cities in region \"" + region + "\" by Population");
            Helpers.writeReport("19", region, report19);
        }

        // The top N populated capital cities in the world where N is provided by the user.
        System.out.print("20. Computing the top " + n + " populated capital cities in the world");
        Helpers.printWithDelays(ms);
        List<CapitalCityReport> topNCapitalCities = CapitalCities.getCapitalCitiesByPopulation(db, n);
        String report20 = Helpers.generateReport(topNCapitalCities, "Top " + n + " Capital Cities in the World by Population");
        Helpers.writeReport("20", "total", report20);

        // The top N populated capital cities in a continent where N is provided by the user.
        System.out.print("21. Computing the top " + n + " populated capital cities in a continent");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CapitalCityReport>> topNCapitalCitiesInContinent = CapitalCities.getCapitalCitiesInContinentByPopulation(db, n);
        for (String continent : topNCapitalCitiesInContinent.keySet()) {
            String report21 = Helpers.generateReport(topNCapitalCitiesInContinent.get(continent), "Top " + n + " Capital Cities in continent \"" + continent + "\" by Population");
            Helpers.writeReport("21", continent, report21);
        }

        // The top N populated capital cities in a region where N is provided by the user.
        System.out.print("22. Computing the top " + n + " populated capital cities in a region");
        Helpers.printWithDelays(ms);
        HashMap<String, List<CapitalCityReport>> topNCapitalCitiesInRegion = CapitalCities.getCapitalCitiesInRegionByPopulation(db, n);
        for (String region : topNCapitalCitiesInRegion.keySet()) {
            String report22 = Helpers.generateReport(topNCapitalCitiesInRegion.get(region), "Top " + n + " Capital Cities in region \"" + region + "\" by Population");
            Helpers.writeReport("22", region, report22);
        }

        // The population of people, people living in cities, and people not living in cities in each continent.
        System.out.print("23. Computing the population of people, people living in cities, and people not living in cities in each continent");
        Helpers.printWithDelays(ms);
        HashMap<String, PopulationReport> populationInContinent = Population.getPopulationByContinent(db);
        for (String continent : populationInContinent.keySet()) {
            String report23 = Helpers.generateReport(Collections.singletonList(populationInContinent.get(continent)), "Population in continent \"" + continent + "\"");
            Helpers.writeReport("23", continent, report23);
        }

        // The population of people, people living in cities, and people not living in cities in each region.
        System.out.print("24. Computing the population of people, people living in cities, and people not living in cities in each region");
        Helpers.printWithDelays(ms);
        HashMap<String, PopulationReport> populationInRegion = Population.getPopulationByRegion(db);
        for (String region : populationInRegion.keySet()) {
            String report24 = Helpers.generateReport(Collections.singletonList(populationInRegion.get(region)), "Population in region \"" + region + "\"");
            Helpers.writeReport("24", region, report24);
        }

        // The population of people, people living in cities, and people not living in cities in each country.
        System.out.print("25. Computing the population of people, people living in cities, and people not living in cities in each country");
        Helpers.printWithDelays(ms);
        HashMap<String, PopulationReport> populationInCountry = Population.getPopulationByCountry(db);
        for (String country : populationInCountry.keySet()) {
            String report25 = Helpers.generateReport(Collections.singletonList(populationInCountry.get(country)), "Population in country \"" + country + "\"");
            Helpers.writeReport("25", country, report25);
        }

        // View population of specific areas
        System.out.print("26. View population of specific areas");
        Helpers.printWithDelays(ms);
        String header = String.format("| %15s | %15s |" + "\n" + "|-----|-----|", "Name", " Population");
        Long worldPopulation = PopulationCounts.getPopulationOfWorld(db);
        String report26A = Helpers.generateBasicReport("Population of the World", header, String.format("| %15s | %15s |", "World", worldPopulation.toString()));
        Helpers.writeReport("26", "world", report26A);

        Long continentPopulation = PopulationCounts.getPopulationOfContinent(db, "Europe");
        String report26B = Helpers.generateBasicReport("Population of Continent", header, String.format("| %15s | %15s |", "Europe", continentPopulation.toString()));
        Helpers.writeReport("26", "continent", report26B);

        Long regionPopulation = PopulationCounts.getPopulationOfRegion(db, "British Islands");
        String report26C = Helpers.generateBasicReport("Population of Region", header, String.format("| %15s | %15s |", "British Islands", regionPopulation.toString()));
        Helpers.writeReport("26", "region", report26C);

        Long countryPopulation = PopulationCounts.getPopulationOfCountry(db, "United Kingdom");
        String report26D = Helpers.generateBasicReport("Population of Country", header, String.format("| %15s | %15s |", "United Kingdom", countryPopulation.toString()));
        Helpers.writeReport("26", "country", report26D);

        Long districtPopulation = PopulationCounts.getPopulationOfDistrict(db, "Scotland");
        String report26E = Helpers.generateBasicReport("Population of District", header, String.format("| %15s | %15s |", "Scotland", districtPopulation.toString()));
        Helpers.writeReport("26", "district", report26E);

        Long cityPopulation = PopulationCounts.getPopulationOfCity(db, "Edinburgh");
        String report26F = Helpers.generateBasicReport("Population of City", header, String.format("| %15s | %15s |", "Edinburgh", cityPopulation.toString()));
        Helpers.writeReport("26", "city", report26F);


        // Number of people who speak each language
        System.out.print("27. Computing the number of people who speak each language");
        Helpers.printWithDelays(ms);
        List<LanguageReport> languages = LanguagesSpoken.getLanguagesSpokenByCountry(db);
        List<String> languageList = Arrays.asList("Chinese", "English", "Hindi", "Spanish", "Arabic");
        List<LanguageReport> filteredLanguages = languages.stream().filter(language -> languageList.contains(language.name)).toList();
        String report27 = Helpers.generateReport(filteredLanguages, "Languages spoken by country");
        Helpers.writeReport("27", "languages", report27);
    }
}
