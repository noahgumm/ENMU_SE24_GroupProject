package com.hotelreservationapp.models.Database.Tables;


import java.sql.Time;
import java.sql.Timestamp;

/**
 * Database table model
 * @author Griffin Graham, Joshua Espana
 */
public class Transaction {
    private int transactionId;
    private int userId;
    private int reservationId;
    private double amount;

    private int userPaymentMethodID;
    private Timestamp transactionDate;

    public Transaction() {
    }

    public Transaction(int transactionId, int userId, int reservationId, double amount, Timestamp transactionDate,int userPaymentMethodID) {
        this.userId = userId;
        this.reservationId = reservationId;
        this.amount = amount;
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.userPaymentMethodID = userPaymentMethodID;
    }

    // Getters and setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getUserPaymentMethodID(){return this.userPaymentMethodID;}

    public void setUserPaymentMethodID(int value){ this.userPaymentMethodID = value;}
}
