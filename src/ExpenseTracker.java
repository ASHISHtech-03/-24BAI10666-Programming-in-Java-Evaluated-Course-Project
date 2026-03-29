import java.util.ArrayList;
import java.util.Scanner;

/**
 * ExpenseTracker.java
 * Main application class. Runs the menu loop and handles user interaction.
 *
 * Java Concepts Used:
 *   - Classes and Objects (Expense, FileManager)
 *   - ArrayList (dynamic list of expenses)
 *   - Scanner (reading user input)
 *   - Loops (while, for-each)
 *   - Conditionals and switch statements
 *   - File I/O (via FileManager)
 *   - Exception handling (try-catch)
 *   - String formatting (printf / String.format)
 */
public class ExpenseTracker {

    // The master list of all expenses (loaded from file at startup)
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Load any previously saved expenses from file
        expenses = FileManager.loadExpenses();

        System.out.println("========================================");
        System.out.println("   Welcome to Personal Expense Tracker  ");
        System.out.println("========================================");
        System.out.println("  " + expenses.size() + " expense(s) loaded from file.");

        boolean running = true;

        // Main menu loop — keeps running until user chooses Exit
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewAllExpenses();
                    break;
                case 3:
                    viewTotalSpent();
                    break;
                case 4:
                    viewByCategory();
                    break;
                case 5:
                    deleteExpense();
                    break;
                case 6:
                    FileManager.saveExpenses(expenses);
                    break;
                case 7:
                    FileManager.saveExpenses(expenses); // auto-save on exit
                    System.out.println("\n  Goodbye! Your expenses have been saved.");
                    running = false;
                    break;
                default:
                    System.out.println("  Invalid choice. Please enter 1-7.");
            }
        }

        scanner.close();
    }

    // ---------------------------------------------------------------
    // MENU
    // ---------------------------------------------------------------

    private static void printMenu() {
        System.out.println("\n----------------------------------------");
        System.out.println("  MENU");
        System.out.println("----------------------------------------");
        System.out.println("  1. Add New Expense");
        System.out.println("  2. View All Expenses");
        System.out.println("  3. View Total Spent");
        System.out.println("  4. View Spending by Category");
        System.out.println("  5. Delete an Expense");
        System.out.println("  6. Save to File");
        System.out.println("  7. Save & Exit");
        System.out.println("----------------------------------------");
    }

    // ---------------------------------------------------------------
    // FEATURE 1 — ADD EXPENSE
    // ---------------------------------------------------------------

    private static void addExpense() {
        System.out.println("\n--- Add New Expense ---");

        System.out.print("  Date (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();

        // Show category options
        System.out.println("  Categories: 1.Food  2.Travel  3.Shopping  4.Bills  5.Health  6.Other");
        int catChoice = readInt("  Choose category (1-6): ");
        String category = getCategory(catChoice);

        double amount = readDouble("  Amount (Rs.): ");

        System.out.print("  Note (brief description): ");
        String note = scanner.nextLine().trim();

        // Create the Expense object and add it to our list
        Expense newExpense = new Expense(date, category, amount, note);
        expenses.add(newExpense);

        System.out.println("  Expense added: " + newExpense);
    }

    /**
     * Maps a number 1-6 to a category name.
     */
    private static String getCategory(int choice) {
        switch (choice) {
            case 1: return "Food";
            case 2: return "Travel";
            case 3: return "Shopping";
            case 4: return "Bills";
            case 5: return "Health";
            default: return "Other";
        }
    }

    // ---------------------------------------------------------------
    // FEATURE 2 — VIEW ALL EXPENSES
    // ---------------------------------------------------------------

    private static void viewAllExpenses() {
        System.out.println("\n--- All Expenses ---");

        if (expenses.isEmpty()) {
            System.out.println("  No expenses recorded yet.");
            return;
        }

        System.out.printf("  %-4s %-12s %-12s %-10s %s%n",
                          "#", "Date", "Category", "Amount", "Note");
        System.out.println("  " + "-".repeat(60));

        // Loop through all expenses and print each one
        for (int i = 0; i < expenses.size(); i++) {
            Expense e = expenses.get(i);
            System.out.printf("  %-4d %-12s %-12s Rs.%-7.2f %s%n",
                              (i + 1), e.getDate(), e.getCategory(),
                              e.getAmount(), e.getNote());
        }
    }

    // ---------------------------------------------------------------
    // FEATURE 3 — TOTAL SPENT
    // ---------------------------------------------------------------

    private static void viewTotalSpent() {
        System.out.println("\n--- Total Spent ---");

        if (expenses.isEmpty()) {
            System.out.println("  No expenses recorded yet.");
            return;
        }

        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();   // accumulate total
        }

        System.out.printf("  Total number of expenses : %d%n", expenses.size());
        System.out.printf("  Total amount spent       : Rs. %.2f%n", total);
        System.out.printf("  Average per expense      : Rs. %.2f%n", total / expenses.size());
    }

    // ---------------------------------------------------------------
    // FEATURE 4 — VIEW BY CATEGORY
    // ---------------------------------------------------------------

    private static void viewByCategory() {
        System.out.println("\n--- Spending by Category ---");

        if (expenses.isEmpty()) {
            System.out.println("  No expenses recorded yet.");
            return;
        }

        // List of all possible categories
        String[] categories = {"Food", "Travel", "Shopping", "Bills", "Health", "Other"};

        System.out.printf("  %-14s %s%n", "Category", "Total Spent");
        System.out.println("  " + "-".repeat(30));

        for (String cat : categories) {
            double catTotal = 0;
            int count = 0;

            // Sum up all expenses that match this category
            for (Expense e : expenses) {
                if (e.getCategory().equalsIgnoreCase(cat)) {
                    catTotal += e.getAmount();
                    count++;
                }
            }

            // Only print categories that have at least one expense
            if (count > 0) {
                System.out.printf("  %-14s Rs. %.2f  (%d item%s)%n",
                                  cat, catTotal, count, count > 1 ? "s" : "");
            }
        }
    }

    // ---------------------------------------------------------------
    // FEATURE 5 — DELETE EXPENSE
    // ---------------------------------------------------------------

    private static void deleteExpense() {
        System.out.println("\n--- Delete Expense ---");

        if (expenses.isEmpty()) {
            System.out.println("  No expenses to delete.");
            return;
        }

        viewAllExpenses(); // show list first so user knows the numbers

        int index = readInt("  Enter # to delete (0 to cancel): ");

        if (index == 0) {
            System.out.println("  Deletion cancelled.");
            return;
        }

        if (index < 1 || index > expenses.size()) {
            System.out.println("  Invalid number.");
            return;
        }

        // ArrayList is 0-indexed, but we showed 1-indexed to the user
        Expense removed = expenses.remove(index - 1);
        System.out.println("  Deleted: " + removed);
    }

    // ---------------------------------------------------------------
    // INPUT HELPER METHODS
    // ---------------------------------------------------------------

    /**
     * Reads an integer from the user safely.
     * Keeps asking if the input is not a valid number.
     */
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a whole number.");
            }
        }
    }

    /**
     * Reads a double (decimal number) from the user safely.
     */
    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value < 0) {
                    System.out.println("  Amount cannot be negative.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a valid amount (e.g. 250.50).");
            }
        }
    }
}
