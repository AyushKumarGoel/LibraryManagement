import controller.*;
import database.LibraryDatabase;
import java.util.Scanner;
import source.LibrarianSource;
import source.UserSource;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserController userController = new UserController();
        LibrarianController librarianController = new LibrarianController();
        UserSource userSource = new UserSource();
        LibrarianSource librarianSource = new LibrarianSource();
    
        while (true) { // Wrap entire role-selection in a loop
            System.out.print("Enter role (user/librarian): ");
            String role = sc.nextLine();
    
            if (role.equalsIgnoreCase("librarian")) {
                System.out.print("Enter librarian password: ");
                String password = sc.nextLine();
                if (!password.equals(LibraryDatabase.librarianPassword)) {
                    System.out.println("Authentication failed!");
                    continue;
                }
                while (true) {
                    System.out.println("\n1. Add Book\n2. Remove Book\n3. Manage Users\n4. View Transactions\n5. Generate Library Card\n6. Register User\n7. Exit");    
                    System.out.print("Choose option: ");
                    int opt = Integer.parseInt(sc.nextLine());
    
                    switch (opt) {
                        case 1 -> {
                            System.out.print("Book ID: "); int id = Integer.parseInt(sc.nextLine());
                            System.out.print("Title: "); String title = sc.nextLine();
                            System.out.print("Author: "); String author = sc.nextLine();
                            librarianController.addBook(id, title, author);
                        }
                        case 2 -> {
                            System.out.print("Book ID to remove: ");
                            librarianController.removeBook(Integer.parseInt(sc.nextLine()));
                        }
                        case 3 -> librarianController.manageUsers();
                        case 4 -> librarianController.viewTransactions();
                        case 5 -> {
                            String card = librarianSource.generateLibraryCard();
                            System.out.println("Generated Library Card: " + card);
                        }
                        case 6 -> {
                            System.out.print("User ID: "); int userId = Integer.parseInt(sc.nextLine());
                            System.out.print("User Name: "); String name = sc.nextLine();
                            System.out.print("Library Card: "); String card = sc.nextLine();
                            userSource.registerUser(userId, name, card);
                        }
                        case 7 -> {
                            System.out.println("Returning to role selection...");
                            return;
                        }
                        default -> System.out.println("Invalid option");
                    }
                    
    
                    if (opt == 6) break;
                }
            } else if (role.equalsIgnoreCase("user")) {
                System.out.print("Enter your library card: ");
                String card = sc.nextLine();
                int userId = userSource.authenticateUser(card);
                if (userId == -1) {
                    System.out.println("Authentication failed!");
                    continue;
                }
    
                while (true) {
                    System.out.println("\n1. View Available Books\n2. Borrow Book\n3. Return Book\n4. Back to Role Selection");
                    System.out.print("Choose option: ");
                    int opt = Integer.parseInt(sc.nextLine());
    
                    switch (opt) {
                        case 1 -> userController.viewBooks();
                        case 2 -> {
                            System.out.print("Book ID: ");
                            userController.borrow(userId, Integer.parseInt(sc.nextLine()));
                        }
                        case 3 -> {
                            System.out.print("Book ID to return: ");
                            userController.returnBook(userId, Integer.parseInt(sc.nextLine()));
                        }
                        case 4 -> {
                            System.out.println("Returning to role selection...");
                            break;
                        }
                        default -> System.out.println("Invalid option");
                    }
    
                    if (opt == 4) break;
                }
            } else {
                System.out.println("Invalid role.");
            }
        }
    }
    
}
