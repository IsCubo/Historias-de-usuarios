# Events & Venues - In-Memory Catalog (REST + Layered Architecture)

## Overview

This project implements a simple in-memory catalogue for an online ticketing platform. It exposes a REST API to manage Events and Venues using a layered architecture (Controller → Service → Repository). The implementation uses temporary in-memory storage (CopyOnWriteArrayList) and documents the API with OpenAPI/Swagger.

This README contains:
- High-level architecture and project structure
- How to run the application locally
- API endpoints and expected request/response shapes
- DTO definitions and examples
- How to access Swagger/OpenAPI documentation
- Troubleshooting tips (including common causes for 500 errors at `/v3/api-docs`)

---

## Architecture & Structure

- Controller layer: REST controllers that expose HTTP endpoints (`/api/v1/events`, `/api/v1/venues`).
- Service layer: business logic and mapping between DTOs and domain models (`service.impl` classes).
- Repository layer (simulated): in-memory repositories implementing `IRepository<T, Long>`.
- DTOs: `EventDTO`, `VenueDTO` — used as request/response payloads for the REST API.
- Exception handling: centralized via `GlobalExceptionHandler` returning RFC-7807 ProblemDetail responses.
- OpenAPI: documented using springdoc-openapi and an `OpenApiConfig` bean.

Package root: `com.riwi.eventsvenues`

---

## Requirements

- Java 17
- Maven (project includes a Maven wrapper: `mvn`)

Recommended (for JSON LocalDate support):
- Jackson JavaTime module (`com.fasterxml.jackson.datatype:jackson-datatype-jsr310`) — Spring Boot usually auto-configures it, but see Troubleshooting if date serialization fails.

---

## Running the application

From the project root run (Linux/macOS):

```bash
mvn spring-boot:run
```

Or build and run jar:

```bash
mvn -DskipTests package
java -jar target/Events-Venues-0.0.1-SNAPSHOT.jar
```

The default server port is configured in `src/main/resources/application.properties`. In this project it is set to `8081`.

---

## Swagger / OpenAPI documentation

- Swagger UI (interactive):
  - http://localhost:8081/swagger-ui/index.html

- Raw OpenAPI JSON:
  - http://localhost:8081/v3/api-docs

Note: some SpringDoc versions expose the UI at `/swagger-ui/index.html` (not `/swagger-ui.html`). Use whichever path your app logs show.

---

## API Endpoints

Base path: `/api/v1`

Events
- POST /api/v1/events
  - Create a new Event
- GET /api/v1/events
  - List all Events
- GET /api/v1/events/{id}
  - Get event by id
- PUT /api/v1/events/{id}
  - Update event by id
- DELETE /api/v1/events/{id}
  - Delete event by id

Venues
- POST /api/v1/venues
  - Create a new Venue
- GET /api/v1/venues
  - List all Venues
- GET /api/v1/venues/{id}
  - Get venue by id
- PUT /api/v1/venues/{id}
  - Update venue by id
- DELETE /api/v1/venues/{id}
  - Delete venue by id

All endpoints use standard HTTP codes (201 created, 200 ok, 204 no content, 400 bad request, 404 not found, 500 internal error).

---

## DTOs (JSON shapes)

EventDTO (fields)
- id: Long (nullable for create)
- name: String (required, 3..50)
- date: LocalDate (format: "YYYY-MM-DD", required)
- capacity: int (positive, required)
- category: String (optional)

Example create request (Event):

```json
{
  "name": "Rock Concert",
  "date": "2025-12-31",
  "capacity": 1000,
  "category": "Music"
}
```

Example response (Event):

```json
{
  "id": 1,
  "name": "Rock Concert",
  "date": "2025-12-31",
  "capacity": 1000,
  "category": "Music"
}
```

VenueDTO (fields)
- id: Long (nullable for create)
- name: String (required, 3..50)
- location: String (required)

Example create request (Venue):

```json
{
  "name": "Main Theater",
  "location": "123 Main St"
}
```

Example response (Venue):

```json
{
  "id": 1,
  "name": "Main Theater",
  "location": "123 Main St"
}
```

---

## Example curl commands

Create an event:

```bash
curl -s -X POST http://localhost:8081/api/v1/events \
  -H "Content-Type: application/json" \
  -d '{"name":"Rock Concert","date":"2025-12-31","capacity":1000,"category":"Music"}'
```

Get all events:

```bash
curl -s http://localhost:8081/api/v1/events
```

Get event by id:

```bash
curl -s http://localhost:8081/api/v1/events/1
```

Create a venue:

```bash
curl -s -X POST http://localhost:8081/api/v1/venues \
  -H "Content-Type: application/json" \
  -d '{"name":"Main Theater","location":"123 Main St"}'
```

---

## Troubleshooting

If you receive HTTP 500 when requesting the OpenAPI JSON (`/v3/api-docs`) or the Swagger UI fails to render, check the following common causes and diagnostics steps:

1. Check application logs and full stacktrace
   - The HTTP 500 coming from `/v3/api-docs` is typically due to an exception thrown while SpringDoc tries to introspect controller models (DTOs) or produce the OpenAPI model. The stacktrace in the app log identifies the root cause.

2. Common root causes
   - Missing or incompatible `springdoc-openapi` dependency. Ensure `pom.xml` contains a compatible starter (e.g. `org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0` for Spring Boot 3.x).
   - Invalid OpenAPI beans: custom `OpenAPI` configuration that adds malformed Schemas or examples can trigger serialization errors. Try temporarily removing custom components to isolate the issue.
   - DTOs using types that Jackson cannot serialize/deserialize (e.g., `java.time` without `jackson-datatype-jsr310`). If you see `HttpMessageNotReadableException` in logs, add the Jackson JavaTime module or configure Spring Boot to handle java.time types.
   - Circular references or complex generic types in model classes that confuse the OpenAPI generator.

3. Quick diagnostic steps
   - Start the app with debug logging for springdoc:
     - Add `logging.level.org.springdoc=DEBUG` to `application.properties` or set an environment variable.
   - Reproduce the error and paste the stacktrace. Look for `Caused by:` lines originating in `org.springdoc` or `com.fasterxml.jackson` packages.

4. If DTO date fields use `LocalDate` (recommended)
   - Confirm your controllers and DTOs expect `YYYY-MM-DD`. Spring Boot usually registers the JavaTimeModule automatically. If not, add the dependency and a Jackson config bean:

```xml
<!-- pom.xml -->
<dependency>
  <groupId>com.fasterxml.jackson.datatype</groupId>
  <artifactId>jackson-datatype-jsr310</artifactId>
</dependency>
```

And, if needed, register the module in a config class.

5. If you still can't resolve the 500
   - Copy the stacktrace from the application log and open an issue or share it with the maintainers. The stacktrace is required to pinpoint the cause; common fixes are removing the offending OpenAPI component or correcting a DTO type.

---

## Notes & Next Steps

- This implementation uses in-memory lists; persistence can be added later by implementing `IRepository` with a JPA `CrudRepository` or any other storage.
- Consider adding integration tests that start the application and verify the OpenAPI endpoints and basic CRUD flows.
- Add example responses and request/response schemas to controller methods with `@Operation` and `@ApiResponse` annotations to make the OpenAPI output richer.

---

If you want, I can also:
- Inspect the server logs to diagnose the current `500` at `/v3/api-docs` and propose an exact fix.
- Add a small Jackson config to ensure `LocalDate` serializes/deserializes consistently.
- Add sample integration tests that verify the Swagger endpoint is reachable.

