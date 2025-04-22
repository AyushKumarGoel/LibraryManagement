package entity;

public class Transaction {
    public int userId;
    public int bookId;
    public String action;

    public Transaction(int userId, int bookId, String action) {
        this.userId = userId;
        this.bookId = bookId;
        this.action = action;
    }
}
