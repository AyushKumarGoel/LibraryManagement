package entity;

import java.time.LocalDate;

public class Transaction {
    public int userId;
    public int bookId;
    public String action; // BORROWED or RETURNED
    public LocalDate date;
    public double fine;

    public Transaction(int userId, int bookId, String action, LocalDate date, double fine) {
        this.userId = userId;
        this.bookId = bookId;
        this.action = action;
        this.date = date;
        this.fine = fine;
    }
}