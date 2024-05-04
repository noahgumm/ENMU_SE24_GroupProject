<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
	<% 
        Double amtPaid = (Double) request.getAttribute("amtPaid");
        String cardType = (String) request.getAttribute("cardType");
        String card = (String) request.getAttribute("card");
        String email = (String) request.getAttribute("email");
        String reservationId = (String) request.getAttribute("reservationId");
        Date checkInDate = (Date) request.getAttribute("checkIn");
        Date checkOutDate = (Date) request.getAttribute("checkOut");
        String numRooms = (String) request.getAttribute("rooms");
        String name = (String) request.getAttribute("name");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String checkInDateString = dateFormat.format(checkInDate);
        String checkOutDateString = dateFormat.format(checkOutDate);
	%>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <title>Enjoy Your Stay, <%= name %>!</title>
    </head>
    <body>	
        <div class="container">		
			<h1>Enjoy Your Stay, <%= name %>!</h1><br>
			
			<h2>Your reservation has been received!</h2>
			
			<h3>Reservation details will be sent to <%= email %>.</h2><br>
		
			<h2>Order #<%= reservationId %></h2>
		
			<div class="row">
				<div class="label"><b>Number of Rooms:</b></div>
				<div class="data"><%= numRooms %></div>
			
				<div class="label"><b>Dates:</b></div>
				<div class="data">
					Check-in: <%= checkInDate %>, 
					Check-out: <%= checkOutDate %>
				</div>		
				
				<div class="label"><b>Total:</b></div>
				<div class="data">$<%= amtPaid %></div>
			</div>
		
			<br>
			<div>You paid with a <%= cardType %> card (<%= card %>).</div><br>
		
			<h2>Thank you for booking at Valhalla!</h2><br>
			
			<button><a href="mainView.jsp">HOME</a></button>
        </div>
		<img class="logo" src="images/logo.png" alt="Logo">
    </body>
</html>