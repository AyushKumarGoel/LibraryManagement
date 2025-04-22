package controller;

import source.UserSource;

public class UserController {
    private final UserSource userSource = new UserSource();

    public void registerUser(String name) {
        userSource.registerUser(name);
    }

    public void viewBooks() {
        userSource.viewAvailableBooks();
    }

    public void borrowBook(int userId, int bookId) {
        userSource.borrowBook(userId, bookId);
    }

    public void returnBook(int userId, int bookId) {
        userSource.returnBook(userId, bookId);
    }
}
