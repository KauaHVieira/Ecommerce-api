# 🛒 Ecommerce API

RESTful e-commerce API developed with Java and Spring Boot, focused on clean architecture, authentication, authorization, and real business rules.

This project was created to simulate a real backend application, implementing features commonly used in production systems such as JWT authentication, role-based authorization, order management, and global exception handling.

---

# 🚀 Technologies

* Java 21
* Spring Boot 3
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Lombok
* Bean Validation

---

# 📁 Project Structure

The project follows a **feature-based architecture**, organizing packages by business domain instead of technical layers.

```text id="2f2lcb"
src/main/java/com/kauahv/ecommerceapi
│
├── auth
├── category
├── order
├── payment
├── product
├── user
│
├── config
├── exception
└── security
```

---

# 🔐 Authentication & Authorization

The API uses **JWT (JSON Web Token)** authentication with stateless sessions.

Implemented features:

* User registration
* User login
* Password encryption with BCrypt
* JWT token generation and validation
* Role-based authorization
* Protected endpoints with Spring Security

### Roles

| Role  | Permissions                    |
| ----- | ------------------------------ |
| USER  | Create and manage own orders   |
| ADMIN | Manage products and categories |

---

# 📦 Features

## 👤 Authentication

* Register users
* Login with JWT

## 🛍 Products

* Create products (ADMIN)
* Update products (ADMIN)
* Delete products (ADMIN)
* List products

## 📂 Categories

* Create categories (ADMIN)
* Update categories (ADMIN)
* Delete categories (ADMIN)
* List categories

## 📑 Orders

* Create orders
* Add items to order
* Remove items from order
* Update item quantity
* List authenticated user orders
* Find authenticated user order
* Order status validation

---

# 🧠 Business Rules

Some implemented business rules:

* Orders cannot be modified after payment
* Item quantity must be greater than zero
* Users can only access their own orders
* Only ADMIN users can manage products and categories

---

# ⚠️ Global Exception Handling

The project implements centralized exception handling using:

* `@RestControllerAdvice`
* Custom exceptions
* Validation error responses
* Standardized API error responses

---

# 🗄 Database

Database used:

* PostgreSQL

ORM:

* Hibernate / JPA

---

# ▶️ Running the Project

## 1. Clone the repository

```bash id="5av80z"
git clone https://github.com/KauaHVieira/Ecommerce-api.git
```

---

## 2. Configure the database

Update your `application.properties`:

```properties id="t2g1dw"
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
spring.datasource.username=postgres
spring.datasource.password=your_password
```

---

## 3. Run the application

```bash id="gt2jvi"
./mvnw spring-boot:run
```

---

# 🔑 Authentication Example

## Login Request

```json id="a0n7xv"
POST /auth/login

{
  "email": "admin@gmail.com",
  "password": "123456"
}
```

---

## Using JWT Token

```http id="8dr33h"
Authorization: Bearer your_token_here
```

---

# 📌 Future Improvements

* Swagger/OpenAPI documentation
* Pagination
* Unit and integration tests
* Docker support
* Refresh tokens
* Payment integration
* Stock validation
* Caching
* CI/CD pipeline

---

# 📄 License

This project is for study and portfolio purposes.
