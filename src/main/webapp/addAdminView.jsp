<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <link rel="stylesheet" href="styles/adminStyles.css" />
        <title>Add Admin</title>
    </head>
    <body>
        <div class="container">
			<img class="logo" src="images/logo.png" alt="Logo">
			
            <h1>Add Admin</h1>

            <a href="AdminHome"><p class="back"><</p></a>
            <form action="AddAdmin" method="post">
                <label for="username">Username</label>
                <input type="text" placeholder="USERNAME" name="username" id="username" required/>

                <label for="password">Password</label>
                <input type="text" placeholder="PASSWORD" name="password" id="password" required/>

                <label for="email">Email</label>
                <input type="email" placeholder="EMAIL@EMAIL.COM" name="email" id="email" required/>

				<br><br>
                <button type="submit">Add</button>
            </form>
        </div>
    </body>
</html>