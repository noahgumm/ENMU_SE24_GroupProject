<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.hotelreservationapp.models.Database.Tables.Room" %>
<%@ page import="com.hotelreservationapp.models.DatabaseLogic.DatabaseManager" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Test Database Info</title>
</head>
<body>
    <h1>Test Database Info</h1>
    <%
        // Initialize the Database Manager
        DatabaseManager databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/hotel_reservation_system", "admin", "password");

        // Get all rooms from the Database
        List<Room> rooms = databaseManager.roomDbManager.getAllRooms(); 

        // Output room information
        for (Room room : rooms) {
    %>
        <div>
            <h2>Room Information</h2>
            <p>Room Number: <%= room.getRoomNumber() %></p>
            <p>Room Type: <%= room.getRoomType() %></p>
            <p>Floor Number: <%= room.getFloorNumber() %></p>
            <p>Price per Night: <%= room.getPricePerNight() %></p>
            <p>Room Description: <%= room.getRoomDescription() %></p>
            <p>Number of Beds: <%= room.getNumberOfBeds() %></p>
        </div>
    <%
        }
    %>
</body>
</html>