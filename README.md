# Codveda Java Development Internship

A collection of Java development projects completed as part of my **Java Development Internship at Codveda Technology**.

This repository contains six selected tasks across three levels, covering Java fundamentals, object-oriented programming, file handling, JDBC, MySQL, and data structures.

##  Internship Projects

### Level 1 – Java Fundamentals

| No. | Project | Description | Technologies |
|---|---|---|---|
| 1 | Basic Calculator | Performs basic arithmetic operations such as addition, subtraction, multiplication, and division. | Java |
| 2 | Number Guessing Game | A console-based game where users guess a randomly generated number within a limited number of attempts. | Java |

### Level 2 – Intermediate Java

| No. | Project | Description | Technologies |
|---|---|---|---|
| 1 | Employee Management System | A menu-driven application supporting employee CRUD operations. | Java, ArrayList |
| 2 | File Handling | Reads text files, counts lines and words, and writes processed output into another file. | Java I/O |

### Level 3 – Advanced Java

| No. | Project | Description | Technologies |
|---|---|---|---|
| 1 | Library Management System | Manages books, users, borrowing, returning, and transaction records using a relational database. | Java, JDBC, MySQL |
| 2 | Binary Search Tree | Implements insertion, searching, deletion, and tree traversal operations. | Java, Data Structures |

## Technologies Used

- Java
- Object-Oriented Programming
- JDBC
- MySQL
- SQL
- Java Collections Framework
- File Handling
- Data Structures and Algorithms
- Git and GitHub
- VS Code

##  Repository Structure

```text
Codveda-Java-Internship/
│
├── LEVEL1/
│   ├── BasicCalculator.java
│   └── NumberGuessingGame.java
│
├── LEVEL2/
│   ├── EmployeeManagementSystem.java
│   ├── FileHandling.java
│   ├── input.txt
│   └── output.txt
│
├── LEVEL3/
│   ├── LibraryManagementSystem.java
│   └── BinarySearchTree.java
│
├── lib/
│   └── mysql-connector-j-26.10.0.jar
│
├── .gitignore
└── README.md
```

## ⚙️ Requirements

- Java Development Kit (JDK)
- MySQL Server
- MySQL Connector/J
- Visual Studio Code or another Java IDE
- Git

##  How to Run

### 1. Clone the repository

```bash
git clone https://github.com/MowshikM/Codveda-Java-Internship.git
```

### 2. Navigate to the project folder

```bash
cd Codveda-Java-Internship
```

### 3. Run Level 1 projects

**Basic Calculator**

```bash
javac -d . LEVEL1/BasicCalculator.java
java LEVEL1.BasicCalculator
```

**Number Guessing Game**

```bash
javac -d . LEVEL1/NumberGuessingGame.java
java LEVEL1.NumberGuessingGame
```

### 4. Run Level 2 projects

**Employee Management System**

```bash
javac -d . LEVEL2/EmployeeManagementSystem.java
java LEVEL2.EmployeeManagementSystem
```

**File Handling**

Ensure an `input.txt` file exists inside the `LEVEL2` folder before running.

```bash
javac -d . LEVEL2/FileHandling.java
java LEVEL2.FileHandling
```

### 5. Run Level 3 projects

**Binary Search Tree**

```bash
javac -d . LEVEL3/BinarySearchTree.java
java LEVEL3.BinarySearchTree
```

**Library Management System**

First, create the MySQL database and tables required by the application.

```sql
CREATE DATABASE library_management;
```

Use the `library_management` database and create the following tables:

```sql
CREATE TABLE books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    available BOOLEAN DEFAULT TRUE
);

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    phone VARCHAR(15)
);

CREATE TABLE transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT NOT NULL,
    user_id INT NOT NULL,
    borrow_date DATE NOT NULL,
    return_date DATE,
    status VARCHAR(20) DEFAULT 'BORROWED',
    FOREIGN KEY (book_id) REFERENCES books(book_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
```

Configure your database credentials using environment variables.

For Windows PowerShell:

```powershell
$env:DB_USER="root"
$env:DB_PASSWORD="your_mysql_password"
```

Compile:

```powershell
javac -cp ".;lib/mysql-connector-j-26.10.0.jar" -d . LEVEL3/LibraryManagementSystem.java
```

Run:

```powershell
java -cp ".;lib/mysql-connector-j-26.10.0.jar" LEVEL3.LibraryManagementSystem
```

##  Key Learning Outcomes

- Understanding Java fundamentals and control structures.
- Implementing object-oriented programming concepts.
- Working with Java Collections and ArrayList.
- Performing file input and output operations.
- Connecting Java applications with MySQL using JDBC.
- Implementing database CRUD operations.
- Managing database transactions using commit and rollback.
- Understanding Binary Search Tree operations and traversals.
- Using Git and GitHub for version control.

##  Author

**Mowshik M.**

Java Development Intern – Codveda Technology

GitHub: [@MowshikM](https://github.com/MowshikM)

## 🔗 Repository

[Codveda Java Development Internship Projects](https://github.com/MowshikM/Codveda-Java-Internship)

---

*Developed as part of the Codveda Technology Java Development Internship.*
