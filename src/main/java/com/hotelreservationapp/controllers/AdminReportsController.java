package com.hotelreservationapp.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;

@WebServlet(name = "AdminReports", urlPatterns = "/AdminReports")
public class AdminReportsController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseManager database = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system","root","password");
        req.getSession().setAttribute("reservations", database.getAdminReservationReportForAllReservations());

        // Perform a redirect to the adminRoomsView.jsp
        resp.sendRedirect(req.getContextPath() + "/adminReservationReportsView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }
}
