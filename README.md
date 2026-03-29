# Personal Expense Tracker

A simple, beginner-friendly Java console application to track your daily expenses, view summaries by category, and save/load your data from a CSV file.

---

## Problem Statement

Students and young adults often lose track of where their money goes each month. This tool gives you a lightweight, no-internet-required way to log expenses, view totals, and understand your spending habits — all from the terminal.

---

## Features

| Feature | Description |
|---|---|
| Add Expense | Log amount, date, category, and a short note |
| View All | See every expense in a formatted table |
| Total Spent | View total, count, and average per expense |
| By Category | Breakdown of spending across Food, Travel, Bills, etc. |
| Delete Entry | Remove a specific expense by number |
| Save / Load | Data saved to `expenses.csv` and loaded automatically on next run |

---

## Prerequisites

- Java JDK 8 or higher installed
- A terminal / command prompt

Check if Java is installed:
```bash
java -version
```

If not installed, download from: https://www.oracle.com/java/technologies/downloads/

---

## How to Run

### Step 1 — Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/ExpenseTracker.git
cd ExpenseTracker
```

### Step 2 — Compile the source files
```bash
cd src
javac Expense.java FileManager.java ExpenseTracker.java
```

### Step 3 — Run the application
```bash
java ExpenseTracker
```

> **Note:** The `expenses.csv` file will be created automatically in the same folder where you run the program. Your data persists across sessions.

---

## Project Structure

```
ExpenseTracker/
├── src/
│   ├── Expense.java          # Model class — represents one expense
│   ├── FileManager.java      # Handles reading/writing expenses.csv
│   └── ExpenseTracker.java   # Main class — menu loop & user interaction
├── expenses.csv              # Auto-generated data file (created at runtime)
└── README.md
```

---

## Sample Session

```
========================================
   Welcome to Personal Expense Tracker
========================================
  0 expense(s) loaded from file.

----------------------------------------
  MENU
----------------------------------------
  1. Add New Expense
  2. View All Expenses
  3. View Total Spent
  4. View Spending by Category
  5. Delete an Expense
  6. Save to File
  7. Save & Exit
----------------------------------------
Enter choice: 1

--- Add New Expense ---
  Date (YYYY-MM-DD): 2024-03-15
  Categories: 1.Food  2.Travel  3.Shopping  4.Bills  5.Health  6.Other
  Choose category (1-6): 1
  Amount (Rs.): 120
  Note (brief description): Lunch at college canteen
  Expense added:   [2024-03-15] Food          Rs.   120.00   (Lunch at college canteen)
```

---

## Java Concepts Demonstrated

- **Classes & Objects** — `Expense` class encapsulates expense data
- **ArrayList** — dynamic list to store all expenses
- **Scanner** — reading user input from the console
- **File I/O** — `FileWriter`, `BufferedWriter`, `FileReader`, `BufferedReader`
- **Exception Handling** — `try-catch` for input validation and file errors
- **Loops** — `while` for menu, `for-each` for totals and display
- **String Formatting** — `String.format` / `printf` for aligned tables
- **Static Methods** — utility methods in `FileManager`

---

## Author

**Ashish Kumar Rai**  
24BAI10666 
VIT Bhopal University  
Programming in Java — Evaluated Course Project

---

## 📄 License

This project is submitted as part of an academic course. Feel free to use it for learning purposes.
