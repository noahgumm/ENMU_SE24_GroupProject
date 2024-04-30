package com.hotelreservationapp.models.DatabaseLogic;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hotelreservationapp.models.Database.Tables.Admin;

/**
 * Handles the database management for the admin table.
 * @author Griffin Graham, Joshua Espana
 */
public class AdminDbManager extends DbManagerBase {
    public  AdminDbManager(String url, String username, String password){
        super(url, username, password);
    }

    public Admin createAdminUser(Admin user){
        return createAdminUser(user.getUsername(), user.getPassword(), user.getEmail());
    }

    /**
     * Inserts a new admin record into the admin table.
     * @param username username of the admin. Used for logging in.
     * @param password password used to login to the site.
     * @param email email associated with the admin.
     * @return newly created admin. If failed to create, returns null.
     */
    public Admin createAdminUser(String username, String password, String email){
        Admin user = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Admins(username, password, email, created_at) values (?,?,?,now())", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int key = -1;
            if (rs.next()) {
                key = rs.getInt(1);
            }
            if(key > 0){
                user = getAdminUser(key);
            }
            conn.close();
        }
        catch (Exception e){
            String msg = e.getMessage();
        }
        return user;
    }

    /**
     * Provided a database ID, returns the admin associated with the ID.
     * @param adminId ID of the admin user to find.
     * @return Admin record. If failed to find, returns null.
     */
    public Admin getAdminUser(int adminId){
        Admin user = new Admin();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Admins WHERE admin_id = ?");
            preparedStatement.setInt(1, adminId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Timestamp createdAt = rs.getTimestamp("created_at");
                user = new Admin(adminId, username, password, email,createdAt);
            }
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Gets a list of all the admins in the database.
     * @return
     */
    public List<Admin> getAdminAllUsers(){
        List<Admin> users = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Admins");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("admin_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Timestamp createdAt = rs.getTimestamp("created_at");
                users.add(new Admin(userID, username, password, email,createdAt));
            }
            conn.close();
        }
        catch (Exception e){

        }
        return users;
    }
}
