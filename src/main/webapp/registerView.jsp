<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/loginStyles.css" />
        <title>Registration</title>
    </head>
    <body>
        <div class="container">
            <h2>Hotel Reservation App</h2>
            <h3>Registration</h3>
            <!--
                action will be the login servlet
                which should route user to login page upon successful registration
             -->
            <form action="/HotelReservationApp_war/UserController" method="post">
                <label for="emailId">Email</label>
                <input type="email" placeholder="youremail@example.com" name="emailId" id="emailId" required>

                <label for="firstName">First Name</label>
                <input type="text" name="firstName" id="firstName" required>

                <label for="lastName">Last Name</label>
                <input type="text" name="lastName" id="lastName" required>

                <label for="address">Address</label>
                <input type="text" name="address" id="address" required>

                <label for="phone">Phone Number</label>
                <input type="text" name="phone" id="phone" required>

                <label for="username">Username</label>
                <input type="text" name="username" id="username" required>

                <label for="password">Password</label>
                <input type="password" name="password" id="password" required>

                <button type="submit">Submit</button>
            </form>
            <button><a href="loginView.jsp">Back</a></button>
        </div>
    </body>
</html>