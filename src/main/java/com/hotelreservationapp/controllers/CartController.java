package com.hotelreservationapp.controllers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import com.hotelreservationapp.models.Database.Prepared.CartInformation;
import com.hotelreservationapp.models.Database.Tables.User;
import com.hotelreservationapp.models.Database.Tables.UserPaymentMethod;
import com.hotelreservationapp.models.DatabaseLogic.DatabaseManager;
import com.hotelreservationapp.models.Database.Tables.Reservation;
import com.hotelreservationapp.models.Database.Tables.Room;

import java.util.logging.Logger;



@WebServlet(name = "Cart", urlPatterns = "/Cart")
public class CartController extends BaseController {
    private static final Logger logger = Logger.getLogger(ManageRoomsController.class.getName());
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DatabaseManager db = new DatabaseManager();
		
		// Check if the user is logged in
		User user = getSessionUser(req);
	
		// Retrieve the reservation ID from the request parameter
		int reservationId = Integer.parseInt(req.getParameter("reservationId"));

		// Get the reservation details from the database using the reservation ID
		Reservation reservation = db.reservationDbManager.getReservation(reservationId);

		// Get the rooms associated with the reservation
		List<Room> rooms = db.reservationDbManager.getRoomsForReservation(reservationId);

		// Set the cart attribute in the request
		req.setAttribute("reservation", reservation);
		
		// Set the cart attribute in the request
		req.setAttribute("rooms", rooms);
		
		
		// Calculate the total price for all rooms
		double totalRoomPrice = 0.0;
		for (Room room : rooms) {
			// Calculate the number of days between check-in and check-out dates
			long numberOfDays = (((reservation.getCheckOutDate().getTime() - reservation.getCheckInDate().getTime()) / (1000 * 60 * 60 * 24)) + 1) ;


			logger.info("Price calculation (number of days): " + numberOfDays);	
			
			// Calculate total price including room rates and pet fee (if applicable)
			double roomRate = room.getPricePerNight();
			
			
			logger.info("Price calculation (room rate): " + roomRate);	
			
			double roomPrice = roomRate * numberOfDays;
			
			
			logger.info("Room price (days * rate): " + roomPrice);	
			
			
			totalRoomPrice += roomPrice;
			
			logger.info("Total price after room added: " + totalRoomPrice);	
		}

		// Add pet fee if applicable
		if (reservation.getPets()) {
			totalRoomPrice += 50.00;
			logger.info("Total price after pet fee added): " + totalRoomPrice);	
		}
				
		req.setAttribute("totalRoomPrice", totalRoomPrice);
	
		// Set the total price for the reservation
		reservation.setTotalPrice(totalRoomPrice);
		
		// Save the updated reservation to the database
		db.reservationDbManager.updateReservation(reservation);
					
		// Make sure we are still gettings the set reservationId attribute
		logger.info("totalRoomPrice request attritubte set to: " + totalRoomPrice);	
		logger.info("rooms request attritubte set to: " + rooms);	
		logger.info("reservation request attritubte set to: " + reservation);		
	
		// Forward the request to the cart view
		req.getRequestDispatcher("cartView.jsp").forward(req, resp);
	}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cardType = req.getParameter("cardType");
        String cardNumber = req.getParameter("cardNumber");
        String expirationDate = req.getParameter("expirationDate");
        String cvv = req.getParameter("cvv");
        String cardHolderName = req.getParameter("cardHolderName");
        int reservationId = Integer.parseInt(req.getParameter("reservationId"));

		// Check that data was received from form
		logger.info("Card Type Received: " + cardType);	
		logger.info("Card Number Received: " + cardNumber);	
		logger.info("Card Expr Date Received: " + expirationDate);	
		logger.info("Card CVV Received: " + cvv);	
		logger.info("Card Holder Name Received: " + cardHolderName);	
		logger.info("Reservation ID Received: " + reservationId);	

        //get the user
        User user = getSessionUser(req);
		
        //get the cart for the logged in user
        DatabaseManager db = new DatabaseManager();
		
		//Save the new payment method to the database 
        UserPaymentMethod paymentMethod = new UserPaymentMethod
        (
            0, 
            cardNumber, 
            cardHolderName, 
            cardType, 
            java.sql.Date.valueOf(expirationDate), 
            cvv,
            user.getUserId(), 
            new java.sql.Timestamp(java.sql.Date.valueOf(expirationDate).getTime()), 
            false
        );
		
		
		logger.info("Payment method created: " + paymentMethod);		
		
        double amountPaid = db.processReservationAsComplete(user, paymentMethod, reservationId);
		
		// Get the reservation from the database using the reservation ID
		Reservation reservation = db.reservationDbManager.getReservation(reservationId);
		
		// Get the rooms associated with the reservation
		List<Room> rooms = db.reservationDbManager.getRoomsForReservation(reservationId);

		// Add booked dates for each room
		for (Room room : rooms) {
			db.roomDbManager.bookRoom(user.getUserId(), room.getRoomId(), reservation.getCheckInDate(), reservation.getCheckOutDate());
		}
	
		String userName = user.getUsername();
		String emailAddress = user.getEmail();
	
		logger.info("Sending Email...");		
		

        // Send email
        sendEmail(req, userName, emailAddress, reservation, rooms, amountPaid);


		logger.info("Email Sent to " + emailAddress + "!");		
		
				
        // redirect to the Receipt page
        req.setAttribute("amtPaid", amountPaid);
        String cardNumberPadded = "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
        req.setAttribute("card", cardNumberPadded);
        req.setAttribute("cardType", cardType);
        req.setAttribute("reservationId",  String.valueOf(reservationId));
        req.setAttribute("email", emailAddress);
        req.setAttribute("checkIn", reservation.getCheckInDate());
        req.setAttribute("checkOut", reservation.getCheckOutDate());
        req.setAttribute("rooms", String.valueOf(rooms.size()));
        req.setAttribute("name", userName);
        req.getRequestDispatcher("transactionReceipt.jsp").forward(req, resp);
		
        // resp.sendRedirect("transactionReceipt.jsp");
    }
	
	private void sendEmail(HttpServletRequest request, String userName, String sendAddress, Reservation reservation, List<Room> rooms, Double total) throws ServletException {
        // Send Grid api key and configuration
        final String username = "apikey";
        final String password = "Get a new Api Key";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.sendgrid.net");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("zacharyfreedom@gmail.com")); 
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendAddress)); 
            message.setSubject("Thank You for Booking at Valhalla! Your reservation has been received.");
			
			String messageString = "Hello ";
			messageString += userName;
			messageString += "!  We have received your Reservation (#";
			messageString += reservation.getReservationId();
			messageString += ") for ";
			messageString += reservation.getCheckInDate();
			messageString += " - ";
			messageString += reservation.getCheckOutDate();
			messageString += " for the following rooms: ";
			for (int i = 0; i < rooms.size(); i++) {
				
				if (i == rooms.size() - 1 && rooms.size() > 1) {
					messageString += " and ";
				}
				
				Room room = rooms.get(i);
				messageString += "Room " + room.getRoomNumber();
				
				if (i < rooms.size() - 1) {
					messageString += ", ";
				}
			} 
			messageString += ".  Your total is $";
			messageString += total;			
			messageString += "0.  Enjoy your stay and remember - at Valhallha you sleep like a god!";
			
            message.setText(messageString);

            Transport.send(message);

            logger.info("Email sent successfully.");

        } catch (MessagingException e) {
            throw new ServletException("Failed to send email", e);
        }
    }
}
