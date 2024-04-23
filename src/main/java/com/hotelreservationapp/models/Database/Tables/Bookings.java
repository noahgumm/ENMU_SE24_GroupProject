package com.hotelreservationapp.models.Database.Tables;

import java.util.Date;

/**
 * Database table model
 * @author Zachary Marrs
 */


public class Bookings {
	private int userId;
    private int roomId;
    private Date checkInDate;
    private Date checkOutDate;
	private double totalPrice;
	private String bookingStatus;

    public Bookings() {
    }

    public Bookings(int roomId, Date checkInDate, Date checkOutDate, double totalPrice, String bookingStatus) {
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Getters and setters
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date startDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
	
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
	
	public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
	
}