import controller.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserController uc = new UserController();
        LibrarianController lc = new LibrarianController();
        Scanner sc = new Scanner(System.in);

        lc.addBook("Atomic Habits", "James Clear");
        lc.addBook("Java Basics", "Oracle");

        uc.registerUser("Ayush");

        System.out.println("\n--- Available Books ---");
        uc.viewBooks();

        System.out.println("\n--- Borrow Book ---");
        uc.borrowBook(1, 1);  // Ayush borrows book 1

        System.out.println("\n--- Return Book ---");
        uc.returnBook(1, 1);  // Ayush returns book 1

        System.out.println("\n--- View All Users ---");
        lc.manageUsers();

        System.out.println("\n--- View Transactions ---");
        lc.viewTransactions();
    }
}
