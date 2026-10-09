# Student Management System
 
A desktop application for managing student records, built with JavaFX and backed by a MySQL database. Supports full CRUD operations (Create, Read, Update, Delete) with input validation and a layered architecture.
 
## Features
 
- Add, update, delete, and view student records
- Input validation (email format, 10-digit phone numbers, CGPA range, graduation year range)
- Click any row in the table to load its data into the form for editing
- Clear error messages for invalid input or database issues, instead of application crashes
- Credentials kept out of source code via a `.env` file
## Tech Stack
 
- **Java 25**
- **JavaFX 21** — UI
- **MySQL** — database
- **Maven** — build and dependency management
- **dotenv-java** — environment variable loading
## Architecture
 
The project follows a layered structure:
 
```
model/      → Student.java (data + validation)
dao/        → StudentDAO.java (database queries)
service/    → StudentService.java (business logic)
util/       → DBConnection.java (database connection handling)
Exception/  → InvalidDataExc.java (custom validation exception)
com.example.placements/  → HelloApplication.java, StudentController.java (JavaFX UI)
```
 
## Setup
 
### Prerequisites
 
- JDK 25
- Maven
- MySQL Server running locally (or accessible remotely)
### Database Setup
 
Create a database and table matching this structure:
 
```sql
CREATE DATABASE my_dummy_db;
USE my_dummy_db;
 
CREATE TABLE student (
    student_ID INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(10),
    cgpa DOUBLE,
    branch VARCHAR(50),
    gradYear INT
);
```
 
### Environment Variables
 
This project reads database credentials from a `.env` file, which is **not** committed to the repository (see `.gitignore`).
 
1. Copy `.env.example` to a new file named `.env`
2. Fill in your actual database details:
```
DB_URL=jdbc:mysql://localhost:3306/my_dummy_db
DB_USER=your_mysql_username
DB_PASSWORD=your_mysql_password
```
 
### Running the App
 
```bash
mvn clean javafx:run
```
 
Or open the project in IntelliJ IDEA and run `HelloApplication.java` directly.
