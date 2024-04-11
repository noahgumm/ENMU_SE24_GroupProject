<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	//Sample Test Data
	
	String orderStartDate = "06/10/2024";
	String orderEndDate = "06/14/2024";
	String[][] myOrders = {{"005", "1", orderStartDate, orderEndDate, "300.00"}};
%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
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
				
			<div id="myOrders">
			<%
			for (int i = 0; i <= myOrders.length - 1; i++) {
			%>
				<h2>Order #<%= myOrders[i][0] %></h2>			
				<div id="orderSummary">
					<div id="roomCountSummary">
						<h2>Rooms</h2>
						<div id="roomcount-text"><%= myOrders[i][1] %></div> 
					</div>
					
					<div id="dateSummary">
						<h2>Dates</h2>
						<div id="startDateSummary"><%= myOrders[i][2] %>}</div> 
						<div id="endDateSummary"><%= myOrders[i][3] %></div> 
					</div>
					
					<div id="totalSummary">
						<h2>Total</h2>
						<div id="total-text"><%= myOrders[i][4] %></div> 
					</div>
				</div><br><br>
							
				<form name="modifyReservationForm" action="ReservationManagementController" method="post">
					<button type="submit" name="action" value="modify">MODIFY</button>
					<button type="submit" name="action" value="cancel">CANCEL</button>
				</form>
			<%
			}
			%>
			
			</div>
        </div>		
    </body>
</html>