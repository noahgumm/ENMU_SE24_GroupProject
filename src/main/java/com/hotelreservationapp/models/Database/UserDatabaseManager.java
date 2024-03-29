package com.hotelreservationapp.models.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotelreservationapp.models.UserModel;

public class UserDatabaseManager extends DatabaseManager implements IDatabaseFunctionality<UserModel>{
    //the databasemanager class contains the database connection for use.


    @Override
    public boolean create(UserModel object) {
        // Create user
        // write SQL here
        String sql = "INSERT INTO users (username, password, email,phone_number,created_at) VALUES (?, ?, ?,?,?);";
        int numRows = 0;
        try {
            databaseConnector.connect();
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setString(1, object.getUsername());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getEmail());
            statement.setString(4, object.getPhoneNumber());
            statement.setString(5, new Date().toString());
            numRows = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return numRows > 0;
    }

    @Override
    public boolean update(UserModel object) {
        // Update user
        //write SQL here

        String sql = "UPDATE users SET username = ?, password = ?, email = ?, phone_number = ? WHERE user_id = ?;";
        int numRows = 0;
        try {
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setString(1, object.getUsername());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getEmail());
            statement.setString(4, object.getPhoneNumber());
            statement.setInt(5, object.getUserId());
            numRows = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numRows > 0;
    }

    @Override
    public boolean delete(UserModel object) {
        // Delete user

        String sql = "DELETE FROM users WHERE user_id = ?;";
        int numRows = 0;
        try {
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setInt(1, object.getUserId());
            numRows = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return numRows > 0;
    }

    @Override
    public UserModel read(int id) {
        // Read user
        //write SQL here. read into UserModel object
        String sql = "SELECT * FROM users WHERE user_id = ?;";
        UserModel user = new UserModel();
        try {
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setCreatedAt(resultSet.getString("created_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<UserModel> readAll() {
        // Read user
        //write SQL here. read into UserModel objects
        List<UserModel> users = new ArrayList<>();

        String sql = "SELECT * FROM users;";
        try {
            Statement statement =  databaseConnector.databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                UserModel user = new UserModel();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setCreatedAt(resultSet.getString("created_at"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public UserModel read(String userName){
        // Read user
        //write SQL here. read into UserModel object
        String sql = "SELECT * FROM users WHERE username = ?;";
        UserModel user = new UserModel();
        try {
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setCreatedAt(resultSet.getString("created_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void close() {
        try {
            databaseConnector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
