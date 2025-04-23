package repository;

import entity.Book;
import java.util.*;

public class BookRepository {
    private static List<Book> books = new ArrayList<>();

    static {
        books.add(new Book(1, "Book 1", "Author 1"));
        books.add(new Book(2, "Book 2", "Author 2"));
    }

    public static List<Book> getAllBooks() {
        return books;
    }

    public static Book getBookById(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    public static void addBook(Book book) {
        books.add(book);
    }

    public static boolean isBookExists(int id) {
        return books.stream().anyMatch(book -> book.getId() == id);
    }

    public static void removeBook(int id) {
        books.removeIf(book -> book.getId() == id);
    }
}
