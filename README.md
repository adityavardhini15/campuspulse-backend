# CampusPulse Backend

Student Feedback Analysis System - Spring Boot REST API

## 🚀 Tech Stack

- **Java 21**
- **Spring Boot 3.2.0**
- **Spring Security** + JWT Authentication
- **Spring Data JPA** + Hibernate
- **MySQL** Database
- **Maven** Build Tool
- **Lombok** for cleaner code
- **SpringDoc OpenAPI** for API documentation

## 📋 Features

- ✅ JWT-based authentication
- ✅ BCrypt password hashing
- ✅ RESTful API endpoints
- ✅ Sentiment analysis on feedback
- ✅ Analytics and reporting
- ✅ Swagger API documentation
- ✅ MySQL database with JPA

## 🛠️ Setup

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- MySQL 8.0+

### Installation

1. Clone the repository
```bash
git clone https://github.com/adityavardhini15/campuspulse-backend.git
cd campuspulse-backend
```

2. Configure MySQL
Update `src/main/resources/application-dev.properties`:
```properties
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

3. Run the application
```bash
mvn spring-boot:run
```

The server will start on `http://localhost:9090`

## 📚 API Documentation

Once the application is running, access Swagger UI:
```
http://localhost:9090/swagger-ui.html
```

## 🔐 API Endpoints

### Authentication (Public)
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user

### Services (Public)
- `GET /api/services` - Get all campus services

### Feedback (Protected - JWT Required)
- `POST /api/feedback` - Submit feedback
- `GET /api/feedback` - Get all feedback
- `GET /api/feedback/user` - Get user's feedback

### Analytics (Protected - JWT Required)
- `GET /api/analytics/overview` - Dashboard overview
- `GET /api/analytics/service-ratings` - Service-wise ratings
- `GET /api/analytics/sentiment` - Sentiment distribution
- `GET /api/analytics/trends` - Feedback trends

## 📦 Database Schema

### Users
- id, username, email, password (BCrypt), role, created_at

### Services
- id, name, description
- Pre-seeded: Canteen, Library, Transport, Classrooms, Cleanliness, Infrastructure

### Feedback
- id, user_id, service_id, rating (1-5), comment, sentiment, created_at

## 🧪 Testing

```bash
mvn test
```

## 📝 Environment Variables

Create `.env` file in root:
```
JWT_SECRET=your-secret-key-here
MYSQL_USERNAME=root
MYSQL_PASSWORD=your-password
```

## 👨‍💻 Author

**Aditya Vardhini Vemulapudi**
- Roll Number: 24EG110B60
- Section: Data Science B
- GitHub: [@adityavardhini15](https://github.com/adityavardhini15)

## 📄 License

This project is for educational purposes.
