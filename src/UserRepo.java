import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {

    /**
     * Registers a new user, and stores the password as a text.
     *
     * @param username The chosen username
     * @param password The user’s chosen password (stored in plain text)
     * @param email    The user’s email address
     * @return         True if registration succeeded, false otherwise
     */
    public static boolean register(String username, String password, String email) {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

        try (Connection connection = DBconnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    /**
     * Login by matching both username and password in the Database.
     *
     * @param username The username entered
     * @param password The password (plain text)
     * @return A User object if login is successful; null otherwise
     */
    public static User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? LIMIT 1";

        try (Connection connection = DBconnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("email")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error during login: " + e.getMessage());
        }

        return null;
    }
}
