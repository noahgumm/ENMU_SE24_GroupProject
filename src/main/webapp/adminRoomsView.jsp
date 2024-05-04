<%@ page import="com.hotelreservationapp.models.Database.Tables.Room" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <link rel="stylesheet" href="styles/adminStyles.css" />
        <title>Rooms</title>
    </head>
    <body>
        <div class="container">		
			<img class="logo" src="images/logo.png" alt="Logo">

            <h1>Rooms</h1>

            <a href="adminMainView.jsp"><p class="back"><</p></a>

            <p>${message}</p>

            <ul class="card-list">
                <%
                    List<Room> rooms = (ArrayList<Room>)request.getSession().getAttribute("rooms");
                    for (Room room : rooms) {
                %>
                <li class="card">
                    <p>Room #: <%= room.getRoomNumber() %></p>
                    <p>Type : <%= room.getRoomType() %></p>
                    <p>Floor : <%= room.getFloorNumber() %></p>
                    <p>Price (per night) : <%= room.getPricePerNight() %></p>
                    <p>Description : <%= room.getRoomDescription() %></p>
                    <p># of Beds: <%= room.getNumberOfBeds() %></p>
                    <p>Timestamp : <%= room.getCreatedAt() %></p>

                    <a href="ModifyRoom?action=modify&id=<%= room.getRoomId() %>"><button>Modify</button></a>
                    <a href="DeleteRoom?action=delete&id=<%= room.getRoomId() %>"><button>Delete</button></a>
                </li>
                <%
                    }
                %>
            </ul>
            <a href="addRoomView.jsp"><button>New Room</button></a>
        </div>
    </body>
</html>