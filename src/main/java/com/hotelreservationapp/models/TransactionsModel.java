package com.hotelreservationapp.models;

public class TransactionsModel {
    private int bookingId;
    private int userID;
    private int transactionID;
    private float totalAmount;
    private String transactionDate;
    private int userPaymentMethodID;

    public TransactionsModel() {
    }

    public TransactionsModel(int bookingId, int userID, int transactionID, float totalAmount, String transactionDate, int userPaymentMethodID) {
        this.bookingId = bookingId;
        this.userID = userID;
        this.transactionID = transactionID;
        this.totalAmount = totalAmount;
        this.transactionDate = transactionDate;
        this.userPaymentMethodID = userPaymentMethodID;
    }
    
    public int getBookingID() {
        return bookingId;
    }

    public void setBookingID(int bookingID) {
        this.bookingId = bookingID;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getUserPaymentMethodID() {
        return userPaymentMethodID;
    }

    public void setUserPaymentMethodID(int userPaymentMethodID) {
        this.userPaymentMethodID = userPaymentMethodID;
    }

}
