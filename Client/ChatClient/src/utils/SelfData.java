package utils;

public class SelfData {
    private String username;

    public SelfData(String username) {
        this.username = username;
    }

    // Set the username to a value.
    public void setUsername(String username) {
        this.username = username;
    }

    // Get the username
    public String getUsername() {
        return this.username;
    }
}
