package entity;

public class Book {
    public int id;
    public String title;
    public String author;
    public boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
}