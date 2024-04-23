<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Database Test Page</title>
</head>
<body>
    <h1>Database Test Page</h1>
    <% 
        try {
            // Check if the JDBC driver is available
            if (Class.forName("com.mysql.cj.jdbc.Driver") != null) {
                out.println("<p>JDBC driver found!</p>");
            } else {
                out.println("<p>JDBC driver not found!</p>");
            }

            // Attempt to connect to the database
            java.sql.Connection connection = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_reservation_system", "admin", "password");
            if (connection != null) {
                // If connection is successful, print a success message
                out.println("<p>Database connection successful!</p>");

                // Check if required tables exist (you can add more tables as needed)
                java.sql.DatabaseMetaData metaData = connection.getMetaData();
                java.sql.ResultSet resultSet = metaData.getTables(null, null, "Room", null);
                if (resultSet.next()) {
                    out.println("<p>Room table found!</p>");
                } else {
                    out.println("<p>Room table not found!</p>");
                }

                // Close the database connection
                connection.close();
            } else {
                out.println("<p>Failed to establish database connection!</p>");
            }
        } catch (Exception e) {
            // If an exception occurs, print the error message
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    %>
</body>
</html>