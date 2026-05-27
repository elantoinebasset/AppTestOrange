package com.orange.demo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //colonne clé qui permet à Hibernate de savoir comment identifier chaque ligne de ma table
    private String username;
    private String email;
    private String password;
    private boolean loggedIn;
    private int whatsNumber;

    public Account() {
        this.username = "Utilisateur";
        this.email = "user@example.com";
        this.password = "passdedeword";
        this.loggedIn = false;
        // this.whatsNumber = 1;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password;}

    public int getWhatsNumber() { return whatsNumber; }
    public void setWhatsNumber(int WhatsNumber) { this.whatsNumber = WhatsNumber;}

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
