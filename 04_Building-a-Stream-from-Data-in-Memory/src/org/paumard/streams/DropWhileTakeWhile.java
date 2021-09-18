package org.paumard.streams;

import java.util.ArrayList;
import java.util.stream.Stream;

public class DropWhileTakeWhile {

	public static void main(String[] args) {
		
		Class<?> clzz = ArrayList.class;
		
		clzz.getSuperclass();
		
//		Stream<Class<?>> classes = 
		Stream.<Class<?>>iterate(clzz, c -> c.getSuperclass())
			.takeWhile(c -> c != null)
			.forEach(System.out::println);
	
	}

}










