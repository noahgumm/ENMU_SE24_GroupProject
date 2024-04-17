package com.hotelreservationapp.models.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotelreservationapp.models.AdminModel;

public class AdminDatabaseManager extends DatabaseManager implements IDatabaseFunctionality<AdminModel>{
    @Override
    public boolean create(AdminModel object) {
        // Create user
        // write SQL here
        String sql = "INSERT INTO admins (admin_id, username, first_name, last_name, address, password, email,created_at) VALUES (?, ?,?,?, ?, ?,?,?);";
        int numRows = 0;
        try {
            databaseConnector.connect();
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setInt(1, object.getAdminId());
            statement.setString(2, object.getUsername());
            statement.setString(3, object.getFirstName());
            statement.setString(4, object.getLastName());
            statement.setString(5, object.getAddress());
            statement.setString(6, object.getPassword());
            statement.setString(7, object.getEmail());
            statement.setString(8, new Date().toString());
            numRows = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return numRows > 0;
    }

    @Override
    public boolean update(AdminModel object) {
        // Update user
        //write SQL here

        String sql = "UPDATE admins SET username = ?, password = ?, email = ?, WHERE admin_id = ?;";
        int numRows = 0;
        try {
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setString(1, object.getUsername());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getEmail());
            statement.setInt(5, object.getAdminId());
            numRows = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numRows > 0;
    }

    @Override
    public boolean delete(AdminModel object) {
        // Delete user
        String sql = "DELETE FROM admins WHERE admin_id = ?;";
        int numRows = 0;
        try {
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setInt(1, object.getAdminId());
            numRows = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return numRows > 0;
    }

    public List<AdminModel> readAll() {
        // Read user
        //write SQL here. read into AdminModel objects
        List<AdminModel> admins = new ArrayList<>();

        String sql = "SELECT * FROM admins;";
        try {
            databaseConnector.connect();
            Statement statement =  databaseConnector.databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                AdminModel admin = new AdminModel();
                admin.setAdminId(resultSet.getInt("admin_id"));
                admin.setUsername(resultSet.getString("username"));
                admin.setPassword(resultSet.getString("password"));
                admin.setEmail(resultSet.getString("email"));
                admin.setCreatedAt(resultSet.getString("created_at"));
                admins.add(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admins;
    }

    public AdminModel read(String email) {
        String sql = "SELECT * FROM admins WHERE email = ?;";
        AdminModel admin = new AdminModel();
        try {
            databaseConnector.connect();
            PreparedStatement statement = databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                admin.setAdminId(resultSet.getInt("admin_id"));
                admin.setUsername(resultSet.getString("username"));
                admin.setPassword(resultSet.getString("password"));
                admin.setEmail(resultSet.getString("email"));
                admin.setCreatedAt(resultSet.getString("created_at"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return admin;
    }

    public void close() {
        try {
            databaseConnector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
