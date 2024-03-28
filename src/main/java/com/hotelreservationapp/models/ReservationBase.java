package com.hotelreservationapp.models;

/**
 * Abstract class that defines the base reservation model.
 */
public abstract class ReservationBase implements IDatabaseFunctionality<ReservationBase> {
    protected int reservationID;
    protected int roomID;
    protected int userID;
    protected String checkInDate;
    protected String checkOutDate;
    protected int numberOfGuests;

    
}
