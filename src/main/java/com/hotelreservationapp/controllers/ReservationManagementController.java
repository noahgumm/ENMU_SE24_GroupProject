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
import com.hotelreservationapp.models.Database.Tables.User;
import com.hotelreservationapp.models.Database.Tables.Room;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@WebServlet(name = "ReservationManagement", urlPatterns = "/ReservationManagement")
public class ReservationManagementController extends HttpServlet {	
    private static final Logger logger = Logger.getLogger(ManageRoomsController.class.getName());
	
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
         // Check if the request comes from the initial booking, room selection, or cart view.
        String roomSelectionParam = request.getParameter("roomSelectionParam");
        String cartParam = request.getParameter("cartParam");
		
        if (roomSelectionParam != null && roomSelectionParam.equals("fromRoomSelectionPage")) {
			DatabaseManager databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system", "admin", "password");
		        
			// Handle data from the room selection view
            List<Room> selectedRooms = new ArrayList<>();
			int roomCount = Integer.parseInt(request.getParameter("roomCount"));
			
			// Make sure we are getting a room count
			logger.info("Room count received: " + roomCount);			
		
		
            for (int i = 0; i < roomCount; i++) {
				String roomIdParam = request.getParameter("room" + i);	

				logger.info("Room ID Param for: " + "room" + i);					
				
				// Make sure we are getting a room added
				logger.info("Room ID Param object: " + roomIdParam);	
				if (roomIdParam != null) {
					
					// Make sure we are getting a room added
					logger.info("Room ID Param found.");	
					
					String roomNumber = roomIdParam;
					
					
					// Make sure we are getting a room added
					logger.info("Room Number matched: " + roomNumber);	
					
					
					// Retrieve the Room object based on the room ID
					Room room = databaseManager.roomDbManager.getRoomByRoomNumber(roomNumber);
					
					
					// Make sure we are getting a room added
					logger.info("Room Found in DB: " + room);	
					
					if (room != null) {						
			
						// Make sure we are getting a room added
						logger.info("Room added to list: " + room);	
						selectedRooms.add(room);
					}
				} else {
				}
				
			}
			
			// Make sure we are still gettings the set reservationId attribute
			logger.info("reservationId request parameter set to: " + request.getParameter("reservationId"));				
			
			int reservationId = Integer.parseInt(request.getParameter("reservationId"));
			
			Reservation reservation = null;
						
			// Fetch the reservation details for the given ID from the database
			reservation = databaseManager.reservationDbManager.getReservation(reservationId);
        
			
			// Make sure we are getting a reservation
			logger.info("Reservation Retrieved: " + reservation);			
		
			reservation.setRooms(selectedRooms); 
			
			// Make sure we are getting rooms set to the reservation
			logger.info("Reservation rooms set to: " + selectedRooms);			
			
			// Save the updated reservation to the database
			databaseManager.reservationDbManager.updateReservation(reservation);
						
			// Redirect to Cart
            response.sendRedirect(request.getContextPath() + "/Cart?reservationId=" + reservationId);
			
        } else if (cartParam != null && cartParam.equals("fromCart")) {
			
		
		} else {
            // Handle data from the booking view
            int guests = Integer.parseInt(request.getParameter("guests"));
            boolean pets = Boolean.parseBoolean(request.getParameter("pets"));
            String dateRange = request.getParameter("dates");

            // Parse date range string to extract start and end dates
            String[] dates = dateRange.split(" - ");
            Date startDate, endDate;
            try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                startDate = dateFormat.parse(dates[0]);
                endDate = dateFormat.parse(dates[1]);
            } catch (ParseException e) {
                e.printStackTrace();
                // Handle parsing error
                return;
            }

			// Retrieve the User object from the session
			User user = (User) request.getSession().getAttribute("user");
			
			if (user == null) {
				//Send to Login if not logged in
				response.sendRedirect(request.getContextPath());
				return;
			}
			
			int userId = user.getUserId();
			
            // Create a new reservation with the provided details for the user
            Reservation reservation = new Reservation();
			
            reservation.setUserId(userId);
            reservation.setNumGuests(guests);
            reservation.setPets(pets);
			
			// Convert Timestamp to java.sql.Date for check-in and check-out dates
			java.sql.Date checkInDate = new java.sql.Date(startDate.getTime());
			java.sql.Date checkOutDate = new java.sql.Date(endDate.getTime());
			
			reservation.setCheckInDate(checkInDate);
			reservation.setCheckOutDate(checkOutDate);

            // Save the reservation to the database using ReservationDbManager
			DatabaseManager databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system", "admin", "password");
            Reservation createdReservation = databaseManager.reservationDbManager.createReservation(reservation);

			// Fetch the ID of the newly created reservation
			int reservationId = createdReservation.getReservationId();
			logger.info("Reservation ID before setting as attribute: " + reservationId);

			// Set the reservation ID as a request attribute for later use
			request.setAttribute("reservationId", reservationId);
				
			// Log the request attributes to verify the attribute is set
			logger.info("reservationId request attibute set to: " + request.getAttribute("reservationId"));
				
            // Redirect to ManageRoomsController passing the date range as parameter
            response.sendRedirect(request.getContextPath() + "/ManageRooms?startDate=" + startDate.getTime() + "&endDate=" + endDate.getTime() + "&reservationId=" + reservationId);
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
