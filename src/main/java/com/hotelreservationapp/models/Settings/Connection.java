package com.hotelreservationapp.models.Settings;


public class Connection {
    private String ConnectionName;
    private ConnectionValues ConnectionValues;

    public String getConnectionName() {
        return ConnectionName;
    }

    public void setConnectionName(String connectionName) {
        ConnectionName = connectionName;
    }

    public ConnectionValues getConnectionValues() {
        return ConnectionValues;
    }

    public void setConnectionValues(ConnectionValues connectionValues) {
        ConnectionValues = connectionValues;
    }
}