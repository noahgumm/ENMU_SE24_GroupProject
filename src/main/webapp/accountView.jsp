<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%			
	//Temp test data
	String email = "guest@email.com";
	String password = "florp";
	String firstname = "User";
	String lastname = "McUserface";
	String address = "100 Percent Made Up St";
	String phone = "111-111-1111";
%>	
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <title>My Account</title>
    </head>
    <body>
        <div class="container">
			<img class="logo" src="/HotelReservationApp/images/logo.png" alt="Logo">
			
            <h1>My Account</h1>
            <!--
                action will be the login servlet
                which should route user to login page upon successful registration
             -->
            <a href="mainView.jsp"><p class="back"><</p></a>
            <form action="HotelReservationApp/UserController" method="post">
                <input type="hidden" name="action" value="modify">
				
                <input type="email" placeholder="Email" name="emailId" id="emailId" value="<%= email %>" required>

                <input type="password" name="password" placeholder="Password" id="password" value="<%= password %>"required>

                <input type="text" name="firstName" placeholder="First Name" id="firstName" value="<%= firstname %>" required>

                <input type="text" name="lastName" placeholder="Last Name"  id="lastName" value="<%= lastname %>" required>

                <input type="text" name="address" placeholder="Address" id="address" value="<%= address %>" required>

                <input type="text" name="phone" placeholder="Phone Number" id="phone" value="<%= phone %>" required>
				<br><br>
                <button type="submit"> SAVE </button>
            </form>
        </div>
    </body>
</html>