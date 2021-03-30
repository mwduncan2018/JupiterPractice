# JupiterPractice

### By default, JUnit creates a new instance of the test class for each test, but you can override this.
##### @TestInstance(TestInstance.Lifecycle.PER_METHOD)
##### @TestInstance(TestInstance.Lifecycle.PER_CLASS)

### POM XML Stuff
##### The API is needed for compile time for JUnit 5 (junit-jupiter-api)
##### The engine runs the tests (junit-jupiter-engine)
##### To run tests with Maven, you need the Surefire plugin
