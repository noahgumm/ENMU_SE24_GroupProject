package com.hotelreservationapp.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.hotelreservationapp.models.Database.Tables.User;
import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;

@WebServlet(name = "UserAccount", urlPatterns = "/UserAccount")
public class UserAccountController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectString = sendToLoginPageIfNotLoggedIn(req, resp);
        if(redirectString != null){
            resp.sendRedirect(redirectString);
            return;
        }
        User user = getSessionUserOrSendToLoginPage(req, resp);
        req.setAttribute("user", user);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("accountView.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get form data
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String password = req.getParameter("password");
        int userId = Integer.parseInt(req.getParameter("userId"));

        DatabaseManager db = new DatabaseManager();
        User updated = db.userDbManager.updateUser(userId, username, password, email, phoneNumber);
        setSessionUser(req, updated);
        resp.sendRedirect("Home");
    }
}
