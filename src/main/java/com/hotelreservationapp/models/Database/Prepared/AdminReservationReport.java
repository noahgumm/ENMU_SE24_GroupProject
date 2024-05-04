package com.hotelreservationapp.models.Database.Prepared;

import java.sql.Date;
import java.util.List;

import com.hotelreservationapp.models.Database.Tables.Reservation;
import com.hotelreservationapp.models.Database.Tables.Room;
import com.hotelreservationapp.models.Database.Tables.User;

/**
 * Data model for the Admin Reservation Report. Contains all the information needed to display a reservation in the admin report.
 */
public class AdminReservationReport {
    private Reservation reservation;
    private List<Room> rooms;
    private User user;

    public AdminReservationReport(Reservation reservation, List<Room> rooms, User user) {
        this.reservation = reservation;
        this.rooms = rooms;
        this.user = user;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRoomNumbers(){
        StringBuilder roomNumbers = new StringBuilder();
        for (Room room : rooms) {
            // Append the room number and the price of the room to the csv string
            roomNumbers.append(room.getRoomNumber()).append(" - $").append(room.getPricePerNight()).append(", ");
        }
        // Remove the last comma and space
        roomNumbers.deleteCharAt(roomNumbers.length() - 1);
        return roomNumbers.toString();
    }

    public String getRoomNumbersForSearch(){
        StringBuilder roomNumbers = new StringBuilder();
        for (Room room : rooms) {
            roomNumbers.append(room.getRoomNumber()).append(",");
        }
        roomNumbers.deleteCharAt(roomNumbers.length() - 1);
        return roomNumbers.toString();
    }


    //get number of days between check in and check out
    public int getNumDaysOfVisit(){
        long diff = reservation.getCheckOutDate().getTime() - reservation.getCheckInDate().getTime();
        return (int) (diff / (1000 * 60 * 60 * 24));
    }


}
