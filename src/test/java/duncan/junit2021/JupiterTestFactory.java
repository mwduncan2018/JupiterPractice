package duncan.junit2021;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;

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
		return IntStream.range(100, 150).limit(3)
				.mapToObj(x -> dynamicTest("Dyno Demo #" + String.valueOf(x) + "... ", () -> {
					Pet pet = new Pet("Fluffy" + String.valueOf(x), x);
					System.out.println(pet.name + " - " + String.valueOf(x));
					assertEquals(x, pet.age);
				}));
	}

	@DisplayName("Dyanamic Random Demo")
	@TestFactory
	Stream<DynamicTest> dynamicRandomeDemo() {
		Random rand = new Random();
		IntStream unlimitedIntStream = rand.ints(0,100000);
		IntStream limitedIntStream = unlimitedIntStream.limit(5000);
		return limitedIntStream.mapToObj(x -> dynamicTest("Dyno Random Demo #" + String.valueOf(x), () -> {
			Pet pet = new Pet("Harry" + String.valueOf(x), x);
			System.out.println(pet.name + " ? " + String.valueOf(x));
			assertEquals(x, pet.age);
		}));
	}

}
