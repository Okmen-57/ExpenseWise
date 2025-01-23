import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static User currentUser;
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--- Expense Tracker ---");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                int choice;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                    continue;
                }
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        AuthHandler.handleRegister(scanner);
                        break;

                    case 2:
                        currentUser = AuthHandler.handleLogin(scanner);
                        if (currentUser != null) {
                            MenuHandler.showMainMenu(scanner, currentUser);
                        }
                        break;

                    case 3:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
