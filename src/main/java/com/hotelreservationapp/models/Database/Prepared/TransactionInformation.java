package com.hotelreservationapp.models.Database.Prepared;

import com.hotelreservationapp.models.Database.Tables.*;

/**
 * Models a single transaction and all relevant information for a user.
 * @author Griffin Graham, Joshua Espana
 */
public class TransactionInformation {
    private User user;

    private Transaction transaction;

    private UserPaymentMethod paymentMethod;

    private Reservation reservation;

    public TransactionInformation(User user,Transaction transaction, UserPaymentMethod paymentMethod, Reservation reservation){
        this.user = user;
        this.transaction = transaction;
        this.paymentMethod = paymentMethod;
        this.reservation = reservation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public UserPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(UserPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}