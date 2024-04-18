package com.hotelreservationapp.models.DatabaseLogic;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hotelreservationapp.models.Database.Tables.Room;

/**
 * Handles the database management for the room table.
 * @author Griffin Graham, Joshua Espana
 */
public class RoomDbManager extends  DbManagerBase{

    public RoomDbManager(String url, String username, String password){
        super(url, username, password);
    }

    /**
     * Provided a room ID, get the room from the database.
     * @param roomID
     * @return The room found. Null if no room found.
     */
    public Room getRoom(int roomID){
        Room room = new Room();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Rooms WHERE room_id = ?");
            preparedStatement.setInt(1, roomID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String roomNum = rs.getString("room_number");
                String roomType = rs.getString("room_type");
                int floorNumber = rs.getInt("floor_number");
                double pricePerNight = rs.getDouble("price_per_night");
                String roomDescription = rs.getString("room_description");
                int numberOfBeds = rs.getInt("number_of_beds");
                Timestamp createdAt = rs.getTimestamp("created_at");
                room = new Room(roomID,roomNum, roomType, floorNumber,pricePerNight,roomDescription,numberOfBeds,createdAt);
            }
            conn.close();
        }
        catch (Exception e){

        }
        return room;
    }

    /**
     * Returns a list of all the rooms in the database.
     * @return
     */
    public List<Room> getAllRooms(){
        List<Room> rooms = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Rooms");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int roomId = rs.getInt("room_id");
                String roomNum = rs.getString("room_number");
                String roomType = rs.getString("room_type");
                int floorNumber = rs.getInt("floor_number");
                double pricePerNight = rs.getDouble("price_per_night");
                String roomDescription = rs.getString("room_description");
                int numberOfBeds = rs.getInt("number_of_beds");
                Timestamp createdAt = rs.getTimestamp("created_at");
                rooms.add(new Room(roomId,roomNum,roomType,floorNumber,pricePerNight,roomDescription,numberOfBeds,createdAt));
            }
            conn.close();
        }
        catch (Exception e){

        }
        return  rooms;
    }

    public Room createRoom(Room room){
        return createRoom(room.getRoomNumber(), room.getRoomType(), room.getFloorNumber(), room.getPricePerNight(), room.getRoomDescription(), room.getNumberOfBeds());
    }

    /**
     * Creates a new room in the database.
     * @param roomNumber Human-readable identifier associated with the room.
     * @param roomType Type of room
     * @param floorNumber Floor which the room is located.
     * @param pricePerNight Price per one night stay in this room.
     * @param roomDescription Description of the room for guests.
     * @param numberOfBeds Number of beds in the room.
     * @return The newly created room. If failed to create, returns null.
     */
    public Room createRoom(String roomNumber, String roomType, int floorNumber, double pricePerNight, String roomDescription, int numberOfBeds){
        Room room = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement
                    ("INSERT INTO Rooms(room_number, room_type, floor_number,price_per_night,room_description,number_of_beds,created_at) " +
                    "values (?,?,?,?,?,?,now())", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, roomNumber);
            preparedStatement.setString(2, roomType);
            preparedStatement.setInt(3, floorNumber);
            preparedStatement.setDouble(4, pricePerNight);
            preparedStatement.setString(5,roomDescription);
            preparedStatement.setInt(6, numberOfBeds);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int key = -1;
            if (rs.next()) {
                key = rs.getInt(1);
            }
            if(key > 0){
                room = getRoom(key);
            }
            conn.close();
        }catch (Exception e){

        }
        return  room;
    }
}