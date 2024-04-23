<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%			
	//Temp test data
	String email = "guest@email.com";
	Integer orderRoomCount = 1;
	String orderNumber = "003";
	String orderStartDate = "06/10/2024";
	String orderEndDate = "06/14/2024";
	String orderTotal = "1200.00";
%>	
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <title>Enjoy Your Stay</title>
    </head>
	<style>
		body {
			background-image: url('images/bg4.jpg');
		}
	</style>
    <body>
        <div class="container">
			<img class="logo" src="images/logo.png" alt="Logo">
			
            <h1>Enjoy Your Stay</h1>
			
            <h2>Your reservation has been received! </h2>
			
			<h4>Reservation details have been sent to <%= email %>.</h4>
			
            <h2>Order #<%= orderNumber %></h2>	
			<div id="orderSummary">
				<div id="roomCountSummary">
					<h4>Rooms</h4>
					<div id="roomcount-text"><%= orderRoomCount %></div> 
				</div>
				
				<div id="dateSummary">
					<h4>Dates</h4>
					<div id="startDateSummary"><%= orderStartDate %> - <%= orderEndDate %></div> 
				</div>
				
				<div id="totalSummary">
					<h4>Total</h4>
					<div>$<%= orderTotal %></div>
				</div>
			</div><br><br>
						
            <h2>Thank you for booking at Valhalla! </h2><br><br>
			
			<a href="mainView.jsp"><button>HOME</button></a> 
            <form name="logoutForm" action="Home" method="post">
				<button type="submit">SIGN OUT</button>
			</form>
        </div>
    </body>
</html>