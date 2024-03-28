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

import java.io.IOException;
        
@WebServlet(name = "UserController", value = "/user")
public class UserController extends HttpServlet {
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

    public void loginView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the login view
        response.getWriter().append("Served at: ").append(request.getContextPath());
        RequestDispatcher dispatcher = request.getRequestDispatcher("loginView.jsp");
        dispatcher.forward(request, response);
    }

    public void loginUserSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the login view
        response.getWriter().append("Served at: ").append(request.getContextPath());
        RequestDispatcher dispatcher = request.getRequestDispatcher("loginViewSuccess.jsp");
        dispatcher.forward(request, response);
    }

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

    public void doGet(
      HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

        loginView(request, response);
    }

    @Override
    protected void doPost(
      HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

        loginUserSuccess(request, response);
    }
    
    /*
        Add User Function
        Allows users to register on the app usings servlets to communicate with JSP pages.
       
        @param request  The login request with the user data 
        @param response The response that will be send back to the server
    */
    public void addUser(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
             
        // Perform validate using the User Model
        boolean isValid = userModel.validateCredentials(username, password);
      
        if (isValid) {
            // Add the user to the model
            userModel.addUser(username, password);
            request.setAttribute("username", username);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/register-success.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "Invalid user credentials. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/register.jsp");
            dispatcher.forward(request, response);
        }
    }
}
