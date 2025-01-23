import java.util.Scanner;

public class AuthHandler {

    public static void handleRegister(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String pass = scanner.nextLine().trim();

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        boolean success = UserRepo.register(username, pass, email);
        if (success) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    public static User handleLogin(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String pass = scanner.nextLine().trim();

        User user = UserRepo.login(username, pass);
        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getUsername());
        } else {
            System.out.println("Invalid username or pass, please try again.");
        }
        return user;
    }
}
