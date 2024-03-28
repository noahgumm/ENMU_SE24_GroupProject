<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/loginStyles.css" />
        <title>Login</title>
    </head>
    <body>
        <div class="container">
            <h2>Hotel Reservation App</h2>
            <h3>Login</h3>
            <!--
                action will be the login servlet
                which should route user to login page upon successful registration
             -->
            <form name="loginForm" action="/HotelReservationApp_war/UserController" method="post">
                <input type="hidden" name="action" value="login">
                <label for="username">Username</label>
                <input type="text" name="username" id="username" required>

                <label for="password">Password</label>
                <input type="password" name="password" id="password" required>

                <button type="submit">Submit</button>
            </form>
            <button><a href="registerView.jsp">Register</a></button>
        </div>
    </body>
</html>