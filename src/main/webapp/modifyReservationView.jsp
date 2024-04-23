<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <link rel="stylesheet" href="styles/adminStyles.css" />
        <title>Modify Reservation</title>
    </head>
    <body>
        <div class="container">
			<img class="logo" src="images/logo.png" alt="Logo">
			
            <h1>Modify Room</h1>

            <a href="ManageReservations"><p class="back"><</p></a>
            <form action="ModifyReservation" method="post">
                <label for="userID">Guest ID</label>
                <input type="number" value="${userID}" name="userID" id="userID"/>

                <label for="roomID">Room ID</label>
                <input type="number" value="${roomID}" name="roomID" id="roomID"/>

                <label for="checkIn">Check-In Date</label>
                <input type="date" value="${checkInDate}" name="checkIn" id="checkIn"/>

                <label for="checkOut">Check-Out Date</label>
                <input type="date" value="${checkOutDate}" name="checkOut" id="checkOut"/>

                <label for="totalPrice">Total Price</label>
                <input type="number" value="${totalPrice}" name="totalPrice" id="totalPrice"/>

                <label for="guests"># of Guest(s)</label>
                <input type="number" value="${guests}" name="guests" id="guests"/>

                <label for="pets">Pets</label>
                <input type="checkbox" id="pets" name="pets" value="${pets}">

                <label for="status">Status</label>
                <select name="status" id="status">
                    <option value="pending" ${status eq 'pending' ? 'selected' : ''}>Pending</option>
                    <option value="confirmed" ${status eq 'confirmed' ? 'selected' : ''}>Confirmed</option>
                    <option value="cancelled" ${status eq 'cancelled' ? 'selected' : ''}>Cancelled</option>
                </select>

				<br><br>
                <button type="submit">Modify</button>
            </form>
        </div>
    </body>
</html>