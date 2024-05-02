package com.hotelreservationapp.models.DatabaseLogic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.hotelreservationapp.models.Database.Tables.Room;
import com.hotelreservationapp.models.Database.Tables.Bookings;
import com.hotelreservationapp.models.DateRange;

import java.util.logging.Logger;

/**
 * Handles the database management for the room table.
 * @author Griffin Graham, Joshua Espana, Zachary Marrs
 */
public class RoomDbManager extends  DbManagerBase{
    private static final Logger logger = Logger.getLogger(RoomDbManager.class.getName());

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
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM rooms WHERE room_id = ?");
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
                room = new Room(roomID, roomNum, roomType, floorNumber, pricePerNight, roomDescription, numberOfBeds, createdAt);
            }
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
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
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM rooms");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int roomID = rs.getInt("room_id");
                String roomNum = rs.getString("room_number");
                String roomType = rs.getString("room_type");
                int floorNumber = rs.getInt("floor_number");
                double pricePerNight = rs.getDouble("price_per_night");
                String roomDescription = rs.getString("room_description");
                int numberOfBeds = rs.getInt("number_of_beds");
                Timestamp createdAt = rs.getTimestamp("created_at");
                rooms.add(new Room(roomID, roomNum, roomType, floorNumber, pricePerNight, roomDescription, numberOfBeds, createdAt));
            }
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  rooms;
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
                    ("INSERT INTO Rooms(room_number, room_type, floor_number, price_per_night, room_description, number_of_beds, created_at) " +
                            "VALUES (?, ?, ?, ?, ?, ?, now())", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, roomNumber);
            preparedStatement.setString(2, roomType);
            preparedStatement.setInt(3, floorNumber);
            preparedStatement.setDouble(4, pricePerNight);
            preparedStatement.setString(5, roomDescription);
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
            e.printStackTrace();
        }
        return  room;
    }

    /**
     * Returns the highest floor number from the database.
     * @return The highest floor number. Returns -1 if no floor number found.
     */
    public int getHighestFloorNumber() {
        int highestFloorNumber = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(floor_number) AS max_floor FROM Rooms");
            if (rs.next()) {
                highestFloorNumber = rs.getInt("max_floor");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return highestFloorNumber;
    }

    /**
     * Updates an existing room in the database.
     * @param room The room object containing updated details.
     * @return True if the update is successful, false otherwise.
     */
    public boolean updateRoom(Room room) {
        boolean success = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE Room SET room_number=?, room_type=?, floor_number=?, price_per_night=?, " +
                            "room_description=?, number_of_beds=? WHERE room_id=?"
            );
            preparedStatement.setString(1, room.getRoomNumber());
            preparedStatement.setString(2, room.getRoomType());
            preparedStatement.setInt(3, room.getFloorNumber());
            preparedStatement.setDouble(4, room.getPricePerNight());
            preparedStatement.setString(5, room.getRoomDescription());
            preparedStatement.setInt(6, room.getNumberOfBeds());
            preparedStatement.setInt(7, room.getRoomId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * Gets a list of available rooms for a given date range.
     * @param startDate - The Beginning of the Date Range entered by the user for the reservation.
     * @param startDate - The End of the Date Range entered by the user for the reservation.
     * @return A List of Available Rooms for those Dates
     */
	public List<Room> getBookedRooms(Date startDate, Date endDate) {
		List<Room> bookedRooms = new ArrayList<>();
		try {
			List<Room> allRooms = getAllRooms(); 
			for (Room room : allRooms) {
				for (DateRange bookedDate : getBookedDatesForRoom(room.getRoomId())) {
					if ((startDate.equals(bookedDate.getStartDate()) || startDate.after(bookedDate.getStartDate())) &&
						(endDate.equals(bookedDate.getEndDate()) || endDate.before(bookedDate.getEndDate()))) {
						bookedRooms.add(room);
						break; 
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookedRooms;
	}

	/**
     * Gets the current booked dates for a given room
     * @param roomID The ID of the room.
     * @return The list of booked dates for the room.
     */
    public List<DateRange> getBookedDatesForRoom(int roomID) {
        List<DateRange> bookedDates = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT check_in_date, check_out_date FROM Bookings WHERE room_id = ?");
            preparedStatement.setInt(1, roomID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Date checkInDate = rs.getDate("check_in_date");
                Date checkOutDate = rs.getDate("check_out_date");
                bookedDates.add(new DateRange(checkInDate, checkOutDate));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookedDates;
    }

    /**
     * Books a room for a given date range.
     * @param roomID The ID of the room to book.
     * @param startDate The start date of the booking.
     * @param endDate The end date of the booking.
     * @return True if the booking is successful, false otherwise.
     */
    public boolean bookRoom(int userID, int roomID, Date startDate, Date endDate) {
        boolean success = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Bookings (user_id, room_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, roomID);
            preparedStatement.setDate(3, startDate);
            preparedStatement.setDate(4, endDate);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

	/**
     * Gets the timestamp of a room given its ID.
     * @param roomId The ID of the room.
     * @return The timestamp of the room. Null if no room found or timestamp is null.
     */
    public Timestamp getRoomTimestamp(int roomId) {
        Timestamp timestamp = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT created_at FROM Rooms WHERE room_id = ?");
            preparedStatement.setInt(1, roomId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                timestamp = rs.getTimestamp("created_at");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
    * Deletes room from database
     * @param roomID The ID of the room to be deleted
    * */
    public void deleteRoom(int roomID){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);

            PreparedStatement preparedStatement = conn.prepareStatement("DELETE from rooms WHERE room_id = ?");

            preparedStatement.setInt(1, roomID);

            // Execute the update statement
            preparedStatement.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
	
	public List<Room> getRoomsByReservationId(int reservationId) {
		List<Room> rooms = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
			PreparedStatement preparedStatement = conn.prepareStatement(
					"SELECT r.room_id, r.room_number, r.room_type, r.floor_number, r.price_per_night, r.room_description, r.number_of_beds, r.created_at " +
					"FROM rooms r " +
					"INNER JOIN reservation_rooms rr ON r.room_id = rr.room_id " +
					"WHERE rr.reservation_id = ?");
			preparedStatement.setInt(1, reservationId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int roomId = rs.getInt("room_id");
				String roomNumber = rs.getString("room_number");
				String roomType = rs.getString("room_type");
				int floorNumber = rs.getInt("floor_number");
				double pricePerNight = rs.getDouble("price_per_night");
				String roomDescription = rs.getString("room_description");
				int numberOfBeds = rs.getInt("number_of_beds");
				Timestamp createdAt = rs.getTimestamp("created_at");
				rooms.add(new Room(roomId, roomNumber, roomType, floorNumber, pricePerNight, roomDescription, numberOfBeds, createdAt));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rooms;
	}
	
	/**
	 * Gets a room from the database based on its room number.
	 * @param roomNumber The room number of the room to retrieve.
	 * @return The room found. Null if no room found.
	 */
	public Room getRoomByRoomNumber(String roomNumber) {
		Room room = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM rooms WHERE room_number = ?");
			preparedStatement.setString(1, roomNumber);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int roomId = rs.getInt("room_id");
				String roomType = rs.getString("room_type");
				int floorNumber = rs.getInt("floor_number");
				double pricePerNight = rs.getDouble("price_per_night");
				String roomDescription = rs.getString("room_description");
				int numberOfBeds = rs.getInt("number_of_beds");
				Timestamp createdAt = rs.getTimestamp("created_at");
				room = new Room(roomId, roomNumber, roomType, floorNumber, pricePerNight, roomDescription, numberOfBeds, createdAt);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return room;
	}
		
}