package com.hotelreservationapp.controllers;


import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "AdminHome", urlPatterns = {"/AdminHome", "/AddAdmin"})
public class AdminHomeController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectString = sendToLoginPageIfNotLoggedIn(req, resp);
        if(redirectString != null){
            resp.sendRedirect(redirectString);
            return;
        }
        String action = req.getServletPath();

        RequestDispatcher dispatcher = req.getRequestDispatcher("adminMainView.jsp");

        if(action.equals("/AdminHome")){
            dispatcher = req.getRequestDispatcher("adminMainView.jsp");
        } else if(action.equals("/AddAdmin")){
            dispatcher = req.getRequestDispatcher("addAdminView.jsp");

        }

        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        //Add admin to the database
        if(Objects.equals(action, "AddAdmin")){
            DatabaseManager database = new DatabaseManager();

            String username = req.getParameter("username");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            database.adminDbManager.createAdminUser(username, password, email);
            req.setAttribute("message", "Admin account created");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("adminMainView.jsp");
        dispatcher.forward(req, resp);

    }
}