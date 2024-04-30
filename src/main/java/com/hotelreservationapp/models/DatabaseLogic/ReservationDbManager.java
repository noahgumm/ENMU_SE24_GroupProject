package com.hotelreservationapp.models.DatabaseLogic;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hotelreservationapp.models.Database.Tables.Reservation;

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
     * @param reservationID
     * @return
     */
    public Reservation getReservation(int reservationID) {
        Reservation reservation = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM reservations WHERE reservation_id = ?");
            preparedStatement.setInt(1, reservationID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("user_id");
                int roomId = rs.getInt("room_id");
                Date checkInDate = rs.getDate("check_in_date");
                Date checkOutDate = rs.getDate("check_out_date");
                double totalPrice = rs.getDouble("total_price");
                int numGuests = rs.getInt("num_guests");
                boolean pets = rs.getBoolean("pets");
                String reservationStatus = rs.getString("reservation_status");
                Timestamp createdAt = rs.getTimestamp("created_at");

                reservation = new Reservation(reservationID, userID, roomId, checkInDate, checkOutDate, totalPrice, numGuests, pets, reservationStatus, createdAt);
            }
            conn.close();
        } catch (Exception e) {

        }
        return reservation;
    }

    /**
     * Provided a reservation status, gets all associated reservations.
     *
     * @param status
     * @return
     */
    public List<Reservation> getReservationsByStatus(String status) {
        List<Reservation> reservations = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM reservations WHERE reservation_status = ?");
            preparedStatement.setString(1, status);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Reservation res = new Reservation();
                res.setReservationId(rs.getInt("reservation_id"));
                res.setUserId(rs.getInt("user_id"));
                res.setRoomId(rs.getInt("room_id"));
                res.setCheckInDate(rs.getDate("check_in_date"));
                res.setCheckOutDate(rs.getDate("check_out_date"));
                res.setNumGuests(rs.getInt("num_guests"));
                res.setReservationStatus(rs.getString("reservation_status"));
                res.setCreatedAt(rs.getTimestamp("created_at"));

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
     * @param userID
     * @return
     */
    public List<Reservation> getReservationsFor(int userID) {
        List<Reservation> reservations = new ArrayList<>();
        try {
            // Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM reservations where user_id = ?");
            preparedStatement.setInt(1, userID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int reservationID = rs.getInt("reservation_id");
                int roomId = rs.getInt("room_id");
                Date checkInDate = rs.getDate("check_in_date");
                Date checkOutDate = rs.getDate("check_out_date");
                double totalPrice = rs.getDouble("total_price");
                int numGuests = rs.getInt("num_guests");
                boolean pets = rs.getBoolean("pets");
                String reservationStatus = rs.getString("reservation_status");
                Timestamp createdAt = rs.getTimestamp("created_at");

                reservations.add(new Reservation(reservationID, userID, roomId, checkInDate, checkOutDate, totalPrice, numGuests, pets, reservationStatus, createdAt));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public List<Reservation> getReservationsFor(int userID, String reservStatus) {
        List<Reservation> reservations = new ArrayList<>();
        try {
            // Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM reservations where user_id = ? and reservation_status = ?");
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, reservStatus);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int reservationID = rs.getInt("reservation_id");
                int roomId = rs.getInt("room_id");
                Date checkInDate = rs.getDate("check_in_date");
                Date checkOutDate = rs.getDate("check_out_date");
                double totalPrice = rs.getDouble("total_price");
                int numGuests = rs.getInt("num_guests");
                String reservationStatus = rs.getString("reservation_status");
                Timestamp createdAt = rs.getTimestamp("created_at");

                reservations.add(new Reservation(reservationID, userID, roomId, checkInDate, checkOutDate, totalPrice, numGuests, reservationStatus, createdAt));
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
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM reservations where room_id = ?");
            preparedStatement.setInt(1, roomID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int reservationID = rs.getInt("reservation_id");
                int userID = rs.getInt("user_id");
                Date checkInDate = rs.getDate("check_in_date");
                Date checkOutDate = rs.getDate("check_out_date");
                double totalPrice = rs.getDouble("total_price");
                int numGuests = rs.getInt("num_guests");
                String reservationStatus = rs.getString("reservation_status");
                Timestamp createdAt = rs.getTimestamp("created_at");

                reservations.add(new Reservation(reservationID, userID, roomID, checkInDate, checkOutDate, totalPrice, numGuests, reservationStatus, createdAt));
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
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM reservations WHERE reservation_id = ?");
            preparedStatement.setInt(1, reservationID);
            ResultSet rs = preparedStatement.executeQuery();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets a list of all the reservations in the database.
     *
     * @return
     */
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM reservations");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int reservationID = rs.getInt("reservation_id");
                int userID = rs.getInt("user_id");
                int roomId = rs.getInt("room_id");
                Date checkInDate = rs.getDate("check_in_date");
                Date checkOutDate = rs.getDate("check_out_date");
                double totalPrice = rs.getDouble("total_price");
                int numGuests = rs.getInt("num_guests");
                boolean pets = rs.getBoolean("pets");
                String reservationStatus = rs.getString("reservation_status");
                Timestamp createdAt = rs.getTimestamp("created_at");

                reservations.add(new Reservation(reservationID, userID, roomId, checkInDate, checkOutDate, totalPrice, numGuests, pets, reservationStatus, createdAt));
            }
            conn.close();
        } catch (Exception e) {

        }
        return reservations;
    }

    public void setReservationStatus(Reservation reservation, String status) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            String query = "UPDATE reservations SET reservation_status = ? WHERE reservation_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, reservation.getReservationId());
            int results = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Reservation> getAllReservationsBetween(String date1, String date2) {
        List<Reservation> reservations = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM reservations where check_in_date >= ? and check_out_date <= ?");
            preparedStatement.setDate(1, Date.valueOf(date1));
            preparedStatement.setDate(2, Date.valueOf(date2));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int reservationID = rs.getInt("reservation_id");
                int userID = rs.getInt("user_id");
                int roomId = rs.getInt("room_id");
                Date checkInDate = rs.getDate("check_in_date");
                Date checkOutDate = rs.getDate("check_out_date");
                double totalPrice = rs.getDouble("total_price");
                int numGuests = rs.getInt("num_guests");
                boolean pets = rs.getBoolean("pets");
                String reservationStatus = rs.getString("reservation_status");
                Timestamp createdAt = rs.getTimestamp("created_at");

                reservations.add(new Reservation(reservationID, userID, roomId, checkInDate, checkOutDate, totalPrice, numGuests, pets, reservationStatus, createdAt));
            }
            conn.close();
        } catch (Exception e) {

        }
        return reservations;
    }

    public Reservation createReservation(Reservation reservation) {
        return createReservation(reservation.getUserId(), reservation.getRoomId(), reservation.getCheckInDate().toString(),
                reservation.getCheckOutDate().toString(), reservation.getTotalPrice(), reservation.getNumGuests(), reservation.getPets(), reservation.getReservationStatus());
    }

    /**
     * Creates a new reservation in the database.
     *
     * @param userID            ID of the user the reservation is for.
     * @param roomID            Room ID where the user will be staying.
     * @param checkInDate       Date of check in
     * @param checkoutDate      Date of check out
     * @param totalPrice        Total price for the reservation.
     * @param numGuests         Number of guests staying in the room.
     * @param reservationStatus Status of the reservation.
     * @return The newly created reservation. If failed to create, returns null.
     */
    public Reservation createReservation(int userID, int roomID, String checkInDate, String checkoutDate,
                                         double totalPrice, int numGuests, boolean pets, String reservationStatus) {
        Reservation reservation = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement
                    ("INSERT INTO reservations(user_id, room_id, check_in_date, check_out_date,total_price,num_guests,pets,reservation_status, created_at) " +
                            "values (?,?,?,?,?,?,?,?,now())", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, roomID);
            preparedStatement.setDate(3, java.sql.Date.valueOf(checkInDate));
            preparedStatement.setDate(4, java.sql.Date.valueOf(checkoutDate));
            preparedStatement.setDouble(5, totalPrice);
            preparedStatement.setInt(6, numGuests);
            preparedStatement.setBoolean(7, pets);
            preparedStatement.setString(8, reservationStatus);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int key = -1;
            if (rs.next()) {
                key = rs.getInt(1);
            }
            if(key > 0){
                reservation = getReservation(key);
            }
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
            String msg = e.getMessage();
        }
        return  reservation;
    }

	 /**
     * Updates an existing reservation in the database.
     * @param reservation The reservation object containing updated details.
     * @return True if the update is successful, false otherwise.
     */
    public boolean updateReservation(Reservation reservation) {
        boolean success = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE reservations SET user_id=?, room_id=?, check_in_date=?, check_out_date=?, " +
                    "total_price=?, num_guests=?, pets=?, reservation_status=? WHERE reservation_id=?"
            );
            preparedStatement.setInt(1, reservation.getUserId());
            preparedStatement.setInt(2, reservation.getRoomId());
            preparedStatement.setDate(3, reservation.getCheckInDate());
            preparedStatement.setDate(4, reservation.getCheckOutDate());
            preparedStatement.setDouble(5, reservation.getTotalPrice());
            preparedStatement.setInt(6, reservation.getNumGuests());
            preparedStatement.setBoolean(7, reservation.getPets());
            preparedStatement.setString(8, reservation.getReservationStatus());
            preparedStatement.setInt(9, reservation.getReservationId());
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
}

