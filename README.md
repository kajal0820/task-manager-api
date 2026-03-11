# Task Manager API (Spring Boot + JWT)

A secure REST API for managing tasks with JWT authentication built using Spring Boot.

## 🚀 Features
- User Registration & Login
- JWT Authentication
- Password Encryption (BCrypt)
- Spring Security (Stateless)
- MySQL Database Integration
- Protected APIs

## 🛠 Tech Stack
- Java 17
- Spring Boot
- Spring Security
- JWT
- Hibernate / JPA
- MySQL
- Maven

## ⚙️ Setup

1. Clone repository
2. Create MySQL database:

CREATE DATABASE taskmanager_db;

3. Update application.properties

4. Run project:
mvn spring-boot:run

## 🔐 API Endpoints

### Register
POST /auth/register

### Login
POST /auth/login

Returns JWT token.

## 🧪 Testing
Use Postman or Swagger UI.

---

Built as part of backend development learning.
