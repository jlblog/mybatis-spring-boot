package me.jlblog.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class StreamTest {

	private List<String> programing = null;

	@Before
	public void init() {
		programing = Arrays.asList("Javascript", "C", "C++", "Nodejs", "Java", "Oracle", "MariaDB", "PHP", "ASP");
	}
	
	@Test
	public void parallelTest() {
		double start = System.nanoTime();
		Stream.of("b", "d", "a", "c", "e").sorted().filter(s -> {
			System.out.println("Filter: " + s);
			return !"d".equals(s);
		})
		.parallel()
		.map(s -> {
			System.out.println("Map: " + s);
			return s+= s;
		})
		.forEach(System.out::println);
		
		double duration = (System.nanoTime() - start);
		System.out.println(duration + " milliseconds");
				
	}
	
	// @Test
	public void standardStreamTest() {
		IntStream is = IntStream.of(1, 2, 3, 4, 5);
		IntStream zeroToNinetyNine = IntStream.range(0, 100); // 0 ~ 99
		// zeroToNinetyNine.forEach(System.out::println);

		IntStream zeroToHundred = IntStream.rangeClosed(0, 100);// 0 ~ 100
		// zeroToHundred.forEach(System.out::println);
	}

	/**
	 * 결과 모우기 
	 * collect
	 * toList(), toSet()
	 * joining(separator, prefix, sufifx)
	 */
	//@Test
	public void collectTest2() {
		//List<String> result = programing.stream().collect(Collectors.toList()); 		
		
		//Iterator<String> it = result.iterator();
		//while(it.hasNext()) {
		//	System.out.print(it.next() + " ");
		//}
		
		String result = programing.stream().collect(Collectors.joining(", ", "This is ", "."));
		System.out.println(result);
	}
	
	/**
	 * 리덕션 연산
	 * 합계를 계산하거나 스트림의 요소들을 다른 방법으로 결합하고 싶은 경우
	 */
	//@Test
	public void reduceTest() {
		// Stream<Integer> values = Stream.of(1, 2, 3);
		// Optional<Integer> sum = values.reduce(Integer::sum);
		// assertSame(6, sum.get());

		int total = IntStream.of(1, 2, 3, 4, 5, 6).reduce(4, Integer::sum);
		assertSame(25, total);
	}
	
	/**
	 * 옵션값 생성 
	 */
	//@Test
	public void optionalTest2() {
		Stream<String> test = programing.stream();
		List<String> result = new ArrayList<String>();
		Optional<Boolean> present = test.findAny().map(result::add);
		for (String string : result) {
			System.out.println(string + ", " + present );
		}
		
		System.out.println(StreamTest.inverse(0.0));
	}
	
	public static Optional<Double> inverse(Double x) {
		return x == 0 ? Optional.empty() : Optional.of(1 / x);
	}
	
	
	/**
	 * findFirst : 비어있지 않은 컬렉션의 첫번째 값을 리턴
	 * findAny : 첫 번째 값은 물론 어떤 일치 결과든 괜찮다면 사용
	 */
	//@Test
	public void findTest() {
		Optional<String> startsWithQ = programing.stream().filter(s -> s.startsWith("J")).findFirst();
		System.out.println("findFirst : " + startsWithQ.get());
		
		Optional<String> startsWithQ2 = programing.parallelStream().filter(s -> s.startsWith("J")).findAny();
		System.out.println("findAny : " + startsWithQ2.get());
		
		boolean aWordStartsWithQ = programing.parallelStream().anyMatch(s -> s.startsWith("v", 2));
		assertEquals(true, aWordStartsWithQ);
	}
	
	/**
	 * Optional 단순한 컨테이너
	 * T 타입의 value나 null을 담는다.
	 */
	//@Test
	public void optionalTest() {
		Optional< String > fullName = Optional.ofNullable(null);
		System.out.println( "Full Name is set? " + fullName.isPresent() );       
		System.out.println( "Full Name: " + fullName.orElseGet( () -> "[none]" ) );
		System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );

	}
	
	/**
	 * peek 중간처리 메서드 (디버깅용)
	 */
	//@Test
	public void peekTest() {
		Stream.iterate(BigDecimal.ZERO, num -> num.add(BigDecimal.ONE))
			.peek(num -> System.out.println(num))
			.limit(5);
	}
	
	/**
	 * 중복제거, 정렬(글자길이로 내림차순)
	 */
	//@Test
	public void distinctTest() {
		List<String> addPro = Arrays.asList("Unity", "Android", "C++", "C#", "Java");
		
		Stream.concat(programing.stream(), addPro.stream()).distinct()
		.sorted(Comparator.comparing(String::length).reversed())
		.forEach(str -> System.out.println("::" + str));
	}
	
	/**
	 * 스트림 결합 concat 메서드
	 */
	//@Test
	public void concatTest() {
		Stream<String> sm1 = Stream.of("P", "l", "a", "y", "D");
		Stream<String> sm2 = Stream.of(" World!!");
		Stream.concat(sm1, sm2).forEach(System.out::print);
		
		Stream<Character> combined = Stream.concat(characterStream("TEST"), characterStream("CONCAT")); 
		combined.forEach(System.out::print);
	}
	
	/**
	 * 무한 스트림 생성, iterate 메서드
	 */
	//@Test
	public void iterateTest() {
		assertEquals(7, 
		Stream.iterate(BigDecimal.ZERO, num -> num.add(BigDecimal.ONE))
			.skip(1)
			.limit(7)
			.count());
	}
	
	/**
	 * 무한스트림 생성
	 */
	//@Test
	public void generateTest() {
		Stream.generate(Math::random).limit(6).map(num -> num * 45).map(Math::round).sorted()
			.forEach(num -> System.out.print("<" + num + ">"));
		
	}
	
	//@Test
	public void flatMapTest() {
		programing.stream()
			.filter(str -> str.length() == 3)
			.flatMap(str -> {
				List<String> result = new ArrayList<>();
				for(int i=0; i < str.length(); i++) {
					result.add(str.substring(i, i + 1));
				}
				return result.stream();
			}).forEach(str -> System.out.print("[" + str + "]"));
	}
	
	//@Test
	public void toUpperCseTest() {
		Stream<String> strs = programing.stream().map(String::toUpperCase).filter(str -> str.equals("JAVA"));
		
		Iterator<String> it = strs.iterator();
		while(it.hasNext()) {
			assertEquals("JAVA", it.next());
		}
	}
	
	//@Test
	public void filterTest3() {
		Stream<String> results = programing.stream().filter(str -> str.length() > 3);
		assertEquals("Optional[Javascript]", results.findFirst().toString());
	}
	
	//@Test
	public void filterTest() {
		long count = programing.stream().filter(str -> (str.indexOf("Java") > -1)).count();
		assertEquals(2, count);
	}

	//@Test
	public void filterTest2() {
		long count = programing.parallelStream().filter(str -> (str.indexOf("C") > -1)).count();
		assertEquals(2, count);
	}

	//@Test
	public void CollectTest() {
		String contents = programing.stream().collect(Collectors.joining("/"));
		assertEquals("Javascript/C/C++/Nodejs/Java/Oracle/MariaDB/PHP/ASP", contents);
		
		Stream<String> splitStr = Stream.of(contents.split("/"));
		//splitStr.forEach(str -> System.out.print("<" + str + "> "));
	}
	
	public static Stream<Character> characterStream(String s) {
		List<Character> result = new ArrayList<>();
		for (char c : s.toCharArray()) {
			result.add(c);
		}
		return result.stream();
	}

}
