package com.hotelreservationapp.models.DatabaseLogic;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hotelreservationapp.models.Database.Tables.Transaction;

/**
 * Handles the database management for the transactions table.
 * @author Griffin Graham, Joshua Espana
 */
public class TransactionDbManager extends  DbManagerBase{

    public TransactionDbManager(String url, String username, String password){
        super(url, username, password);
    }

    /**
     * Provided a user ID, gets all transactions associated with the specified user.
     * @param userID
     * @return
     */
    public  List<Transaction> getAllTransactionsFor(int userID){
        List<Transaction> transactions = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM transactions where user_id = ?");
            preparedStatement.setInt(1, userID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int transactionID = rs.getInt("transaction_id");
                int reservationId = rs.getInt("reservation_id");
                double amount = rs.getDouble("amount");
                Timestamp transactionDate = rs.getTimestamp("transaction_date");
                int userPaymentMethodId = rs.getInt("user_payment_method_id");
                transactions.add(new Transaction(transactionID, userID, reservationId, amount,transactionDate, userPaymentMethodId));
            }
            conn.close();
        }
        catch (Exception e){

        }
        return  transactions;
    }

    /**
     * Gets all transactions from the database.
     * @return
     */
    public List<Transaction> getAllTransactions(){
        List<Transaction> transactions = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM transactions");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int transactionID = rs.getInt("transaction_id");
                int userID = rs.getInt("user_id");
                int reservationId = rs.getInt("reservation_id");
                double amount = rs.getDouble("amount");
                Timestamp transactionDate = rs.getTimestamp("transaction_date");
                int userPaymentMethodId = rs.getInt("user_payment_method_id");
                transactions.add(new Transaction(transactionID, userID, reservationId, amount,transactionDate, userPaymentMethodId));
            }
            conn.close();
        }
        catch (Exception e){

        }
        return  transactions;
    }

    /**
     * Provided a specific transaction ID, gets the transaction from the database.
     * @param transactionID
     * @return
     */
    public  Transaction getTransaction(int transactionID){
        Transaction transaction = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM transactions WHERE transaction_id = ?");
            preparedStatement.setInt(1, transactionID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("user_id");
                int reservationId = rs.getInt("reservation_id");
                double amount = rs.getDouble("amount");
                Timestamp transactionDate = rs.getTimestamp("transaction_date");
                int userPaymentMethodId = rs.getInt("user_payment_method_id");
                transaction = new Transaction(transactionID, userID, reservationId, amount,transactionDate, userPaymentMethodId);
            }
            conn.close();
        }
        catch (Exception e){

        }
        return  transaction;
    }

    public Transaction createTransaction(Transaction transaction){
        return createTransaction(transaction.getUserId(), transaction.getReservationId(), transaction.getAmount(), transaction.getUserPaymentMethodID());
    }

    /**
     * Creates a new transactoion in the database.
     * @param userID ID of the user which the transaction belongs.
     * @param reservationID ID of the users specific reservation to associate with the transaction.
     * @param amount Total amount paid or to be paid on the transaction.
     * @param userPaymentMethodID Users specific payment method.
     * @return The newly created transaction. If failed to create, returns null;
     */
    public Transaction createTransaction(int userID, int reservationID, double amount, int userPaymentMethodID){
        Transaction transaction = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement
                    ("INSERT INTO transactions(user_id,reservation_id,amount,user_payment_method_id,transaction_date) " +
                            "values (?,?,?,?,now())", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, reservationID);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setInt(4, userPaymentMethodID);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int key = -1;
            if (rs.next()) {
                key = rs.getInt(1);
            }
            if(key > 0){
                transaction = getTransaction(key);
            }
            conn.close();
        }catch (Exception e){
            String msg = e.getMessage();
        }
        return  transaction;
    }
}
