

package com.hotelreservationapp.models.Database.Tables;


import java.sql.Date;
import java.sql.Timestamp;

/**
 * Database table model
 * @author Griffin Graham, Joshua Espana
 */
public class UserPaymentMethod {
    private int userPaymentMethodID;
    private String cardNumber;
    private String cardHolderName;
    private String cardType;
    private Date expiryDate;
    private String cvv;
    private int userId;
    private Timestamp createdAt;

    private boolean isDeleted;

    public UserPaymentMethod() {
    }

    public UserPaymentMethod(int cardID, String cardNumber, String cardHolderName, String cardType, Date expiryDate,
                             String cvv, int userId, Timestamp createdAt, boolean isDeleted) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cardType = cardType;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.userId = userId;
        this.userPaymentMethodID = cardID;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    // Getters and setters
    public int getUserPaymentMethodID() {
        return userPaymentMethodID;
    }

    public void setUserPaymentMethodID(int userPaymentMethodID) {
        this.userPaymentMethodID = userPaymentMethodID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean value) {
        this.isDeleted = value;
    }
}
