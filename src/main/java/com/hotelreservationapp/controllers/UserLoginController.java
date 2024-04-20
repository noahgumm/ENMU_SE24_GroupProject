package com.hotelreservationapp.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.hotelreservationapp.models.Database.Tables.User;
import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login", urlPatterns = "/Login")
public class UserLoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("loginView.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseManager databaseManager = new DatabaseManager();
        try{
            if("login".equals(request.getParameter("action"))){
                //Attempt to log in if the hidden fields action parameter is equal to log in
                User user = databaseManager.userDbManager.getUser(request.getParameter("email"));
                if(user != null && user.getPassword().equals(request.getParameter("password"))){
                    request.getSession().setAttribute("username", user.getUsername());
                    response.sendRedirect("Home");
                } else {
                    //this is keeping the error message throughout the entire session. I do not want this.
                    // request.getSession().setAttribute("error", "Error finding account.");
                    request.setAttribute("error", "Error finding account.");
                    
                    // RequestDispatcher dispatcher = request.getRequestDispatcher("Login");
                    // dispatcher.forward(request, response);
                    doGet(request, response);
                }
            } else if ("register".equals(request.getParameter("action"))) {
                //Attempt to add user if the hidden fields action parameter is equal to register
                databaseManager.userDbManager.createUser(request.getParameter("username"), request.getParameter("email"), request.getParameter("email"), "" );
                response.sendRedirect("Login");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
