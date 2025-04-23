import controller.LibrarianController;
import controller.UserController;
import database.LibraryDatabase;
import java.util.InputMismatchException;
import java.util.Scanner;
import source.LibrarianSource;
import source.UserSource;

public class LibraryApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserSource userSource = new UserSource();
    private static final LibrarianSource librarianSource = new LibrarianSource();
    private static final UserController userController = new UserController();
    private static final LibrarianController librarianController = new LibrarianController();

    public static void main(String[] args) {
        LibraryDatabase.librarianCredentials.put("admin", "admin123"); // Default admin
        boolean running = true;
        while (running) {
            try {
                System.out.println("\n===== Library Management System =====");
                System.out.println("1. Register User");
                System.out.println("2. Login as User");
                System.out.println("3. Login as Librarian");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> registerUser();
                    case 2 -> loginUser();
                    case 3 -> loginLibrarian();
                    case 4 -> {
                        System.out.println("Exiting... Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear the invalid input
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter User ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Set Password: ");
        String password = scanner.nextLine();
        String card = librarianSource.generateLibraryCard();
        userSource.registerUser(id, name, card, password);
        System.out.println("User registered successfully. Your Library Card: " + card);
    }

    private static void loginUser() {
        System.out.print("Enter Library Card: ");
        String card = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (!userSource.authenticateUser(card, password)) {
            System.out.println("Authentication failed.");
            return;
        }

        boolean loggedIn = true;
        while (loggedIn) {
            try {
                System.out.println("\n===== User Menu =====");
                System.out.println("1. View All Books");
                System.out.println("2. Borrow Book");
                System.out.println("3. Return Book");
                System.out.println("4. View Transaction History");
                System.out.println("5. Exit");
                System.out.println("6. Logout");
                System.out.print("Enter choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> userController.viewBooks();
                    case 2 -> {
                        System.out.print("Enter Book ID to borrow: ");
                        int id = scanner.nextInt();
                        try {
                            userController.borrowBook(card, id);
                            System.out.println("Book borrowed successfully.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter Book ID to return: ");
                        int id = scanner.nextInt();
                        try {
                            userController.returnBook(card, id);
                            System.out.println("Book returned successfully.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 4 -> userController.viewTransactions(card);
                    case 5 -> {
                        System.out.println("Exiting application. Goodbye!");
                        System.exit(0);
                    }
                    case 6 -> {
                        System.out.println("Logging out...");
                        loggedIn = false;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void loginLibrarian() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (!librarianSource.authenticateLibrarian(username, password)) {
            System.out.println("Authentication failed.");
            return;
        }

        boolean loggedIn = true;
        while (loggedIn) {
            try {
                System.out.println("\n===== Librarian Menu =====");
                System.out.println("1. Add Book");
                System.out.println("2. Remove Book");
                System.out.println("3. Manage Users");
                System.out.println("4. View All Books");
                System.out.println("5. View All Transactions");
                System.out.println("6. Exit Application");
                System.out.println("9. Logout");
                System.out.print("Enter choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Book ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Book Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Author Name: ");
                        String author = scanner.nextLine();
                        try {
                            librarianController.addBook(id, title, author);
                            System.out.println("Book added successfully.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 2 -> {
                        System.out.print("Enter Book ID to remove: ");
                        int id = scanner.nextInt();
                        try {
                            librarianController.removeBook(id);
                            System.out.println("Book removed successfully.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 3 -> librarianController.manageUsers();
                    case 4 -> librarianController.viewAllBooks();
                    case 5 -> librarianController.viewTransactions();
                    case 6 -> {
                        System.out.println("Exiting application. Goodbye!");
                        System.exit(0);
                    }
                    case 9 -> {
                        System.out.println("Logging out...");
                        loggedIn = false;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }
}
