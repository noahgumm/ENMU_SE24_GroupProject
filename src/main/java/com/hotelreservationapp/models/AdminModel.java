package com.hotelreservationapp.models;

public class AdminModel {
    private int adminId;
    private String username;
    private String password;
    private String email;
    private String createdAt;
    private String firstName;
    private String lastName;
    private String address;

    public AdminModel() {
    }

    public AdminModel(int adminId, String username, String password, String email, String phoneNumber, String createdAt, String firstName, String lastName, String address) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }
    
    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int userId) {
        this.adminId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
