# 🍽️ GRANDMASFOOD

Project developed for the GrandmasFood business using **Hexagonal Architecture** (Ports and Adapters) with clear separation between **domain**, **application**, and **infrastructure** layers for maintainability and scalability.

The system is built as a **monorepo** with **four microservices**:


# 🧱 Hexagonal Architecture

This project adopts a **Hexagonal Architecture (Ports and Adapters)** to isolate the domain logic from infrastructure details such as databases, web, and external services (Auth0, SMTP).

The architecture is divided into:

- **Domain Layer**: Business logic, models, and interfaces.
- **Application Layer**: Services and use cases.
- **Infrastructure Layer**: Implementations such as controllers, persistence, and external adapters.

This allows for:

- High testability
- Technological independence
- Simple maintenance and evolution

## 🧩 Microservices
The system consists of four independent microservices:

- 🧑‍🦳 **Clients** - Customer management
- 📦 **Products** - Food menu items
- 🛒 **Orders** - Purchase tracking
- 👤 **Users** - Authentication and account management

## 🚀 Tech Stack
- Spring Boot 3.4.2
- Spring Data JPA
- MySQL + H2 (in-memory testing)
- Mockito + JUnit 5
- Swagger (OpenAPI 3)

## ✅ Prerequisites
- **JDK 17+** [Download](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- **MySQL Server**
- Recommended IDEs: VS Code or IntelliJ IDEA

## 🚀 Getting Started

### 1. Clone Repository
```bash
git clone https://github.com/your-username/grandmasfood.git
cd grandmasfood
```

### 2. Install Dependencies
Navigate to each microservice directory and run:
```bash
mvn install
```

### 3. Database Setup
- Install MySQL from [here](https://dev.mysql.com/downloads/)
- Create your database:
```sql
CREATE DATABASE grandmasfood_db;
USE grandmasfood_db;
-- Run table creation SQL scripts from each microservice's resources folder
```

### 4. Environment Configuration
Create a `.env` file at the project root:
```bash
# Microservice Ports
PORTC=8081  # Clients
PORTO=8082  # Orders
PORTP=8083  # Products
PORTU=8084  # Users

# Email Service
EMAIL_USERNAME=your.email@example.com
EMAIL_PASSWORD=your_email_password

# Auth Settings
KEY_SECRET=your_auth0_client_secret

# Database Connection
DATABASE_URL=jdbc:mysql://localhost:3306/grandmasfood_db
DATABASE_USERNAME=root
DATABASE_PASSWORD=your_password

# Test Database
DATABASE_URLCTEST=jdbc:h2:mem:testdb
```

### 5. Configure Application Properties
Update each microservice's `src/main/resources/application.properties`:
```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
```

### 6. Run Microservices
In each service's directory:
```bash
mvn spring-boot:run
```

### 7. API Documentation
Access Swagger UI for each service:
- Clients: http://localhost:8081/swagger-ui/index.html
- Orders: http://localhost:8082/swagger-ui/index.html
- Products: http://localhost:8083/swagger-ui/index.html
- Users: http://localhost:8084/swagger-ui/index.html

### 8. User Verification
After registering via `POST /api/v1/user/register`, verify your email through the link sent to your inbox.

### 9. Testing
Run tests for each service:
```bash
mvn test
```

### 10. Troubleshooting
- **Port conflicts**: Change port numbers in .env file
- **Database issues**: Verify MySQL is running with correct credentials

## API Endpoints Summary

### 🧍 Client Microservice
- `POST /api/v1/client` – Create client
- `GET /api/v1/{id}` – Get client by ID
- `GET /api/v1/client/{document}` – Get client by document
- `GET /api/v1/client?orderBy={value}&direction={value}` – List clients ordered
- `PUT /api/v1/client/{document}` – Update client
- `DELETE /api/v1/client/{document}` – Delete client

### 📦 Order Microservice
- `POST /api/v1/order` – Create order
- `PATCH /api/v1/order/{uuid}/deliverd/{timestamp}` – Mark order as delivered

### 🛒 Product Microservice
- `POST /api/v1/product` – Create product
- `GET /api/v1/{id}` – Get product by ID
- `GET /api/v1/product/menu` – Get menu
- `GET /api/v1/product/search` – Search products
- `GET /api/v1/product/{uuid}` – Get product by UUID
- `PUT /api/v1/product/{uuid}` – Update product
- `DELETE /api/v1/product/{uuid}` – Delete product

### 👤 User Microservice
- `POST /api/v1/user/register` – Register user and send verification email
- `GET /api/v1/user/verify` – Verify email
- `POST /api/v1/user/login` – Login with Auth0 and generate access token
- `POST /api/v1/user/logged` – Validate session with token

> ⚠️ **Important:** Users must verify their email to log in and access other services.