package com.hotelreservationapp.models.Database.Tables;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

/**
 * Database table model
 * @author Griffin Graham, Joshua Espana
 */
public class Reservation {
    private int reservationId;
    private int userId;
    private List<Room> rooms;
    private Date checkInDate;
    private Date checkOutDate;
    private double totalPrice;
    private int numGuests;
	private boolean pets;
    private String reservationStatus;
    private Timestamp createdAt;

    public Reservation() {
        this.rooms = new ArrayList<>();
    }

    public Reservation(int reservationId,int userId, List<Room> rooms, Date checkInDate, Date checkOutDate, double totalPrice,
                       int numGuests, boolean pets, String reservationStatus, Timestamp createdAt) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.rooms = rooms;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.numGuests = numGuests;
		this.pets = pets;
        this.reservationStatus = reservationStatus;
        this.createdAt = createdAt;
    }


    public Reservation(int reservationId,int userId, List<Room> rooms, Date checkInDate, Date checkOutDate, double totalPrice,
                       int numGuests, String reservationStatus, Timestamp createdAt) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.rooms = rooms;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.numGuests = numGuests;
		this.pets = false;
        this.reservationStatus = reservationStatus;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
	
    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public String getCheckInDateAsString() {
        return checkInDate.toString();
    }

    public String getCheckOutDateAsString() {
        return checkOutDate.toString();
    }

    public void setCheckInDate(Date checkInDate) {
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

    public int getNumGuests() {
        return numGuests;
    }

    public void setNumGuests(int numGuests) {
        this.numGuests = numGuests;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
	
    public boolean getPets() {
        return pets;
    }
	
    public void setPets(boolean pets) {
        this.pets = pets;
    }
}
