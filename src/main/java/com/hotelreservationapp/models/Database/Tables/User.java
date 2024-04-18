package com.hotelreservationapp.models.Database.Tables;

import java.sql.Timestamp;

/**
 * Database table model
 * @author Griffin Graham, Joshua Espana
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Timestamp createdAt;

    public User() {
    }

    public User(int userID, String username, String password, String email, String phoneNumber, Timestamp createdAt) {
        this.userId = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
