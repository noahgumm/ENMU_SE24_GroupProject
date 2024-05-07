package com.hotelreservationapp.models.DatabaseLogic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hotelreservationapp.models.Database.Tables.Reservation;

import com.hotelreservationapp.models.Database.Tables.Room;

/**
 * Handles the database management for the reservation table.
 * @author Griffin Graham, Joshua Espana
 */
public class ReservationDbManager extends  DbManagerBase {
	
    public ReservationDbManager(String url, String username, String password) {
        super(url, username, password);
    }

    /**
     * Provided a reservation ID, gets the reservation associated.
     *
     * @param reservationID The ID of the reservation to get
     * @return The found reservation or null if one was not found
     */
	public Reservation getReservation(int reservationID) {
		Reservation reservation = null;
		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword)){
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM reservations WHERE reservation_id = ?");
			preparedStatement.setInt(1, reservationID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int userID = rs.getInt("user_id");
				Date checkInDate = rs.getDate("check_in_date");
				Date checkOutDate = rs.getDate("check_out_date");
				double totalPrice = rs.getDouble("total_price");
				int numGuests = rs.getInt("num_guests");
				boolean pets = rs.getBoolean("pets");
				String reservationStatus = rs.getString("reservation_status");
				Timestamp createdAt = rs.getTimestamp("created_at");

				// Fetch room IDs associated with the reservation
				List<Room> rooms = getRoomsForReservation(reservationID); 

				// Create Reservation object with room IDs
				reservation = new Reservation(reservationID, userID, rooms, checkInDate, checkOutDate, totalPrice, numGuests, pets, reservationStatus, createdAt);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reservation;
	}

    /**
     * Provided a reservation status, gets all associated reservations.
     *
     * @param status The status of the reservations you wish to find
     * @return A list of found reservations matching the provided status
     */
	public List<Reservation> getReservationsByStatus(String status) {
		List<Reservation> reservations = new ArrayList<>();
		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword)){
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM reservations WHERE reservation_status = ?");
			preparedStatement.setString(1, status);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Reservation res = new Reservation();
				int reservationId = rs.getInt("reservation_id");
				res.setReservationId(reservationId);
				res.setUserId(rs.getInt("user_id"));
				res.setCheckInDate(rs.getDate("check_in_date"));
				res.setCheckOutDate(rs.getDate("check_out_date"));
				res.setNumGuests(rs.getInt("num_guests"));
				res.setReservationStatus(status);
				res.setCreatedAt(rs.getTimestamp("created_at"));

				// Fetch room IDs associated with the reservation
				List<Room> rooms = getRoomsForReservation(reservationId); 

				// Set the list of room IDs in the Reservation object
				res.setRooms(rooms);

				reservations.add(res);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reservations;
	}

    /**
     * Provided a user ID, returns a list of registrations belonging to the user specified.
     *
     * @param userID The ID of the user you wish to find reservations for
     * @return The found reservations
     */
    public List<Reservation> getReservationsFor(int userID) {
        List<Reservation> reservations = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword)){
            // //Class.forName("com.mysql.cj.jdbc.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM reservations where user_id = ?");
            preparedStatement.setInt(1, userID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
				int reservationID = rs.getInt("reservation_id");
				int userId = rs.getInt("user_id");
				
				// Fetch room IDs associated with the reservation
				List<Room> rooms = getRoomsForReservation(reservationID); 
				
				Date checkInDate = rs.getDate("check_in_date");
				Date checkOutDate = rs.getDate("check_out_date");
				double totalPrice = rs.getDouble("total_price");
				int numGuests = rs.getInt("num_guests");
				boolean pets = rs.getBoolean("pets"); // Include pets field
				String reservationStatus = rs.getString("reservation_status");
				Timestamp createdAt = rs.getTimestamp("created_at");

				// Pass to the Reservation constructor
				reservations.add(new Reservation(reservationID, userId, rooms, checkInDate, checkOutDate, totalPrice, numGuests, pets, reservationStatus, createdAt));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public List<Reservation> getReservationsFor(int userID, String reservStatus) {
        List<Reservation> reservations = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword)){
            // //Class.forName("com.mysql.cj.jdbc.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM reservations where user_id = ? and reservation_status = ?");
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, reservStatus);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int reservationID = rs.getInt("reservation_id");
				
				// Fetch room IDs associated with the reservation
				List<Room> rooms = getRoomsForReservation(reservationID); 
				
                Date checkInDate = rs.getDate("check_in_date");
                Date checkOutDate = rs.getDate("check_out_date");
                double totalPrice = rs.getDouble("total_price");
                int numGuests = rs.getInt("num_guests");
                String reservationStatus = rs.getString("reservation_status");
                Timestamp createdAt = rs.getTimestamp("created_at");

                reservations.add(new Reservation(reservationID, userID, rooms, checkInDate, checkOutDate, totalPrice, numGuests, reservationStatus, createdAt));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return reservations;
    }

    /**
     * Provided a room ID, returns a list of reservations for the room
     *
     * @param roomID
     * @return
     */
    public List<Reservation> getReservationByRoom(int roomID) {
        List<Reservation> reservations = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword)){
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM reservations where room_id = ?");
            preparedStatement.setInt(1, roomID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int reservationID = rs.getInt("reservation_id");
				
				// Fetch room IDs associated with the reservation
				List<Room> rooms = getRoomsForReservation(reservationID); 
				
                int userID = rs.getInt("user_id");
                Date checkInDate = rs.getDate("check_in_date");
                Date checkOutDate = rs.getDate("check_out_date");
                double totalPrice = rs.getDouble("total_price");
                int numGuests = rs.getInt("num_guests");
                String reservationStatus = rs.getString("reservation_status");
                Timestamp createdAt = rs.getTimestamp("created_at");

                reservations.add(new Reservation(reservationID, userID, rooms, checkInDate, checkOutDate, totalPrice, numGuests, reservationStatus, createdAt));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservations;
    }

    /**
     * Deletes a reservation from the database
     *
     * @param reservationID The ID of the reservation to be deleted
     */
    public void deleteReservation(int reservationID) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword)){
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE reservations SET reservation_status = 'cancelled'  WHERE reservation_id = ?");
            preparedStatement.setInt(1, reservationID);
            preparedStatement.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets a list of all the reservations in the database.
     *
     * @return All reservations
     */
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword)){
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM reservations");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int reservationID = rs.getInt("reservation_id");
                int userID = rs.getInt("user_id");
				
				// Fetch room IDs associated with the reservation
				List<Room> rooms = getRoomsForReservation(reservationID); 
				
                Date checkInDate = rs.getDate("check_in_date");
                Date checkOutDate = rs.getDate("check_out_date");
                double totalPrice = rs.getDouble("total_price");
                int numGuests = rs.getInt("num_guests");
                boolean pets = rs.getBoolean("pets");
                String reservationStatus = rs.getString("reservation_status");
                Timestamp createdAt = rs.getTimestamp("created_at");

                reservations.add(new Reservation(reservationID, userID, rooms, checkInDate, checkOutDate, totalPrice, numGuests, pets, reservationStatus, createdAt));
            }
            conn.close();
        } catch (Exception e) {
			e.printStackTrace();
        }
        return reservations;
    }

    public void setReservationStatus(Reservation reservation, String status) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword)){
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            String query = "UPDATE reservations SET reservation_status = ? WHERE reservation_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, reservation.getReservationId());
			preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Reservation> getAllReservationsBetween(String date1, String date2) {
        List<Reservation> reservations = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword)){
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM reservations where check_in_date >= ? and check_out_date <= ?");
            preparedStatement.setDate(1, Date.valueOf(date1));
            preparedStatement.setDate(2, Date.valueOf(date2));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int reservationID = rs.getInt("reservation_id");
                int userID = rs.getInt("user_id");
				
				// Fetch room IDs associated with the reservation
				List<Room> rooms = getRoomsForReservation(reservationID); 
				
                Date checkInDate = rs.getDate("check_in_date");
                Date checkOutDate = rs.getDate("check_out_date");
                double totalPrice = rs.getDouble("total_price");
                int numGuests = rs.getInt("num_guests");
                boolean pets = rs.getBoolean("pets");
                String reservationStatus = rs.getString("reservation_status");
                Timestamp createdAt = rs.getTimestamp("created_at");

                reservations.add(new Reservation(reservationID, userID, rooms, checkInDate, checkOutDate, totalPrice, numGuests, pets, reservationStatus, createdAt));
            }
            conn.close();
        } catch (Exception e) {
			e.printStackTrace();
        }
        return reservations;
    }

    /**
     * Creates a new reservation in the database.
     *
     * @param reservation Then reservation object to create a copy of in the database
     * @return The newly created reservation. If failed to create, returns null.
     */
	public Reservation createReservation(Reservation reservation) {
		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword)){
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
			
			// Insert reservation details into reservations table
			PreparedStatement reservationStatement = conn.prepareStatement(
				"INSERT INTO reservations(user_id, check_in_date, check_out_date, total_price, num_guests, pets, reservation_status, created_at) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, NOW())",
				Statement.RETURN_GENERATED_KEYS
			);
			reservationStatement.setInt(1, reservation.getUserId());
			reservationStatement.setDate(2, reservation.getCheckInDate());
			reservationStatement.setDate(3, reservation.getCheckOutDate());
			reservationStatement.setDouble(4, reservation.getTotalPrice());
			reservationStatement.setInt(5, reservation.getNumGuests());
			reservationStatement.setBoolean(6, reservation.getPets());
			reservationStatement.setString(7, "pending");
			
			int rowsAffected = reservationStatement.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = reservationStatement.getGeneratedKeys();
				if (rs.next()) {
					int reservationId = rs.getInt(1);
					reservation.setReservationId(reservationId);
					
					// Insert room reservations into mapping table
					PreparedStatement mappingStatement = conn.prepareStatement(
						"INSERT INTO reservation_rooms(reservation_id, room_id) VALUES (?, ?)"
					);
					for (Room room : reservation.getRooms()) {
						mappingStatement.setInt(1, reservationId);
						mappingStatement.setInt(2, room.getRoomId());
						mappingStatement.addBatch();
					}
					mappingStatement.executeBatch();
					
					return reservation;
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 /**
     * Updates an existing reservation in the database.
     * @param reservation The reservation object containing updated details.
     * @return True if the update is successful, false otherwise.
     */
	public boolean updateReservation(Reservation reservation) {
		boolean success = false;
		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword)){
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);

			// Update reservation details
			PreparedStatement preparedStatement = conn.prepareStatement(
					"UPDATE reservations SET user_id=?, check_in_date=?, check_out_date=?, " +
					"total_price=?, num_guests=?, pets=?, reservation_status=? WHERE reservation_id=?"
			);
			preparedStatement.setInt(1, reservation.getUserId());
			preparedStatement.setDate(2, reservation.getCheckInDate());
			preparedStatement.setDate(3, reservation.getCheckOutDate());
			preparedStatement.setDouble(4, reservation.getTotalPrice());
			preparedStatement.setInt(5, reservation.getNumGuests());
			preparedStatement.setBoolean(6, reservation.getPets());
			preparedStatement.setString(7, reservation.getReservationStatus());
			preparedStatement.setInt(8, reservation.getReservationId());
			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				// Update rooms for the reservation
				// First, delete existing room reservations for this reservation
				PreparedStatement deleteRoomsStatement = conn.prepareStatement(
						"DELETE FROM reservation_rooms WHERE reservation_id=?"
				);
				deleteRoomsStatement.setInt(1, reservation.getReservationId());
				deleteRoomsStatement.executeUpdate();

				// Then, insert new room reservations for this reservation
				PreparedStatement insertRoomsStatement = conn.prepareStatement(
						"INSERT INTO reservation_rooms (reservation_id, room_id) VALUES (?, ?)"
				);
				for (Room room : reservation.getRooms()) {
					insertRoomsStatement.setInt(1, reservation.getReservationId());
					insertRoomsStatement.setInt(2, room.getRoomId());
					insertRoomsStatement.addBatch();
				}
				int[] batchResult = insertRoomsStatement.executeBatch();
				if (batchResult.length == reservation.getRooms().size()) {
					success = true;
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public List<Room> getRoomsForReservation(int reservationID) {
		List<Room> rooms = new ArrayList<>();
		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword)){
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT B.* FROM reservation_rooms as A JOIN rooms as B on A.room_id = B.room_id WHERE A.reservation_id = ?");
			preparedStatement.setInt(1, reservationID);
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
                Room room = new Room(roomId, roomNum, roomType, floorNumber, pricePerNight, roomDescription, numberOfBeds, createdAt);
				
				if (room != null) {
					rooms.add(room);
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rooms;
	}
	
}