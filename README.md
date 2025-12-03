# JWT Login System

A simple demo of a JWT-based login system with Java Spring Boot (backend) and Angular (frontend).

## Features
- User registration and login
- JWT authentication for protected routes

## Setup
1. **Database (MySQL):**  
   - Create a database. 
   - Run the SQL script in `starter.sql` to create tables and sample data.  
   - Update the database connection in `backend/src/main/resources/application.properties`.

2. **Backend:** `cd backend` → `mvn spring-boot:run`  
3. **Frontend:** `cd frontend` → `npm install` → `npm start`

