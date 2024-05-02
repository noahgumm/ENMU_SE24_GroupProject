package com.hotelreservationapp.models.Settings;

import  com.google.gson.*;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;

/**
 * Reads the settings.json file for database settings.
 * @author Joshua Espana
 */
public class SettingsReader {

    private final String settingsPath = "src/main/resources/settings.json";

    
        // {
        //     "connections": [
        //       {
        //         "ConnectionName": "local",
        //         "ConnectionValues": {
        //               "host": "localhost",
        //               "port": "3306",
        //               "database_name": "hotel_reservation_system",
        //               "user" : "root",
        //               "password" : "password"
        //         }
        //       },
        //       {
        //         "ConnectionName": "AWS",
        //         "ConnectionValues": {
        //               "host" : "hotel-reservation-system.cbzvnoedvh5z.us-east-1.rds.amazonaws.com",
        //               "port" : "3306",
        //               "database_name" : "hotel_reservation_system",
        //               "user" : "root",
        //               "password" : "3NMU_S324_Gr0upPr0j3ct"
        //         }
        //       }
        //     ]
        //   }
            
    //create a string named hardCodedSettings that contains the JSON string above
    private final String hardCodedSettings = "{\n" +
            "    \"connections\": [\n" +
            "      {\n" +
            "        \"ConnectionName\": \"DEFAULT\",\n" +
            "        \"ConnectionValues\": {\n" +
            "              \"host\": \"localhost\",\n" +
            "              \"port\": \"3306\",\n" +
            "              \"database_name\": \"hotel_reservation_system\",\n" +
            "              \"user\" : \"root\",\n" +
            "              \"password\" : \"password\"\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"ConnectionName\": \"AWS\",\n" +
            "        \"ConnectionValues\": {\n" +
            "              \"host\" : \"hotel-reservation-system.cbzvnoedvh5z.us-east-1.rds.amazonaws.com\",\n" +
            "              \"port\" : \"3306\",\n" +
            "              \"database_name\" : \"hotel_reservation_system\",\n" +
            "              \"user\" : \"root\",\n" +
            "              \"password\" : \"3NMU_S324_Gr0upPr0j3ct\"\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  }";

    public SettingsReader() {
    }

    public ConnectionsConfig readSettings() {
        Gson gson = new Gson();
        try  {


            // FileReader reader = new FileReader(settingsPath);
            // ConnectionsConfig toReturn = gson.fromJson(reader, ConnectionsConfig.class);
            // reader.close();
            ConnectionsConfig toReturn = gson.fromJson(hardCodedSettings, ConnectionsConfig.class);

            return toReturn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ConnectionsConfig(); // Return an empty config if there's an error
    }
}
