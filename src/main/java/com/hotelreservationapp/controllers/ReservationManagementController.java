package com.hotelreservationapp.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;
import com.hotelreservationapp.models.Database.Tables.Reservation;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ReservationManagement", urlPatterns = "/ReservationManagement")
public class ReservationManagementController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        // RequestDispatcher requestDispatcher = req.getRequestDispatcher("home.jsp");
        // requestDispatcher.forward(req, resp);
        
        resp.setContentType("text/html");

        // Write the response message, in an HTML page
        try (PrintWriter out = resp.getWriter()) {
            // Writing HTML content
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("<title>ReservationManagement</title></head>");
            out.println("<body>");
            out.println("<h1>ReservationManagement</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         // Check if the request comes from the room selection page
        String roomSelectionParam = request.getParameter("roomSelectionParam");
        if (roomSelectionParam != null && roomSelectionParam.equals("fromRoomSelectionPage")) {
            // Handle data from the room selection page
            // Example: int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
        } else {
            // Handle data from the booking view
            int guests = Integer.parseInt(request.getParameter("guests"));
            boolean pets = Boolean.parseBoolean(request.getParameter("pets"));
            String dateRange = request.getParameter("dates");

            // Parse date range string to extract start and end dates
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy - MM/dd/yyyy");
            String[] dates = dateRange.split(" - ");
            Date startDate, endDate;
            try {
                startDate = dateFormat.parse(dates[0]);
                endDate = dateFormat.parse(dates[1]);
            } catch (ParseException e) {
                e.printStackTrace();
                // Handle parsing error
                return;
            }

            // Create a new reservation with the provided details
            Reservation reservation = new Reservation();
            reservation.setNumGuests(guests);
            reservation.setPets(pets);
			
			// Convert Timestamp to java.sql.Date for check-in and check-out dates
			java.sql.Date checkInDate = new java.sql.Date(startDate.getTime());
			java.sql.Date checkOutDate = new java.sql.Date(endDate.getTime());
			
			reservation.setCheckInDate(checkInDate);
			reservation.setCheckOutDate(checkOutDate);

            // Save the reservation to the database using ReservationDbManager
			DatabaseManager databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system", "admin", "password");
            databaseManager.reservationDbManager.createReservation(reservation);

            // Redirect to ManageRoomsController passing the date range as parameter
            response.sendRedirect(request.getContextPath() + "/ManageRooms?startDate=" + startDate.getTime() + "&endDate=" + endDate.getTime());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doDelete(req, resp);
    }

}
