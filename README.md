# PaintBlend API

PaintBlend API is a service that provides color management functionality, converting colors from HEX format to RGB, and dynamically generating color proportions when not stored in the in-memory database.

## Prerequisites

Ensure you have the following installed before starting:

- JDK 21 or higher
- Gradle 8.9 or higher

## Local Environment Setup

### 1. In-Memory Data Storage

The service uses an in-memory `Map` to store HEX colors and their corresponding RGB representations. This is a temporary solution; no additional setup is required. The data will not persist between application restarts.

### 2. Running the Service

To run the service locally:

1. Clone the repository and navigate to the project directory.
2. Start the service with the following command:

```bash
./gradlew bootRun
```

Alternatively, you can use the green run button in your IDE for the PaintBlendApplication (main) class.

The service will be available at http://localhost:8080/

### 3. Running Tests

The project includes basic unit tests to ensure the accuracy of business logic. You can run all tests using Gradle.

To run the tests:

```bash
./gradlew test
```

## API Endpoints

### `GET /color`

- **Description**: Retrieves color data for the specified HEX code. If the color is not stored in the in-memory database, it generates the RGB values dynamically and adds them to the storage.
- **Parameters**:
    - `hex` (required): The HEX color code (without the `#` prefix).
- **Example Response**:
  ```json
  {
    "hex": "008000",
    "rgb": {
      "red": 0,
      "green": 128,
      "blue": 0
    }
  }

## Key Dependencies

- **Spring Web:** The main dependency for building the service with Spring Boot.

## Additional Documentation

For more information, refer to the following resources:

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Gradle Documentation](https://docs.gradle.org/current/userguide/userguide.html)
- [JDK Documentation](https://docs.oracle.com/en/java/)

---