<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.hotelreservationapp.models.Database.Tables.User" %>
<%			
	//Temp test data
    User user = (User) request.getAttribute("user");
	int userID = user.getUserId();
    String email = user.getEmail();
	String password = user.getPassword();
	String username = user.getUsername();
	String phone = user.getPhoneNumber();
%>	
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <title>My Account</title>
    </head>
    <body>
        <div class="container">
			<img class="logo" src="images/logo.png" alt="Logo">
			
            <h1>My Account</h1>
            <!--
                action will be the login servlet
                which should route user to login page upon successful registration
             -->
            <a href="mainView.jsp"><p class="back"><</p></a>
            <form action="UserAccount" method="post">
                <input type="hidden" name="userId" value="<%= userID %>">
				
                <input type="email" placeholder="Email" name="email" id="emailId" value="<%= email %>" required>

                <input type="password" name="password" placeholder="Password" id="password" value="<%= password %>" required>

                <input type="text" name="username" placeholder="Username" id="username" value="<%= username %>" required>

                <input type="text" name="phoneNumber" placeholder="Phone Number" id="phone" value="<%= phone %>" required>
				<br><br>
                <button type="submit"> SAVE </button>
            </form>
        </div>
    </body>
</html>