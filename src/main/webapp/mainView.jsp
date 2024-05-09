<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css?v=1" />
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
			
			<h2>Welcome ${sessionScope.user.getUsername()}</h2>
			
            <a href="Booking"><button>BOOK NOW</button></a><br><br>
			
            <a href="UserReservations"><button>MY RESERVATIONS</button></a><br><br>
			
            <a href="UserAccount"><button>MY ACCOUNT</button></a><br><br>

            <!-- <a href="Cart"><button>CART</button></a><br><br> -->
			
            <form name="logoutForm" action="Home" method="post">
				<button type="submit">SIGN OUT</button>
			</form>
        </div>
		<img class="logo" src="images/logo.png" alt="Logo">
    </body>
</html>