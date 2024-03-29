package com.hotelreservationapp.models.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.crypto.Data;


/**
 * Class that connects to the database.
 */
public class DatabaseConnector implements AutoCloseable   {
    DatabaseSettingsReader settingsReader;
    Connection databaseConnection;
    boolean isConnected = false;

    /**
     * Constructor for the DatabaseConnector class.
     * @param connectionInformation The information needed to connect to the database
     */
    public DatabaseConnector(String settingsFilePath) {
        String url = "jdbc:mysql://localhost:3306/hotel_reservation_system";
        String username = "root";
        String password = "password";
        this.settingsReader = new DatabaseSettingsReader(settingsFilePath);
        try{
            databaseConnection = DriverManager.getConnection(url, username, password);
        }
        catch(Exception e){
            databaseConnection = null;
        }
    }

    @Override  
    public void close() throws Exception {
        // Close the connection
        if(databaseConnection != null){
            try{
                databaseConnection.close();
                isConnected = false;
            }
            catch(SQLException ex){
            }
        }
    }

    /**
     * Gets the connection to the database.
     * @return The connection to the database
     */
    public void connect() {
        try{
            String username = settingsReader.getDatabaseSettingsModel().getUsername();
            String password = settingsReader.getDatabaseSettingsModel().getPassword();
            String connStr = settingsReader.getDatabaseSettingsModel().getDbConnString();
            databaseConnection = DriverManager.getConnection(connStr, username, password);
            isConnected = true;
        }
        catch(Exception e){
            if(databaseConnection != null){
                try{
                    databaseConnection.close();
                }
                catch(SQLException ex){
                }
            }
        }
    }

    /**
     * Checks if the connection to the database is open.
     * @return
     */
    public boolean isConnected() {
        if(databaseConnection == null){
            isConnected = false;
        }
        else{
            try{
                isConnected = !databaseConnection.isClosed();
            }
            catch(SQLException ex){
                isConnected = false;
            }
        }
        return isConnected;
    }
}
