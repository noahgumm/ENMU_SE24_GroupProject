<%@ page import="com.hotelreservationapp.models.Database.Tables.Reservation" %>
<%@ page import="com.hotelreservationapp.models.Database.Tables.Room" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css?v=1" />
        <link rel="stylesheet" href="styles/adminStyles.css?v=1" />
        <title>Edit Reservation</title>
    </head>
    <body>
        <div class="container">
			<img class="logo" src="images/logo.png" alt="Logo">
			
            <h1>Edit Reservation</h1>

            <a href="myReservationsView.jsp"><p class="back"><</p></a>
            <form action="UserReservationsEdit" method="post">
                <input type="hidden" name="reservationID" value="<%= request.getParameter("id") %>"/>
                <!-- Need to add the ability to remove rooms -->
                <input type="hidden" id="roomIDs" name="roomIDs">
                <label for="roomList">Rooms</label>
                <ul id="roomList">
                    <%
                        Reservation reservation = (Reservation) request.getSession().getAttribute("reservationToEdit");
                        List<Room> rooms = reservation.getRooms();
                        for (Room room : rooms) {
                    %>
                    <li class="room-list-button" value="<%= room.getRoomId() %>"><span><%= room.getRoomNumber() %></span><button onclick="deleteRoom(this)">Delete</button></li>
                    <%
                        }
                    %>
                </ul>

                <label for="checkIn">Check-In Date</label>
                <input type="date" value="${reservationToEdit.checkInDate}" name="checkIn" id="checkIn"/>

                <label for="checkOut">Check-Out Date</label>
                <input type="date" value="${reservationToEdit.checkOutDate}" name="checkOut" id="checkOut"/>

                <label for="guests"># of Guest(s)</label>
                <input type="number" value="${reservationToEdit.numGuests}" name="guests" id="guests"/>

                <label for="pets">Pets</label>
                <input type="checkbox" id="pets" name="pets" value="${reservationToEdit.pets}">

				<br><br>
                <button type="submit">Confirm</button>
            </form>
        </div>


        <script>
            setRooms();

            function deleteRoom(button) {
                var listItem = button.parentNode;
                listItem.parentNode.removeChild(listItem);
                setRooms();
            }

            function roomsToCSV(){
                var listItems = document.querySelectorAll("#roomList li");
                var roomNumbers = [];

                listItems.forEach(function(item) {
                    roomNumbers.push(item.value);
                });

                return roomNumbers.join(",");
            }
            
            function setRooms(){
                // set roomIDs as csv of room numbers
                document.getElementById("roomIDs").value = roomsToCSV();
            }

            // document.getElementById("roomForm").onsubmit = function() {
            //     var listItems = document.querySelectorAll("#roomList li");
            //     var roomNumbers = [];

            //     listItems.forEach(function(item) {
            //         roomNumbers.push(item.value);
            //     });

            //     document.getElementById("roomIDs").value = roomNumbers.join(",");
            // };
        </script>

    </body>
</html>