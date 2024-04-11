package com.hotelreservationapp.controllers;

/**
 *
 * @author Zachary Marrs
 */
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelreservationapp.models.UserModel;
import com.hotelreservationapp.models.Database.UserDatabaseManager;

import java.io.IOException;
        
@WebServlet(name = "UserController", value="/HotelReservationApp/UserController")
public class UserController extends HttpServlet {
    private UserModel userModel;
    
    // Constructor
    public UserController(){ this.userModel = new UserModel(); }
    public UserController(UserModel userModel) {
        this.userModel = userModel;
    }
    
    /*
        Login User Function
        Allows users to log in to the app using servlets to communicate with JSP pages.
    
        @param request  The login request with the user data 
        @param response The response that will be sent back to the server
    */

    public void loginView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the login view
        response.getWriter().append("Served at: ").append(request.getContextPath());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/loginView.jsp");
        dispatcher.forward(request, response);
    }

    public void loginUserSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the login view
        response.getWriter().append("Served at: ").append(request.getContextPath());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/loginViewSuccess.jsp");
        dispatcher.forward(request, response);
    }

    public void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve username and password from the request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Perform authentication using the User Model
        //Keep commented out for now to ensure login for any data entered
        //boolean isAuthenticated = userModel.authenticateUser(username, password);
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();
        userModel = userDatabaseManager.read(username);
        // boolean isAuthenticated = true;
        boolean isAuthenticated = userModel.getPassword().equals(password);
        userDatabaseManager.close();
        // Forward to Success or Login View based on authentication result
        if (isAuthenticated) {
            request.setAttribute("username", username);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/loginViewSuccess.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "Login authentication failed. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/loginView.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(
      HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
        try{
            if("login".equals(request.getParameter("action"))){
                //Attempt to login if the hidden fields action parameter is equal to login
                    loginUser(request,response);
            } else if ("register".equals(request.getParameter("action"))) {
                //Attempt to login if the hidden fields action parameter is equal to register
                    loginUser(request,response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    /*
        Add User Function
        Allows users to register on the app using servlets to communicate with JSP pages.
       
        @param request  The login request with the user data 
        @param response The response that will be sent back to the server
    */
    public void addUser(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
             
        // Perform validate using the User Model
        //Keep commented to ensure user can register no matter what
        //boolean isValid = userModel.validateCredentials(username, password);
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();
        userModel = userDatabaseManager.read(username);

        boolean isValid = userModel.getUsername() == "";
      
        if (isValid) {
            // Add the user to the model
            userModel.setUsername(username);
            userModel.setPassword(password);
            userDatabaseManager.create(userModel);
            userDatabaseManager.close();
            request.setAttribute("username", username);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/registerViewSuccess.jsp");
            dispatcher.forward(request, response);
        } else {
            userDatabaseManager.close();
            request.setAttribute("error", "Invalid user credentials. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/registerView.jsp");
            dispatcher.forward(request, response);
        }
    }
}
