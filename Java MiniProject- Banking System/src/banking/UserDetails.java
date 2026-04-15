package banking;

// Represents a bank customer who can log in
public class UserDetails {

    private String username;
    private String password;
    private String linkedAccountNumber;

    public UserDetails(String username, String password, String linkedAccountNumber) {
        this.username = username;
        this.password = password;
        this.linkedAccountNumber = linkedAccountNumber;
    }

    public boolean authenticate(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    public String getUsername() {
        return username;
    }

    public String getLinkedAccountNumber() {
        return linkedAccountNumber;
    }
}
