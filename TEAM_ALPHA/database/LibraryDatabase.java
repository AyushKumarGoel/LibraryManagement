package database;
import entity.*;
import java.util.*;

public class LibraryDatabase {
    public static Map<Integer, Book> books = new HashMap<>();
    public static Map<String, User> users = new HashMap<>();
    public static List<Transaction> transactions = new ArrayList<>();
    public static Map<String, String> librarianCredentials = new HashMap<>();
    public static Map<String, String> userCredentials = new HashMap<>();
}
