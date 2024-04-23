package com.hotelreservationapp.models.Database.Tables;

import java.sql.Timestamp;

/**
 * Database table model
 * @author Griffin Graham, Joshua Espana
 */
public class Admin {
    private int adminId;
    private String username;
    private String password;
    private String email;
    private Timestamp createdAt;

    public Admin() {
    }

    public Admin(int adminID, String username, String password, String email, Timestamp createdAt) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.adminId = adminID;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
