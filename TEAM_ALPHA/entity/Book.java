package entity;

public class Book {
    private int id;
    private String title;
    private String author;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
    public int getId(){
        return id;
    }
    public String toString() {
        return id + ": " + title + " by " + author;
    }
}