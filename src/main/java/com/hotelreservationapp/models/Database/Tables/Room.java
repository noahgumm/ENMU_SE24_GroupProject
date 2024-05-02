package com.hotelreservationapp.models.Database.Tables;

import java.util.List;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * Database table model for rooms
 * @author Griffin Graham, Joshua Espana, Zachary Marrs
 */
public class Room implements Serializable {
    private int roomId;
    private String roomNumber;
    private String roomType;
    private int floorNumber;
    private double pricePerNight;
    private String roomDescription;
    private int numberOfBeds;
    private Timestamp createdAt;

    public Room() {
    }

    public Room(int roomId, String roomNumber, String roomType, int floorNumber, double pricePerNight,
                String roomDescription, int numberOfBeds, Timestamp createdAt) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.floorNumber = floorNumber;
        this.pricePerNight = pricePerNight;
        this.roomDescription = roomDescription;
        this.numberOfBeds = numberOfBeds;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}