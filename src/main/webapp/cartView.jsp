<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.hotelreservationapp.models.Database.Prepared.CartInformation" %>
<%@ page import="com.hotelreservationapp.models.Database.Prepared.RoomReservation" %>
<%@ page import="com.hotelreservationapp.models.Database.Tables.Room" %>
<%@ page import="com.hotelreservationapp.models.Database.Tables.Reservation" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>

<%			
	//Temp test data
	Integer guests = 1;
	Integer days = 3;
	String pets = "yes";
	int roomcount = 1;
	String[][] rooms = {{"301", "Single", "1", "300.00"}};
%>	
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <title>Your Stay</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
	<style>
		body {
			background-image: url('images/bg3.jpg');
		}

		.form-control{
			border: 1px solid #ced4da !important;
			border-radius: .25rem;
			padding: .375rem .75rem;
			font-size: 1rem;
			line-height: 1.5;
			color: #495057 !important;
			background-color: #fff;
			background-clip: padding-box;
			box-shadow: none;
			transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;

		}

		.form-select{
			border: 1px solid #ced4da !important;
			border-radius: .25rem;
			padding: .375rem .75rem;
			font-size: 1rem;
			line-height: 1.5;
			color: #495057 !important;
			background-color: #fff;
			background-clip: padding-box;
			box-shadow: none;
			transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
		
		}

		.form-select option{
			color: #495057 !important;
			background-color: #fff;
			background-clip: padding-box;
			box-shadow: none;
			transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
		}
	</style>
    <body>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
		<div class="container">
			<img class="logo" src="images/logo.png" alt="Logo">
			<h1 style="color: white">Your Stay</h1>
			<a href="roomView.jsp"><p class="back"><</p></a>
			<form name="cartForm" action="Cart" method="post">
				<div class="row">
					<%Double grandTtl= 0.0; %>
					<%CartInformation cartInfo = (CartInformation)request.getAttribute("cart"); %>
					<%List<RoomReservation> roomReservations = cartInfo.getReservations(); %>
					<%for(int i = 0; i < roomReservations.size(); i++) { 
						String roomNum = roomReservations.get(i).getRoom().getRoomNumber(); 
						String roomType = roomReservations.get(i).getRoom().getRoomType(); 
						int floorNumber = roomReservations.get(i).getRoom().getFloorNumber(); 
						String checkInDate = roomReservations.get(i).getReservation().getCheckInDateAsString(); 
						String checkOutDate = roomReservations.get(i).getReservation().getCheckOutDateAsString(); 
						Double roomRate = roomReservations.get(i).getReservation().getTotalPrice(); 
						int numGuests = roomReservations.get(i).getReservation().getNumGuests(); 
						String status = roomReservations.get(i).getReservation().getReservationStatus(); 
						int reservationID = roomReservations.get(i).getReservation().getReservationId();
						grandTtl += roomRate;
					%>
					<div class="col-md-4 mb-4">
						<div class="card">
							<div class="card-header">
								Reservation # <%= reservationID %>
							</div>
							<div class="card-body">
								<h5 class="card-title">Floor <%= floorNumber %> Room <%= roomNum %> - <%= roomType %></h5>
								<p class="card-text"><b>Check-in:</b> <%= checkInDate %></p>
								<p class="card-text"><b>Check-out:</b> <%= checkOutDate %></p>
								<p class="card-text"><b>Rate:</b> $<%= String.format("%.2f", roomRate) %></p>
								<p class="card-text"><b>Guests:</b> <%= numGuests %></p>
								<p class="card-text"><b>Status:</b> <%= status %></p>
								<a class="btn btn-primary" href="/HotelReservationApp/ReservationManagementEdit?id=<%= reservationID %>">Edit</a>
								<a class="btn btn-danger" href="/HotelReservationApp/ReservationManagementDelete?id=<%= reservationID %>">Delete</a>
							</div>
							<div class="card-footer text-muted">
								Total Price: $<%= String.format("%.2f", roomRate) %>
							</div>
						</div>
					</div>
					<% } %>
					<% if (grandTtl > 0.0) { %>
						<div class="col-xs-12">
							<div class="card">
								<div class="card-header">
									<h5 class="card-title">Grand Total</h5>
								</div>
								<div class="card-body">
									<p class="card-text">Total Price: $<%= String.format("%.2f", grandTtl) %></p>
									<div class="row">
										<div class="col-md-2 mb-2">
											<label class="form-label">Card Type</label>
											<select class="form-select" name="cardType">
												<option value="Visa">Visa</option>
												<option value="Mastercard">Mastercard</option>
												<option value="American Express">American Express</option>
												<option value="Discover">Discover</option>
											</select>
										</div>
										<div class="col-md-3 mb-3">
											<label class="form-label">Card Holder Name</label>
											<input type="text" class="form-control" name="cardHolderName" required />
										</div>
										<div class="col-md-2 mb-2">
											<label class="form-label">Card Number</label>
											<input type="text" class="form-control" name="cardNumber" required />
										</div>
										<div class="col-md-2 mb-2">
											<label class="form-label">Expiration Date</label>
											<input type="date" class="form-control" name="expirationDate" required />
										</div>
										<div class="col-md-2 mb-2">
											<label class="form-label">CVV</label>
											<input type="text" class="form-control" name="cvv" required />	
										</div>
									</div>
									<button type="submit" class="btn btn-primary">Checkout</button>
								</div>
							</div>
						</div>
					<% } else {%>
						<h3 style="color: white">No reservations at this time</h3>
					<% } %>
				</div>
					
			</form>
		</div>

        
    </body>
</html>