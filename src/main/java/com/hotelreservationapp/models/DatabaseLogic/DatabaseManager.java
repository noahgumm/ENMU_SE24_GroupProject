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
        System.out.println(settings.getConnections());
        Connection aws = findConnectionByName(settings, "AWS");
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
                System.out.println(connection.getConnectionName() + " vs " + name);
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

    /**
     * Gets all relevant information for a specific user's cart.
     * @param userID The user's ID.
     * @return The cart information.
     * @author Joshua Espana
     */
    public CartInformation getCartInformationFor(int userID){
        User user = userDbManager.getUser(userID);
        List<RoomReservation> reservations = getRoomReservationsFor(userID);
        return new CartInformation(user, reservations);
    }

    /**
     * Gets all relevant information for a specific user's cart.
     * @param user The user.
     * @return The cart information.
     * @author Joshua Espana
     */
    public CartInformation getCartInformationFor(User user){
        List<RoomReservation> reservations = getRoomReservationsFor(user.getUserId());
        return new CartInformation(user, reservations);
    }

    public List<RoomReservation> getRoomReservationsFor(int userID){
        List<Reservation> reservations = reservationDbManager.getReservationsFor(userID, "pending");
        List<RoomReservation> roomReservations = new ArrayList<>();
        for(int i = 0; i < reservations.size(); ++i){
            Reservation resv = reservations.get(i);
            Room room = roomDbManager.getRoom(resv.getRoomId());
            roomReservations.add(new RoomReservation(room, resv));
        }
        return roomReservations;
    }

    public List<RoomReservation> getRoomReservationsFor(User user){
        List<Reservation> reservations = reservationDbManager.getReservationsFor(user.getUserId());
        List<RoomReservation> roomReservations = new ArrayList<>();
        for(int i = 0; i < reservations.size(); ++i){
            Reservation resv = reservations.get(i);
            Room room = roomDbManager.getRoom(resv.getRoomId());
            roomReservations.add(new RoomReservation(room, resv));
        }
        return roomReservations;
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

    /**
     * Takes a reservation and processes it as complete. Pays the reservation and sets the status to complete.
     * If the payment method does not exist, it will be created and added to the users files.
     * @param user The user.
     * @param paymentMethod The payment method.
     * @return The total amount paid for all reservations.
     */
    public double processReservationAsComplete(User user, UserPaymentMethod paymentMethod) {
        //1. get all "pending" reservations for the user
        //2. pay for the reservation
        //3. set the reservation status to "complete"

        //does the user payment method already exist?
        paymentMethod.setUserId(user.getUserId());
        UserPaymentMethod exising = userPaymentMethodDbManager.getUserPaymentMethod(paymentMethod);
        if(exising == null) {
            paymentMethod.setUserId(user.getUserId());
            paymentMethod = userPaymentMethodDbManager.createUserPaymentMethod(paymentMethod);
        }
        else{
            paymentMethod = exising;
        }
        final int userPaymentMethodID = paymentMethod.getUserPaymentMethodID();
        var wrapper = new Object(){ double finalAmountPaid = 0;};
        reservationDbManager.getReservationsFor(user.getUserId(), "pending").forEach(reservation -> {
            wrapper.finalAmountPaid += reservation.getTotalPrice();
            transactionDbManager.createTransaction(user.getUserId(), reservation.getReservationId(), reservation.getTotalPrice(), userPaymentMethodID);
            reservationDbManager.setReservationStatus(reservation, "confirmed");
        });

        return wrapper.finalAmountPaid;
    }

    public AdminReservationReport getAdminReservationReportFor(int reservationID){
        Reservation reservation = reservationDbManager.getReservation(reservationID);
        List<Room> rooms = roomDbManager.getRoomsFor(reservationID);
        User user = userDbManager.getUser(reservation.getUserId());
        return new AdminReservationReport(reservation, rooms, user);
    }

    public List<AdminReservationReport> getAdminReservationReportForAllReservations(){
        List<AdminReservationReport> reports = new ArrayList<>();
        List<Reservation> reservations = reservationDbManager.getAllReservations();
        for(int i = 0; i < reservations.size(); ++i){
            Reservation reservation = reservations.get(i);
            List<Room> rooms = roomDbManager.getRoomsFor(reservation.getReservationId());
            User user = userDbManager.getUser(reservation.getUserId());
            reports.add(new AdminReservationReport(reservation, rooms, user));
        }
        return reports;
    }
}