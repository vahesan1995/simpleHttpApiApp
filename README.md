# Simple HTTP API Application

This is a simple Java-based HTTP API built using **Spring Boot** and **Java 17**, implementing the given assessment requirements.

---

## Overview

The application exposes a single endpoint: GET /hello-world?name=alice

### Expected Behavior

| Condition | HTTP Status | Response Body |
|------------|--------------|----------------|
| `name` starts with **A–M** (case-insensitive) | 200 OK | `{ "message": "Hello Alice" }` |
| `name` starts with **N–Z** (case-insensitive) | 400 Bad Request | `{ "error": "Invalid Input" }` |
| `name` is missing, empty, or non-alphabetic | 400 Bad Request | `{ "error": "Invalid Input" }` |

---

### Steps to Run

1. Clone the repository:
   git clone https://github.com/vahesan1995/simpleHttpApiApp.git
   cd simpleHttpApiApp
2. Run the application:
   mvn spring-boot:run
3. The API will be available at:
   http://localhost:8080/hello-world

### Test
To execute all unit tests:
  mvn test

The tests cover:
  1. Valid input (A–M)
  2. Invalid input (N–Z)
  3. Missing, empty, or non-letter inputs
  4. Edge cases (single character names, whitespace)

### Example Requests
#### Valid request
curl "http://localhost:8080/hello-world?name=alice" → { "message": "Hello Alice" }

#### Invalid request (starts with N)
curl "http://localhost:8080/hello-world?name=nabeel" → { "error": "Invalid Input" }

#### Missing name
curl "http://localhost:8080/hello-world" → { "error": "Invalid Input" }

---

### Assumptions
1. Leading/trailing spaces in name are ignored.
2. Only alphabetic first letters are considered valid.
3. Response format is simple JSON (Map<String, String>).
4. No need for persistence, services, or advanced frameworks because this project focuses on correctness and code clarity.
