package com.hotelreservationapp.controllers;

import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.Date;

@WebServlet(name = "AdminReservations", urlPatterns = "/AdminReservations" )
public class AdminReservationsController extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Set an attribute to store all the rooms to display them on the page
        DatabaseManager database = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system","admin","password");
        req.getSession().setAttribute("reservations", database.reservationDbManager.getAllReservations());

        // Perform a redirect to the adminRoomsView.jsp
        resp.sendRedirect(req.getContextPath() + "/adminReservationsView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchForReservation(req);

        // Perform a redirect to the adminRoomsView.jsp
        resp.sendRedirect(req.getContextPath() + "/adminReservationsView.jsp");
    }

    private void SearchForReservation(HttpServletRequest req){
        //Set an attribute to store all the rooms to display them on the page
        DatabaseManager database = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system","admin","password");

        String selectedSearchOption = req.getParameter("searchType");
        System.out.println(selectedSearchOption);
        if(selectedSearchOption != null){
            //Change what parameter is used to get rooms depending on selected search option
            switch (selectedSearchOption){
                case "customerID":
                    int userID = Integer.parseInt(req.getParameter("customerId"));
                    req.getSession().setAttribute("reservations", database.reservationDbManager.getReservationsFor(userID));

                    break;
                case "roomID":
                    int roomID = Integer.parseInt(req.getParameter("roomId"));
                    req.getSession().setAttribute("reservations", database.reservationDbManager.getReservationByRoom(roomID));

                    break;
                case "dates":
                    Date start = Date.valueOf(req.getParameter("startDate"));
                    Date end = Date.valueOf(req.getParameter("endDate"));
                    req.getSession().setAttribute("reservations", database.reservationDbManager.getAllReservationsBetween(start.toString(), end.toString()));

                    break;
                case "status":
                    String status = req.getParameter("status");
                    req.getSession().setAttribute("reservations", database.reservationDbManager.getReservationsByStatus(status));

                    break;
                case "all":
                default:
                    //Get all reservations by default
                    req.getSession().setAttribute("reservations", database.reservationDbManager.getAllReservations());
                    break;
            }
        }
        else{
            req.getSession().setAttribute("reservations", database.reservationDbManager.getAllReservations());
        }
    }
}
