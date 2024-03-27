package com.hotelreservationapp.controllers;

/**
 *
 * @author Zachary Marrs
 */
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
        
public class UserController {
    private UserModel userModel;
    
    // Constructor
    public UserController(UserModel userModel) {
        this.userModel = userModel;
    }
    
    /*
        Login User Function
        Allows users to log in to the app usings servlets to communicate with JSP pages.
    
        @param request  The login request with the user data 
        @param response The response that will be send back to the server
    */
    public void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve username and password from the request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Perform authentication using the User Model
        boolean isAuthenticated = userModel.authenticateUser(username, password);

        // Forward to Success or Login View based on authentication result
        if (isAuthenticated) {
            request.setAttribute("username", username);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/success.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "Login authentication failed. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/loginView.jsp");
            dispatcher.forward(request, response);
        }
    }
}
