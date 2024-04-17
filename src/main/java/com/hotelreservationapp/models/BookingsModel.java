package com.hotelreservationapp.models;

public class BookingsModel {
    private int bookingId;
    private int userID;
    private int roomID;
    private String checkInDate;
    private String checkOutDate;
    private float totalPrice;
    private String bookingStatus;

    public BookingsModel() {
    }

    public BookingsModel(int bookingId, int userID, int roomID, String checkInDate, String checkOutDate, float totalPrice, String bookingStatus) {
        this.bookingId = bookingId;
        this.userID = userID;
        this.roomID = roomID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.bookingStatus = bookingStatus;
    }
    
    public int getBookingID() {
        return bookingId;
    }

    public void setBookingID(int bookingID) {
        this.bookingId = bookingID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

}
