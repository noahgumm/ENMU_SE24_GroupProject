package com.hotelreservationapp.models.Database;
import java.io.File;
import java.util.Scanner;

import org.json.*;

/**
 * Reads the database connection settings from the settings file.
 */
public class DatabaseSettingsReader {
    private DatabaseSettingsModel databaseSettingsModel; //models the database settings

    /**
     * Provided the settings file path, this constructor reads the settings file and creates a DatabaseSettingsModel object.
     * @param settingsFilePath
     */
    public DatabaseSettingsReader(String settingsFilePath) {
        // Read the settings file
        String fileContents = "{\r\n" + //
                        "    \"database\": {\r\n" + //
                        "        \"host\": \"localhost\",\r\n" + //
                        "        \"port\": \"3306\",\r\n" + //
                        "        \"database_name\": \"hotel_reservation_system\",\r\n" + //
                        "        \"user\" : \"root\",\r\n" + //
                        "        \"password\" : \"password\"\r\n" + //
                        "    }\r\n" + //
                        "}";
        String dbConn = "jdbc:mysql://localhost:3306/hotel_reservation_system?user=root&password=password";
        
        // File file = new File(settingsFilePath);
        // try {
        //     Scanner scanner = new Scanner(file);
        //     while (scanner.hasNextLine()) {
        //         fileContents += scanner.nextLine();
        //     }
        //     scanner.close();
        // } catch (Exception e) {
        //     fileContents = "";
        // }
        // System.out.println(fileContents);

        JSONObject settings = new JSONObject(fileContents);

        // Get the database connection information
        JSONObject databaseConnectionInformation = settings.getJSONObject("database");
        String host = databaseConnectionInformation.getString("host");
        String port = databaseConnectionInformation.getString("port");
        String databaseName = databaseConnectionInformation.getString("database_name");
        String username = databaseConnectionInformation.getString("user");
        String password = databaseConnectionInformation.getString("password");

        // Create the DatabaseConnectionInformation object
        this.databaseSettingsModel = new DatabaseSettingsModel(host, port, databaseName, username, password, "jdbc:mysql://" + host + ":" + port + "/" + databaseName);
    }

    /**
     * Gets the DatabaseSettingsModel object.
     * @return The DatabaseSettingsModel object
     */
    public DatabaseSettingsModel getDatabaseSettingsModel() {
        return databaseSettingsModel;
    }
}
