package com.hotelreservationapp.models;

/**
 * Abstract class that defines the base user model.
 */
public abstract class UserModelBase {
    protected String username;
    protected String password;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String phoneNumber;


    /**
     * Authenticates a user based on the provided username and password against the user in the database.
     * @param username Username of the user
     * @param password Password of the user
     * @return True if the user is authenticated, false otherwise
     */
    public abstract boolean authenticateUser(String username, String password);

    /**
     * Adds a new user to the database. The users personal information can be provided at a later stage.
     * @param username Username of the user
     * @param password Password of the user
     */
    public abstract void addUser(String username, String password); 


    /**
     * Validates the provided credentials against the database. Username should be unique (not already taken) and password should meet the requirements.
     * @param username Username of the user
     * @param password Password of the user
     * @return True if the credentials are valid, false otherwise
     */
    public abstract boolean validateCredentials(String username, String password);
}
