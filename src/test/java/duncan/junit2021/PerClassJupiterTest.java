package duncan.junit2021;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Demo of Lifecycle.PER_CLASS")
class PerClassJupiterTest {

	private int x;

	@BeforeAll
	void stepDefinition() {
		x = 1;
	}

	@DisplayName("Given x has been initialized to 1")
	@Nested
	@TestInstance(TestInstance.Lifecycle.PER_CLASS)
	class givenXHasBeenInitializedTo1 {

		@DisplayName("When 3 is added to x")
		@Nested
		@TestInstance(TestInstance.Lifecycle.PER_CLASS)
		class when3IsAddedToX {

			@BeforeEach
			void stepDefinition() {
				x = x + 3;
			}

			@DisplayName("Then x is 4")
			@Test
			void thenXIs4() {
				assertEquals(4, x);
			}
		}

		@DisplayName("When 4 is added to x")
		@Nested
		@TestInstance(TestInstance.Lifecycle.PER_CLASS)
		class when4isAddedToX {

			@BeforeEach
			void stepDefinition() {
				x = x + 4;
			}

			@DisplayName("Then x is 8")
			@Test
			void thenXIs5() {
				assertEquals(8, x);
			}
		}
	}

	// Transient Fresh
	// Persistence Fresh
	// Persistence Shared

	// By default, JUnit creates a new instance of the
	// test class for each test, but you can override this.
	// @TestInstance(TestInstance.Lifecycle.PER_METHOD)
	// @TestInstance(TestInstance.Lifecycle.PER_CLASS)

}
