<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
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
			background-image: url('/HotelReservationApp/images/bg3.jpg');
		}
	</style>
	<body>
        <div class="container">
			<h2>Select Rooms</h2>

            <a href="bookingView.jsp"><p class="back"><</p></a>

				<button onclick="changeFloor(-1)" id="increment1">-</button>
				<label id="floor">Floor 1</label>
				<button onclick="changeFloor(1)" id="increment2">+</button><br>
				<%-- data for booked rooms is available in a list named bookedRooms --%>
				<%-- data for available rooms is available in a list named availableRooms --%>
				<%-- data for room types (single, deluxe, suite) is available in a map named roomTypes --%>
				<%
					
					List<String> bookedRooms = Arrays.asList("101", "102", "201", "302", "303", "304", "401", "402", "403"); // Example booked rooms					
					Map<String, String> roomTypes = new HashMap<>();
					roomTypes.put("101", "Single");
					roomTypes.put("102", "Deluxe");
					roomTypes.put("103", "Single");
					roomTypes.put("104", "Single");
					roomTypes.put("105", "Suite");
					roomTypes.put("106", "Single");
					roomTypes.put("107", "Deluxe");
					roomTypes.put("108", "Single");
					roomTypes.put("109", "Single");
					roomTypes.put("110", "Suite");
					roomTypes.put("201", "Deluxe");
					roomTypes.put("202", "Deluxe");
					roomTypes.put("203", "Single");
					roomTypes.put("204", "Single");
					roomTypes.put("205", "Suite");
					roomTypes.put("206", "Single");
					roomTypes.put("207", "Deluxe");
					roomTypes.put("208", "Single");
					roomTypes.put("209", "Single");
					roomTypes.put("210", "Suite");
					roomTypes.put("301", "Deluxe");
					roomTypes.put("302", "Deluxe");
					roomTypes.put("303", "Single");
					roomTypes.put("304", "Single");
					roomTypes.put("305", "Suite");
					roomTypes.put("306", "Single");
					roomTypes.put("307", "Deluxe");
					roomTypes.put("308", "Single");
					roomTypes.put("309", "Single");
					roomTypes.put("310", "Suite");
					roomTypes.put("401", "Deluxe");
					roomTypes.put("402", "Deluxe");
					roomTypes.put("403", "Single");
					roomTypes.put("404", "Single");
					roomTypes.put("405", "Suite");
					roomTypes.put("406", "Single");
					roomTypes.put("407", "Deluxe");
					roomTypes.put("408", "Single");
					roomTypes.put("409", "Single");
					roomTypes.put("410", "Suite");
				%>
				<%
				// Loop through each room and display it on the floor map
				for (int floor = 1; floor <= 4; floor++) { // Assuming 4 floors
				%>
				<div class="floor-map 
				<%	if (floor == 1) { %> active <% } %>
				">
				<%
					for (int roomNumber = (floor * 100) + 1; roomNumber <= (floor * 100) + 10; roomNumber++) { // Assuming 10 rooms per floor
						String roomKey = String.valueOf(roomNumber);
						String roomType = roomTypes.get(roomKey);
						String roomClass = "room";
						if (bookedRooms.contains(roomKey)) {
							roomClass += " booked";
						} else {
							roomClass += " available";						
						}
				%>
					<div class="<%= roomClass %>" data-room="<%= roomKey %>">
						<%= roomType %>
						<br>
						<%= roomKey %>
					</div>				
				<%
					}

				%>
				</div>
				<%
				}
				%>
			<br><br>	
            <form name="roomsForm" action="BookingController" method="post">
				<h3>Selected Rooms:</h3>
				<div id="selected-rooms"></div><br><br>
                <input type="hidden" id="roomCount" name="roomCount" value="0">
				<button type="submit">ADD TO CART</button>
			</form>
		</div>
		<script>
			// Javascript for Selecting Rooms - Can be moved to controller or use controller instead later
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


			// JavaScript for controlling navigation between floors - Can be moved to controller or use controller instead later
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
		</script>
	</body>
</html>