<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <link rel="stylesheet" href="styles/adminStyles.css" />
        <title>New Room</title>
    </head>
    <body>
        <div class="container">
			<img class="logo" src="images/logo.png" alt="Logo">
			
            <h1>New Room</h1>

            <a href="adminRoomsView.jsp"><p class="back"><</p></a>
            <form action="AddRoom" method="post">
                <label for="roomNumber">Room #</label>
                <input type="number" name="roomNumber" id="roomNumber"/>

                <label for="roomType">Type</label>
                <input type="text" name="roomType" id="roomType"/>

                <label for="floorNumber">Floor</label>
                <input type="number" name="floorNumber" id="floorNumber"/>

                <label for="price">Price (per night)</label>
                <input type="number" name="price" id="price"/>

                <label for="description">Description</label>
                <input type="text" name="description" id="description"/>

                <label for="beds"># of Bed(s)</label>
                <input type="text" name="beds" id="beds"/>

				<br><br>
                <button type="submit">Add</button>
            </form>
        </div>
    </body>
</html>