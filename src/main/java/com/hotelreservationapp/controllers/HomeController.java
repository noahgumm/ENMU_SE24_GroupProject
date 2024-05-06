package com.hotelreservationapp.controllers;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelreservationapp.models.Settings.SettingsReader;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Home", urlPatterns = "/Home")
public class HomeController extends BaseController {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendToLoginPageIfNotLoggedIn(req, resp);
        resp.sendRedirect("mainView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("loginView.jsp");
        dispatcher.forward(req, resp);
    }
}
