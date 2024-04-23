package com.hotelreservationapp.models.Database.Prepared;

import java.util.List;

import com.hotelreservationapp.models.Database.Tables.*;

public class CartInformation {
    private User user;
    List<RoomReservation> reservations;

    public CartInformation(User user, List<RoomReservation> reservations){
        this.user = user;
        this.reservations = reservations;
    }

    public List<RoomReservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<RoomReservation> reservations) {
        this.reservations = reservations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
