# Lost and Found Application

A RESTful API for a Lost and Found system for an educational institute, using Spring Boot, Spring Data JPA, Spring Security, and MySQL with JWT authentication.

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

