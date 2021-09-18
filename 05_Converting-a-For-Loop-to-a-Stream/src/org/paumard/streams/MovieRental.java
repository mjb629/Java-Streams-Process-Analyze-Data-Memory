package org.paumard.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.paumard.streams.model.Rental;

public class MovieRental {

	public static void main(String[] args) {
		
		MovieRental movieRental = new MovieRental();
		movieRental.addRental("Blade Runner", 2);
		movieRental.addRental("Frozen", 3);
		movieRental.addRental("Star Wars", 1);
		
		String statement = movieRental.statement();
		System.out.println(statement);
	}

	private void addRental(String name, int daysRented) {
		Rental rental = new Rental(name, daysRented);
		this.rentals.add(rental);
	}

	private List<Rental> rentals = new ArrayList<>();
		
	public String statement() {
		double totalAmount = 
			rentals.stream()
				.mapToDouble(this::computeRentalAmount)
				.sum();
		
		int frequentRenterPoints = 
			rentals.stream()
				.mapToInt(this::getFrequentRenterPoints)
				.sum();

		String statement = composeHeader();
		statement +=
			rentals.stream()
				.map(this::computeStatementLine)
				.collect(Collectors.joining());

		statement += composeFooter(totalAmount, frequentRenterPoints);

		return statement;
	}

	private String composeFooter(double totalAmount, int frequentRenterPoints) {
		
		return "Total amount owed: " + totalAmount + 
				"\nFrequent renter points earned: " + frequentRenterPoints;
	}

	private String computeStatementLine(Rental rental) {
		
		return "\t" + rental.getName() + ": " + computeRentalAmount(rental) + "\n";
	}

	private int getFrequentRenterPoints(Rental rental) {
		return rental.getDaysRented() > 2 ? 2 : 1;
	}

	private double computeRentalAmount(Rental rental) {
		return 2.0 + rental.getDaysRented();
	}

	private String composeHeader() {
		return "Statement for the rental of " + rentals.size() + " movies\n";
	}
}
