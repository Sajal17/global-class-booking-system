# Classora — Class Booking & Scheduling Backend

## Project Overview

Classora is a production-ready backend system for managing course offerings, sessions, and parent bookings across different timezones.

The system supports:

* Teachers creating recurring course offerings
* Session scheduling
* Parent/student booking
* Booking conflict detection
* Timezone-aware scheduling
* Swagger API documentation

---

# Tech Stack

* Java 17
* Spring Boot 3.3.5
* Spring Data JPA
* Spring Validation
* Spring Security
* MySQL
* Swagger / OpenAPI
* Maven
* Lombok

---

# Features

## Teacher Management

* Create teachers
* Store teacher timezone

## Course Management

* Create courses
* Create offerings

## Session Scheduling

* Add sessions to offerings
* Store session times in UTC

## Booking System

* Parent booking support
* Duplicate booking prevention
* Conflict detection

## Timezone Handling

* Teachers create schedules in local timezone
* Sessions stored in UTC
* Parent schedules converted to local timezone

---

# Database Schema Overview

## Tables

* teachers
* parents
* courses
* offerings
* sessions
* bookings

### Relationships

* One Teacher → Many Offerings
* One Course → Many Offerings
* One Offering → Many Sessions
* One Parent → Many Bookings

---

# Concurrency Handling Approach

To prevent duplicate bookings during concurrent requests:

* Pessimistic locking is used
* Parent booking rows are locked during booking operations
* Unique constraints are applied on:

    * parent_id
    * offering_id

This ensures transactional consistency.

---

# Timezone Handling Approach

* Teachers create schedules in their local timezone
* Backend converts local datetime to UTC
* UTC timestamps are stored in database
* Parent timezone is used while displaying schedules

Java ZonedDateTime and Instant APIs are used for conversion.

---

# API Documentation

Swagger UI:

http://localhost:8080/swagger-ui/index.html

---

# Environment Variables

## application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/classora
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

---

# Setup Instructions

## Clone Repository

git clone <repository-url>

## Navigate to Project

cd Classora

## Run MySQL

Create database:

CREATE DATABASE classora;

## Run Application

./mvnw spring-boot:run

OR run from IntelliJ.

---

# Run Application Locally

1. Start MySQL
2. Configure application.properties
3. Run Spring Boot application
4. Open Swagger UI
5. Test APIs

---

# Example Workflows

## Weekly Course

* Every Saturday
* 6 PM–7 PM
* 8 weeks

## Summer Camp

* Monday–Friday
* 5 PM–6 PM
* Same week

---

# Assumptions Made

* Session times are always stored in UTC
* One parent cannot book the same offering twice
* Overlapping sessions are considered conflicts
* Authentication is not implemented in current version

---

# Future Improvements

* JWT Authentication
* Docker support
* Flyway migrations
* Unit testing
* Pagination & filtering
* Email notifications
