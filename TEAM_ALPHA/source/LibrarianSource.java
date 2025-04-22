package source;

import database.LibraryDatabase;
import entity.*;

public class LibrarianSource {
    public void addBook(String title, String author) {
        Book book = new Book(LibraryDatabase.nextBookId++, title, author);
        LibraryDatabase.books.add(book);
        System.out.println("Book added: " + title);
    }

    public void removeBook(int bookId) {
        LibraryDatabase.books.removeIf(book -> book.id == bookId);
        System.out.println("Book removed.");
    }

    public void manageUsers() {
        for (User user : LibraryDatabase.users) {
            System.out.println("User ID: " + user.id + " | Name: " + user.name);
        }
    }

    public void viewTransactions() {
        for (Transaction t : LibraryDatabase.transactions) {
            System.out.println("UserID: " + t.userId + " | BookID: " + t.bookId + " | Action: " + t.action);
        }
    }
}
