# PaintBlend API

PaintBlend API is a color management service that converts colors from HEX format to RGB. If a color is not stored in the database, the service dynamically generates the RGB and CMY values. Additionally, the RYB and `parts` values are only available when the color is stored in the database; otherwise, they are returned as `null`.

## Prerequisites

Ensure you have the following installed before starting:

- JDK 21 or higher
- Gradle 8.9 or higher

## Local Environment Setup

### 1. Database Configuration

The service uses an H2 database to store HEX colors and their corresponding RGB, RYB, CMY representations, and proportional components (`parts`). The database is embedded, so no additional setup is required.

### 2. Running the Service

To run the service locally:

1. Clone the repository and navigate to the project directory.
2. Start the service with the following command:

```bash
./gradlew bootRun
```

Alternatively, you can use the green run button in your IDE for the `PaintBlendApplication` (main) class.

The service will be available at http://localhost:8080/

### 3. Running Tests

The project includes basic unit tests to ensure the accuracy of business logic. You can run all tests using Gradle.

To run the tests:

```bash
./gradlew test
```

## API Endpoints

### `GET /color`

**Description**: Retrieves color data for the specified HEX code.
- Retrieves color data for the specified HEX code.
- If the color is not found, it generates the RGB and CMY values dynamically, adds them to the database, and returns the color with RYB and `parts` as null.

**Parameters**:
- `hex` (required): The HEX color code (without the `#` prefix).

**Example Responses**

**When the color is in the database**:
  ```json
  {
    "hex": "0000FF",
    "rgb": {
      "red": 0,
      "green": 0,
      "blue": 255
    },
    "ryb": {
      "red": 0,
      "yellow": 0,
      "blue": 255
    },
    "cmy": {
      "cyan": 1.0,
      "magenta": 1.0,
      "yellow": 0.0
    },
    "parts": {
      "cyanParts": 1,
      "magentaParts": 1,
      "yellowParts": 0
    }
}
  ```
**When the color is not in the database**:
  ```json
  {
    "hex": "D47F2F",
    "rgb": {
      "red": 212,
      "green": 127,
      "blue": 47
    },
    "ryb": null,
    "cmy": {
      "cyan": 0.1686,
      "magenta": 0.502,
      "yellow": 0.8157
    },
    "parts": null
  }
  ```  

## Key Dependencies

- **Spring Web:** The main dependency for building the service with Spring Boot.
- **H2 Database:** An embedded relational database for storing colors persistently.
- **Spring Data JDBC:** Used for interaction with the H2 database.

## Additional Documentation

For more information, refer to the following resources:

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Gradle Documentation](https://docs.gradle.org/current/userguide/userguide.html)
- [H2 Database Documentation](https://www.h2database.com/html/main.html)
- [Spring Data JDBC Documentation](https://spring.io/projects/spring-data-jdbc)
- [JDK Documentation](https://docs.oracle.com/en/java/)

---
