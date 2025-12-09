# 📦 Inventory Management System

Enterprise-grade inventory management system built with Spring Boot, featuring complete user authentication and role-based access control.

## 🚀 Features

### Phase 1 - Authentication System ✅
- User registration with server-side validation
- Secure login/logout with Spring Security
- BCrypt password encryption
- Role-based access control (ADMIN, MANAGER, STAFF)
- Protected dashboard with user information
- Professional gradient UI with success/error messaging
- Session management
- PostgreSQL database with Docker

## 🛠️ Technologies

- **Backend:** Spring Boot 3.x, Spring Security 6, Spring Data JPA
- **Frontend:** Thymeleaf, HTML5, CSS3
- **Database:** PostgreSQL 15
- **Authentication:** BCrypt, Spring Security
- **Validation:** Bean Validation API
- **Containerization:** Docker

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- Docker & Docker Compose
- PostgreSQL 15 (via Docker)

## 🏃 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/godwinme/inventory-system.git
cd inventory-system
```

### 2. Start PostgreSQL with Docker
```bash
docker-compose up -d
```

### 3. Run the application
```bash
mvn spring-boot:run
```

### 4. Access the application
- URL: `http://localhost:8082`
- Register a new account
- Login with your credentials

## 📸 Screenshots

### Login Page
Professional login interface with validation and error handling.

### Dashboard
Personalized dashboard showing user information and role.

## 🔐 Default Roles

- **STAFF** - Default role for new registrations
- **MANAGER** - (To be implemented in Phase 2)
- **ADMIN** - (To be implemented in Phase 2)

## 🗺️ Roadmap

### Phase 1 - Authentication System ✅ (COMPLETE)
- [x] User registration
- [x] Login/logout
- [x] Password encryption
- [x] Role-based foundation
- [x] Protected routes

### Phase 2 - Product Management (Coming Soon)
- [ ] Product CRUD operations
- [ ] Category management
- [ ] Product-Category relationships
- [ ] Search and filtering
- [ ] Role-based permissions

### Phase 3 - Inventory Operations (Planned)
- [ ] Stock tracking
- [ ] Inventory adjustments
- [ ] Low stock alerts
- [ ] Audit logs (createdBy, updatedBy)

## 📂 Project Structure
```
src/main/java/com/godwin/inventory/
├── config/          # Security configuration
├── controller/      # MVC controllers
├── models/          # JPA entities
├── repository/      # Data repositories
├── security/        # Custom security services
└── service/         # Business logic

src/main/resources/
├── static/css/      # Stylesheets
└── templates/       # Thymeleaf templates
```

## 👨‍💻 Author

**Godwin Mugabe**  
Telecom Professional @ MTN Rwanda  
Building enterprise systems with Spring Boot

## 📄 License

This project is open source and available for learning purposes.

---

**⭐ Star this repository if you find it helpful!**
