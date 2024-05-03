<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <title>Sign In</title>
    </head>
    <body>
        <style>
            .error-results {
                color: red;
                font-weight: bold;
            }
        </style>
        <div class="container">		
			<img class="logo" src="images/logo.png" alt="Logo">

            <h1>Sign In</h1>
            <!--
                action will be the login servlet
                which should route user to login page upon successful registration
             -->
            <form name="loginForm" action="Login" method="post">
                <input type="hidden" name="action" value="login">
                <p>${sessionScope.error}</p>
                <input type="email" name="email" id="email" placeholder="Email" required><br><br>
                <input type="password" name="password" id="password" placeholder="Password" required><br>
                <p><a href="AdminLogin">Admins login here.</a></p>
				<p><a href="registerView.jsp">New? Create an account.</a></p>
				<br>
                <button type="submit">LOGIN</button>
            </form>

            <div class="error-results">${error}</div>
        </div>
    </body>
</html>