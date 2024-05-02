package com.hotelreservationapp.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelreservationapp.models.Database.Tables.User;
import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;

import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author Zachary Marrs
 */
@WebServlet(name = "UserController", value="/UserController")
public class UserController extends HttpServlet {
    private User userModel;

    public UserController(){
        userModel = new User();
    }
    /*
        Login User Function
        Allows users to log in to the app using servlets to communicate with JSP pages.
    
        @param request  The login request with the user data 
        @param response The response that will be sent back to the server
    */

    public void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve username and password from the request parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Perform authentication using the User Model
        //Keep commented out for now to ensure login for any data entered
        //boolean isAuthenticated = userModel.authenticateUser(username, password);
        DatabaseManager databaseManager = new DatabaseManager();
        userModel = databaseManager.userDbManager.getUser(email);

        // boolean isAuthenticated = true;
        boolean isAuthenticated = userModel == null || Objects.equals(userModel.getPassword(), password);
        // Forward to Success or Login View based on authentication result
        if (isAuthenticated) {
            request.getSession().setAttribute("username", userModel.getUsername());
            response.sendRedirect("Home");
        } else {
            request.getSession().setAttribute("error", "Error finding account.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(
      HttpServletRequest request, HttpServletResponse response) 
      throws ServletException {
        try{
            if("login".equals(request.getParameter("action"))){
                //Attempt to log in if the hidden fields action parameter is equal to log in
                    loginUser(request,response);
            } else if ("register".equals(request.getParameter("action"))) {
                //Attempt to add user if the hidden fields action parameter is equal to register
                    addUser(request,response);
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
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone");
             
        // Perform validate using the User Model
        //Keep commented to ensure user can register no matter what
        //boolean isValid = userModel.validateCredentials(username, password);
        DatabaseManager databaseManager = new DatabaseManager();
        userModel = databaseManager.userDbManager.getUser(email);
        boolean isValid = userModel == null || userModel.getUsername() == null;

        if (isValid) {
            userModel = new User();
            // Add the user to the model
            userModel.setUsername(username);
            userModel.setPassword(password);
            userModel.setEmail(email);
            userModel.setPhoneNumber(phoneNumber);

            databaseManager.userDbManager.createUser(userModel);
            response.sendRedirect("loginView.jsp");
        } else {
            request.setAttribute("error", "Invalid user credentials. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/registerView.jsp");
            dispatcher.forward(request, response);
        }
    }
}
