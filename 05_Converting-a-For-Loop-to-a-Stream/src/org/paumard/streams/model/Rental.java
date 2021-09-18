package org.paumard.streams.model;

public class Rental {

	private String name;
	private int daysRented;
	
	public Rental(String name, int daysRented) {
		this.name = name;
		this.daysRented = daysRented;
	}

	
	
	public int getDaysRented() {
		return daysRented;
	}

	public String getName() {
		return this.name;
	}

}
