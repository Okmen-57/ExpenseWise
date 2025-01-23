import java.util.Objects;

public class User {
    private final int id;
    private final String username;
    private final String email;

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User other = (User) o;
        return id == other.id &&
               Objects.equals(username, other.username) &&
               Objects.equals(email, other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email);
    }
}
