package com.hotelreservationapp.models.Database.Prepared;

import java.util.List;

import com.hotelreservationapp.models.Database.Tables.*;

/**
 * Represents a room reservation. Contains a room and a reservation.
 * @see Room
 * @see Reservation
 * @author Joshua Espana
 */
public class RoomReservation {
    private List<Room> rooms;
    private Reservation reservation;

    public RoomReservation(List<Room> rooms, Reservation reservation){
        this.rooms = rooms;
        this.reservation = reservation;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRoom(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
