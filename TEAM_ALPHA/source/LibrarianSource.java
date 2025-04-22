package source;

import database.LibraryDatabase;
import entity.*;
import java.util.UUID;

public class LibrarianSource {
    public void addBook(int id, String title, String author) {
        LibraryDatabase.books.add(new Book(id, title, author));
        System.out.println("Book added: " + title);
    }

    public void removeBook(int id) {
        LibraryDatabase.books.removeIf(book -> book.id == id);
        System.out.println("Book removed.");
    }

    public void manageUsers() {
        for (User user : LibraryDatabase.users) {
            System.out.println("User ID: " + user.id + ", Name: " + user.name);
        }
    }

    public void viewAllTransactions() {
        for (Transaction t : LibraryDatabase.transactions) {
            System.out.println("User ID: " + t.userId + ", Book ID: " + t.bookId + ", Action: " + t.action + ", Date: " + t.date + ", Fine: ₹" + t.fine);
        }
    }

    public String generateLibraryCard() {
        return UUID.randomUUID().toString();
    }
    public void viewAllBooks() {
        for (Book b : LibraryDatabase.books) {
            String status = b.isAvailable ? "Available" : "Borrowed";
            System.out.println("Book ID: " + b.id + ", Title: " + b.title + ", Author: " + b.author + ", Status: " + status);
        }
    }
    
}
