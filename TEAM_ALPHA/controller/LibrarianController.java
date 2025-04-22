package controller;

import source.LibrarianSource;

public class LibrarianController {
    private final LibrarianSource librarianSource = new LibrarianSource();

    public void addBook(String title, String author) {
        librarianSource.addBook(title, author);
    }

    public void removeBook(int bookId) {
        librarianSource.removeBook(bookId);
    }

    public void manageUsers() {
        librarianSource.manageUsers();
    }

    public void viewTransactions() {
        librarianSource.viewTransactions();
    }
}
