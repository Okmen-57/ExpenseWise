import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpenseHandler {

    public static void handleAddExpense(Scanner scanner, User currentUser) {
        if (currentUser == null) {
            System.err.println("No user is currently logged in. Cannot add expense.");
            return;
        }

        try {
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter category: ");
            String category = scanner.nextLine().trim();

            System.out.print("Enter description: ");
            String description = scanner.nextLine().trim();

            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine().trim();

            boolean success = Expense.addExpense(currentUser.getId(), amount, category, description, date);
            if (success) {
                System.out.println("Expense added successfully!");
            } else {
                System.out.println("Failed to add expense. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Invalid input for amount. Operation cancelled.");
            scanner.nextLine();
        }
    }

    public static void handleViewExpenses(User currentUser) {
        if (currentUser == null) {
            System.err.println("No user is currently logged in. Cannot view expenses.");
            return;
        }

        List<Expense> expenses = Expense.getExpenses(currentUser.getId());
        if (expenses.isEmpty()) {
            System.out.println("No expenses found for user: " + currentUser.getUsername());
        } else {
            System.out.println("\n--- Your Expenses ---");
            for (Expense exp : expenses) {
                exp.print();
            }
        }
    }

    public static void handleGenerateReport(Scanner scanner, User currentUser) {
        if (currentUser == null) {
            System.err.println("No user is currently logged in. Cannot generate report.");
            return;
        }

        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine().trim();

        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine().trim();

        Map<String, Double> reportData = Report.generate(currentUser.getId(), startDate, endDate);

        if (reportData.isEmpty()) {
            System.out.println("No expenses found in the given date range.");
        } else {
            System.out.println("\n--- Expense Report by Category ---");
            for (Map.Entry<String, Double> entry : reportData.entrySet()) {
                System.out.printf("Category: %s | Total: %.2f%n", entry.getKey(), entry.getValue());
            }
        }
    }
}
