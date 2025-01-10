# PaintBlend API

PaintBlend API is a color management service that converts colors from HEX format to RGB. When the color is not stored in the in-memory database, the service dynamically generates the RGB values. Additionally, the RYB values are only available when the color is stored in the in-memory database; otherwise, they are returned as null.

## Prerequisites

Ensure you have the following installed before starting:

- JDK 21 or higher
- Gradle 8.9 or higher

## Local Environment Setup

### 1. In-Memory Data Storage

The service uses an in-memory `Map` to store HEX colors and their corresponding RGB representations. This is a temporary solution that does not require additional setup. Please note that the data will not persist between application restarts.

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

- **Description**: Retrieves color data for the specified HEX code. If the color is stored in the in-memory database, it returns both RGB and RYB values. If the color is not found, it generates the RGB values dynamically, adds them to the in-memory storage, and returns the color with RGB and RYB values as `null`.
- **Parameters**:
    - `hex` (required): The HEX color code (without the `#` prefix).
- **Example Response when the color is in the database**:
  ```json
  {
    "hex": "008000",
    "rgb": {
      "red": 0,
      "green": 128,
      "blue": 0
    },
    "ryb": {
      "red": 0,
      "yellow": 128,
      "blue": 0
    }
  }
  ```
- **Example Response when the color is not in the database**:
  ```json
  {
    "hex": "D47F2F",
    "rgb": {
      "red": 212,
      "green": 127,
      "blue": 47
    },
    "ryb": null
  }
  ```  

## Key Dependencies

- **Spring Web:** The main dependency for building the service with Spring Boot.

## Additional Documentation

For more information, refer to the following resources:

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Gradle Documentation](https://docs.gradle.org/current/userguide/userguide.html)
- [JDK Documentation](https://docs.oracle.com/en/java/)

---