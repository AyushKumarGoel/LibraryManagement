package database;

import entity.*;

import java.util.ArrayList;

public class LibraryDatabase {
    public static ArrayList<Book> books = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Transaction> transactions = new ArrayList<>();
    public static int nextBookId = 1;
    public static int nextUserId = 1;
}
