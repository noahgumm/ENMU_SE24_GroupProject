<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Room</title>
</head>
<body>
    <h1>Add New Room</h1>
    <form action="ManageRooms" method="post">
		<input type="hidden" id="action" name="action" value="createRoom">
	
        <label for="roomNumber">Room Number:</label>
        <input type="text" id="roomNumber" name="roomNumber" required><br>
        
        <label for="roomType">Room Type:</label>
        <input type="text" id="roomType" name="roomType" required><br>
        
        <label for="floorNumber">Floor Number:</label>
        <input type="number" id="floorNumber" name="floorNumber" required><br>
        
        <label for="pricePerNight">Price per Night:</label>
        <input type="number" id="pricePerNight" name="pricePerNight" step="0.01" required><br>
        
        <label for="roomDescription">Room Description:</label><br>
        <textarea id="roomDescription" name="roomDescription" required></textarea><br>
        
        <label for="numberOfBeds">Number of Beds:</label>
        <input type="number" id="numberOfBeds" name="numberOfBeds" required><br>
        
        <input type="submit" value="Add Room">
    </form>
</body>
</html>