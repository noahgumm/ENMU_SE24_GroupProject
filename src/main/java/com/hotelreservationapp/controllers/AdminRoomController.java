package com.hotelreservationapp.controllers;

import com.hotelreservationapp.models.Database.Tables.Room;
import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This servlet gets the rooms from the database and stores them in an attribute named rooms.
 * The servlet then forwards the admin to the rooms view along with the stored rooms.
 * */
@WebServlet(name = "AdminRooms", urlPatterns = "/AdminRooms")
public class AdminRoomController extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Set an attribute to store all the rooms to display them on the page
        DatabaseManager database = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system","admin","password");
        req.getSession().setAttribute("rooms", database.roomDbManager.getAllRooms());

        // Perform a redirect to the adminRoomsView.jsp
        resp.sendRedirect(req.getContextPath() + "/adminRoomsView.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Set an attribute to store all the rooms to display them on the page
        DatabaseManager database = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system","admin","password");
        req.getSession().setAttribute("rooms", database.roomDbManager.getAllRooms());

        // Perform a redirect to the adminRoomsView.jsp
        resp.sendRedirect(req.getContextPath() + "/adminRoomsView.jsp");
    }
}
