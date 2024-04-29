<%@ page import="com.hotelreservationapp.models.Database.Tables.Room" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hotelreservationapp.models.Database.Tables.Reservation" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <link rel="stylesheet" href="styles/adminStyles.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="styles/cardStyles.css" />
        <title>Reservations</title>

        <script>
            function toggleSearchBar() {
                const selectedOption = document.getElementById("searchType").value;
                const customerIdBar = document.getElementById("customerIdBar");
                const roomIdBar = document.getElementById("roomIdBar");
                const datesBar = document.getElementById("datesBar");
                const statusBar = document.getElementById("statusBar");

                // Hide all search bars
                customerIdBar.style.display = "none";
                roomIdBar.style.display = "none";
                datesBar.style.display = "none";
                statusBar.style.display = "none";

                // Show the selected search bar
                if (selectedOption === "customerID") {
                    customerIdBar.style.display = "block";
                } else if (selectedOption === "roomID") {
                    roomIdBar.style.display = "block";
                } else if (selectedOption === "dates") {
                    datesBar.style.display = "block";
                } else if (selectedOption === "status") {
                    statusBar.style.display = "block";
                }
            }
        </script>

    </head>
    <body>
        <div class="container">		
			<img class="logo" src="images/logo.png" alt="Logo">

            <h1>Reservations</h1>

            <a href="adminMainView.jsp"><p class="back"><</p></a>
            <p>${message}</p>
            <form action="ManageReservations" method="post">
                <select name="searchType" id="searchType" onchange="toggleSearchBar()">
                    <option value="all" selected>Show All</option>
                    <option value="customerID">Customer ID</option>
                    <option value="roomID">Room ID</option>
                    <option value="dates">Dates</option>
                    <option value="status">Status</option>
                </select>

                <!-- Customer ID Search Bar -->
                <div id="customerIdBar" style="display: none;">
                    <input type="text" name="customerId" placeholder="Enter Customer ID">
                </div>

                <!-- Room ID Search Bar -->
                <div id="roomIdBar" style="display: none;">
                    <input type="text" name="roomId" placeholder="Enter Room ID">
                </div>

                <!-- Dates Search Bar -->
                <div id="datesBar" style="display: none;">
                    <input type="date" name="startDate" placeholder="Start Date">
                    <input type="date" name="endDate" placeholder="End Date">
                </div>

                <!-- Status Search Bar -->
                <div id="statusBar" style="display: none;">
                    <label for="status">Status</label>
                        <select name="status" id="status">
                            <option value="pending">Pending</option>
                            <option value="confirmed">Confirmed</option>
                            <option value="cancelled">Cancelled</option>
                        </select>
                </div>

                <button type="submit">Search</button>
            </form>
            <ul class="card-list">
                <%
                    List<Reservation> reservations = (ArrayList<Reservation>)request.getSession().getAttribute("reservations");
                    for (Reservation res : reservations) {
                %>
                <li class="card">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Reservation ID : <%= res.getReservationId() %></li>
                        <li class="list-group-item">Customer ID : <%= res.getUserId() %></li>
                        <li class="list-group-item">Room ID : <%= res.getRoomId() %></li>
                        <li class="list-group-item">Check In : <%= res.getCheckInDate() %></li>
                        <li class="list-group-item">Check Out : <%= res.getCheckOutDate() %></li>
                        <li class="list-group-item">Price Total: <%= res.getTotalPrice() %></li>
                        <li class="list-group-item"># of Guests : <%= res.getNumGuests() %></li>
                        <li class="list-group-item">Status : <%= res.getReservationStatus() %></li>
                        <li class="list-group-item">Pets : <%= res.getPets() %></li>
                        <li class="list-group-item">Created At : <%= res.getCreatedAt() %></li>
                    </ul>
                    <div class="card-body">
                        <a href="ModifyReservation?action=modify&id=<%= res.getReservationId() %>"><button>Modify</button></a>
                        <a href="DeleteReservation?action=delete&id=<%= res.getReservationId() %>"><button>Delete</button></a>
                    </div>
                </li>
                <%
                    }
                %>
            </ul>
        </div>
    </body>
</html>