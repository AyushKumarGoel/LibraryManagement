package database;

import entity.*;
import java.util.*;

public class LibraryDatabase {
    public static List<Book> books = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Transaction> transactions = new ArrayList<>();
    public static Set<String> issuedCards = new HashSet<>();
}
