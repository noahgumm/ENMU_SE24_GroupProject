<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <title>Register</title>
    </head>
    <body>
        <div class="container">
			<img class="logo" src="images/logo.png" alt="Logo">
			
            <h1>Register</h1>
            <!--
                action will be the login servlet
                which should route user to login page upon successful registration
             -->
            <a href="loginView.jsp"><p class="back"><</p></a>
            <form action="UserController" method="post">
                <input type="hidden" name="action" value="register">
				
                <input type="email" placeholder="Email" name="email" id="email" required>

                <input type="password" name="password" placeholder="Password" id="password" required>

                <input type="text" name="username" placeholder="Username" id="username" required>

                <input type="text" name="phone" placeholder="Phone Number" id="phone" required>
				<br><br>
                <button type="submit">CREATE ACCOUNT</button>
            </form>
        </div>
    </body>
</html>