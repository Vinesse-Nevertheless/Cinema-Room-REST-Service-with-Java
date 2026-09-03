# 🎬 Cinema Room REST Service

A Spring Boot backend RESTful web service that handles movie theater seating reservations, ticket purchasing, ticket refunds/returns, and real-time administrative statistics.

---

## 📋 Table of Contents
- [Project Overview](#-project-overview)
- [How to Run](#-how-to-run)
- [API Documentation](#-api-documentation)
  - [1. View Seating Layout](#1-view-seating-layout)
  - [2. Purchase a Ticket](#2-purchase-a-ticket)
  - [3. Return a Ticket](#3-return-a-ticket)
  - [4. View Admin Statistics](#4-view-admin-statistics)
- [Business Rules & Error Handling](#-business-rules--error-handling)

---

## 📌 Project Overview

- **Base Server URL:** `http://localhost:28852`
- **Seating Capacity:** 9 rows × 9 columns (81 total seats)
- **Ticket Pricing:**
  - **Rows 1 – 4:** `$10`
  - **Rows 5 – 9:** `$8`
- **Tech Stack:** Java 17+, Spring Boot 3, Gradle, Jackson JSON

---

## 🚀 How to Run

### Prerequisites
- **JDK 17** or higher installed and set in your `JAVA_HOME`.
- **Gradle** (or use the included Gradle wrapper).

### 1. Configure the Application Port
To ensure the server starts on port `28852`, verify that your `src/main/resources/application.properties` includes:

```properties
server.port=28852
```

### 2. Start the Application
Open a terminal in the project root directory and run:

Linux / macOS:

``` Bash
./gradlew bootRun
```
Windows (Command Prompt / PowerShell):

``` DOS
gradlew.bat bootRun
```

Alternatively, build the executable JAR and run it directly specifying the port:

``` Bash
./gradlew build
java -jar build/libs/cinema-rest-service-1.0.jar --server.port=28852
```

Once started, the service will be listening at:

👉 http://localhost:28852

##🔌 API Documentation
### 1. View Seating Layout
Returns the cinema layout and a list of all currently available seats with their respective prices.

- URL: http://localhost:28852/seats

- Method: GET

- Response (200 OK):

``` json
{
  "rows": 9,
  "columns": 9,
  "seats": [
    {
      "row": 1,
      "column": 1,
      "price": 10
    },
    {
      "row": 1,
      "column": 2,
      "price": 10
    }
  ]
}
```

### 2. Purchase a Ticket
Reserves a seat if available and generates a unique UUID token for the buyer.

- URL: http://localhost:28852/purchase

- Method: POST

- Headers: Content-Type: application/json

- Request Body:

``` json
{
  "row": 3,
  "column": 5
}
```

- Response (200 OK):

``` JSON
{
  "token": "d290f1ee-6c54-4b01-90e6-d701748f0851",
  "ticket": {
    "row": 3,
    "column": 5,
    "price": 10
  }
}
```

3. Return a Ticket
Cancels a booking using the ticket token provided during purchase and releases the seat back to the available pool.

- URL: http://localhost:28852/return

- Method: POST

- Headers: Content-Type: application/json

- Request Body:

``` JSON
{
  "token": "d290f1ee-6c54-4b01-90e6-d701748f0851"
}
```

- Response (200 OK):

``` JSON
{
  "ticket": {
    "row": 3,
    "column": 5,
    "price": 10
  }
}
```

4. View Admin Statistics
Fetches financial metrics and current occupancy counts. Requires admin password authentication via a query parameter.

- URL: http://localhost:28852/stats?password=super_secret

- Method: POST

- Query Parameter: password (Default: super_secret)

- Response (200 OK):

``` JSON
{
  "income": 10,
  "available_seats": 80,
  "purchased_tickets": 1
}
```

## ⚠️ Business Rules & Error Handling

When an error occurs, the API responds with an appropriate HTTP status code and a JSON error message:

| Scenario | HTTP Status | Response Payload |
| :--- | :--- | :--- |
| **Seat out of bounds** (e.g. row 10, col 0) | `400 Bad Request` | `{"error": "The number of a row or a column is out of bounds!"}` |
| **Seat already booked** | `400 Bad Request` | `{"error": "The ticket has been already purchased!"}` |
| **Invalid/Wrong Token on Return** | `400 Bad Request` | `{"error": "Wrong token!"}` |
| **Wrong Admin Password on `/stats`** | `401 Unauthorized` | `{"error": "Wrong password!"}` |

