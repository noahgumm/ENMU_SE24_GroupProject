package com.hotelreservationapp.models.Database;

/**
 * Interact with the database using this class.
 */
public abstract class DatabaseManager implements AutoCloseable {
    // database connection information
    final String SETTINGS_FILEPATH = "settings.json";
    protected DatabaseConnector databaseConnector;

    public DatabaseManager() {
        this.databaseConnector = new DatabaseConnector(SETTINGS_FILEPATH);
    }

    @Override
    public void close() throws Exception {
        // Close the connection
        if (databaseConnector != null) {
            try {
                databaseConnector.close();
            } 
            catch (Exception ex) {
            }
        }
    }
}


