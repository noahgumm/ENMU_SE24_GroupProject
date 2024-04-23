package com.hotelreservationapp.models.Database;

public class DatabaseSettingsModel {
    String host;
    String port;
    String databaseName;
    String username;
    String password;
    String dbConnString;

    public DatabaseSettingsModel(String host, String port, String databaseName, String username, String password, String dbConnString) {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
        this.dbConnString = dbConnString;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDbConnString() {
        return dbConnString;
    }
}
