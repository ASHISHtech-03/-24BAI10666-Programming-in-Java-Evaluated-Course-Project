import java.io.*;
import java.util.ArrayList;

/**
 * FileManager.java
 * Handles all file operations: saving expenses to a CSV file and loading them back.
 * Demonstrates: File I/O, BufferedReader, FileWriter, exception handling.
 */
public class FileManager {

    private static final String FILE_NAME = "expenses.csv";

    /**
     * Saves all expenses to expenses.csv.
     * Each expense is written as one CSV line.
     */
    public static void saveExpenses(ArrayList<Expense> expenses) {
        try {
            // FileWriter creates (or overwrites) the file
            FileWriter fw = new FileWriter(FILE_NAME);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Expense e : expenses) {
                bw.write(e.toCSV());
                bw.newLine(); // write each expense on its own line
            }

            bw.close(); // always close the file after writing
            System.out.println("  Data saved successfully.");

        } catch (IOException ex) {
            System.out.println("  Error saving data: " + ex.getMessage());
        }
    }

    /**
     * Loads expenses from expenses.csv into an ArrayList.
     * If the file does not exist yet, returns an empty list (first run).
     */
    public static ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();

        File file = new File(FILE_NAME);

        // If the file doesn't exist yet, just return an empty list
        if (!file.exists()) {
            return expenses;
        }

        try {
            FileReader fr = new FileReader(FILE_NAME);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) { // skip blank lines
                    try {
                        Expense e = Expense.fromCSV(line);
                        expenses.add(e);
                    } catch (Exception ex) {
                        // Skip any corrupted lines gracefully
                        System.out.println("  Skipping corrupted line: " + line);
                    }
                }
            }

            br.close();

        } catch (IOException ex) {
            System.out.println("  Error loading data: " + ex.getMessage());
        }

        return expenses;
    }
}
