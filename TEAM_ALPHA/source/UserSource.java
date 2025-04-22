package source;

import database.LibraryDatabase;
import entity.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class UserSource {
    Scanner sc = new Scanner(System.in);

    public void registerUser(int id, String name, String libraryCard) {
        LibraryDatabase.users.add(new User(id, name, libraryCard));
        System.out.println("User registered: " + name);
    }

    public void viewAvailableBooks() {
        for (Book b : LibraryDatabase.books) {
            if (b.isAvailable) {
                System.out.println("Book ID: " + b.id + ", Title: " + b.title + ", Author: " + b.author);
            }
        }
    }

    public void borrowBook(int userId, int bookId) {
        int activeBorrows = 0;
        double pendingFines = 0;

        for (Transaction t : LibraryDatabase.transactions) {
            if (t.userId == userId) {
                if (t.action.equals("BORROWED")) {
                    boolean returned = LibraryDatabase.transactions.stream()
                        .anyMatch(ret -> ret.userId == userId && ret.bookId == t.bookId && ret.action.equals("RETURNED"));
                    if (!returned) activeBorrows++;
                } else if (t.action.equals("RETURNED")) {
                    pendingFines += t.fine;
                }
            }
        }

        if (pendingFines > 0) {
            System.out.println("Cannot borrow. Please clear fine of ₹" + pendingFines + " first.");
            return;
        }

        if (activeBorrows >= 3) {
            System.out.println("Borrow limit reached (3 books). Please return a book first.");
            return;
        }

        for (Book book : LibraryDatabase.books) {
            if (book.id == bookId && book.isAvailable) {
                book.isAvailable = false;
                LibraryDatabase.transactions.add(new Transaction(userId, bookId, "BORROWED", LocalDate.now(), 0));
                System.out.println("Book borrowed successfully on " + LocalDate.now());
                return;
            }
        }
        System.out.println("Book not available.");
    }

    public void returnBook(int userId, int bookId) {
        LocalDate borrowDate = null;
        double fine = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Transaction t : LibraryDatabase.transactions) {
            if (t.userId == userId && t.bookId == bookId && t.action.equals("BORROWED")) {
                borrowDate = t.date;
                break;
            }
        }

        if (borrowDate == null) {
            System.out.println("No borrowing record found.");
            return;
        }

        System.out.print("Enter return date (yyyy-MM-dd): ");
        String input = sc.nextLine();
        LocalDate returnDate = LocalDate.parse(input, formatter);

        long days = ChronoUnit.DAYS.between(borrowDate, returnDate);
        if (days > 7) {
            fine = (days - 7) * 2;
        }

        for (Book book : LibraryDatabase.books) {
            if (book.id == bookId) {
                book.isAvailable = true;
                break;
            }
        }

        LibraryDatabase.transactions.add(new Transaction(userId, bookId, "RETURNED", returnDate, fine));
        System.out.println("Book returned on " + returnDate + ". Fine: Rs." + fine);
    }

    public int authenticateUser(String libraryCard) {
        for (User user : LibraryDatabase.users) {
            if (user.libraryCard.equals(libraryCard)) {
                return user.id;
            }
        }
        return -1;
    }
}
