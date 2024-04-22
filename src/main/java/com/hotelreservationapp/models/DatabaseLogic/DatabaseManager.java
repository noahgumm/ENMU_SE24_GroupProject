package com.hotelreservationapp.models.DatabaseLogic;




import java.util.ArrayList;
import java.util.List;

import com.hotelreservationapp.models.Database.Prepared.*;
import com.hotelreservationapp.models.Database.Tables.*;
import com.hotelreservationapp.models.Settings.Connection;
import com.hotelreservationapp.models.Settings.ConnectionsConfig;
import com.hotelreservationapp.models.Settings.SettingsReader;

/**
 * Main class for handling all database management related tasks. This includes the CRUD for all tables.
 * @author Griffin Graham, Joshua Espana
 */
public class DatabaseManager extends DbManagerBase {
    public AdminDbManager adminDbManager;
    public ReservationDbManager reservationDbManager;
    public RoomDbManager roomDbManager;
    public UserDbManager userDbManager;
    public UserPaymentMethodDbManager userPaymentMethodDbManager;
    public  TransactionDbManager transactionDbManager;

    /**
     * Connects to the default database, determined by the settings file.
     */
    public DatabaseManager(){
        SettingsReader settingsReader = new SettingsReader();
        ConnectionsConfig settings = settingsReader.readSettings();
        Connection aws = findConnectionByName(settings, "DEFAULT");
        String dbURL = "jdbc:mysql://" + aws.getConnectionValues().getHost() + ":" + aws.getConnectionValues().getPort() + "/" + aws.getConnectionValues().getDatabase_name();
        super.setDbURL(dbURL);
        super.setDbUsername(aws.getConnectionValues().getUser());
        super.setDbPassword(aws.getConnectionValues().getPassword());
        
        adminDbManager = new AdminDbManager(dbURL, dbUsername, dbPassword);
        reservationDbManager = new ReservationDbManager(dbURL, dbUsername, dbPassword);
        roomDbManager = new RoomDbManager(dbURL, dbUsername, dbPassword);
        userDbManager = new UserDbManager(dbURL, dbUsername, dbPassword);
        userPaymentMethodDbManager = new UserPaymentMethodDbManager(dbURL, dbUsername, dbPassword);
        transactionDbManager = new TransactionDbManager(dbURL, dbUsername, dbPassword);
    }

    /**
     * Connects to a specific database.
     * @param url
     * @param username
     * @param password
     */
    public DatabaseManager(String url, String username, String password){
        super(url, username, password);
        adminDbManager = new AdminDbManager(dbURL, dbUsername, dbPassword);
        reservationDbManager = new ReservationDbManager(dbURL, dbUsername, dbPassword);
        roomDbManager = new RoomDbManager(dbURL, dbUsername, dbPassword);
        userDbManager = new UserDbManager(dbURL, dbUsername, dbPassword);
        userPaymentMethodDbManager = new UserPaymentMethodDbManager(dbURL, dbUsername, dbPassword);
        transactionDbManager = new TransactionDbManager(dbURL, dbUsername, dbPassword);
    }

    /**
     * Gets specific connection from the settings file.
     * @param config
     * @param name
     * @return
     */
    private static Connection findConnectionByName(ConnectionsConfig config, String name) {
        try{
            for (Connection connection : config.getConnections()) {
                if (connection.getConnectionName().equals(name)) {
                    return connection;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets all relevant information for a specific user.
     * @param userID
     * @return
     */
    public UserOverview getUserOverviewFor(int userID){
        User user = userDbManager.getUser(userID);
        List<Transaction> userTransactions = transactionDbManager.getAllTransactionsFor(userID);
        List<UserPaymentMethod> paymentMethods = userPaymentMethodDbManager.getAllUserPaymentMethodsFor(userID);
        List<Reservation> reservations = reservationDbManager.getReservationsFor(userID);
        return new UserOverview(user, reservations, userTransactions, paymentMethods);
    }

    public List<Room> getAvailableRoomsBetweenDates(String date1, String date2){
        List<Reservation> reservations = reservationDbManager.getAllReservationsBetween(date1, date2);
        List<Room> allRooms = roomDbManager.getAllRooms();
        List<Room> availableRooms = new ArrayList<>();
        for(int i = 0; i < allRooms.size(); ++i){
            boolean isRoomAvailable = true;
            Room room = allRooms.get(i);
           for(int j = 0; j < reservations.size(); ++j){
               Reservation resv = reservations.get(j);
               if(resv.getRoomId() == room.getRoomId()){
                   isRoomAvailable = false;
                   break;
               }
           }
           if(isRoomAvailable){
               availableRooms.add(room);
           }
        }
        return availableRooms;
    }

    public TransactionInformation getTransactionInformationFor(int transactionID){
        Transaction transaction = transactionDbManager.getTransaction(transactionID);
        User user = null;
        UserPaymentMethod paymentMethod = null;
        Reservation reservation = null;
        if(transaction != null){
            user = userDbManager.getUser(transaction.getUserId());
            paymentMethod = userPaymentMethodDbManager.getUserPaymentMethod(transaction.getUserPaymentMethodID());
            reservation = reservationDbManager.getReservation(transaction.getReservationId());
        }
        return  new TransactionInformation(user, transaction, paymentMethod,reservation);
    }
}