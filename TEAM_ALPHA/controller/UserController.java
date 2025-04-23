package controller;

import database.LibraryDatabase;
import entity.*;
import exception.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Scanner;
public class UserController {
    public void viewBooks() {
        System.out.println("Fetching all available books...");
        LibraryDatabase.books.values().forEach(System.out::println);
    }

    public void borrowBook(String card, int bookId) throws Exception {
        System.out.println("Attempting to borrow book ID: " + bookId + " for user with card: " + card);
        Book book = LibraryDatabase.books.get(bookId);
        if (book == null) throw new BookNotFoundException("Book not found.");
        User user = LibraryDatabase.users.get(card);
        if (user == null) throw new Exception("User not found.");
        if (user.getBorrowedBooks().contains(bookId)) {
            throw new Exception("Book already borrowed by user.");
        }
        user.getBorrowedBooks().add(bookId);
        LocalDate today = LocalDate.now();
        // Create the Transaction with a null dueDate
        LibraryDatabase.transactions.add(new Transaction(card, bookId, "Borrowed", today, null));

        System.out.println("Book borrowed successfully.");
    }

    public void returnBook(String card, int bookId) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter today's date (yyyy-MM-dd): ");
        String todayInput = scanner.nextLine();

        LocalDate today;
        try {
            today = LocalDate.parse(todayInput, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            throw new Exception("Invalid date format. Please use yyyy-MM-dd.");
        }

        System.out.println("Attempting to return book ID: " + bookId + " for user with card: " + card);
        User user = LibraryDatabase.users.get(card);
        if (user == null || !user.getBorrowedBooks().contains(bookId)) {
            throw new Exception("Book not borrowed or user not found.");
        }

        Optional<Transaction> borrowTransaction = LibraryDatabase.transactions.stream()
            .filter(t -> t.getCard().equals(card) && t.getAction().equals("Borrowed") && t.getBookId() == bookId)
            .reduce((first, second) -> second);  // Get the latest borrow transaction

        double fine = 0.0;
        if (borrowTransaction.isPresent()) {
            LocalDate borrowDate = borrowTransaction.get().getDate();
            long daysBetween = ChronoUnit.DAYS.between(borrowDate, today);
            if (daysBetween > 7) {
                fine = (daysBetween - 7) * 5.0;
            }
        }

        user.getBorrowedBooks().remove((Integer) bookId);
        LibraryDatabase.transactions.add(new Transaction(card, bookId, "Returned", today, null));

        if (fine > 0) {
            System.out.println("Book returned late. Fine: Rs. " + fine);
        } else {
            System.out.println("Book returned on time. No fine.");
        }
    }

    public void viewTransactions(String card) {
        System.out.println("Fetching transaction history for card: " + card);
        LibraryDatabase.transactions.stream()
                .filter(t -> t.getCard().equals(card))
                .forEach(System.out::println);
    }
}
