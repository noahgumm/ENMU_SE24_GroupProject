package com.hotelreservationapp.models.Database.Prepared;


import java.util.List;

import com.hotelreservationapp.models.Database.Tables.*;

/**
 * Provides all relative information for a single user.
 * @author Griffin Graham, Joshua Espana
 */
public class UserOverview {
    private User user;

    private List<Reservation> reservations;

    private  List<Transaction> transactions;

    private List<UserPaymentMethod> paymentMethods;

    public  UserOverview(User user, List<Reservation> reservations, List<Transaction> transactions,
                         List<UserPaymentMethod> paymentMethods){
        this.user = user;
        this.transactions = transactions;
        this.reservations = reservations;
         this.paymentMethods = paymentMethods;
    }


    public List<UserPaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<UserPaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

