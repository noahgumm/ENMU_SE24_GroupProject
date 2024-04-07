package com.hotelreservationapp.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelreservationapp.models.UserModel;
import com.hotelreservationapp.models.Database.UserDatabaseManager;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminLogin", urlPatterns = "/AdminLogin")
public class AdminLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // RequestDispatcher requestDispatcher = req.getRequestDispatcher("loginView.jsp");
        // requestDispatcher.forward(req, resp);
        resp.setContentType("text/html");

        // Write the response message, in an HTML page
        try (PrintWriter out = resp.getWriter()) {
            // Writing HTML content
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("<title>Admin Login</title></head>");
            out.println("<body>");
            out.println("<h1>Admin Login</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
