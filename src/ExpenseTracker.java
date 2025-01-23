import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.Statement;

public class ExpenseTracker {
    public static void addExpense(Scanner scanner) {
        try {

            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter category: ");
            String category = scanner.nextLine();

            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            String sql = "INSERT INTO expenses (amount, category, description, date) VALUES (?, ?, ?, ?)";
            try (Connection connection = DBconnect.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setDouble(1, amount);
                statement.setString(2, category);
                statement.setString(3, description);
                statement.setString(4, date);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Expense was added successfully");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while adding the expense: " + e.getMessage());
        }
    }


    public static void viewExpenses() {
        try (Connection connection = DBconnect.getConnection()) {
            String sql = "SELECT * FROM expenses";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("ID | Amount | Category | Description | Date");
            System.out.println("-------------------------------------------");
            while (resultSet.next()) {
                System.out.printf("%d | %.2f | %s | %s | %s%n",
                        resultSet.getInt("id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("category"),
                        resultSet.getString("description"),
                        resultSet.getDate("date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
