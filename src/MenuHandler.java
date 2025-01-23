import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuHandler {

    /**
     * Displays the main menu to the user and handles their choices
     * till they decide to log out.
     *
     * @param scanner     the Scanner to read user input
     * @param currentUser the currently logged-in user
     */
    public static void showMainMenu(Scanner scanner, User currentUser) {
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Generate Report");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        ExpenseHandler.handleAddExpense(scanner, currentUser);
                        break;
                    case 2:
                        ExpenseHandler.handleViewExpenses(currentUser);
                        break;
                    case 3:
                        ExpenseHandler.handleGenerateReport(scanner, currentUser);
                        break;
                    case 4:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }

            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }
}
