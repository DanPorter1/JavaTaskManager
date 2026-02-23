# Java Task Manager - Technical Documentation

## Project Overview

The Java Task Manager is a console-based task management application with user authentication, task tracking, and budget management capabilities. The application follows a layered architecture with clear separation of concerns.

## Architecture

### Technology Stack

- **Java 15** - Programming language
- **Maven** - Build and dependency management
- **H2 Database** - Embedded database for data persistence
- **JUnit** - Testing framework

### Project Structure

```
src/main/java/taskManager/
├── Main.java                    # Application entry point
├── model/                       # Data models
│   ├── BaseItem.java           # Base class with ID
│   ├── Task.java               # Task entity
│   ├── Transaction.java        # Financial transaction entity
│   └── User.java               # User entity
├── database/                    # Data access layer
│   ├── DatabaseActions.java    # Generic CRUD interface
│   ├── TaskData.java           # Task data access
│   ├── UserData.java           # User data access
│   └── TransactionData.java    # Transaction data access (placeholder)
├── service/                     # Business logic layer
│   ├── AuthService.java        # User authentication
│   ├── BudgetService.java      # Budget management
│   ├── InputService.java       # User input handling
│   ├── MenuService.java        # Main application controller
│   ├── ReportingService.java   # Report generation
│   └── TaskService.java        # Task management
├── util/                        # Utilities and enums
│   ├── DBConnection.java       # Database connection
│   ├── Priority.java           # Task priority enum
│   ├── Status.java             # Task status enum
│   ├── Summary.java            # Summary interface
│   └── TransType.java          # Transaction type enum
└── exception/                   # Custom exceptions
    ├── InvalidID.java
    └── InvalidNumber.java
```

## Component Responsibilities

### 1. Entry Point Layer

**`Main.java`**
- **Responsibility**: Application bootstrap and initialization
- **Flow**: 
  - Test database connection
  - Initialize database tables
  - Launch main menu system

### 2. Model Layer

**`BaseItem.java`**
- **Responsibility**: Provides common ID functionality for all entities
- **Methods**: `getId()`, `setId(int id)`

**`Task.java`**
- **Responsibility**: Represents a task with title, description, priority, and status
- **Implements**: `Summary` interface for reporting
- **Attributes**: taskTitle, taskDetails, priorityEnum, statusEnum
- **Methods**: `getDetails()`, `getSummary()`

**`Transaction.java`**
- **Responsibility**: Represents financial transactions
- **Implements**: `Summary` interface
- **Attributes**: reference, amount, type
- **Methods**: `getDetails()`, `getSummary()`

**`User.java`**
- **Responsibility**: User authentication data
- **Attributes**: username, password
- **Methods**: `getUsername()`, `getPassword()`

### 3. Database Layer

**`DBConnection.java`**
- **Responsibility**: Database connection management
- **Configuration**: H2 file-based database at `./db/tempDB`
- **Methods**: `getConnection()`, `testConnection()`

**`DatabaseActions<T>.java`**
- **Responsibility**: Generic CRUD interface for data access
- **Methods**: `insert(T item)`, `update(T item)`, `delete(int id)`

**`TaskData.java`**
- **Responsibility**: Task persistence operations
- **Implements**: `DatabaseActions<Task>`
- **Methods**: `insert()`, `delete()`, `getAllTasks()`, `createTaskTable()`

**`UserData.java`**
- **Responsibility**: User persistence and authentication
- **Implements**: `DatabaseActions<User>`
- **Methods**: `insert()`, `getUserCheck()`, `login()`, `createUserTable()`

### 4. Service Layer

**`MenuService.java`**
- **Responsibility**: Main application controller and UI flow management
- **Dependencies**: All other services
- **Key Methods**: `mainMenu()`, `handleAddTask()`, authentication methods

**`TaskService.java`**
- **Responsibility**: Task business logic
- **Methods**: `addTask()`, `removeTask()`, `getAllTasks()`, `view()`

**`AuthService.java`**
- **Responsibility**: User authentication logic
- **Methods**: `checkUserExist()`, `login()`

**`ReportingService.java`**
- **Responsibility**: Generate various task reports
- **Methods**: `getReport()`, `getOpenReport()`, `getOCReport()`, `getTaskPriorityReport()`

**`BudgetService.java`**
- **Responsibility**: Budget and transaction management (partially implemented)
- **Methods**: `addTransaction()`, `getAllTransactions()`

**`InputService.java`**
- **Responsibility**: User input validation and handling
- **Methods**: `readString()`, `readInt()`, `readPriority()`

### 5. Utility Layer

**Enums:**
- `Priority`: HIGH, MEDIUM, LOW
- `Status`: OPEN, CLOSED  
- `TransType`: INCOMING, OUTGOING

**Interfaces:**
- `Summary`: Provides `getSummary()` method for reporting

## Application Flow

### 1. Application Startup Flow

```
Main.main()
├── DBConnection.testConnection()
├── TaskData.createTaskTable()
├── UserData.createUserTable()
└── MenuService.mainMenu()
```

### 2. Authentication Flow

```
MenuService.mainMenu()
├── Login Option
│   ├── InputService.readString() (username)
│   ├── InputService.readString() (password)
│   ├── AuthService.login()
│   └── UserData.login()
└── Register Option
    ├── InputService.readString() (username)
    ├── InputService.readString() (password)
    ├── AuthService.checkUserExist()
    ├── UserData.getUserCheck()
    └── UserData.insert()
```

### 3. Task Management Flow

```
MenuService.mainMenu() (after auth)
├── Add Task
│   ├── InputService.readString() (title)
│   ├── InputService.readString() (description)
│   ├── InputService.readPriority()
│   ├── Task constructor
│   ├── TaskService.addTask()
│   └── TaskData.insert()
├── Remove Task
│   ├── TaskService.view()
│   ├── InputService.readInt() (ID)
│   ├── TaskService.removeTask()
│   └── TaskData.delete()
├── View Tasks
│   ├── TaskService.getAllTasks()
│   └── TaskData.getAllTasks()
└── Reports
    ├── ReportingService.getTaskPriorityReport()
    ├── ReportingService.getOCReport()
    └── ReportingService.getOpenReport()
```

### 4. Data Flow Patterns

**Task Creation Flow:**
```
User Input → InputService → MenuService → TaskService → TaskData → H2 Database
```

**Task Retrieval Flow:**
```
MenuService → TaskService → TaskData → H2 Database → Task Objects → ReportingService → Display
```

**Authentication Flow:**
```
User Input → InputService → MenuService → AuthService → UserData → H2 Database → User Object
```

## Database Schema

### Tasks Table

```sql
CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    priority VARCHAR(50),
    status VARCHAR(50)
);
```

### Users Table

```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
```

## Current Limitations and Future Improvements

### Incomplete Features:
- **Transaction Management**: `TransactionData` is empty, budget functionality not fully implemented
- **Task Updates**: `TaskData.update()` method is empty
- **User Management**: User update/delete operations not implemented
- **Exception Handling**: Custom exceptions exist but not consistently used

### Security Concerns:
- **Password Storage**: Plain text passwords in database
- **SQL Injection**: Uses PreparedStatement (good practice)
- **Input Validation**: Basic validation implemented

### Potential Enhancements:
- **Session Management**: Current authentication is stateless
- **Data Validation**: More comprehensive input validation
- **Error Handling**: Consistent exception handling across layers
- **Logging**: Add proper logging framework
- **Configuration**: Externalize database configuration
- **Testing**: Expand test coverage beyond basic setup

---
