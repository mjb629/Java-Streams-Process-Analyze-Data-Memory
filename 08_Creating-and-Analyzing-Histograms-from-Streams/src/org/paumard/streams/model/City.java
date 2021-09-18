package org.paumard.streams.model;

public class City {

	private String name;
	private String state;
	private int population;
	private double area;
	
	
	public City(String name, String state, int population, double area) {
		this.name = name;
		this.state = state;
		this.population = population;
		this.area = area;
	}

	public String getName() {
		return name;
	}
	
	public String getState() {
		return state;
	}

	public int getPopulation() {
		return population;
	}

	public double getArea() {
		return area;
	}
	
	public double getDensity() {
		return population / area;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", state=" + state + "]";
	}
}
