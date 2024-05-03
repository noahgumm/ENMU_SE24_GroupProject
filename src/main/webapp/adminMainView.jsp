<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <title>Admin Home</title>
    </head>
	<style>
		body {
			background-image: url('images/bg2.jpg');
		}
	</style>
    <body>
        <div class="container">
			<img class="logo" src="images/logo.png" alt="Logo">
			
            <h1>Admin Home</h1>
			
			<h2>Welcome ${sessionScope.username}</h2>
			
            <a href="ManageReservations"><button>VIEW RESERVATIONS</button></a><br><br>
			
            <a href="AdminRooms"><button>MANAGE ROOMS</button></a><br><br>

			<a href="adminLoginView.jsp"><button>SIGN OUT</button></a>
        </div>
    </body>
</html>