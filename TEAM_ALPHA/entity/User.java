package entity;

public class User {
    public int id;
    public String name;
    public String libraryCard; // Used for authentication

    public User(int id, String name, String libraryCard) {
        this.id = id;
        this.name = name;
        this.libraryCard = libraryCard;
    }
}