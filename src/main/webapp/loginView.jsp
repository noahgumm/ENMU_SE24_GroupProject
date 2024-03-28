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
            <form action="loginUserSuccess" method="post">
                <label for="emailId">Email</label>
                <input type="email" placeholder="youremail@example.com" name="emailId" id="emailId" required>

                <label for="password">Password</label>
                <input type="password" name="password" id="password" required>

                <button type="submit">Submit</button>
            </form>
        </div>
    </body>
</html>