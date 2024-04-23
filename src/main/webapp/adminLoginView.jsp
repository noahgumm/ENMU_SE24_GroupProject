<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <title>Admin Sign In</title>
    </head>
    <body>
        <div class="container">		
			<img class="logo" src="images/logo.png" alt="Logo">

            <h1>Admin Sign In</h1>

            <form name="loginForm" action="AdminLogin" method="post">
                <p>${sessionScope.error}</p>
                <input type="email" name="email" id="email" placeholder="Email" required>
                <input type="password" name="password" id="password" placeholder="Password" required>
                <p><a href="loginView.jsp">User login.</a></p>
                <br>
                <button type="submit">LOGIN</button>
            </form>
        </div>
    </body>
</html>