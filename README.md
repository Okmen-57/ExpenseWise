# Expense Tracker

A simple **console-based** application written in **Java** that demonstrates **CRUD** operations, user **authentication**, and **report generation** using a **MySQL** relational database.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Database Setup](#database-setup)

---

## Features
1. **User Registration and Login**  
   - Register a new user with a username, password, and email.  
   - Log in with credentials to access the main features.

2. **Expense Management**  
   - Add new expenses (amount, category, description, date).  
   - View all expenses for the logged-in user.

3. **Reporting**  
   - Generate a report (by category) within a chosen date range.

4. **Console Menus**  
   - Simple interactive menus for navigation and input.

---

## Technologies
- **Java**
- **MySQL** 
- **MySQL Connector/J**
- **JDBC** for database connectivity

---

## Prerequisites
1. **Java**  
   - Install a JDK (version 8 or higher).  
   - Verify by running `java -version` in your terminal.

2. **MySQL**  
   - Install and run MySQL server.  
   - Confirm you can connect via CLI or MySQL Workbench.

3. **MySQL Connector/J**  
   - Ensure the JAR (`mysql-connector-java-x.x.xx.jar`) is placed in the `lib/` folder.  
   - Adjust the version to match your environment.

---

## Database Setup

### 1. Create the Database and Tables
If not already done, run the following SQL in MySQL to set up the database schema:

```sql
CREATE DATABASE IF NOT EXISTS expense_tracker;
USE expense_tracker;

-- Create `users` table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create `expenses` table
CREATE TABLE IF NOT EXISTS expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    category VARCHAR(50),
    description VARCHAR(255),
    expense_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
