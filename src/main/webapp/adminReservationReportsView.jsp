<%@ page import="com.hotelreservationapp.models.Database.Tables.Room" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hotelreservationapp.models.Database.Prepared.AdminReservationReport" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- <link rel="stylesheet" href="styles/UserStyles.css" />
        <link rel="stylesheet" href="styles/adminStyles.css" /> -->
        <title>Reservations</title>
        <style>
            td, th {
                padding: 7px;
                /* text-align: center; */
            }

            @media print{
                .search{
                    display: none;
                }
            }
        </style>
    </head>
    <body>
        <div class="search">
            <div><b>Filter Results By</b>:</div>
            <label>Room #</label>
            <input type="text" id="roomNumber" name="roomNumber" placeholder="Room Number" value="">

            <label>Check-In</label>
            <input type="date" id="checkIn" name="checkIn"  value="">

            <label>Check-Out</label>
            <input type="date" id="checkOut" name="checkOut"  value="">

            <label>User Email</label>
            <input type="email" id="userEmail" name="userEmail" placeholder="User Email"  value="">

            <label>Status</label>
            <select id="status">
                <option value="">All</option>
                <option value="pending">Pending</option>
                <option value="confirmed">Confirmed</option>
                <option value="cancelled">Cancelled</option>
            </select>

            <button onclick="applyFilter()">Apply Filter</button>
            <button onclick="printReport()">Print</button>
            <span id="showingResults"></span>
            <br>
        </div>
        <div class="container">		
            <div id="filterSummary"></div>
            <table style="width: 100%; border-collapse: collapse;" border="1" >
                <thead>
                    <tr>
                        <td colspan="1">
                            <img class="logo" style="width: 75px; height: auto;" src="images/logo.png" alt="Logo">
                        </td>
                        <td colspan="3"><h1>Reservations Report</h1></td>
                    </tr>
                </thead>
                <tbody>
                    <%
                    int rowID = 1;
                    boolean flag = false;
                    List<AdminReservationReport> reservations = (ArrayList<AdminReservationReport>)request.getSession().getAttribute("reservations");
                        for (AdminReservationReport report : reservations) {
                        String rowClass = rowID++ + "_row";
                    %>
                    <tr class="<%= rowClass %>" <%= flag ? "style='border-top: 2px solid black;'" : "style=' border-top: 2px solid black;'" %>>
                        <td style="width: 10%;"><b>User Information</b></td>
                        <td style="width: 30%;"><b>Username:</b> <%= report.getUser().getUsername() %></td>
                        <td style="width: 30%;"><b>Email:</b> <%= report.getUser().getEmail() %></td>
                        <td style="width: 30%;"><b>Phone:</b> <%= report.getUser().getPhoneNumber() %></td>
                    </tr>
                    <tr class="<%= rowClass %>" <%= flag ? "style=''" : "style=''" %>>
                        <td ><b>Reservation ID:</b> <%= report.getReservation().getReservationId() %></td>
                        <td><b>Check-In:</b> <%= report.getReservation().getCheckInDate() %></td>
                        <td><b>Check-Out:</b> <%= report.getReservation().getCheckOutDate() %></td>
                        <td><b>Total Visit Days:</b> <%= report.getNumDaysOfVisit() %></td>
                        <!-- <td>Status: <%= report.getReservation().getReservationStatus() %></td> -->
                        <!-- <td>Total Price: <%= report.getReservation().getTotalPrice() %></td> -->
                    </tr>
                        <% 
                        int i = 0;
                        for (Room room : report.getRooms()) {
                        %>
                        <tr class="<%= rowClass %> room-detail" <%= flag ? "style=''" : "style=''" %>>
                            <%
                            if(i++ == 0){
                            %>    
                            <td rowspan="<%= report.getRooms().size()%>" ><b>Room Details</b></td>
                            <%
                            }
                            %>
                            <td><b>Floor:</b> <%= room.getFloorNumber() %> Room: <%= room.getRoomNumber() %></td>
                            <td><b>Type:</b> <%= room.getRoomType() %></td>
                            <td><b>Price/Night:</b> <%= room.getPricePerNight() %></td>
                        </tr>
                        <%
                        }
                        %>
                        <tr class="<%= rowClass %>" <%= flag ? "style=''" : "style=''" %>>
                            <td colspan="2" style="text-align: right; font-weight: bold;"><div>Status: <%= report.getReservation().getReservationStatus() %></div> <input hidden type="text" value="<%= report.getRoomNumbersForSearch() %>"/> </td>
                            <td colspan="2" style="text-align: right; font-weight: bold;">Total Price: <%= report.getReservation().getTotalPrice() %></td>
                        </tr>
                    <%
                    flag = !flag;
                    }
                    %>
            </table>
        </div>

        <script>
            var minRow = 1;
            var maxRow = "<%= rowID - 1 %>"
            
            function applyFilter(){
                let filterRoom = document.getElementById("roomNumber");
                let filterCheckIn = document.getElementById("checkIn");
                let filterCheckOut = document.getElementById("checkOut");
                let filterUserEmail = document.getElementById("userEmail");
                let filterStatus = document.getElementById("status");

                let showingResults = document.getElementById("showingResults");
                let showingCount = 0;
                let showingFlag = false;
                for(let i = 1; i <= parseInt(maxRow); i++){
                    showingFlag = false;
                    let shouldHide1 = false;
                    let shouldHide2 = false;
                    let shouldHide3 = false;
                    let shouldHide4 = false;
                    let shouldHide5 = false;
                    let row = document.getElementsByClassName(i + "_row");
                    //last element in row array
                    let last = row[row.length - 1];
                    let roomNumbers = last.getElementsByTagName("input")[0].value;
                    let splits = roomNumbers.split(",");
                    //is there at least one room number that matches the filter
                    let roomMatch = false;
                    for(let j = 0; j < splits.length; j++){
                        if(splits[j].includes(filterRoom.value)){
                            roomMatch = true;
                            break;
                        }
                    }

                    let checkInDate = row[1].getElementsByTagName("td")[1].innerText.split(":")[1].trim();
                    let checkOutDate = row[1].getElementsByTagName("td")[2].innerText.split(":")[1].trim();
                    let userEmail = row[0].getElementsByTagName("td")[2].innerText.split(":")[1].trim();
                    let status = last.getElementsByTagName("div")[0].innerText.split(":")[1].trim();

                    //hide the row if none of the filters match
                    if(filterRoom.value != "" && !roomMatch){
                        shouldHide1 = true;
                    }
                    

                    //check in date to date object
                    let checkInDateObj = new Date(checkInDate + "T00:00:00");
                    //check out date to date object
                    let checkOutDateObj = new Date(checkOutDate + "T00:00:00");
                    //filter check in date to date object
                    let filterCheckInDateObj = new Date(filterCheckIn.value+ "T00:00:00");
                    //filter check out date to date object
                    let filterCheckOutDateObj = new Date(filterCheckOut.value+ "T00:00:00");


                    //is check in date eqal to filter check in date
                    let checkInDateEqual = checkInDateObj.getTime() == filterCheckInDateObj.getTime();
                    //is check out date eqal to filter check out date
                    let checkOutDateEqual = checkOutDateObj.getTime() == filterCheckOutDateObj.getTime();

                    if(filterCheckIn.value != "" && !checkInDateEqual){
                        shouldHide2 = true;
                    }

                    if(filterCheckOut.value != "" && !checkOutDateEqual){
                        shouldHide3 = true;
                    }

                    if(filterUserEmail.value != "" && userEmail != filterUserEmail.value){
                        shouldHide4 = true;
                    }

                    if(filterStatus.value != "" && status != filterStatus.value){
                        shouldHide5 = true;
                    }

                    document.getElementById("filterSummary").innerText = "Filter Summary: Room: [" + filterRoom.value + "] Check-In: [" + filterCheckIn.value + "] Check-Out: [" + filterCheckOut.value + "] User Email: [" + filterUserEmail.value + "] Status: [" + filterStatus.value + "]";


                    if(shouldHide1 || shouldHide2 || shouldHide3 || shouldHide4 || shouldHide5){
                        for(let j = 0; j < row.length; j++){
                            row[j].style.display = "none";
                        }
                    } else {
                        for(let j = 0; j < row.length; j++){
                            row[j].style.display = "";
                            showingFlag = true
                        }
                    }
                    if(showingFlag){
                        showingCount++;
                    }
                }
                showingResults.innerText = "Showing " + showingCount + " results";
                if(showingCount == 0){
                    showingResults.innerText = "No results found";
                }
                else if(showingCount == parseInt(maxRow)){
                    showingResults.innerText = "Showing all " + showingCount + " results";
                }
            }

            function printReport(){
                window.print();
            }
        </script>
    </body>
</html>