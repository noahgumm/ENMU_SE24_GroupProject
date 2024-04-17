package com.hotelreservationapp.models.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotelreservationapp.models.TransactionsModel;

public class TransactionsDatabaseManager extends DatabaseManager implements IDatabaseFunctionality<TransactionsModel>{
    @Override
    public boolean create(TransactionsModel object) {
        // Create transaction
        // write SQL here
        String sql = "INSERT INTO transactions (transaction_id, user_id, booking_id, amount, transaction_date, user_payment_method_id) VALUES (?, ?, ?,?,?,?);";
        int numRows = 0;
        try {
            databaseConnector.connect();
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setInt(1, object.getTransactionID());
            statement.setInt(2, object.getUserID());
            statement.setInt(3, object.getBookingID());
            statement.setFloat(4, object.getTotalAmount());
            statement.setString(5, object.getTransactionDate());
            statement.setInt(6, object.getUserPaymentMethodID());
            numRows = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return numRows > 0;
    }

    @Override
    public boolean update(TransactionsModel object) {
        // Update transaction
        //write SQL here

        String sql = "UPDATE transactions SET user_id = ?, booking_id = ?, amount = ?, transaction_date = ? WHERE transaction_id = ?;";
        int numRows = 0;
        try {
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setInt(1, object.getUserID());
            statement.setInt(2, object.getBookingID());
            statement.setFloat(3, object.getTotalAmount());
            statement.setString(4, object.getTransactionDate());
            statement.setInt(5, object.getTransactionID());
            numRows = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numRows > 0;
    }

    @Override
    public boolean delete(TransactionsModel object) {
        // Delete transaction
        String sql = "DELETE FROM transactions WHERE transaction_id = ?;";
        int numRows = 0;
        try {
            PreparedStatement statement =  databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setInt(1, object.getTransactionID());
            numRows = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return numRows > 0;
    }

    public List<TransactionsModel> readAll() {
        // Read transaction
        //write SQL here. read into TransactionsModel objects
        List<TransactionsModel> transactions = new ArrayList<>();

        String sql = "SELECT * FROM transactions;";
        try {
            databaseConnector.connect();
            Statement statement =  databaseConnector.databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                TransactionsModel transaction = new TransactionsModel();
                transaction.setTransactionID(resultSet.getInt("transaction_id"));
                transaction.setUserID(resultSet.getInt("user_id"));
                transaction.setBookingID(resultSet.getInt("booking_id"));
                transaction.setTotalAmount(resultSet.getFloat("total_amount"));
                transaction.setTransactionDate(resultSet.getString("transaction_date"));
                transaction.setUserPaymentMethodID(resultSet.getInt("user_payment_method_id"));
                transactions.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public TransactionsModel read(String transactionDate) {
        String sql = "SELECT * FROM transactions WHERE transaction_date = ?;";
        TransactionsModel transaction = new TransactionsModel();
        try {
            databaseConnector.connect();
            PreparedStatement statement = databaseConnector.databaseConnection.prepareStatement(sql);
            statement.setString(1, transactionDate);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                transaction.setTransactionID(resultSet.getInt("transaction_id"));
                transaction.setUserID(resultSet.getInt("user_id"));
                transaction.setBookingID(resultSet.getInt("booking_id"));
                transaction.setTotalAmount(resultSet.getFloat("total_amount"));
                transaction.setTransactionDate(resultSet.getString("transaction_date"));
                transaction.setUserPaymentMethodID(resultSet.getInt("user_payment_method_id"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return transaction;
    }

    public void close() {
        try {
            databaseConnector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
