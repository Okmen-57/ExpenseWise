import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Report {

    /**
     * Generates a report of total expense amount by category 
     * for a given user, within a specified date range
     *
     * @param userId    The ID of the user
     * @param startDate The start date (YYYY-MM-DD)
     * @param endDate   The end date (YYYY-MM-DD)
     * @return          Total expense in that category
     */
    public static Map<String, Double> generate(int userId, String startDate, String endDate) {
        Map<String, Double> reportData = new HashMap<>();

        String sql = "SELECT category, SUM(amount) AS total "
                   + "FROM expenses "
                   + "WHERE user_id = ? "
                   + "  AND expense_date BETWEEN ? AND ? "
                   + "GROUP BY category";

        try (Connection connection = DBconnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setString(2, startDate);
            statement.setString(3, endDate);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String category = resultSet.getString("category");
                    double total = resultSet.getDouble("total");
                    reportData.put(category, total);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }

        return reportData;
    }
}
