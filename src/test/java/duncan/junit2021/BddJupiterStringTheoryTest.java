package duncan.junit2021;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DisplayName("Jupiter String Theory")
class BddJupiterStringTheoryTest {
	private List<String> strList;

	@DisplayName("Given the list has been initialized")
	@Nested
	class givenTheListHasBeenInitialized {
		@BeforeEach
		void stepDefinition() {
			strList = new ArrayList<String>();
			strList.add("A");
			strList.add("B");
			strList.add("C");
			strList.add("D");
			strList.add("E");
		}

		@DisplayName("When I try to access an index greater than the size of the list, Then an exception is thrown")
		@Test
		void whenITryToAccessAnIndexGreaterThanTheSizeOfTheList() {
			Assertions.assertThrows(Exception.class, () -> {
				strList.get(1000);
			});
		}

		@DisplayName("When I add one hundred million strings to the list, Then the operation is completed in less than 60 seconds")
		@Test
		void whenIAddOneMillionStringsToTheList() {
			Assertions.assertTimeout(Duration.ofSeconds(10), () -> {
				IntStream.range(0, 99999999).forEach(x -> {
					strList.add(String.valueOf(x));
				});
			});

		}

		@DisplayName("When I add F to the list")
		@Nested
		class whenIAddFToTheList {
			@BeforeEach
			void stepDefinition() {
				strList.add("F");
			}

			@DisplayName("Then the list contains 6 strings")
			@Test
			void thenTheListContains6Strings() {
				assertTrue(strList.size() == 6, () -> {
					return "The list did not contain 6 strings";
				});
			}

			@DisplayName("Then the list contains the correct characters")
			@Test
			void thenTheListContainsTheCorrectCharacters() {
				assertAll("Then the list contains the correct characters",
						() -> assertTrue(strList.stream().filter(x -> x.equals("A")).count() > 0),
						() -> assertTrue(strList.stream().filter(x -> x.equals("B")).count() > 0),
						() -> assertTrue(strList.stream().filter(x -> x.equals("C")).count() > 0),
						() -> assertTrue(strList.stream().filter(x -> x.equals("D")).count() > 0),
						() -> assertTrue(strList.stream().filter(x -> x.equals("E")).count() > 0),
						() -> assertTrue(strList.stream().filter(x -> x.equals("F")).count() > 0),
						() -> assertTrue(strList.stream().filter(x -> x.equals("X")).count() == 0),
						() -> assertTrue(strList.stream().filter(x -> x.equals("Y")).count() == 0),
						() -> assertTrue(strList.stream().filter(x -> x.equals("Z")).count() == 0));
			}

			@DisplayName("Then Z is not in the list")
			@Test
			void thenZIsNotInTheList() {
				assertTrue(strList.stream().filter(x -> x.equals("Z")).count() == 0, () -> {
					return "Z should not be in the list";
				});
			}

		}
	}

}
