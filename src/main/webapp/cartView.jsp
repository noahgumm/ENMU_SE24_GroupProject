<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
		
    </head>
	<style>
		body {
			background-image: url('images/bg3.jpg');
		}
	</style>
    <body>
        <div class="container">
			<img class="logo" src="images/logo.png" alt="Logo">
			
            <h1>Your Stay</h1>		
			
            <a href="roomView.jsp"><p class="back"><</p></a>
			
            <form name="cartForm" action="Cart" method="post">
				<label for="guests">Guests</label><br>
				<input type="number" id="guests" name="guests" class="number-input" min="1" value="<%= guests %>" max="100" disabled /><br>
				
				<label for="days">Days</label><br>
				<input type="number" id="days" name="days" class="number-input" min="1" value="<%= days %>" max="100" disabled /><br>
						
				<label for="pets">Bringing Pets?</label><br>
				<input type="text" name="pets" id="pets" value="<%= pets %>" disabled /><br><br>
				
				<label>Rooms</label><br>
				<div class="roomsList">				
				<%			
				Double grandTotal = 0.00;
					for (int i = 0; i < roomcount;  i++) { 
						String roomNumber = rooms[i][0];
						String roomType = rooms[i][1];
						String roomBeds = rooms[i][2];
						String roomRate = rooms[i][3];
						Double roomRateMath = Double.parseDouble(rooms[i][3]);
						Double roomTotal = roomRateMath * days;
						grandTotal += roomTotal;
						
				%>				
					<div class="roomListing">
						<div class="image-block">
							<input type="text" name="room<%= i %>" id="room<%= i %>" class="roomcount" disabled hidden />
							<input type="text" name="room<%= i %>number" id="room<%= i %>number" value="Room <%= roomNumber %>" class="roomnumber" disabled />							
				<%
						if ( roomType.equals("Single") ) {						
				%>
							<img src="images/single.jpeg" class="room-picture" alt="room"/>
				<%
						} else if ( roomType.equals("Deluxe") ) {						
				%>
							<img src="images/deluxe.jpg" class="room-picture" alt="room"/>
				<%
						} else if ( roomType.equals("Suite") ) {						
				%>
							<img src="images/suite.jpeg" class="room-picture" alt="room"/>
				<%
						}
				%>											
						</div>
						<input type="text" name="room<%= i %>type" id="room<%= i %>type" value="<%= roomType %>" class="roomtype" disabled />	
						<input type="text" name="room<%= i %>beds" id="room<%= i %>beds" value="<%= roomBeds %> Bed(s)" class="roombeds" disabled />	
						<input type="text" name="room<%= i %>rate" id="room<%= i %>rate" value="$<%= roomRate %> / day" class="roomrate" disabled />
						<label  for="room<%= i %>total">Sub Total:</label>
						<input type="text" name="room<%= i %>total" id="room<%= i %>total" value="$<%= roomTotal %>" class="roomtotal" disabled />							
					</div>
				<%
					}
				%>
				</div>
				<br><br>
			<%
				if ( pets.equals("yes") ) {						
			%>
				<label for="petfee">Pet Fee</label><br>
				<input type="text" name="petfee" id="petfee" value="$50" disabled />	
				<br><br>					
			<%					
					grandTotal += 50.00;
				}
			%>
				<label for="grandtotal">Total</label><br>
				<input type="text" name="grandtotal" id="grandtotal" value="$<%= grandTotal %>" disabled /><br><br> 
		
				<button type="submit">ENTER PAYMENT</button>
			</form>
        </div>
    </body>
</html>