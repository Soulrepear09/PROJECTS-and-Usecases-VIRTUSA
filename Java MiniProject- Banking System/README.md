## Problem Statement

Banks require secure and efficient systems to manage customer transactions. Build a Java-based banking simulation.
---

## Features

- Create Savings or Current bank accounts
- Deposit money into an account
- Withdraw money with balance validation
- Transfer money between two accounts
- View complete transaction history with timestamps
- Basic login authentication (username and password)
- Overdraft support for Current accounts
- Interest calculation for Savings accounts

---

## How it Works
When the customer/user starts the application, the system will ask the user to create an account or login to existing account. After this, the system shows user to choose different options like financial operations that frequently performed in banks like checking balance or deposit money. These were operated by account portal in dashboard.

---

## Technologies Used

- Java (JDK 17+)
- Object-Oriented Programming (OOP)
- Collections Framework — ArrayList, HashMap
- IntelliJ IDEA (IDE)

---

## OOP Concepts Applied

| Concept | Where Used |
|---|---|
| Abstraction | AccountRecords is an abstract class |
| Inheritance | SavingsAccount and CurrentAccount extend AccountRecords |
| Encapsulation | All fields are private, accessed via methods |
| Polymorphism | withdraw() behaves differently for each account type |

---

## Project Structure
src/
└── banking/
├── BankTransaction.java     → Records each money movement
├── AccountRecords.java      → Abstract parent class for accounts
├── SavingsAccount.java      → Savings account with interest
├── CurrentAccount.java      → Current account with overdraft
├── UserDetails.java         → Stores login credentials
├── BankAdmin.java           → Manages all accounts and users
└── BankDashboard.java       → Main class, console menu

---

---

## ▶️ How to Run

1. Clone or download this repository
2. Open the src folder in IntelliJ IDEA
3. Run BankDashboard.java
4. Follow the console menu to create an account and perform transactions

---

## 📸 Output Screenshots

### 1. Create Account
### 2. Login
### 3. Deposit Money
### 4. Withdraw Money
### 5. Transfer Money
### 6. Transaction History

Images were added in seperate folder

---


