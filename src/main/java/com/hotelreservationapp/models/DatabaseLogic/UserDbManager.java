package com.hotelreservationapp.models.DatabaseLogic;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hotelreservationapp.models.Database.Tables.User;

/**
 * Handles the database management for the user table.
 * @author Griffin Graham, Joshua Espana
 */
public class UserDbManager extends DbManagerBase {
    public  UserDbManager(String url, String username, String password){
        super(url, username, password);
    }

    public User createUser(User user){
        return createUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getPhoneNumber());
    }

    /**
     * Creates a new user.
     * @param username
     * @param password
     * @param email
     * @param phone
     * @return The newly created user. If failed to create, returns null.
     */
    public User createUser(String username, String password, String email, String phone){
        User user = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Users(username, password, email, phone_number, created_at) values (?,?,?,?,now())",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int key = -1;
            if (rs.next()) {
                key = rs.getInt(1);
            }
            if(key > 0){
                user = getUser(key);
            }
            conn.close();
        }
        catch (Exception e){
            String msg = e.getMessage();
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Gets the user with the specific email provided.
     * @param email
     * @return
     */
    public User getUser(String email){
        User user = new User();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Users WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String phone = rs.getString("phone_number");
                Timestamp createdAt = rs.getTimestamp("created_at");
                user = new User(userID, username, password, email, phone,createdAt);
            }
            conn.close();
            return user;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the user with the specific ID provided.
     * @param userID
     * @return The user. If not found, returns null.
     */
    public User getUser(int userID){
        User user = new User();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Users WHERE user_id = ?");
            preparedStatement.setInt(1, userID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone_number");
                Timestamp createdAt = rs.getTimestamp("created_at");
                user = new User(userID, username, password, email, phone,createdAt);
            }
            conn.close();
        }
        catch (Exception e){

        }
        return user;
    }

    /**
     * Gets all the users in the database.
     * @return
     */
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Users");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone_number");
                Timestamp createdAt = rs.getTimestamp("created_at");
                users.add(new User(userID, username, password, email, phone,createdAt));
            }
            conn.close();
        }
        catch (Exception e){

        }
        return users;
    }
}