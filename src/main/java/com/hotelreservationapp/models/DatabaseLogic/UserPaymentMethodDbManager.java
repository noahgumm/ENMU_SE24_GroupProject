package com.hotelreservationapp.models.DatabaseLogic;


import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.hotelreservationapp.models.Database.Tables.UserPaymentMethod;


/**
 * Handles the database management for the user payment method table.
 * @author Griffin Graham, Joshua Espana
 */
public class UserPaymentMethodDbManager extends DbManagerBase{

    public  UserPaymentMethodDbManager(String url, String username, String password){
        super(url, username, password);
    }

    /**
     * Provided a specific ID, returns the specific user payment method.
     * @param cardID The unique ID of the user payment method record. THIS IS NOT THE CREDIT CARD NUMBER.
     * @return The found record, or null if not found.
     */
    public  UserPaymentMethod getUserPaymentMethod(int cardID){
        UserPaymentMethod userPaymentMethod = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM user_payment_methods WHERE card_id = ?");
            preparedStatement.setInt(1, cardID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String cardNumber = rs.getString("card_number");
                String cardHolderName = rs.getString("card_holder_name");
                String cardType = rs.getString("card_type");
                Date expiryDate = rs.getDate("expiry_date");
                String cvv = rs.getString("cvv");
                int userId = rs.getInt("user_id");
                Timestamp createdAt = rs.getTimestamp("created_at");
                boolean isDeleted = rs.getBoolean("is_deleted");

                userPaymentMethod = new UserPaymentMethod(cardID, cardNumber,cardHolderName,cardType,expiryDate,cvv,userId,createdAt,isDeleted);
            }
            conn.close();
        }
        catch (Exception e){

        }
        return  userPaymentMethod;
    }

    /**
     * Gets a user payment method using the card number and the user ID.
     * @param cardID The unique ID of the user payment method record. THIS IS NOT THE CREDIT CARD NUMBER.
     * @return The found record, or null if not found.
     */
    public UserPaymentMethod getUserPaymentMethod(int userID, String cardNum){
        UserPaymentMethod userPaymentMethod = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM user_payment_methods WHERE user_id = ? and card_number = ?");
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, cardNum);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int cardID = rs.getInt("card_id");
                String cardNumber = rs.getString("card_number");
                String cardHolderName = rs.getString("card_holder_name");
                String cardType = rs.getString("card_type");
                Date expiryDate = rs.getDate("expiry_date");
                String cvv = rs.getString("cvv");
                int userId = rs.getInt("user_id");
                Timestamp createdAt = rs.getTimestamp("created_at");
                boolean isDeleted = rs.getBoolean("is_deleted");

                userPaymentMethod = new UserPaymentMethod(cardID, cardNumber,cardHolderName,cardType,expiryDate,cvv,userId,createdAt,isDeleted);
            }
            conn.close();
        }
        catch (Exception e){

        }
        return  userPaymentMethod;
    }

    public UserPaymentMethod getUserPaymentMethod(UserPaymentMethod userPaymentMethod){
        UserPaymentMethod toReturn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM user_payment_methods WHERE user_id = ? and card_number = ? and card_holder_name = ? and card_type = ? and expiry_date = ? and cvv = ?");
            preparedStatement.setInt(1, userPaymentMethod.getUserId());
            preparedStatement.setString(2, userPaymentMethod.getCardNumber());
            preparedStatement.setString(3, userPaymentMethod.getCardHolderName());
            preparedStatement.setString(4, userPaymentMethod.getCardType());
            preparedStatement.setDate(5, userPaymentMethod.getExpiryDate());
            preparedStatement.setString(6, userPaymentMethod.getCvv());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int cardID = rs.getInt("card_id");
                String cardNumber = rs.getString("card_number");
                String cardHolderName = rs.getString("card_holder_name");
                String cardType = rs.getString("card_type");
                Date expiryDate = rs.getDate("expiry_date");
                String cvv = rs.getString("cvv");
                int userId = rs.getInt("user_id");
                Timestamp createdAt = rs.getTimestamp("created_at");
                boolean isDeleted = rs.getBoolean("is_deleted");

                toReturn = new UserPaymentMethod(cardID, cardNumber,cardHolderName,cardType,expiryDate,cvv,userId,createdAt,isDeleted);
            }
            conn.close();
        }
        catch (Exception e){

        }
        return toReturn;
    }

    /**
     * Provide a user ID, returns all the payment methods associated with the user.
     * @param userID
     * @return
     */
    public  List<UserPaymentMethod> getAllUserPaymentMethodsFor(int userID){
        List<UserPaymentMethod> userPaymentMethods = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM user_payment_methods where user_id = ?");
            preparedStatement.setInt(1, userID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String cardNumber = rs.getString("card_number");
                String cardHolderName = rs.getString("card_holder_name");
                String cardType = rs.getString("card_type");
                Date expiryDate = rs.getDate("expiry_date");
                String cvv = rs.getString("cvv");
                int userId = rs.getInt("user_id");
                Timestamp createdAt = rs.getTimestamp("created_at");
                int cardID = rs.getInt("card_id");
                boolean isDeleted = rs.getBoolean("is_deleted");
                userPaymentMethods.add(new UserPaymentMethod(cardID, cardNumber,cardHolderName,cardType,expiryDate,cvv,userId,createdAt, isDeleted));
            }
            conn.close();
        }
        catch (Exception e){

        }
        return userPaymentMethods;
    }

    /**
     * Gets all the payment methods in the database.
     * @return
     */
    public List<UserPaymentMethod> getAllUserPaymentMethodsFor(){
        List<UserPaymentMethod> userPaymentMethods = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM user_payment_methods");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String cardNumber = rs.getString("card_number");
                String cardHolderName = rs.getString("card_holder_name");
                String cardType = rs.getString("card_type");
                Date expiryDate = rs.getDate("expiry_date");
                String cvv = rs.getString("cvv");
                int userId = rs.getInt("user_id");
                Timestamp createdAt = rs.getTimestamp("created_at");
                int cardID = rs.getInt("card_id");
                boolean isDeleted = rs.getBoolean("is_deleted");
                userPaymentMethods.add(new UserPaymentMethod(cardID, cardNumber,cardHolderName,cardType,expiryDate,cvv,userId,createdAt,isDeleted));
            }
            conn.close();
        }
        catch (Exception e){

        }
        return userPaymentMethods;
    }

    /**
     * Given the ID of the payment method, marks the payment method as deleted. Note: payment methods are not deleted
     * from the database because they tie into transactions.
     * @param card_id
     * @return
     */
    public boolean deleteUserPaymentMethod(int card_id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement("update a set a.is_deleted = 1 from user_payment_methods where card_id = ?");
            preparedStatement.setInt(1, card_id);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        }
        catch (Exception e){

        }
        return false;
    }

    public UserPaymentMethod createUserPaymentMethod(UserPaymentMethod userPaymentMethod){
        return createUserPaymentMethod(userPaymentMethod.getCardNumber(), userPaymentMethod.getCardHolderName(),
                userPaymentMethod.getCardType(), userPaymentMethod.getExpiryDate().toString(), userPaymentMethod.getCvv(),
                userPaymentMethod.getUserId());
    }

    /**
     * Creates a new payment method.
     * @param cardNumber Credit card number
     * @param cardholderName Name on the credit card.
     * @param cardType Type of credit card.
     * @param expDate Date of expiration.
     * @param cvv CVV code.
     * @param userID ID of the user the credit card belongs.
     * @return The newly created card. If not created, returns null.
     */
    public UserPaymentMethod createUserPaymentMethod(String cardNumber, String cardholderName, String cardType,
                                                     String expDate, String cvv, int userID){
        UserPaymentMethod userPaymentMethod = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement preparedStatement = conn.prepareStatement
                    ("INSERT INTO user_payment_methods(card_number, card_holder_name, card_type, expiry_date,cvv,user_id, created_at) values (?,?,?,?,?,?,now())", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cardNumber);
            preparedStatement.setString(2, cardholderName);
            preparedStatement.setString(3, cardType);
            preparedStatement.setDate(4, java.sql.Date.valueOf(expDate));
            preparedStatement.setString(5, cvv);
            preparedStatement.setInt(6, userID);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int key = -1;
            if (rs.next()) {
                key = rs.getInt(1);
            }
            if(key > 0){
                userPaymentMethod = getUserPaymentMethod(key);
            }
            conn.close();
        }catch (Exception e){

        }
        return userPaymentMethod;
    }
}
