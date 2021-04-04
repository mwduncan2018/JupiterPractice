package duncan.junit2021;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.function.ThrowingConsumer;

import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DisplayName("Demo of Jupiter's TestFactory")
class JupiterTestFactory {
	private Boolean flag;
	private List<Pet> pets;

	private class Pet {
		public String name;
		public Integer age;
		public Supplier<Boolean> isOld = () -> age > 10;

		public Pet(String name, Integer age) {
			this.name = name;
			this.age = age;
		}

	}

	@BeforeEach
	void initialize() {
		System.out.println("Initializing...");
		pets = new ArrayList<Pet>();
		pets.add(new Pet("Hair Bear", 2));
		pets.add(new Pet("McKinley", 3));
		pets.add(new Pet("Badger", 5));
		pets.add(new Pet("Boxer", 5));
	}

	@DisplayName("Dynamic Demo")
	@TestFactory
	Stream<DynamicTest> dynamicDemo() {
		return IntStream.range(1000, 9999).limit(3)
				.mapToObj(x -> dynamicTest("Dyno Demo #" + String.valueOf(x) + "... ", () -> {
					Pet pet = new Pet("Fluffy" + String.valueOf(x), x);
					System.out.println(pet.name + " - " + String.valueOf(pet.age));
					assertEquals(x, pet.age);
				}));
	}

	@DisplayName("Dyanamic Random Demo")
	@TestFactory
	Stream<DynamicTest> dynamicRandomeDemo() {
		return new Random().ints(1000, 9999).limit(5)
				.mapToObj(x -> dynamicTest("Dyno Random Demo #" + String.valueOf(x), () -> {
					Pet pet = new Pet("Harry" + String.valueOf(x), x);
					System.out.println(pet.name + " ? " + String.valueOf(pet.age));
					assertEquals(x, pet.age);
				}));
	}

	@DisplayName("Dynamic Stream Demo")
	@TestFactory
	Stream<DynamicTest> dynamicStreamDemo() {
		return DynamicTest.stream(new Random().ints(1000, 9999).limit(5).iterator(), (x) -> {
			return "Dyno Stream Demo #" + x;
		}, (x) -> {
			Pet pet = new Pet("Cloud" + String.valueOf(x), x);
			System.out.println(pet.name + " & " + String.valueOf(pet.age));
			assertEquals(x, pet.age);
		});
	}

	@DisplayName("Pets are old for ages greater than 10")
	@TestFactory
	Stream<DynamicTest> dynamicStreamPractice() {
		Iterator<Integer> i = new Random().ints(11, 9999).limit(10).iterator();
		Function<Integer, String> n = (input) -> {
			return "Dynamic Stream Practice #" + String.valueOf(input);
		};
		ThrowingConsumer<Integer> x = (input) -> {
			Pet pet = new Pet("Pet" + String.valueOf(input), input);
			System.out.println(pet.name + " * " + String.valueOf(pet.age));
			assertTrue(pet.isOld.get());
		};
		return DynamicTest.stream(i, n, x);
	}
}
