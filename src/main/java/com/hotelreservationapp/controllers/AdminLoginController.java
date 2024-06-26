package com.hotelreservationapp.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelreservationapp.models.Database.Tables.Admin;
import com.hotelreservationapp.models.Database.Tables.User;
import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "AdminLogin", urlPatterns = "/AdminLogin")
public class AdminLoginController extends BaseController {

    public Admin admin;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("adminLoginView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loginAdmin(req, resp);
    }

    public void loginAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve username and password from the request parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Perform authentication using the User Model
        //Keep commented out for now to ensure login for any data entered
        //boolean isAuthenticated = userModel.authenticateUser(username, password);
        DatabaseManager databaseManager = new DatabaseManager();
        admin = null;
        for (Admin a : databaseManager.adminDbManager.getAdminAllUsers()) {
            //System.out.println(a.getEmail());
            if (Objects.equals(a.getEmail(), email)) {
                admin = a;
            }
        }

        // boolean isAuthenticated = true;
        boolean isAuthenticated = admin != null && password.compareTo(admin.getPassword()) == 0;
        // Forward to Success or Login View based on authentication result
        if (isAuthenticated) {
            request.getSession().setAttribute("username", admin.getUsername());
            setSessionUser(request, new User(admin.getAdminId(), admin.getUsername(), admin.getPassword(), admin.getEmail(), "", admin.getCreatedAt()));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminHome");
            dispatcher.forward(request, response);
        } else {
            request.getSession().setAttribute("error", "Error finding account.");

            response.sendRedirect("adminLoginView.jsp");
        }
    }
}
