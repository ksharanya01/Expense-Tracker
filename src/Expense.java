public class Expense {
    String title;
    double amount;
    String category;

    public Expense(String title, double amount, String category) {
        this.title = title;
        this.amount = amount;
        this.category = category;
    }

    // Convert object → file string
    public String toFileString() {
        return title + "," + amount + "," + category;
    }

    // Convert file string → object
    public static Expense fromFileString(String line) {
        String[] parts = line.split(",");
        return new Expense(parts[0], Double.parseDouble(parts[1]), parts[2]);
    }
}