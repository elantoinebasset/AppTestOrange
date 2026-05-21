package com.orange.demo;

public class Account {
    private String username;
    private String email;
    private String password;
    private boolean loggedIn;

    public Account() {
        this.username = "Utilisateur";
        this.email = "user@example.com";
        this.password = "password";
        this.loggedIn = false;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password;}

    public boolean getLoggedIn() { return loggedIn; }
    public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn;
    if (loggedIn) {
        System.out.println("logged in");
    } else {
        System.out.println("not logged in");
    }
    if (username != null) {
        System.out.println("Username: " + username
                        + " | Email: " + email
                        + " | Password: " + password);
    }
}
}
