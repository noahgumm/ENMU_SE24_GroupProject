package com.hotelreservationapp.models.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotelreservationapp.models.BookingsModel;

public class BookingsDatabaseManager extends DatabaseManager implements IDatabaseFunctionality<BookingsModel>{
    @Override
    public boolean create(BookingsModel object) {
        // Create booking
        // write SQL here
        String sql = "INSERT INTO bookings (booking_id, user_id, room_id, check_in_date, check_out_date, total_price, booking_status, created_at) VALUES (?, ?, ?,?,?,?,?,?);";
        int numRows = 0;
        try {
            databaseConnector.connect();
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setInt(1, object.getBookingID());
            statement.setInt(2, object.getUserID());
            statement.setInt(3, object.getRoomID());
            statement.setString(4, object.getCheckInDate());
            statement.setString(5, object.getCheckOutDate());
            statement.setFloat(6, object.getTotalPrice());
            statement.setString(7,object.getBookingStatus());
            statement.setString(8, new Date().toString());
            numRows = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return numRows > 0;
    }

    @Override
    public boolean update(BookingsModel object) {
        // Update user
        //write SQL here

        String sql = "UPDATE bookings SET room_id = ?, check_in_date = ?, check_out_date = ?, total_price = ?, booking_states = ? WHERE booking_id = ?;";
        int numRows = 0;
        try {
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setInt(1, object.getRoomID());
            statement.setString(2, object.getCheckInDate());
            statement.setString(3, object.getCheckOutDate());
            statement.setFloat(4, object.getTotalPrice());
            statement.setString(5, object.getBookingStatus());
            statement.setInt(6, object.getBookingID());
            numRows = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numRows > 0;
    }

    @Override
    public boolean delete(BookingsModel object) {
        // Delete user
        String sql = "DELETE FROM bookings WHERE booking_id = ?;";
        int numRows = 0;
        try {
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setInt(1, object.getBookingID());
            numRows = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return numRows > 0;
    }

    public List<BookingsModel> readAll() {
        // Read user
        //write SQL here. read into BookingsModel objects
        List<BookingsModel> bookings = new ArrayList<>();

        String sql = "SELECT * FROM bookings;";
        try {
            databaseConnector.connect();
            Statement statement =  databaseConnector.databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                BookingsModel booking = new BookingsModel();
                booking.setUserID(resultSet.getInt("user_id"));
                booking.setBookingID(resultSet.getInt("booking_id"));
                booking.setRoomID(resultSet.getInt("room_id"));
                booking.setCheckInDate(resultSet.getString("check_in_date"));
                booking.setCheckOutDate(resultSet.getString("check_out_date"));
                booking.setTotalPrice(resultSet.getFloat("total_price"));
                booking.setBookingStatus(resultSet.getString("booking_status"));
                bookings.add(booking);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public List<BookingsModel> readAllUserBookings(int userID) {

        List<BookingsModel> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE user_id = ?;";
        try {
            databaseConnector.connect();
            PreparedStatement statement = databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BookingsModel booking = new BookingsModel();
                booking.setUserID(resultSet.getInt("user_id"));
                booking.setBookingID(resultSet.getInt("booking_id"));
                booking.setRoomID(resultSet.getInt("room_id"));
                booking.setCheckInDate(resultSet.getString("check_in_date"));
                booking.setCheckOutDate(resultSet.getString("check_out_date"));
                booking.setTotalPrice(resultSet.getFloat("total_price"));
                booking.setBookingStatus(resultSet.getString("booking_status"));
                bookings.add(booking);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return bookings;
    }

    public BookingsModel readSingleBooking(int bookingID) {
        String sql = "SELECT * FROM bookings WHERE bookingID = ?;";
        BookingsModel booking = new BookingsModel();
        try {
            databaseConnector.connect();
            PreparedStatement statement = databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setInt(1, bookingID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                booking.setUserID(resultSet.getInt("user_id"));
                booking.setBookingID(resultSet.getInt("booking_id"));
                booking.setRoomID(resultSet.getInt("room_id"));
                booking.setCheckInDate(resultSet.getString("check_in_date"));
                booking.setCheckOutDate(resultSet.getString("check_out_date"));
                booking.setTotalPrice(resultSet.getFloat("total_price"));
                booking.setBookingStatus(resultSet.getString("booking_status"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return booking;
    }

    public BookingsModel read(String email){
        BookingsModel booking = new BookingsModel();
        return booking;
    }

    public void close() {
        try {
            databaseConnector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
