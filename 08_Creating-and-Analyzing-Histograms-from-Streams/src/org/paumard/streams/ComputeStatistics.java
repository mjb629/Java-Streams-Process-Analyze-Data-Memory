package org.paumard.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.paumard.streams.model.City;


public class ComputeStatistics {

	public static void main(String[] args) {
		
		Function<String, City> lineToCity = 
				line -> {
					String[] split = line.split(";");
					
					String cityName = split[1].trim();
					
					String state = split[2].trim();
					
					String populationAsString = split[3];
					populationAsString = populationAsString.replace(" ", "");
					int population = Integer.parseInt(populationAsString);
					
					String landAreaAsString = split[4];
					landAreaAsString = landAreaAsString.replace(" ", "").replace(',', '.');
					double landArea = Double.parseDouble(landAreaAsString);
					
					return new City(cityName, state, population, landArea);
				};
				
		// https://en.wikipedia.org/wiki/List_of_United_States_cities_by_population
		Path path = Path.of("data/cities.csv");
		Set<City> cities = null;
		try (Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1);) {
			
			cities = lines.skip(2)
				.map(lineToCity)
				.collect(Collectors.toSet());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("# cities = " + cities.size());
		
		Map<String, List<City>> citiesPerState =
			cities.stream()
				.collect(
						Collectors.groupingBy(
								city -> city.getState()
								)
						);
		System.out.println("Map size = " + citiesPerState.size());
		List<City> citiesOfUtah = citiesPerState.get("Utah");
		System.out.println(citiesOfUtah);
		
		Map<String, Long> numberOfCitiesPerState = 
			cities.stream()
			.collect(
					Collectors.groupingBy(
							city -> city.getState(), Collectors.counting()
							)
					);
		System.out.println("# cities in Utah: " + numberOfCitiesPerState.get("Utah"));
		
		Map.Entry<String, Long> stateWithMostCities = 
		numberOfCitiesPerState.entrySet().stream() // Stream Map.Entry<String, Long>
			// .max(Comparator.comparing(Entry::getValue))
			.max(Entry.comparingByValue())
			.orElseThrow();
		System.out.println(stateWithMostCities);
		
		int populationOfUtah = 
		citiesPerState.get("Utah").stream()
//			.mapToInt(city -> city.getPopulation())
//			.sum();
			.collect(Collectors.summingInt(city -> city.getPopulation()));
		System.out.println("Population of Utah = " + populationOfUtah);
		
		Map<String, Integer> populationOfCitiesPerState = 
				cities.stream()
				.collect(
						Collectors.groupingBy(
								city -> city.getState(), 
								Collectors.summingInt(city -> city.getPopulation())
								)
						);
		
		System.out.println("Population of Utah = " + populationOfCitiesPerState.get("Utah"));
		
		
		Map.Entry<String, Integer> stateWithTheMostPeople = 
		populationOfCitiesPerState.entrySet().stream() // Stream Map.Entry<String, Integer>
			// .max(Comparator.comparing(Entry::getValue))
			.max(Entry.comparingByValue())
			.orElseThrow();
		
		System.out.println(stateWithTheMostPeople);
	}
}






