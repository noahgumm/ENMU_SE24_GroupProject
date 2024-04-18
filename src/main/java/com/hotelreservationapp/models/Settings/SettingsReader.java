package com.hotelreservationapp.models.Settings;

import  com.google.gson.*;

import java.io.FileReader;

/**
 * Reads the settings.json file for database settings.
 * @author Joshua Espana
 */
public class SettingsReader {

    private final String settingsPath = "src/main/java/settings.json";

    public SettingsReader() {
    }

    public ConnectionsConfig readSettings() {
        Gson gson = new Gson();
        try  {
            FileReader reader = new FileReader(settingsPath);
            return gson.fromJson(reader, ConnectionsConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  new ConnectionsConfig();
    }
}
