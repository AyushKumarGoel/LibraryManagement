package entity;

public class User {
    public int id;
    public String name;
    public String libraryCardId;

    public User(int id, String name, String libraryCardId) {
        this.id = id;
        this.name = name;
        this.libraryCardId = libraryCardId;
    }
}