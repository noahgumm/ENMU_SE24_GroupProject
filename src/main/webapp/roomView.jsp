<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.hotelreservationapp.models.Database.Tables.Room" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/UserStyles.css" />
    <title>Select Your Room</title>
</head>
<style>
    body {
        background-image: url('images/bg3.jpg');
    }
</style>
<body>
<div class="container">
    <h2>Select Rooms</h2>
    <a href="bookingView.jsp"><p class="back"><</p></a>
    <button onclick="changeFloor(-1)" id="increment1">-</button>
    <label id="floor">Floor 1</label>
    <button onclick="changeFloor(1)" id="increment2">+</button><br>
    <% if (request.getAttribute("rooms") != null && request.getAttribute("bookedRooms") != null && request.getAttribute("highestFloorNumber") != null) { %>
        <%
            List<Room> rooms = (List<Room>) request.getAttribute("rooms");
            List<Room> bookedRooms = (List<Room>) request.getAttribute("bookedRooms");
            int highestFloorNumber = (int) request.getAttribute("highestFloorNumber");
        %>
        <%
            for (int floor = 1; floor <= highestFloorNumber; floor++) {
        %>
        <div class="floor-map <%= floor == 1 ? "active" : "" %>">
            <%
                for (Room room : rooms) {
                    if (room.getFloorNumber() == floor) {
                        boolean isBooked = false;
                        for (Room bookedRoom : bookedRooms) {
                            if (room.getRoomId() == bookedRoom.getRoomId()) {
                                isBooked = true;
                                break;
                            }
                        }
                        if (isBooked) {
            %>
            <div class="room booked" data-room="<%= room.getRoomNumber() %>" data-price="<%= room.getPricePerNight() %>">
                <div class="roomDeets"><br>
                    <b>Room <%= room.getRoomNumber() %></b><br><br>
					<%= room.getRoomType() %><br>
                    $<%= room.getPricePerNight() %>0/Night<br>
                    <%= room.getNumberOfBeds() %> Bed(s)
                </div>
            </div>
            <%
                        } else {
            %>
            <div class="room available" data-room="<%= room.getRoomNumber() %>" data-price="<%= room.getPricePerNight() %>">
                <div class="roomDeets"><br>
                    <b>Room <%= room.getRoomNumber() %></b><br><br>
					<%= room.getRoomType() %><br>
                    $<%= room.getPricePerNight() %>0/Night<br>
                    <%= room.getNumberOfBeds() %> Bed(s)
                </div>
            </div>
            <%
                        }
                    }
                }
            %>
        </div>
        <%
            }
        %>
    <% 
    }
    %>
    <% if (request.getAttribute("rooms") == null) { %>
        <p>Error: Room list is missing.</p>
    <% } %>
    <% if (request.getAttribute("bookedRooms") == null) { %>
        <p>Error: Booked list is missing.</p>
    <% } %>
    <% if (request.getAttribute("highestFloorNumber") == null) { %>
        <p>Error: Highest floor number is missing.</p>
    <% } %>
    <br><br>	
    <form name="roomsForm" action="/ReservationManagement" method="post">
        <h3>Selected Rooms:</h3>
        <div id="selected-rooms"></div><br><br>
        <h3>Nights Staying:</h3>
		<div id="days-num" data-days="<%= request.getAttribute("daysStaying") %>"><%= request.getAttribute("daysStaying") %></div><br><br>
        <input type="hidden" id="roomCount" name="roomCount" value="0">
        <h3>Total Price:<br>$<span id="totalPrice">0</span></h3><br>
        <button type="submit">ADD TO CART</button>
    </form>
</div>
<script>
    // Javascript for Selecting Rooms (Updates View)
    const selectedRoomsContainer = document.getElementById('selected-rooms');
    const selectedRooms = new Set();

    document.querySelectorAll('.room.available').forEach(room => {
        room.addEventListener('click', function() {
            const roomNumber = this.getAttribute('data-room');
            if (selectedRooms.has(roomNumber)) {
                selectedRooms.delete(roomNumber);
                this.classList.remove('selected');
            } else {
                selectedRooms.add(roomNumber);
                this.classList.add('selected');
            }
            updateSelectedRoomsList();
            sumTotalPrice();
        });
    });

    function updateSelectedRoomsList() {
        count = 0;
        selectedRoomsContainer.innerHTML = '';
        selectedRooms.forEach(room => {
            const roomItem = document.createElement('input');
            roomItem.value = room;
            roomItem.type = "text"; 
            roomItem.disabled = true;
            let roomItemNumber = "room" + count; 
            roomItem.name = roomItemNumber;
            roomItem.className = "roomItem";
            roomItem.style.borderBottom = "none";
            selectedRoomsContainer.appendChild(roomItem);
            count++;
        });
        document.getElementById("roomCount").value = count;
    }

    // Navigation between floors (Updates View)
    let currentFloor = 1;
    const floors = document.querySelectorAll('.floor-map');

    function changeFloor(direction) {
        const newFloor = currentFloor + direction;
        if (newFloor >= 1 && newFloor <= floors.length) {
            // Hide current floor
            floors[currentFloor - 1].classList.remove('active');
            // Show new floor
            floors[newFloor - 1].classList.add('active');
            currentFloor = newFloor;
        }
        document.getElementById('floor').textContent="Floor " + currentFloor;
    }

    // Sum of selected rooms (Updates View)
    function sumTotalPrice() {
        var totalPrice = 0;
        var selectedRooms = document.querySelectorAll('.room.selected');
        selectedRooms.forEach(function(room) {
            // Assuming each room has a data attribute for price, adjust this line according to your actual implementation
            var price = parseFloat(room.getAttribute('data-price'));
			var days = parseFloat(document.getElementById('days-num').getAttribute('data-days'));
            totalPrice += (price * days);
        });
        document.getElementById('totalPrice').textContent = totalPrice.toFixed(2); // Update displayed total price
    }
</script>
</body>
</html>
