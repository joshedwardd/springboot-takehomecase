# Microservices API for Authentication and User Management (Java / Spring Boot)

**try-user-service (Port 8081)**\
**auth-service (Port 8080)**

## Prerequisites:
- Java 17 JDK (or higher)
- Apache Maven 3.6+
- A running MySQL instance (XAMPP's MySQL)

## How to Run the Application: 
### 1. Database setup
- Connect to your local MySQL server
- CREATE DATABASE test;

### 2. Configure the services
- Navigate to try-user-service/src/main/resources/application.properties
- Update the spring.datasource.password with your MySQL root user's password. (Note: if using the default XAMPP setup, the password should be left blank)

### 3. Start the Services
- Run both try-user-service and auth-service simultaneously by locating the SpringBootApplication\
try-user-service will start on port 8081\
auth-service will start on port 8080


## Testing the API (Postman)
### 1. Register a new user
Method: POST\
URL: http://localhost:8081/api/users/register \
Body (raw, JSON):
```
{
    "username": "test",
    "email": "test@gmail.com",
    "password": "test123",
    "name": "test",
    "age": 20
}
```

### 2. Login and get the jwt token
Method: POST\
URL: http://localhost:8080/api/auth/login \
Body (raw, JSON):
```
{
    "username": "test",
    "password": "test123"
}
```
Copy the jwt string from the response body to test the next step

### 3. Access user detail
Use the copied jwt string from Step 2 to access a secure endpoint on the user-service\
Method: GET\
URL: http://localhost:8081/api/users/1 

**Authorization Tab:**\
Type: Bearer Token\
Token: Paste the JWT you copied here

### 4. Update user information
Use the copied jwt string from Step 2 to access a secure endpoint on the user-service\
Method: PUT\
URL: http://localhost:8081/api/users/1 

**Authorization Tab:**\
Type: Bearer Token\
Token: Paste the JWT you copied here

Update user information in the Body section
