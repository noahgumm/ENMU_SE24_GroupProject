package com.hotelreservationapp.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelreservationapp.models.Database.Tables.Admin;
import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;

import java.io.IOException;
import java.util.Objects;

//Handles logging in admin accounts
@WebServlet(name = "AdminLogin", urlPatterns = "/AdminLogin")
public class AdminLoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("adminLoginView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loginAdmin(req, resp);
    }

    public void loginAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = null;

        // Retrieve username and password from the request parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Perform authentication using the User Model
        //Keep commented out for now to ensure login for any data entered
        //boolean isAuthenticated = userModel.authenticateUser(username, password);
        DatabaseManager databaseManager = new DatabaseManager("jdbc:mysql://hotel-reservation-system.cbzvnoedvh5z.us-east-1.rds.amazonaws.com:3306/hotel_reservation_system", "root", "3NMU_S324_Gr0upPr0j3ct");

        for (Admin a : databaseManager.adminDbManager.getAdminAllUsers()) {
            System.out.println(a.getEmail());
            if (Objects.equals(a.getEmail(), email)) {
                admin = a;
            }
        }
        int isAuthenticated = 1;
        if(admin != null){
            // boolean isAuthenticated = 0 if passwords are the same;
            isAuthenticated = password.compareTo(admin.getPassword());
        }

        // Forward to Success or Login View based on authentication result
        if (isAuthenticated == 0) {
            request.getSession().setAttribute("username", admin.getUsername());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminHome");
            dispatcher.forward(request, response);
        } else {
            request.getSession().setAttribute("error", "Error finding account.");

            response.sendRedirect("adminLoginView.jsp");
        }
    }
}
