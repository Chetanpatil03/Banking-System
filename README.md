# 💳 Banking App (JDBC Mini Project)

This is a simple console-based **Banking Application** built using **Java** and **JDBC (Java Database Connectivity)**.  
It demonstrates essential JDBC concepts such as:
- Connecting to a database
- Using JDBC components (`DriverManager`, `Connection`, `Statement`, `ResultSet`)
- Performing **CRUD operations**
- Handling **transactions**
- Object-oriented design with `User` and `Accounts` classes

---

## 📂 Project Structure

<pre>
📂 src
 ├── 📂DatabaseConfig
 |    ├── db_schema.sql // SQL script to create database and tables
 | 
 ├── Banking_App.java // Main entry point of the application
 ├── AccountManager.java // Contains logic for account operations
 |── Accounts.java // Model class for account data
 ├── User.java // Model class for user data

</pre>


## 💡 Features

- Create users and accounts
- Check account details
- Deposit and withdraw money
- Transfer between accounts with transaction management
- Basic error handling and input validation



## 🔧 Technologies Used

- **Java (Core Java, OOP)**
- **JDBC API**
- **MySQL** (or any JDBC-compatible RDBMS)
- **SQL** for schema and queries



## 🧠 What I Learned

Through this project, I explored:
- How to connect Java applications to relational databases using JDBC
- Proper usage of JDBC components
- Executing SQL commands from Java
- Implementing and managing **database transactions**
- Designing maintainable object-oriented applications



## 🚀 Getting Started

### 1. Clone the Repository

<pre> 
  git clone https://github.com/Chetanpatil03/Banking-system.git 
</pre>

### 2. Set Up the Database
Make sure MySQL is running, then:

Open a MySQL client (like MySQL Workbench or command-line)

Run the SQL script provided in db_schema.sql:
<pre>
   source path/to/db_schema.sql;
</pre>




This will:

Create a database bank_sys
Create two tables: accounts and user

📁 You can modify the schema as needed, just ensure the code and DB stay in sync.

### 3. Update JDBC Connection
In your Java code (e.g., Banking_App.java), update the connection details:

<pre>
 String url = "jdbc:mysql://localhost:3306/bank_sys";
 String username = "your_mysql_username";
 String password = "your_mysql_password";
</pre>

### 4. Run the Application

Open the project in your Java IDE
Compile and run Banking_App.java
Follow on-screen prompts


🧑‍💻 Author<br>
Chetan Patil

