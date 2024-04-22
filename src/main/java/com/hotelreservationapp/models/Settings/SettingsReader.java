package com.hotelreservationapp.models.Settings;

import  com.google.gson.*;

import java.io.*;

/**
 * Reads the settings.json file for database settings.
 * @author Joshua Espana
 */
public class SettingsReader {

    private final String settingsPath = "src/main/resources/settings.json";

    public SettingsReader() {
    }

    public ConnectionsConfig readSettings() {
        Gson gson = new Gson();
        try  {
            // Read json file to classes
            FileReader reader = new FileReader(settingsPath);
            return gson.fromJson(reader, ConnectionsConfig.class);
        } catch (IOException e) {
            e.printStackTrace(); // Log the error or handle it appropriately
        }
        return new ConnectionsConfig(); // Return an empty config if there's an error
    }
}
