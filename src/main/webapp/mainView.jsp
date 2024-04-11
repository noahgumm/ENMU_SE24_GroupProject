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
    <body>
        <div class="container">
			<img class="logo" src="images/logo.png" alt="Logo">
			
            <h1>Booking Manager</h1>
			
			<h2>Welcome ${sessionScope.username}</h2>
			
            <a href="bookingView.jsp"><button>BOOK NOW</button></a><br><br>
			
            <a href="myReservationsView.jsp"><button>MY RESERVATIONS</button></a><br><br>
			
            <a href="accountView.jsp"><button>MY ACCOUNT</button></a><br><br>
			
            <form name="logoutForm" action="Home" method="post">
				<button type="submit">SIGN OUT</button>
			</form>
        </div>
    </body>
</html>