package me.jlblog.app;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class Java8Test {
	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> results = new ArrayList<>();
		for (T s : list) {
			if (p.test(s)) {
				results.add(s);
			}
		}

		return results;
	}
	
	public void predicateTest() {
		List<String> listOfStrings = Arrays.asList("test1", "test2", "test3");
		
		Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
		List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
		
		nonEmpty.forEach(str -> System.out.println(str));
		
		assertSame("test1", listOfStrings.get(0));
		assertSame("test2", listOfStrings.get(1));
		assertSame("test3", listOfStrings.get(2));
	}
	
	@Test
	public void streamTest() {
		Stream<String> words = Stream.of("gently", "down", "the", "stream");
		
//		Stream<String> filter = words.filter(str -> str.indexOf("t") > -1);
		Stream<Double> stream = Stream.generate(Math::random);
		stream.limit(6).map(num -> num*45).map(Math::round).sorted().forEach(str -> System.out.print(str + " "));
//		Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
//		Stream<String> lowercaseWords = words.map(String::toUpperCase);
//		Stream<Character> chars = lowercaseWords.map(s -> s.charAt(0));
//		lowercaseWords.forEach(System.out::println);
//		Stream<Stream<Character>> result = words.map(w -> characterStream(w));
		
		
	}
	
	public static Stream<Character> characterStream(String s) {
		List<Character> result = new ArrayList<>();
		for (char c : s.toCharArray()) {
			result.add(c);
		}
		return result.stream();
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
