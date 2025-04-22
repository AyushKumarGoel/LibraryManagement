package source;

import database.LibraryDatabase;
import entity.*;

public class UserSource {
    public void registerUser(String name) {
        User user = new User(LibraryDatabase.nextUserId++, name);
        LibraryDatabase.users.add(user);
        System.out.println("User registered: " + name);
    }

    public void viewAvailableBooks() {
        for (Book book : LibraryDatabase.books) {
            if (book.isAvailable) {
                System.out.println("ID: " + book.id + " | Title: " + book.title + " | Author: " + book.author);
            }
        }
    }

    public void borrowBook(int userId, int bookId) {
        for (Book book : LibraryDatabase.books) {
            if (book.id == bookId && book.isAvailable) {
                book.isAvailable = false;
                LibraryDatabase.transactions.add(new Transaction(userId, bookId, "BORROWED"));
                System.out.println("Book borrowed successfully.");
                return;
            }
        }
        System.out.println("Book not available.");
    }

    public void returnBook(int userId, int bookId) {
        for (Book book : LibraryDatabase.books) {
            if (book.id == bookId && !book.isAvailable) {
                book.isAvailable = true;
                LibraryDatabase.transactions.add(new Transaction(userId, bookId, "RETURNED"));
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("Invalid return.");
    }
}
