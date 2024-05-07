<%@ page import="com.hotelreservationapp.models.Database.Tables.Reservation" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hotelreservationapp.models.Database.Tables.Room" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
		<link rel="stylesheet" href="styles/adminStyles.css" />
        <title>My Reservations</title>
    </head>
	<style>
		body {
			background-image: url('images/bg2.jpg');
		}
	</style>
    <body>
        <div class="container">
			<img class="logo" src="images/logo.png" alt="Logo">
			
            <h1>My Reservations</h1>
            <a href="mainView.jsp"><p class="back"><</p></a>

			<p>${sessionScope.resvMessage}</p>

			<ul class="card-list">
				<%
					ArrayList<Reservation> reservations = (ArrayList<Reservation>)request.getSession().getAttribute("reservations");
					for (Reservation res : reservations) {
						StringBuilder roomIDs = new StringBuilder();
						for(Room room: res.getRooms()){
							roomIDs.append(room.getRoomId());
							roomIDs.append(",");
						}
				%>
				<li class="card">
					<p>Reservation ID : <%= res.getReservationId() %></p>
					<p>Customer ID : <%= res.getUserId() %></p>
					<p>Room(s) ID(s) : <%= roomIDs %></p>
					<p>Check In : <%= res.getCheckInDate() %></p>
					<p>Check Out : <%= res.getCheckOutDate() %></p>
					<p>Price Total: <%= res.getTotalPrice() %></p>
					<p># of Guests : <%= res.getNumGuests() %></p>
					<p>Status : <%= res.getReservationStatus() %></p>
					<p>Pets : <%= res.getPets() %></p>
					<p>Created At : <%= res.getCreatedAt() %></p>

					<a href="UserReservationsEdit?action=modify&id=<%= res.getReservationId() %>"><button>Edit</button></a>
					<a href="UserReservationsDelete?action=delete&id=<%= res.getReservationId() %>"><button>Cancel</button></a>
				</li>
				<%
					}
				%>
			</ul>
        </div>		
    </body>
</html>