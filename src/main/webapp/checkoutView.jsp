<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<%			
	//Temp test data
	String days = "3";
	String grandTotal = "950.00";
%>	
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <title>Check Out</title>
		
    </head>
	<style>
		body {
			background-image: url('images/bg3.jpg');
		}
	</style>
    <body>
        <div class="container">
			<img class="logo" src="images/logo.png" alt="Logo">
			
            <h1>Check Out</h1>		
			
            <a href="cartView.jsp"><p class="back"><</p></a>
			
            <form name="cartForm" action="ReservationManagementController" method="post">			
				<label for="days">Days</label><br>
				<input type="number" id="days" name="days" class="number-input" min="1" value="<%= days %>" max="100" disabled /><br>
				
				<label for="grandtotal">Total</label><br>
				<input type="text" name="grandtotal" id="grandtotal" value="$<%= grandTotal %>" disabled /> 
							
				<h2>Check Out</h2><br>
				<label id="cclabel">Credit Card</label><br>
				<div id="creditcardblock">
					<input type="text" name="cardNumber" placeholder="Card Number" id="cardNumber" required><br>
					<input type="text" name="cardName" placeholder="Name on Card" id="cardName" required><br>
					<label for="exprMonth">Expiration Date</label><br>
					<select name="exprMonth" id="exprMonth">
					  <option value="01">01</option>
					  <option value="02">02</option>
					  <option value="03">03</option>
					  <option value="04">03</option>
					  <option value="05">04</option>
					  <option value="06">06</option>
					  <option value="07">07</option>
					  <option value="08">08</option>
					  <option value="09">09</option>
					  <option value="10">10</option>
					  <option value="11">11</option>
					  <option value="12">12</option>
					</select>				
					<select name="expryear" id="exprYear">
					  <option value="24">2024</option>
					  <option value="25">2025</option>
					  <option value="26">2026</option>
					  <option value="27">2027</option>
					  <option value="28">2028</option>
					  <option value="29">2029</option>
					  <option value="30">2030</option>
					</select>				
					<input type="text" name="cvc" placeholder="CVC" id="cvc" required><br>
				</div><br><br>
				
				<button type="submit">Submit Order</button>
			</form>
        </div>
    </body>
</html>