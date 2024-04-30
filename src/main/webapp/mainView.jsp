<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <title>Booking Manager</title>
    </head>
	<style>
		body {
			background-image: url('images/bg2.jpg');
		}
	</style>
	
	<% 
        String name = (String) request.getAttribute("name");
	%>
    <body>
        <div class="container">			
            <h1>Booking Manager</h1>
			
			<h2>Welcome <%= name %>!</h2>
			
            <a href="Booking"><button>BOOK NOW</button></a><br><br>
			
            <a href="myReservationsView.jsp"><button>MY RESERVATIONS</button></a><br><br>
			
            <a href="accountView.jsp"><button>MY ACCOUNT</button></a><br><br>
			
            <form name="logoutForm" action="Home" method="post">
				<button type="submit">SIGN OUT</button>
			</form>
        </div>
		<img class="logo" src="images/logo.png" alt="Logo">
    </body>
</html>