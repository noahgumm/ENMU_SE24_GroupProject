package com.hotelreservationapp.models.DatabaseLogic;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Base class for database managers. Contains the logic for running custom SQL commands on the database.
 * @author Griffin Graham, Joshua Espana
 */
public abstract class DbManagerBase {

    protected String dbURL;
    protected String dbUsername;
    protected String dbPassword;
    public DbManagerBase(String dbURL, String dbUsername, String dbPassword){
        this.dbURL = dbURL;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public DbManagerBase(){
    }

    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    /**
     * Runs RAW, UNPREPARED queries on the database. For safety, use the runCustomDBCommand function with a
     * prepared statement.
     * @param sql
     * @return true if the statement was executed. False if not.
     */
    public boolean runCustomDBCommand(String sql){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            conn.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * Provided a prepared SQL query and array of the parameter values in their proper order,
     * makes a prepared statement call to the database.
     * Example: sql="update a set a.email = ? from users a where user_id=?"
     *          arr=["emailaddress@email.com", 15]
     * The above statement updates user 15's email address.
     * @param preparedSQL
     * @param parameters
     * @return
     */
    public boolean runCustomDBCommand(String preparedSQL, Object[] parameters){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(this.dbURL, this.dbUsername, this.dbPassword);
            PreparedStatement statement = conn.prepareStatement(preparedSQL);
            for(int i = 0; i < parameters.length; ++i){
                statement.setObject(i+1, parameters[i]);
            }
            statement.executeUpdate();
            conn.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
