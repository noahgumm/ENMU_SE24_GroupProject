package com.hotelreservationapp.models;

public class RoomModel 
{
    private int roomID;
    private String roomNumber;
    private String roomType;
    private int floorNumber;
    private float pricePerNight;
    private String roomDescription;
    private int numberOfBeds;

    public RoomModel() {
    }

    public RoomModel(int roomId, String roomNumber, String roomType, int floorNumber, float pricePerNight, String roomDescription, int numberOfBeds) {
        this.roomID = roomId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.floorNumber = floorNumber;
        this.pricePerNight = pricePerNight;
        this.roomDescription = roomDescription;
        this.numberOfBeds = numberOfBeds;
    }
    
    public int getroomID() {
        return roomID;
    }

    public void setroomID(int roomID) {
        this.roomID = roomID;
    }

    public String getroomNumber() {
        return roomNumber;
    }

    public void setroomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getroomType() {
        return roomType;
    }

    public void setroomType(String roomType) {
        this.roomType = roomType;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public float getpricePerNight() {
        return pricePerNight;
    }

    public void setpricePerNight(float pricePerNight) {
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
}
