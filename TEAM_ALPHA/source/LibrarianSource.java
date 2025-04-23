package source;
import database.*;
import java.util.UUID;
public class LibrarianSource {
    public boolean authenticateLibrarian(String username, String password) {
        return LibraryDatabase.librarianCredentials.getOrDefault(username, "").equals(password);
    }

    public String generateLibraryCard() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
