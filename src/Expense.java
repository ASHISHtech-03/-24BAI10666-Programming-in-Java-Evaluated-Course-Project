/**
 * Expense.java
 * Represents a single expense entry with amount, category, date, and a note.
 */
public class Expense {

    // Fields (instance variables) for each expense
    private String date;       // e.g. "2024-01-15"
    private String category;   // e.g. "Food", "Travel"
    private double amount;     // e.g. 250.00
    private String note;       // e.g. "Lunch at canteen"

    // Constructor: called when we create a new Expense object
    public Expense(String date, String category, double amount, String note) {
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.note = note;
    }

    // Getters: allow other classes to read private fields
    public String getDate()     { return date; }
    public String getCategory() { return category; }
    public double getAmount()   { return amount; }
    public String getNote()     { return note; }

    /**
     * Converts this expense to a CSV line for saving to file.
     * Format: date,category,amount,note
     */
    public String toCSV() {
        return date + "," + category + "," + amount + "," + note;
    }

    /**
     * Creates an Expense object from a CSV line read from file.
     * This is a static "factory" method.
     */
    public static Expense fromCSV(String csvLine) {
        String[] parts = csvLine.split(",", 4); // split into at most 4 parts
        String date     = parts[0].trim();
        String category = parts[1].trim();
        double amount   = Double.parseDouble(parts[2].trim());
        String note     = parts[3].trim();
        return new Expense(date, category, amount, note);
    }

    /**
     * Returns a nicely formatted string to display this expense on screen.
     */
    @Override
    public String toString() {
        return String.format("  [%s] %-12s  Rs. %8.2f   (%s)", date, category, amount, note);
    }
}
