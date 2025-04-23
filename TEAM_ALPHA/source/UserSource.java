package source;

import database.LibraryDatabase;
import entity.User;
import java.util.UUID;

public class UserSource {
    public void registerUser(int id, String name, String card, String password) {
        LibraryDatabase.users.put(card, new User(id, name, card));
        LibraryDatabase.userCredentials.put(card, password);
    }

    public boolean authenticateUser(String card, String password) {
        return LibraryDatabase.userCredentials.getOrDefault(card, "").equals(password);
    }

    public String generateLibraryCard() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
