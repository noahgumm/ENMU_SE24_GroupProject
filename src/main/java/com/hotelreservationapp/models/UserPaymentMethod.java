package com.hotelreservationapp.models;

/**
 * Simple class that defines the user payment method. Links to the user model.
 */
public class UserPaymentMethod {
    public int cardID;
    public String cardNumber;
    public String cardHolderName;
    public String expiryDate;
    public int cvv;
    public String cardType;
    public int userID;
}
