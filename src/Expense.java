import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Expense {
    private final int id;
    private final double amount;
    private final String category;
    private final String description;
    private final String expense_date;

    public Expense(int id, double amount, String category, String description, String expense_date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.expense_date = expense_date;
    }

    public Expense(double amount, String category, String description, String expense_date) {
        this(0, amount, category, description, expense_date);
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return expense_date;
    }

    @Override
    public String toString() {
        return String.format("Expense{id=%d, amount=%.2f, category='%s', description='%s', expense_date='%s'}",
                id, amount, category, description, expense_date);
    }

    public void print() {
        System.out.println(this.toString());
    }

    /**
     * Inserts a new expense in the database for the given user.
     *
     * @param userId      The ID of the user to which this expense belongs.
     * @param amount      The amount spent.
     * @param category    The category of the expense (e.g. "Groceries").
     * @param description Description provided by user
     * @param date        The date of the expense (YYYY-MM-DD).
     * @return            true if the insert succeeded, false otherwise.
     */
    public static boolean addExpense(int userId, double amount, String category, String description, String date) {
        String sql = "INSERT INTO expenses (user_id, amount, category, description, expense_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBconnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setDouble(2, amount);
            statement.setString(3, category);
            statement.setString(4, description);
            statement.setString(5, date);

            return (statement.executeUpdate() > 0);

        } catch (SQLException e) {
            System.err.println("Error adding expense: " + e.getMessage());
            return false;
        }
    }

    /**
     * Gets all expenses for a given user from the database
     *
     * @param userId    The ID of the user whose expenses we want to fetch
     * @return          A list of expenses
     */
    public static List<Expense> getExpenses(int userId) {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT * FROM expenses WHERE user_id = ?";

        try (Connection connection = DBconnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Expense expense = new Expense(
                            resultSet.getInt("id"),
                            resultSet.getDouble("amount"),
                            resultSet.getString("category"),
                            resultSet.getString("description"),
                            resultSet.getString("expense_date")

                    );
                    expenses.add(expense);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving expenses: " + e.getMessage());
        }

        return expenses;
    }
}
