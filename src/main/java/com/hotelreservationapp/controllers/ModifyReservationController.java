package com.hotelreservationapp.controllers;

import com.hotelreservationapp.models.Database.Tables.Reservation;
import com.hotelreservationapp.models.Database.Tables.Room;
import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * This servlet fetches room data in the doGet() method to populate the form
 * The servlet then forwards the admin to the rooms view along with the stored rooms.
 * */
@WebServlet(name = "ModifyRoom", urlPatterns = {"/ModifyReservation", "/DeleteReservation"})
public class ModifyReservationController extends HttpServlet{

    //Stores id of room to be modified or deleted
    //Found in url of request
    private int id;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get the id of the room whose modify button was clicked
        String reservationID = req.getParameter("id");
        id = Integer.parseInt(reservationID);

        //Get room data
        DatabaseManager database = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system","admin","password");
        Reservation reservationToModify = database.reservationDbManager.getReservation(id);

        String action = req.getServletPath();

        //Use specified action to determine next steps
        if(action.equals("/DeleteReservation")){
            //Delete the room from the database
            database.reservationDbManager.deleteReservation(reservationToModify.getReservationId());

            //Notify the user
            req.setAttribute("message", "Reservation with id of " + reservationToModify.getReservationId() + " deleted.");

            RequestDispatcher dispatcher = req.getRequestDispatcher("AdminReservations");
            dispatcher.forward(req, resp);

        } else if (action.equals("/ModifyReservation")){
            //Use attributes to store room data in the form as default/placeholder
            req.setAttribute("userID", reservationToModify.getReservationId());
            req.setAttribute("rooms", reservationToModify.getRooms()); 
            req.setAttribute("checkInDate", reservationToModify.getCheckInDate());
            req.setAttribute("checkOutDate", reservationToModify.getCheckOutDate());
            req.setAttribute("totalPrice", reservationToModify.getTotalPrice());
            req.setAttribute("guests", reservationToModify.getNumGuests());
            req.setAttribute("pets", reservationToModify.getPets());
            req.setAttribute("status", reservationToModify.getReservationStatus());

            RequestDispatcher dispatcher = req.getRequestDispatcher("modifyReservationView.jsp");
            dispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        Modify(req);

        RequestDispatcher dispatcher = req.getRequestDispatcher("AdminReservations");
        dispatcher.forward(req, resp);
    }

     private void Modify(HttpServletRequest req) {
        DatabaseManager database = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system", "admin", "password");
        Reservation reservation = database.reservationDbManager.getReservation(id);

        // Update room information
        List<Room> rooms = new ArrayList<>();
        String[] roomIdsString = req.getParameter("roomIDs").split(",");
        for (String roomIdString : roomIdsString) {
            int roomId = Integer.parseInt(roomIdString);
            Room room = database.roomDbManager.getRoom(roomId);
            if (room != null) {
                rooms.add(room);
            }
        }
        reservation.setRooms(rooms); // Set the list of Room objects

        reservation.setUserId(Integer.parseInt(req.getParameter("userID")));
        reservation.setCheckInDate(Date.valueOf(req.getParameter("checkInDate")));
        reservation.setCheckOutDate(Date.valueOf(req.getParameter("checkOutDate")));
        reservation.setTotalPrice(Float.parseFloat(req.getParameter("totalPrice")));
        reservation.setNumGuests(Integer.parseInt(req.getParameter("guests")));
        reservation.setPets(Boolean.parseBoolean(req.getParameter("pets")));
        reservation.setReservationStatus(req.getParameter("status"));

        // Update database and notify user
        database.reservationDbManager.updateReservation(reservation);
        req.setAttribute("message", "Reservation with id of " + reservation.getReservationId() + " modified.");
    }

}
