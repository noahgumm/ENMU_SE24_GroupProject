package com.hotelreservationapp.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;
import com.hotelreservationapp.models.Database.Tables.Room;

import java.io.IOException;
import java.util.List;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;

@WebServlet(name = "ManageRooms", urlPatterns = "/ManageRooms")
public class ManageRoomsController extends HttpServlet{
    private static final Logger logger = Logger.getLogger(ManageRoomsController.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// Log a debug message
		logger.info("Entering doGet method for Rooms View");
		
		// Initialize the Database Manager
		DatabaseManager databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system", "admin", "password");

		try {
			List<Room> rooms;
			List<Room> bookedRooms;
			int highestFloorNumber;
			int daysStaying = 0;

			// Check if request parameters are null
			if (request.getParameter("startDate") == null || request.getParameter("endDate") == null) {
				// Set the start date as today's date
				LocalDate today = LocalDate.now();
				Date startDate = Date.valueOf(today);
				
				// Set the end date as tomorrow's date
				LocalDate tomorrow = today.plusDays(1);
				Date endDate = Date.valueOf(tomorrow);

				// Calculate the number of days for the stay
				daysStaying = (int) (((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24)) + 1);

				// Fetch rooms that are booked for the specified date range
				rooms = databaseManager.roomDbManager.getAllRooms();
				bookedRooms = databaseManager.roomDbManager.getBookedRooms(startDate, endDate);

				// Fetch overlapping reservations on the start date
				List<Room> overlappingRoomsStart = databaseManager.roomDbManager.getBookedRooms(startDate, startDate);

				// Merge overlapping reservations into the bookedRooms list
				bookedRooms.addAll(overlappingRoomsStart);

				highestFloorNumber = databaseManager.roomDbManager.getHighestFloorNumber();
				
				logger.info("Giving default parameters: ");            
				logger.info("Rooms: "  + rooms);        
				logger.info("Booked Rooms: "  + bookedRooms);    
				logger.info("Highest Floor: "  + highestFloorNumber);
				
			} else {
				// Get the Reservation dates inputted by the user from the Form Request
				long startDateMillis = Long.parseLong(request.getParameter("startDate"));
				long endDateMillis = Long.parseLong(request.getParameter("endDate"));
				Date startDate = new Date(startDateMillis);
				Date endDate = new Date(endDateMillis);
	 
				// Calculate the number of days for the stay
				daysStaying = (int) (((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24)) + 1);

				// Fetch rooms that are Booked for the specified date range
				rooms = databaseManager.roomDbManager.getAllRooms();
				bookedRooms = databaseManager.roomDbManager.getBookedRooms(startDate, endDate);

				// Fetch overlapping reservations on the start date
				List<Room> overlappingRoomsStart = databaseManager.roomDbManager.getBookedRooms(startDate, startDate);

				// Merge overlapping reservations into the bookedRooms list
				bookedRooms.addAll(overlappingRoomsStart);

				highestFloorNumber = databaseManager.roomDbManager.getHighestFloorNumber();
				
				logger.info("Received parameters from form: ");       
				logger.info("Rooms: "  + rooms);        
				logger.info("Booked Rooms: "  + bookedRooms);            
				logger.info("Highest Floor: "  + highestFloorNumber);
			}

			// Pass the rooms data to the Room View
			request.setAttribute("rooms", rooms);
			
			// Pass the list of booked rooms to the Room View
			request.setAttribute("bookedRooms", bookedRooms);
			
			// Pass the highest floor number to the Room View
			request.setAttribute("highestFloorNumber", highestFloorNumber);
			
			// Pass the number of days staying to the Room View
			request.setAttribute("daysStaying", daysStaying);

			logger.info("Days Staying: "  + daysStaying);
			
			// Forward to the Room View for rendering
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("roomView.jsp");
			requestDispatcher.forward(request, resp);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		// Log a debug message
		logger.info("Exiting doGet method for Rooms View");
	}
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
       String action = request.getParameter("action");
       DatabaseManager databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system", "admin", "password");
	   
       try {
			//	Create Room
            if ("createRoom".equals(action)) {
                databaseManager.roomDbManager.createRoom(
                    request.getParameter("roomNumber"),
                    request.getParameter("roomType"),
                    Integer.parseInt(request.getParameter("floorNumber")),
                    Double.parseDouble(request.getParameter("pricePerNight")),
                    request.getParameter("roomDescription"),
                    Integer.parseInt(request.getParameter("numberOfBeds"))
                );
			// Edit Room
            } else if ("editRoom".equals(action)) {
                // Get the room ID from the request
                int roomId = Integer.parseInt(request.getParameter("roomId"));
				
				// Check if the room exists
				Room existingRoom = databaseManager.roomDbManager.getRoom(roomId);
			
				// If it does, get the updated room details from the request
				if (existingRoom != null) {
					String roomNumber = request.getParameter("roomNumber");
					String roomType = request.getParameter("roomType");
					int floorNumber = Integer.parseInt(request.getParameter("floorNumber"));
					double pricePerNight = Double.parseDouble(request.getParameter("pricePerNight"));
					String roomDescription = request.getParameter("roomDescription");
					int numberOfBeds = Integer.parseInt(request.getParameter("numberOfBeds"));
					
					// Retrieve the existing timestamp for the room from the database
					Timestamp createdAt = databaseManager.roomDbManager.getRoomTimestamp(roomId);

					// Create a Room object with the updated details
					Room room = new Room(roomId, roomNumber, roomType, floorNumber, pricePerNight, roomDescription, numberOfBeds, createdAt);

					// Update the room in the database
					boolean updated = databaseManager.roomDbManager.updateRoom(room);
				}
            }
        } catch (Exception e) {
            throw new ServletException(e);
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