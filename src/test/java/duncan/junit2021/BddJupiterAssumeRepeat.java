package duncan.junit2021;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DisplayName("Demo of Jupiter's assumeTrue, assumingThat, and RepeatedTest")
class BddJupiterAssumeRepeat {
	private class Person {
		public String firstName;
		public String lastName;

		public Person(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
	}

	private Boolean flag = true;

	private List<Person> people = new LinkedList<>();

	@DisplayName("Given the linked list has been initialized")
	@Nested
	class givenTheLinkedListHasBeenInitialized {
		@BeforeEach
		void stepDefinition() {
			people.add(new Person("Michael", "Bittner"));
			people.add(new Person("Travis", "Bush"));
			people.add(new Person("Stephanie", "Thompson"));
		}

		@DisplayName("When two people are added to the linked list")
		@Nested
		class whenTwoPeopleAreAddedToTheLinkedList {
			@BeforeEach
			void stepDefinition() {
				people.add(new Person("Erik", "Himmler"));
				people.add(new Person("Andrew", "Bormann"));
			}

			@DisplayName("Then five people are in the linked list")
			@RepeatedTest(10)
			void thenFivePeopleAreInTheLinkedList() {
				assumeTrue(flag);
				assertEquals(5, people.size());
			}

			@DisplayName("Then the list contains Andrew Bormann")
			@RepeatedTest(value = 5, name = "{displayName} -> {currentRepetition}/{totalRepetitions}")
			void thenTheListContainsAndrewBormann() {
				assumingThat(flag.equals(true), () -> {
					assertEquals(1, people.stream().filter(x -> {
						return x.firstName.equals("Andrew") && x.lastName.equals("Bormann");
					}).count());
				});
			}
		}

	}

}
