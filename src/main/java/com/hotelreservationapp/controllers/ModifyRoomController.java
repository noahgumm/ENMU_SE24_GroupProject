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
 * This servlet fetches room data in the doGet() method to populate the form
 * The servlet then forwards the admin to the rooms view along with the stored rooms.
 * */
@WebServlet(name = "ModifyRoom", urlPatterns = {"/ModifyRoom", "/DeleteRoom", "/AddRoom"})
public class ModifyRoomController extends HttpServlet{

    //Stores id of room to be modified or deleted
    //Found in url of request
    private int id;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get the id of the room whose modify button was clicked
        String roomId = req.getParameter("id");
        id = Integer.parseInt(roomId);

        //Get room data
        DatabaseManager database = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system","admin","password");
        Room roomToModify = database.roomDbManager.getRoom(id);

        String action = req.getServletPath();

        //Use specified action to determine next steps
        if(action.equals("/DeleteRoom")){
            //Delete the room from the database
            database.roomDbManager.deleteRoom(roomToModify.getRoomId());

            //Notify the user
            req.setAttribute("message", "Room with id of " + roomToModify.getRoomId() + " deleted.");

            RequestDispatcher dispatcher = req.getRequestDispatcher("AdminRooms");
            dispatcher.forward(req, resp);

        } else if (action.equals("/ModifyRoom")){
            //Use attributes to store room data in the form as default/placeholder
            req.setAttribute("roomNumber", roomToModify.getRoomNumber());
            req.setAttribute("type", roomToModify.getRoomType());
            req.setAttribute("floor", roomToModify.getFloorNumber());
            req.setAttribute("price", roomToModify.getPricePerNight());
            req.setAttribute("description", roomToModify.getRoomDescription());
            req.setAttribute("beds", roomToModify.getNumberOfBeds());

            RequestDispatcher dispatcher = req.getRequestDispatcher("modifyRoomView.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        if(action.equals("/ModifyRoom")){
            Modify(req);

            RequestDispatcher dispatcher = req.getRequestDispatcher("AdminRooms");
            dispatcher.forward(req, resp);
        } else if (action.equals("/AddRoom")){
            Add(req);
            RequestDispatcher dispatcher = req.getRequestDispatcher("AdminRooms");
            dispatcher.forward(req, resp);
        }

    }

    private void Modify(HttpServletRequest req){
        DatabaseManager database = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system","admin","password");
        Room roomToModify = database.roomDbManager.getRoom(id);

        //Update room information
        roomToModify.setRoomNumber(req.getParameter("roomNumber"));
        roomToModify.setRoomType(req.getParameter("roomType"));
        roomToModify.setFloorNumber(Integer.parseInt(req.getParameter("floorNumber")));
        roomToModify.setPricePerNight(Float.parseFloat(req.getParameter("price")));
        roomToModify.setRoomDescription(req.getParameter("description"));
        roomToModify.setNumberOfBeds(Integer.parseInt(req.getParameter("beds")));

        //Update database and notify user
        database.roomDbManager.updateRoom(roomToModify);
        req.setAttribute("message", "Room with id of " + roomToModify.getRoomId() + " modified.");
    }

    private void Add(HttpServletRequest req){
        DatabaseManager database = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system","admin","password");
        Room roomToModify = new Room();

        //Store room information
        roomToModify.setRoomNumber(req.getParameter("roomNumber"));
        roomToModify.setRoomType(req.getParameter("roomType"));
        roomToModify.setFloorNumber(Integer.parseInt(req.getParameter("floorNumber")));
        roomToModify.setPricePerNight(Float.parseFloat(req.getParameter("price")));
        roomToModify.setRoomDescription(req.getParameter("description"));
        roomToModify.setNumberOfBeds(Integer.parseInt(req.getParameter("beds")));

        //Add room to database
        database.roomDbManager.createRoom(roomToModify.getRoomNumber(), roomToModify.getRoomType(), roomToModify.getFloorNumber(), roomToModify.getPricePerNight(), roomToModify.getRoomDescription(), roomToModify.getNumberOfBeds());

        //Notify user that room addition was successful
        req.setAttribute("message", "Room #" + roomToModify.getRoomNumber() + " added.");
    }
}
