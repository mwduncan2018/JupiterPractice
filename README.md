# JupiterPractice

#### By default, JUnit creates a new instance of the test class for each test, but you can override this.
@TestInstance(TestInstance.Lifecycle.PER_METHOD)\n
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
