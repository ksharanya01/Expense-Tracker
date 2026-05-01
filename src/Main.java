import java.io.*;
import java.util.*;

public class Main {

    // SAVE data to file
    public static void saveToFile(ArrayList<Expense> expenses) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("expenses.txt"));
            for (Expense e : expenses) {
                writer.write(e.toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving file");
        }
    }

    // LOAD data from file
    public static void loadFromFile(ArrayList<Expense> expenses) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("expenses.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                expenses.add(Expense.fromFileString(line));
            }
            reader.close();
        } catch (Exception e) {
            // No file found → ignore
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Expense> expenses = new ArrayList<>();

        loadFromFile(expenses);

        while (true) {
            System.out.println("\n==== Expense Tracker ====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Total Expense");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter category: ");
                    String category = sc.nextLine();

                    expenses.add(new Expense(title, amount, category));
                    saveToFile(expenses);

                    System.out.println("Expense added!");
                    break;

                case 2:
                    if (expenses.isEmpty()) {
                        System.out.println("No expenses found.");
                    } else {
                        for (int i = 0; i < expenses.size(); i++) {
                            Expense e = expenses.get(i);
                            System.out.println(i + ". " + e.title + " | " + e.amount + " | " + e.category);
                        }
                    }
                    break;

                case 3:
                    if (expenses.isEmpty()) {
                        System.out.println("No expenses to delete.");
                    } else {
                        for (int i = 0; i < expenses.size(); i++) {
                            Expense e = expenses.get(i);
                            System.out.println(i + ". " + e.title + " | " + e.amount);
                        }

                        System.out.print("Enter index to delete: ");
                        int index = sc.nextInt();
                        sc.nextLine();

                        if (index >= 0 && index < expenses.size()) {
                            expenses.remove(index);
                            saveToFile(expenses);
                            System.out.println("Expense deleted!");
                        } else {
                            System.out.println("Invalid index");
                        }
                    }
                    break;
                case 4:
                    double total = 0;
                    for (Expense e : expenses) {
                        total += e.amount;
                    }
                    System.out.println("Total Expense: Rs " + total);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}