
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Receipt</title>
    </head>
    <body>
        <% 
            Double amtPaid = (Double) request.getAttribute("amtPaid");
            String cardType = (String) request.getAttribute("cardType");
            String card = (String) request.getAttribute("card");
            String cardholderName = (String) request.getAttribute("cardholderName");
        %>

        <h1>Receipt</h1>
        <p>Amount Paid: $<%= amtPaid %></p>
        <p>Card Type: <%= cardType %></p>
        <p>Card Number: <%= card %></p>
        <p>Cardholder Name: <%= cardholderName %></p>

        <br />
        <a href="/HotelReservationApp/">Return Home</a>
    </body>
</html>