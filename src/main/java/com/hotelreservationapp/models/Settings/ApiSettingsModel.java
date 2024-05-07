package com.hotelreservationapp.models.Settings;

public class ApiSettingsModel {
    private String username;
    private String password;

    public ApiSettingsModel() {
    }

    public ApiSettingsModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
