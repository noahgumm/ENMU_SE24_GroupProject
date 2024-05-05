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

import com.hotelreservationapp.models.Database.Tables.User;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserReservations", urlPatterns = {"/UserReservations", "/UserReservationsEdit", "/UserReservationsDelete"} )
public class UserReservationsController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        DatabaseManager database = new DatabaseManager();

        if(action.equals("/UserReservationsEdit")){
            Reservation resv = database.reservationDbManager.getReservation(Integer.parseInt(req.getParameter("id")));
            req.getSession().setAttribute("reservationToEdit", resv);

            RequestDispatcher dispatcher = req.getRequestDispatcher("userEditReservationsView.jsp");
            dispatcher.forward(req, resp);
        } else {
            //Set an attribute to store all the reservations for the user on the page
            User user = (User) req.getSession().getAttribute("user");
            int userID = user.getUserId();
            req.getSession().setAttribute("reservations", database.reservationDbManager.getReservationsFor(userID));
            // Perform a redirect to the myReservationsView.jsp
            resp.sendRedirect(req.getContextPath() + "/myReservationsView.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        DatabaseManager databaseManager = new DatabaseManager();

        if(action.equals("/UserReservationsEdit")){
            Reservation resv = (Reservation) req.getAttribute("reservationToEdit");

            //read new rooms and set them from string
            // Update room information
            List<Room> rooms = new ArrayList<>();
            String[] roomIdsString = req.getParameter("roomIDs").split(",");
            for (String roomIdString : roomIdsString) {
                int roomId = Integer.parseInt(roomIdString);
                Room room = databaseManager.roomDbManager.getRoom(roomId);
                if (room != null) {
                    rooms.add(room);
                }
            }

            resv.setRooms(rooms);
            resv.setCheckInDate(Date.valueOf(req.getParameter("checkIn")));
            resv.setCheckOutDate(Date.valueOf(req.getParameter("checkOut")));
            resv.setPets(Boolean.parseBoolean(req.getParameter("pets")));

            //Need to also calculate the new total
            int daysOfStay = (int)(Math.abs(resv.getCheckInDate().getTime() - resv.getCheckOutDate().getTime()) * (1000 * 60 * 60 * 24));
            double price = 0;

            for(Room room: resv.getRooms()){
                price += room.getPricePerNight() * daysOfStay;
            }

            resv.setTotalPrice(price);

            if(price <= resv.getTotalPrice()){
                //Route user back to reservations view
                req.getSession().setAttribute("resvMessage", "Reservation was updated. A refund will be given if required.");
                resp.sendRedirect("UserReservations");
            } else {
                //Route to payment page for additional payment to be made
            }

        } else if(action.equals("/UserReservationsDelete")){
            databaseManager.reservationDbManager.deleteReservation(Integer.parseInt(req.getParameter("id")));
        }
        // Perform a redirect to the myReservationsView.jsp
        resp.sendRedirect(req.getContextPath() + "/myReservationsView.jsp");
    }
}