package com.freetmp.investigate.java8;

import java.lang.annotation.Repeatable;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

interface Formula {
	
	double calculate(int a);
	
	default double sqrt(int a){
		return Math.sqrt(a);
	}
}

@FunctionalInterface
interface Converter<F,T>{
	T convert(F f);
}

@interface Hints {
    Hint[] value();
}

@Repeatable(Hints.class)
@interface Hint {
    String value();
}

@Hint("hint1")
@Hint("hint2")
class Person {
    String firstName;
    String lastName;

    Person() {}

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
    
}

interface PersonFactory<P extends Person>{
	P create(String firstName, String lastName);
}


public class NewFeatureTest {

	public void interfaceDefaultMethod(){
		Formula formula = new Formula() {
			
			@Override
			public double calculate(int a) {
				return sqrt(a * 100);
			}
		};
		
		print(formula.calculate(100));
		print(formula.sqrt(16));
	}
	
	public void lambda(){
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		
		Collections.sort(names, (a,b)->b.compareTo(a));
		print(names);
	}
	
	public void functionalInterface(){
		Converter<String,Integer> converter = (from) -> Integer.valueOf(from);
		Integer convertered = converter.convert("123");
		print(convertered);
	}
	
	public void methodReference(){
		Converter<String,Integer> converter = Integer::valueOf;
		Integer convertered = converter.convert("123");
		print(convertered);		
	}
	
	public void constructReference(){
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");
		print(person);
	}
	
	public void accessLocals(){
		final int num = 1;
		Converter<Integer,String> stringConverter = (from) -> String.valueOf(from + num);
		print(stringConverter.convert(2));
	}
	
	public void supplier(){
		Supplier<Person> personSupplier = Person::new;
		print(personSupplier.get());
	}
	
	public void consumer(){
		Consumer<Person> greeter = (p) -> {print("Hello " + p.firstName);};
		greeter.accept(new Person("Luke", "Skywalker"));
	}
	
	public void comparator(){
		Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

		Person p1 = new Person("John", "Doe");
		Person p2 = new Person("Alice", "Wonderland");
		
		print(comparator.compare(p1, p2));
		print(comparator.reversed().compare(p1, p2));
	}
	
	public void function(){
		Function<String, Integer> stoi = Integer::valueOf;
		Function<String, String> itos = stoi.andThen(String::valueOf);
		print(itos.apply("123"));
	}
	
	public void optional(){
		Optional<String> optional = Optional.of("bam");
		print(optional.isPresent());
		print(optional.get());
		print(optional.orElse("what"));
		optional.ifPresent((s)->print(s.charAt(0)));
	}
	
	public void stream(){
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
		
		stringCollection.stream().sorted().filter((s)->s.startsWith("a")).forEach(NewFeatureTest::print);
		
		stringCollection.stream().map(String::toUpperCase).sorted((a,b)->b.compareTo(a)).forEach(System.out::println);
		
		boolean anyStartsWithA = stringCollection.stream().anyMatch((s)->s.startsWith("a"));
		print(anyStartsWithA);
		
		boolean allStartsWithA = stringCollection.stream().allMatch((s)->s.startsWith("a"));
		print(allStartsWithA);
		
		boolean noneStartsWithZ = stringCollection.stream().noneMatch((s)->s.startsWith("z"));
		print(noneStartsWithZ);
		
		print(stringCollection.stream().filter((s)->s.startsWith("b")).count());
		
		Optional<String> reduced = stringCollection.stream().sorted().reduce((s1,s2) -> s1 + "#" + s2);
		
		reduced.ifPresent(System.out::println);
		
		print(stringCollection);
	}
	
	public void parallelOrNot(){
		int max = 1000000;
		List<String> values = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
		    UUID uuid = UUID.randomUUID();
		    values.add(uuid.toString());
		}
		
		long t0 = System.nanoTime();

		long count = values.stream().sorted().count();
		System.out.println(count);

		long t1 = System.nanoTime();
		
		long millis = TimeUnit.NANOSECONDS.toMillis(t1-t0);
		print(String.format("sequential sort took: %d ms", millis));
		
		t0 = System.nanoTime();
		count = values.parallelStream().sorted().count();
		print(count);
		t1 = System.nanoTime();
		
		millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		print(String.format("parallel sort took: %d ms", millis));
	}
	
	public void map(){
		Map<Integer, String> map = new HashMap<>();

		for (int i = 0; i < 10; i++) {
		    map.putIfAbsent(i, "val" + i);
		}

		map.forEach((id, val) -> System.out.println(val));
		
		map.computeIfPresent(3, (num,val) -> val + num);
		print(map.get(3));
		
		map.computeIfPresent(9, (num,val) -> null);
		print(map.containsKey(9));
		
		map.computeIfAbsent(23, num -> "val" + num);
		print(map.containsKey(23));
		
		map.computeIfAbsent(3, num -> "bam");
		print(map.get(3));
	}
	
	public void time(){
		Clock clock = Clock.systemDefaultZone();
		long millis = clock.millis();
		print(millis);
		Instant instant = clock.instant();
		print(instant);
		Date legacyDate = Date.from(instant);   // legacy java.util.Date
		print(legacyDate);
		
		System.out.println(ZoneId.getAvailableZoneIds());
		// prints all available timezone ids

		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		System.out.println(zone1.getRules());
		System.out.println(zone2.getRules());
		
		LocalTime now1 = LocalTime.now(zone1);
		LocalTime now2 = LocalTime.now(zone2);

		System.out.println(now1.isBefore(now2));  // false

		long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
		long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

		System.out.println(hoursBetween);       // -3
		System.out.println(minutesBetween);     // -239
		
		LocalTime late = LocalTime.of(23, 59, 59);
		System.out.println(late);       // 23:59:59

		DateTimeFormatter germanFormatter =
		    DateTimeFormatter
		        .ofLocalizedTime(FormatStyle.SHORT)
		        .withLocale(Locale.GERMAN);

		LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
		System.out.println(leetTime);   // 13:37
		
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		LocalDate yesterday = tomorrow.minusDays(2);
		print(yesterday);

		LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
		DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
		System.out.println(dayOfWeek);    // FRIDAY
		
		LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

		dayOfWeek = sylvester.getDayOfWeek();
		System.out.println(dayOfWeek);      // WEDNESDAY

		Month month = sylvester.getMonth();
		System.out.println(month);          // DECEMBER

		long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
		System.out.println(minuteOfDay);    // 1439
		
		instant = sylvester
		        .atZone(ZoneId.systemDefault())
		        .toInstant();

		legacyDate = Date.from(instant);
		System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd, yyyy - HH:mm");

		LocalDateTime parsed = LocalDateTime.parse("11 03, 2014 - 07:13", formatter);
		String string = formatter.format(parsed);
		System.out.println(string);     // Nov 03, 2014 - 07:13
		
	}
	
	public void annotations(){
		Hint hint = Person.class.getAnnotation(Hint.class);
		System.out.println(hint);                   // null

/*		Hints hints1 = Person.class.getAnnotation(Hints.class);
		System.out.println(hints1.value().length);  // 2
*/
		Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
		System.out.println(hints2.length);          // 2
	}
	
	public static <T> void print(T t){
		System.out.println(t);
	}
	
	public static void main(String...args){
		NewFeatureTest nf = new NewFeatureTest();
		nf.interfaceDefaultMethod();
		
		nf.lambda();
		
		nf.functionalInterface();
		
		nf.methodReference();
		
		nf.constructReference();
		
		nf.accessLocals();
		
		nf.supplier();
		
		nf.consumer();
		
		nf.function();
		
		nf.comparator();
		
		nf.optional();
		
		nf.stream();
		
		nf.map();
		
		nf.parallelOrNot();
		
		nf.time();
		
		nf.annotations();
	}
}
