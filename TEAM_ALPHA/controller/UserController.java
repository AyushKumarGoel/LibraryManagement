package controller;

import source.UserSource;

public class UserController {
    UserSource userSource = new UserSource();

    public void registerUser(int id, String name, String cardId) {
        userSource.registerUser(id, name, cardId);
    }

    public boolean authenticate(String cardId) {
        return userSource.authenticate(cardId);
    }

    public void viewBooks() {
        userSource.viewAvailableBooks();
    }

    public void borrow(int userId, int bookId) {
        userSource.borrowBook(userId, bookId);
    }

    public void returnBook(int userId, int bookId) {
        userSource.returnBook(userId, bookId);
    }
}