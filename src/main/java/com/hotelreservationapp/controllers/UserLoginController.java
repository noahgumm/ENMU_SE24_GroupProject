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
        DatabaseManager databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system","admin","password");
        try{
            if("login".equals(request.getParameter("action"))){
                //Attempt to log in if the hidden fields action parameter is equal to log in
                String email = request.getParameter("email");
                User user = databaseManager.userDbManager.getUser(email);
                if(user != null && user.getPassword().equals(request.getParameter("password"))){
                    // request.getSession().setAttribute("username", user.getUsername());
                    request.getSession().setAttribute("user", user);
                    request.getSession().setMaxInactiveInterval(10*60);
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
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String username = request.getParameter("username");
                String phone = request.getParameter("phone");
                databaseManager.userDbManager.createUser(username,password,email,phone);
                response.sendRedirect("loginView.jsp");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
