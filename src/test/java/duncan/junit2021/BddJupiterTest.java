package duncan.junit2021;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DisplayName("Jupiter BDD Simulator")
class BddJupiterTest {

	static Map<String, Integer> dict = new HashMap<>();

	@BeforeEach
	void stepDefinition() {
		dict.put("One", 1);
		dict.put("Two", 2);
		dict.put("Three", 3);
		dict.put("Four", 5);
		dict.put("Five", 6);
	}

	@DisplayName("Given the dictionary has been initialized")
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_METHOD)
	class givenTheDictionaryHasBeenInitialized {

		@BeforeEach
		void stepDefinition() {
			dict.put("Six", 6);
		}

		@DisplayName("When I put six in the dictionary")
		@Nested
		@TestInstance(TestInstance.Lifecycle.PER_METHOD)
		class whenIPutSixInTheDictionary {

			@DisplayName("Then six should be in the dictionary")
			@Test
			void thenSixShouldBeInTheDictionary() {
				assertNotNull(dict.get("Six"));
			}

			@DisplayName("Then seven should not be in the dictionary")
			@Test
			void thenSevenShouldNotBeInTheDictionary() {
				assertNull(dict.get("Seven"));
			}

		}
		
	}
}
