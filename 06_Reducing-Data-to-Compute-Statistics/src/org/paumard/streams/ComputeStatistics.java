package org.paumard.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.DoubleSummaryStatistics;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;


public class ComputeStatistics {

	public static void main(String[] args) {
		
		String lineForNewYork = "1;New York; New York;8 336 817;780,9";
		ToDoubleFunction<String> lineToDensity = 
				line -> {
					
					String[] split = line.split(";");
					
					String populationAsString = split[3];
					populationAsString = populationAsString.replace(" ", "");
					int population = Integer.parseInt(populationAsString);
					
					String landAreaAsString = split[4];
					landAreaAsString = landAreaAsString.replace(" ", "").replace(',', '.');
					double landArea = Double.parseDouble(landAreaAsString);
					
					return population / landArea;
				};
				
		double density = lineToDensity.applyAsDouble(lineForNewYork);
		System.out.println("Density of New York = " + density);
		
		// https://en.wikipedia.org/wiki/List_of_United_States_cities_by_population
		Path path = Path.of("data/cities.csv");
		try (Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1);) {
			
			DoubleSummaryStatistics summaryStatistics = lines.skip(2)
				.mapToDouble(lineToDensity)
				.summaryStatistics();
			
			System.out.println("Stats = " + summaryStatistics);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
