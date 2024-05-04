package com.hotelreservationapp.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;

@WebServlet(name = "RoomReservationManagement", urlPatterns = "/RoomReservationManagement")
public class RoomReservationManagementController extends BaseController {
    

    // @Override
    // protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //     // TODO Auto-generated method stub
    //     String reservationID = req.getParameter("reservationID");
    //     String[] roomIDs = req.getParameterValues("selectedRoom");

    //     DatabaseManager databaseManager = new DatabaseManager();
    //     if(databaseManager.reservationDbManager.addReservationRooms(Integer.parseInt(reservationID), roomIDs)) {
    //         databaseManager.reservationDbManager.updateTotalReservationCost(Integer.parseInt(reservationID));
    //         resp.sendRedirect(req.getContextPath() + "/Cart");
    //     } else {
    //         req.setAttribute("message", "Error adding rooms to reservation.");
    //     }
    // }
}
