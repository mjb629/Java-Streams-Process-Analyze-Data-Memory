package org.paumard.streams;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StreamFromRegularExpression {

	public static void main(String[] args) {

		String sentence = "the quick brown fox jumps over the lazy dog";

		String[] words = sentence.split(" ");
		Stream<String> wordsStream = Arrays.stream(words);
		
		long count = wordsStream.count();
		System.out.println("Count = " + count);
	
		Pattern pattern = Pattern.compile(" ");
		long count2 = pattern.splitAsStream(sentence).count();
		System.out.println("Count 2 = " + count2);
	}
}









