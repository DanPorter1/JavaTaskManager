# Intermediate Java Project

## Project Title
**Smart Task & Budget Manager (Console Application)**

---

## Project Overview

You are required to build a **console-based Java application** that helps users manage their daily tasks and track simple budgets. The system should demonstrate strong understanding of **core Java concepts** and object-oriented programming principles.

The application must be modular, scalable, and written using clean coding practices.

---

## Learning Objectives

This project is designed to assess the following Core Java topics:

- Object-Oriented Programming (Encapsulation, Abstraction, Inheritance, Polymorphism)
- Classes and Objects
- Constructors
- Access Modifiers
- Collections Framework (ArrayList, HashMap, etc.)
- Exception Handling (try-catch, custom exceptions)
- JDBC (Java Database Connectivity)
- Java 8+ Features (Optional: Streams, Lambda Expressions)
- Interfaces and Abstract Classes
- Enums
- Basic Input Handling (Scanner)

---

## Functional Requirements

### 1. User Management
- User can register
- User can log in
- Store user data in a database using JDBC
- Validate login credentials

### 2. Task Management Module

> All task data must be stored and retrieved from a database using JDBC.
Each user should be able to:

- Add a new task
- Update a task
- Delete a task
- Mark task as completed
- View all tasks
- Filter tasks by:
    - Status (Completed / Pending)
    - Priority (High / Medium / Low)

Each task must contain:
- Task ID
- Title
- Description
- Due Date
- Priority (Enum)
- Status (Enum)

---

### 3. Budget Management Module

> All transaction data must be stored and retrieved from a database using JDBC.
Each user should be able to:

- Add income
- Add expense
- View transaction history
- View total income
- View total expenses
- View remaining balance

Each transaction must contain:
- Transaction ID
- Type (Income / Expense)
- Amount
- Category
- Date

---

## Technical Requirements


### 1. Core Java Concepts That MUST Be Used

#### OOP Principles
- Proper encapsulation (private fields + getters/setters)
- At least one abstract class or interface
- Demonstrate inheritance
- Demonstrate polymorphism

#### Collections
- Use ArrayList for storing tasks
- Use HashMap for user-session mapping or categorization

#### Exception Handling
- Handle invalid inputs
- Handle file not found
- Create at least one custom exception

#### JDBC Integration
- Connect to a relational database (MySQL or PostgreSQL)
- Use JDBC API for CRUD operations
- Use PreparedStatement for secure queries
- Implement proper connection management
- Handle SQLExceptions appropriately
- Data must persist in the database and remain available after program restart

- Create required database tables:
    - users
    - tasks
    - transactions

---

## Non-Functional Requirements

- Follow clean code principles
- Use meaningful class and method names
- Proper indentation and formatting
- Avoid code duplication
- Use comments where necessary

---

## Bonus Features (Optional)

- Implement search functionality
- Use Java Streams for filtering
- Add password hashing
- Implement simple logging
- Add export to CSV functionality
- Add unit testing (JUnit)

---