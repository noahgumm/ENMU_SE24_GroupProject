package com.hotelreservationapp.models;

public class UserModel extends UserModelBase {
    public boolean authenticateUser(String username, String password) {
        return true;
    }

    public void addUser(String username, String password) {
        // Add user to the database
    }

    public boolean validateCredentials(String username, String password) {
        // Validate credentials
        return true;
    }
    
}
