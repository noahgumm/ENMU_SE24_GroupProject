<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.hotelreservationapp.models.Database.Prepared.CartInformation" %>
<%@ page import="com.hotelreservationapp.models.Database.Prepared.RoomReservation" %>
<%@ page import="com.hotelreservationapp.models.Database.Tables.Room" %>
<%@ page import="com.hotelreservationapp.models.Database.Tables.Reservation" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <title>Your Stay</title>
    </head>
	<style>
		body {
			background-image: url('images/bg3.jpg');
		}
	</style>
    <body>
		<div class="container">		
			<h1>Cart</h1>
			<a href="bookingView.jsp"><p class="back"><</p></a>
			<% 
				// Retrieve the reservation object from the request attribute
				Reservation reservation = (Reservation) request.getAttribute("reservation");

				// Retrieve the list of rooms from the request attribute
				List<Room> rooms = (List<Room>) request.getAttribute("rooms");

				// Check if the reservation and room list exist
				if (reservation != null && rooms != null && !rooms.isEmpty()) {
					// Calculate the number of days between check-in and check-out dates
					long numberOfDays = (((reservation.getCheckOutDate().getTime() - reservation.getCheckInDate().getTime()) / (1000 * 60 * 60 * 24)) + 1);
			%>
			<div><b>Reservation #:</b> <%= reservation.getReservationId() %></div>
			<div><b>Check-in Date:</b> <%= reservation.getCheckInDate() %></div>
			<div><b>Check-out Date:</b> <%= reservation.getCheckOutDate() %></div>
			<div><b>Number of Days:</b> <%= numberOfDays %></div>
			<div><b>Number of Guests:</b> <%= reservation.getNumGuests() %></div><br>
			<div><b>Room Details:</b></div><br>
			<div class="room-details-container">
				<% 
					// Iterate over each room and display details
					int roomCount = 0;
					for (Room room : rooms) {
						if (roomCount % 2 == 0) { %> 
					<div class="room-details">
					  <% } %>
						<% if (room.getRoomType().equals("Standard")) { %>
						  <img class="room-picture" src="images/single.jpeg" />
						<% } else if (room.getRoomType().equals("Deluxe")) { %>   
						  <img class="room-picture" src="images/deluxe.jpg" />   
						<% } else if (room.getRoomType().equals("Suite")) { %>       
						  <img class="room-picture" src="images/suite.jpeg" />   
						<% } %>						
					  <div class="room-details-content">
						<ul>
							<li>Room Number: <%= room.getRoomNumber() %></li>
							<li>Room Type: <%= room.getRoomType() %></li>
							<li>Beds: <%= room.getNumberOfBeds() %></li>
							<li>Price per Day: $<%= room.getPricePerNight() %>0</li>
						<ul>
					  </div>
					  <% if (roomCount % 2 != 0 || roomCount == rooms.size() - 1) { %> 
						</div>
					  <% } %>
					  <% roomCount++; %>
					<% } %>
			</div>
			<div><b>Pets Included:</b> <%= reservation.getPets() ? "Yes ($50 fee)" : "No" %></div><br>

			<form action="Cart" method="POST">	
				<input type="hidden" id="cartParam" name="cartParam" value="fromCart">
				<input type="hidden" id="reservationId" name="reservationId" value="<%= reservation.getReservationId() %>">
				<div><b>Total Price:</b> $<%= String.format("%.2f", request.getAttribute("totalRoomPrice")) %></div>
				<div class="form">
					<h3>Credit Card</h3>
					<div class="form-group leftist">
						<label for="cardType">Card Type</label>
						<select id="cardType" class="form-control" name="cardType">
							<option value="Visa">Visa</option>
							<option value="Mastercard">Mastercard</option>
							<option value="American Express">American Express</option>
							<option value="Discover">Discover</option>
						</select>
					</div>
					<div class="form-group">
						<input type="text" id="cardHolderName" class="form-control" name="cardHolderName" placeholder="Name on Card" required />
					</div>
					<div class="form-group">
						<input type="text" id="cardNumber" class="form-control" name="cardNumber" placeholder="Card Number" required />
					</div>
					<div class="form-group">
						<input type="date" id="expirationDate" class="form-control" name="expirationDate" placeholder="Expiration Date" required />
					</div>
					<div class="form-group">
						<label for="cvv">Expiration Date</label>
						<input type="text" id="cvv" class="form-control" name="cvv" placeholder="CVV" required />
					</div>
				</div><br>
				<button type="submit" class="btn btn-primary">Checkout</button>
			</form>

			<% } %>
			<% if (request.getAttribute("rooms") == null) { %>
				<p>Error: Room list is missing.</p>
			<% } %>
			<% if (request.getAttribute("reservation") == null) { %>
				<p>Error: Reservation is missing.</p>
			<% } %>
			<% if (request.getAttribute("totalRoomPrice") == null) { %>
				<p>Error: Total Room Price is missing.</p>
			<% } %>
		</div>
		<img class="logo" src="images/logo.png" alt="Logo">
	</body>
</html>