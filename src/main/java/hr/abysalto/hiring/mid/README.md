# Abysalto Hiring MID – Backend Application

## Overview

This project is a **Spring Boot REST API** that manages **users, products, carts, and favorites**.

- **Users, carts, and favorites** are stored in a relational database.
- **Products** are retrieved from the external **DummyJSON API**.
- The application uses **JWT-based authentication**.
- **Caching** is applied to reduce unnecessary calls to the external product service.
- The API is fully documented and testable via **Swagger UI**.

The solution focuses on **clean architecture**, clear separation of concerns, and proper API design.

---

## Architecture & Design

### High-level structure

The application follows a layered architecture:

```
controller  →  service  →  repository
                    ↓
              external client (DummyJSON)
```

### Key design decisions

- **External products**
  - Products are **not persisted** in the local database.
  - Only product IDs are stored in carts and favorites.
  - Product details are fetched from DummyJSON and cached.

- **Authentication**
  - Stateless JWT authentication.
  - Security context is used to identify the currently authenticated user.

- **Validation**
  - Request validation is handled using Jakarta Validation (`@Valid`, `@Min`, etc.).
  - Global exception handling ensures consistent error responses.

- **Caching**
  - Product lists and individual products are cached using **Caffeine** via Spring Cache.
  - This significantly reduces calls to DummyJSON.

---

## Features

### Users
- User registration
- User login (JWT token generation)
- Retrieve the currently authenticated user

### Products
- Retrieve all products (with pagination and sorting)
- Retrieve a single product
- Data fetched from DummyJSON and cached

### Favorites
- Add product to favorites
- Remove product from favorites
- Retrieve user’s favorite products with full product details

### Cart
- Add product to cart
- Remove product from cart
- Retrieve the current user cart with quantities and product details

---

## Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Security (JWT)
- Spring Data JPA
- H2 in-memory database
- Spring Cache + Caffeine
- Springdoc OpenAPI (Swagger UI)
- Maven

---

## How to Run the Application

### Prerequisites
- Java 17 or newer
- Maven

### Steps

1. Clone the repository
2. Build and run the application:

```bash
mvn spring-boot:run
```

The application will start on:

```
http://localhost:8080
```

---

## Database (H2)

- H2 Console:
  ```
  http://localhost:8080/h2-console
  ```
- Database credentials and JDBC URL are configured in `application.yml`.

---

## How to Test the API with Swagger

Swagger UI is enabled and configured with JWT authentication.

### Open Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

### Authentication Flow in Swagger

1. **Register a user**
   - Endpoint: `POST /auth/register`
   - Provide email, password, first name, and last name.

2. **Login**
   - Endpoint: `POST /auth/login`
   - Copy the `accessToken` from the response.

3. **Authorize**
   - Click **Authorize** (top-right in Swagger UI).
   - Paste the token:
     ```
     Bearer <accessToken>
     ```

4. All secured endpoints will now automatically include the JWT token.

---

### Testing Endpoints

After authorization, you can test the following endpoints directly from Swagger UI:

#### Products
- `GET /products`
- `GET /products/{id}`

#### Cart
- `GET /cart`
- `POST /cart/items/{productId}`
- `DELETE /cart/items/{productId}`

#### Favorites
- `GET /favorites`
- `POST /favorites/{productId}`
- `DELETE /favorites/{productId}`

---

## Error Handling

The application uses centralized global exception handling via `@RestControllerAdvice`.

### HTTP Status Codes
- `400` – Bad request / validation errors
- `401` – Unauthorized
- `403` – Forbidden
- `404` – Resource not found
- `409` – Conflict
- `502` – External service failure

---

## Notes

- Products are intentionally **not persisted locally**.
- Pagination and sorting are delegated to the external DummyJSON API.
- Caching improves performance and reduces external API calls.

---

## Possible Improvements

- Refresh tokens
- Logout / token invalidation
- Batch-fetching product details for carts and favorites
- Integration and end-to-end tests
- React frontend client
