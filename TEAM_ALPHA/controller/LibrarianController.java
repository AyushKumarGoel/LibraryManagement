package controller;

import database.LibraryDatabase;
import entity.Book;
import entity.Transaction;
import java.util.*;

public class LibrarianController {
    public void addBook(int id, String title, String author) throws Exception {
        if (LibraryDatabase.books.containsKey(id)) {
            throw new Exception("Book with this ID already exists.");
        }
        LibraryDatabase.books.put(id, new Book(id, title, author));
    }

    public void removeBook(int id) throws Exception {
        if (!LibraryDatabase.books.containsKey(id)) {
            throw new Exception("Book not found.");
        }
        LibraryDatabase.books.remove(id);
    }

    public void manageUsers() {
        System.out.println("Registered Users:");
        LibraryDatabase.users.forEach((k, v) -> System.out.println(v));
    }

    public void viewAllBooks() {
        if (LibraryDatabase.books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        LibraryDatabase.books.values().forEach(System.out::println);
    }

    public void viewTransactions() {
        LibraryDatabase.transactions.forEach(System.out::println);
    }
}
