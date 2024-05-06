package com.hotelreservationapp.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;

@WebServlet(name = "AdminReservationReport", urlPatterns = "/AdminReservationReport")
public class AdminReservationReportController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendToLoginPageIfNotLoggedIn(req, resp);

        DatabaseManager databaseManager = new DatabaseManager();
        // req.getSession().setAttribute("reservations", databaseManager.getAdminReservationReportForAllReservations());

        // Perform a redirect to the adminRoomsView.jsp
        resp.sendRedirect(req.getContextPath() + "/adminReservationReportsView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }
}
