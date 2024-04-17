package com.hotelreservationapp.models.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotelreservationapp.models.RoomModel;

public class RoomDatabaseManager extends DatabaseManager implements IDatabaseFunctionality<RoomModel>{
    @Override
    public boolean create(RoomModel object) {
        // Create user
        // write SQL here
        String sql = "INSERT INTO rooms (room_id, room_number, room_type,floor_number,price_per_night,room_description, number_of_beds, created_at) VALUES (?, ?, ?,?,?,?,?,?);";
        int numRows = 0;
        try {
            databaseConnector.connect();
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setInt(1, object.getroomID());
            statement.setString(2, object.getroomNumber());
            statement.setString(3, object.getroomType());
            statement.setInt(4, object.getFloorNumber());
            statement.setFloat(5, object.getpricePerNight());
            statement.setString(6, object.getRoomDescription());
            statement.setInt(7, object.getNumberOfBeds());
            statement.setString(8, new Date().toString());
            numRows = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return numRows > 0;
    }

    @Override
    public boolean update(RoomModel object) {
        // Update user
        //write SQL here

        String sql = "UPDATE rooms SET room_number = ?, room_type = ?, floor_number = ?, price_per_night = ?, room_description = ?, number_of_beds =? WHERE room_id = ?;";
        int numRows = 0;
        try {
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setString(1, object.getroomNumber());
            statement.setString(2, object.getroomType());
            statement.setInt(3, object.getFloorNumber());
            statement.setFloat(4, object.getpricePerNight());
            statement.setString(5, object.getRoomDescription());
            statement.setInt(6, object.getNumberOfBeds());
            statement.setInt(7, object.getroomID());
            numRows = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numRows > 0;
    }

    @Override
    public boolean delete(RoomModel object) {
        // Delete user
        String sql = "DELETE FROM rooms WHERE room_id = ?;";
        int numRows = 0;
        try {
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setInt(1, object.getroomID());
            numRows = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return numRows > 0;
    }

    public List<RoomModel> readAll() {
        // Read user
        //write SQL here. read into RoomModel objects
        List<RoomModel> rooms = new ArrayList<>();

        String sql = "SELECT * FROM rooms;";
        try {
            databaseConnector.connect();
            Statement statement =  databaseConnector.databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                RoomModel room = new RoomModel();
                room.setroomID(resultSet.getInt("room_id"));
                room.setroomNumber(resultSet.getString("room_number"));
                room.setroomType(resultSet.getString("room_type"));
                room.setFloorNumber(resultSet.getInt("floor_number"));
                room.setpricePerNight(resultSet.getFloat("price_per_night"));
                room.setRoomDescription(resultSet.getString("room_description"));
                room.setNumberOfBeds(resultSet.getInt("number_of_beds"));
                rooms.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public RoomModel read(String roomNumber) {
        String sql = "SELECT * FROM rooms WHERE room_number = ?;";
        RoomModel room = new RoomModel();
        try {
            databaseConnector.connect();
            PreparedStatement statement = databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setString(1, roomNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                room.setroomID(resultSet.getInt("room_id"));
                room.setroomNumber(resultSet.getString("room_number"));
                room.setroomType(resultSet.getString("room_type"));
                room.setFloorNumber(resultSet.getInt("floor_number"));
                room.setpricePerNight(resultSet.getFloat("price_per_night"));
                room.setRoomDescription(resultSet.getString("room_description"));
                room.setNumberOfBeds(resultSet.getInt("number_of_beds"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return room;
    }

    public void close() {
        try {
            databaseConnector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
