# AI Agent Guidelines for Todo List API

## Project Context
This is a Spring Boot (v4.0.6) RESTful API implemented in Java 17, using Gradle as the build tool. The architecture follows a standard layered approach (Model, Repository, Service, Controller, Security) under the root package `orochi.todo_`.

## Architecture & Conventions
- **Layered Architecture:** 
  - `model/`: Entity classes mapped to database tables using Spring Data JPA. Use Lombok annotations (`@Data`, `@NoArgsConstructor`, etc.) to reduce boilerplate.
  - `repository/`: Spring Data JPA repositories (interfaces extending `JpaRepository`).
  - `service/`: Business logic layer. Services should handle transactional boundaries (`@Transactional`).
  - `controller/`: REST controllers (`@RestController`) that expose APIs. Follow RESTful URI naming conventions.
  - `security/`: Spring Security configurations.

- **Stack & Dependencies:**
  - Java 17
  - Spring Boot (Spring MVC, Data JPA, Security)
  - PostgreSQL (Runtime)
  - Lombok (Annotation Processor required)

## Workflows & Commands
- **Build the project:** `./gradlew build`
- **Run the application locally:** `./gradlew bootRun`
- **Run tests:** `./gradlew test` (JUnit Platform)

## Development Guidelines
- **Lombok:** Always use Lombok annotations instead of manually writing getters/setters/constructors.
- **REST APIs:** Expose JSON endpoints using `@RestController` and standard mapping annotations.
- **Database:** Configure PostgreSQL properties in `src/main/resources/application.properties` once the database infrastructure is ready.

