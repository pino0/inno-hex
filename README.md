Price - Hexagonal Architecture with DDD

This is a demo application using Hexagonal Architecture with Domain-Driven Design (DDD) to maintain a clear
and decoupled structure that facilitates code evolution and maintenance.


Technologies Used

    Java 8
    Spring Boot
    Maven
    H2 Database (in-memory database)
    JUnit 5 (for unit testing)
    Mockito (for integration testing)
    Spring Boot Test (for integration testing)
    Jackson (for JSON serialization/deserialization)

Directory Structure

The application follows a clear and organized directory structure:

    src/main/java/com/it/innoit: Contains the application's source code.
        application: Contains the application services and logic.
        domain: Contains the domain models, aggregates, and repository interfaces.
        infrastructure: Contains the concrete implementation of repositories and other infrastructure components.
            adapters: Contains the adapters that interact with the outside world, such as REST controllers and repositories.
    src/main/resources: Contains configuration files, such as application.properties.
    src/test/java/com/it/innoit: Contains unit and integration tests.
        application: Tests for application services.
        domain: Tests for domain models and aggregates.
        adapters: Tests for REST controllers.
        infrastructure: Tests for repositories and infrastructure implementations.

Configuration

To run the application, make sure you have Java 8 and Maven installed on your system. The application uses Spring Boot's
integrated web server, so there's no need to install an external server.

How to Run the Application

    Clone this repository to your local machine.
    From the command line, navigate to the project's root directory.
    Run the following command to compile and run the application:

            mvn spring-boot:run

    Once the application is up and running, you can access the following endpoints:

    GET /prices/{brandId}/{productId}/{dateTime}: Gets the applicable price for a brand, product, and given date.

        Example: http://localhost:8080/prices?dateTime=2020-06-16T21:00:00&productId=35455&brandId=1
        OutPut: {"brandId":1,"productId":35455,"startDate":"2020-06-15T16:00:00","endDate":"2020-12-31T23:59:59","priceList":4,"priority":1,"price":38.95,"currency":"EUR"}

    GET /h2-console Url to the H2 console you can set the JDBC URL to jdbc:h2:mem:testdb

        Example: http://localhost:8080/h2-console

Integration Tests

Integration tests have been developed to verify the functionality of the REST endpoint that provides the price service.
These tests are executed against an in-memory database (H2) that is initialized with the data provided as an example.
The tests have been implemented using the JUnit 5 and Spring Boot Test libraries.

Test Cases

    Query at 10:00 AM on day 14 for product 35455 and brand ZARA (Brand ID 1):
    Verifies that the service correctly returns the applicable price for product ID 35455 in the brand ZARA at 10:00 AM on day 14 of June.

    Query at 4:00 PM on day 14 for product 35455 and brand ZARA (Brand ID 1):
    Verifies that the service correctly returns the applicable price for product ID 35455 in the brand ZARA at 4:00 PM on day 14 of June.

    Query at 9:00 PM on day 14 for product 35455 and brand ZARA (Brand ID 1):
    Verifies that the service correctly returns the applicable price for product ID 35455 in the brand ZARA at 9:00 PM on day 14 of June.

    Query at 10:00 AM on day 15 for product 35455 and brand ZARA (Brand ID 1):
    Verifies that the service correctly returns the applicable price for product ID 35455 in the brand ZARA at 10:00 AM on day 15 of June.

    Query at 9:00 PM on day 16 for product 35455 and brand ZARA (Brand ID 1):
    Verifies that the service correctly returns the applicable price for product ID 35455 in the brand ZARA at 9:00 PM on day 16 of June.

If all tests pass successfully, you will see a success message in the command-line output. If any test fails, you will receive an error
message indicating which test failed and the reason for the failure.

How to Run Tests

To run the tests, you can use the following command:

            mvn test
