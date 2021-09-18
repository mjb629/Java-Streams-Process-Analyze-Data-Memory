package org.paumard.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ComputeStatistics {

	public static void main(String[] args) {
		
		Function<String, String> lineToName = 
				line -> line.split(";")[1];
				
		// https://en.wikipedia.org/wiki/List_of_United_States_cities_by_population
		Path path = Path.of("data/cities.csv");
		Set<String> cities = null;
		try (Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1);) {
			
			cities = lines.skip(2)
				.map(lineToName)
				.collect(Collectors.toSet());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("# cities = " + cities.size());
		
		List<String> citiesWithA = cities.stream()
			.filter(city -> city.startsWith("A"))
			.collect(Collectors.toList());
		System.out.println(citiesWithA);
		
		String[] array = cities.stream().toArray(String[]::new);
		System.out.println("# array = " + array.length);
		
		String joined = 
			cities.stream()
				.filter(name -> name.length() == 4) 
				.collect(Collectors.joining(", ", "[", "]"));
		System.out.println(joined);
		
		String collect = Stream.<String>of("one")
				.collect(Collectors.joining(", ", "[", "]"));
		System.out.println("Collecting an empty stream: " + collect);
	}
}
