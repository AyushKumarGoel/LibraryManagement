import controller.*;

public class LibraryApp {
    public static void main(String[] args) {
        UserController user = new UserController();
        LibrarianController librarian = new LibrarianController();

        librarian.addBook(1, "Java Basics", "Ayush");
        librarian.addBook(2, "Python Pro", "Sharma");

        String cardId = librarian.generateCard();
        user.registerUser(100, "Ayush Goel", cardId);

        if (user.authenticate(cardId)) {
            user.viewBooks();
            user.borrow(100, 1);
            user.returnBook(100, 1);
        }

        librarian.manageUsers();
        librarian.viewTransactions();
    }
}
