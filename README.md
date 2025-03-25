GRANDMASFOOD
Project developed for the GrandmasFood business.
This project implements microservices using hexagonal architecture, which facilitates scalability, maintainability, and a clear separation between the domain, application, and infrastructure layers, ensuring proper communication between ports and adapters.
Additionally, for integration testing, an in-memory H2 database is used, allowing the complete behavior of endpoints and services to be validated in an isolated, safe, and reproducible environment..

🥬 Spring Boot 3.4.2
🥬 Spring Data JPA
🐘 mySQL + H2
🍸 Mockito + JUnit 5 (Test)
🔍 Swagger (OpenAPI 3) (API documentation)
🧩 Microservices
🧩 Hexagonal architecture

Hexagonal architecture:

📦 GrandmasFood Clients
┣  application # Application layer (ports and use cases)
┃ ┣  ports # Definition of input and output ports
┃ ┃ ┣  input # Input ports (interfaces for data input)
┃ ┃ ┗  output # Output ports (interfaces to infrastructure)
┃ ┗  service # Core business logic (use cases)
┃ ┗ 📄 ClientService
┣  domain # Domain layer (models and exceptions)
┃ ┣  exceptions # Business exceptions
┃ ┗  model # Domain models and DTOs
┃ ┣ 📄 Client
┃ ┣ 📄 ErrorResponseDTO
┃ ┗ 📄 PageableDTO
┣  infrastructure.adapters # Infrastructure layer (adapters and persistence)
┃ ┣  input.rest # REST input adapter
┃ ┃ ┣  controller # REST controllers
┃ ┃ ┣  mapper # Input mappers
┃ ┃ ┗  model # REST input models
┃ ┗  output # Output adapter (persistence)
┃ ┣  entities # JPA entities
┃ ┣  mapper # Entity mappers
┃ ┣  repository # JPA repositories
┃ ┗ 📄 ClientPersistenceAdapter # Persistence adapter
┣  swagger # Swagger documentation
┣  utils # Utilities and error catalogs
┃ ┗ 📄 ErrorCatalog
┗ 📄 ClientsApplication # Main application class