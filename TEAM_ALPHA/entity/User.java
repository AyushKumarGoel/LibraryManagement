package entity;

import java.util.*;

public class User {
    private int id;
    private String name;
    private String libraryCard;
    private List<Integer> borrowedBooks = new ArrayList<>();

    public User(int id, String name, String card) {
        this.id = id;
        this.name = name;
        this.libraryCard = card;
    }

    public List<Integer> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String toString() {
        return id + ": " + name + " (Card: " + libraryCard + ")";
    }
}