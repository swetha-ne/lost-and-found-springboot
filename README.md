# Lost and Found Application

A RESTful API for a Lost and Found system for an educational institute, developed for CMJD Batch 108/109 Assignment 1 using Spring Boot, Spring Data JPA, Spring Security, and MySQL with JWT authentication.

## Tech Stack
- Spring Boot 3.2.0
- MySQL 8.0
- Spring Data JPA
- Spring Security (JWT)
- Maven, Java 17

## Setup
1. **Prerequisites**: Java 17, Maven, MySQL.
2. **Clone Repository**:
   ```bash
   git clone https://github.com/yourusername/lostandfound.git
   cd lostandfound
   ```
3. **Configure MySQL**:
   ```sql
   CREATE DATABASE lost_and_found;
   ```
   Update `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/lost_and_found?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   jwt.secret=your_super_secret_key_32_chars_long
   jwt.expiration=86400000
   ```
4. **Run**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## API Endpoints
- **Auth**:
  - `POST /api/auth/signup`: Register user (`{"username": "test", "email": "test@example.com", "password": "pass", "role": "USER"}`).
  - `POST /api/auth/signin`: Get JWT (`{"username": "test", "password": "pass"}`).
- **Items**:
  - `GET /api/items`: List all items.
  - `POST /api/items`: Create item (authenticated).
  - `PUT /api/items/{id}`: Update item (authenticated).
  - `DELETE /api/items/{id}`: Delete item (ADMIN).
- **Requests**:
  - `GET /api/requests`: List requests (authenticated).
  - `POST /api/requests`: Create request (authenticated).
  - `PUT /api/requests/{id}`: Update request (STAFF/ADMIN).
  - `DELETE /api/requests/{id}`: Delete request (ADMIN).
- **Users** (ADMIN only):
  - `GET /api/admin/users`: List users.
  - `DELETE /api/admin/users/{id}`: Delete user.

## Authentication
- JWT-based; include `Authorization: Bearer <token>` for protected endpoints.
- Roles: `ADMIN`, `STAFF`, `USER`.

## Database
- Tables: `user`, `item`, `request`.
- Enums: Item (`LOST`, `FOUND`, `CLAIMED`), Request (`PENDING`, `APPROVED`, `REJECTED`), Role (`ADMIN`, `STAFF`, `USER`).

## Testing
- Use Postman to test endpoints.
- Example: `POST http://localhost:8080/api/auth/signup` with user JSON.

## Version Control
- GitHub: [https://github.com/yourusername/lostandfound](https://github.com/yourusername/lostandfound)

