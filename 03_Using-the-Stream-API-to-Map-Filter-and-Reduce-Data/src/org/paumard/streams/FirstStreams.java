package org.paumard.streams;

import java.util.List;
import java.util.stream.Stream;

import org.paumard.streams.model.Person;

public class FirstStreams {
	
	public static void main(String[] args) {
		
		Person p01 = new Person("Paul", 25);
		Person p02 = new Person("Sarah", 27);
		Person p03 = new Person("James", 31);
		Person p04 = new Person("Julie", 25);
		Person p05 = new Person("Charles", 22);
		Person p06 = new Person("Charlotte", 31);
		Person p07 = new Person("Ann", 27);
		Person p08 = new Person("Boris", 29);
		Person p09 = new Person("Emily", 34);
		Person p10 = new Person("", 34);
	
		List<Person> people = List.of(p01, p02, p03, p04, p05, p06, p07, p08, p09, p10);
		
		long countEmptyNames = 
				people.stream()
					.map(p -> p.getName())
					.filter(name -> name.isEmpty())
					.count();
		
		Stream<Person> stream2 = people.stream();
		Stream<String> nameStream2 = stream2.map(p -> p.getName());
		Stream<String> nonEmptyNames = nameStream2.filter(name -> !name.isEmpty());
		long countNonEmptyNames = nonEmptyNames.count();
		
		System.out.println("Empty names = " + countEmptyNames);
		System.out.println("Non empty names = " + countNonEmptyNames);
		
		
	}
}
