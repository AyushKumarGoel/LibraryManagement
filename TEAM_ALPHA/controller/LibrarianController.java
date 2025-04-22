package controller;

import source.LibrarianSource;

public class LibrarianController {
    LibrarianSource librarianSource = new LibrarianSource();

    public void addBook(int id, String title, String author) {
        librarianSource.addBook(id, title, author);
    }

    public void removeBook(int id) {
        librarianSource.removeBook(id);
    }

    public void manageUsers() {
        librarianSource.manageUsers();
    }

    public void viewTransactions() {
        librarianSource.viewAllTransactions();
    }

    public String generateCard() {
        return librarianSource.generateLibraryCard();
    }
}