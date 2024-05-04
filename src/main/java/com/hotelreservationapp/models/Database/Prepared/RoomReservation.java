package com.hotelreservationapp.models.Database.Prepared;

import com.hotelreservationapp.models.Database.Tables.*;

/**
 * Represents a room reservation. Contains a room and a reservation.
 * @see Room
 * @see Reservation
 * @author Joshua Espana
 */
public class RoomReservation {
    private Room room;
    private Reservation reservation;

    public RoomReservation(Room room, Reservation reservation){
        this.room = room;
        this.reservation = reservation;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
