<%@ page import="com.hotelreservationapp.models.Database.Tables.Room" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/UserStyles.css" />
        <link rel="stylesheet" href="styles/adminStyles.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="styles/cardStyles.css" />
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
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Room #: <%= room.getRoomNumber() %></li>
                        <li class="list-group-item">Type : <%= room.getRoomType() %></li>
                        <li class="list-group-item">Floor : <%= room.getFloorNumber() %></li>
                        <li class="list-group-item">Price (per night) : $<%= room.getPricePerNight() %></li>
                        <li class="list-group-item">Description : <%= room.getRoomDescription() %></li>
                        <li class="list-group-item"># of Beds: <%= room.getNumberOfBeds() %></li>
                        <li class="list-group-item">Time Created : <%= room.getCreatedAt() %></li>
                    </ul>
                    <div class="card-body">
                        <a href="ModifyRoom?action=modify&id=<%= room.getRoomId() %>"><button>Modify</button></a>
                        <a href="DeleteRoom?action=delete&id=<%= room.getRoomId() %>"><button>Delete</button></a>
                    </div>
                </li>
                <%
                    }
                %>
            </ul>
            <a href="addRoomView.jsp"><button>New Room</button></a>
        </div>
    </body>
</html>