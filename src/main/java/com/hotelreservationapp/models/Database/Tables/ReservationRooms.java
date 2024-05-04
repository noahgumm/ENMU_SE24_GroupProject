package com.hotelreservationapp.models.Database.Tables;

/**
 * Links a reservation to a room. Contains a reservation ID and a room ID.
 */
public class ReservationRooms {
    private int reservationID; // Unique identifier for the reservation
    private int roomID; // Unique identifier for the room

    public ReservationRooms(){
        this.reservationID = 0;
        this.roomID = 0;
    }

    public ReservationRooms(int reservationID, int roomID) {
        this.reservationID = reservationID;
        this.roomID = roomID;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
}
