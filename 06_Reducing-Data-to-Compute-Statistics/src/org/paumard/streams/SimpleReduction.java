package org.paumard.streams;

import java.util.List;
import java.util.Optional;

public class SimpleReduction {

	public static void main(String[] args) {
		
		List<Integer> ints = List.of(1, 1, 1, 1);
		
		int sum = ints.stream().reduce(0, (i1, i2) -> i1 + i2);
		
		System.out.println("sum = " + sum);
	}
}
