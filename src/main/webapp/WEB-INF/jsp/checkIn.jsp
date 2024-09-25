<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet">
    <title>Check In</title>
    <style>
        .error{
            color:red;
            font-style:italic;
            font-weight:bold;
        }
    </style>
<body>
<%@ include file="menuNoSec.jsp" %>

<div class=container-md>
<c:choose>
<c:when test="${checkInSuccess}">

    <h1>Thank you for Checking In</h1>
    Your flight details below. Thank you.<br>
        
    <br>
    <table border="1" class="table table-striped">
    <thead><tr>
        <td>Ticket Number</td> <td>Passenger ID</td> <td>First Name</td> <td>Last Name</td>
        <td>Flight ID</td> <td>Flight Number</td> <td>Departure</td> <td>Arrival</td>
        <td>Checked In</td>
    </tr></thead>
    <tr>
    <td>${rsv.ticketNumber}</td> <td>${rsv.passenger.passengerId}</td> <td>${rsv.passenger.firstName}</td> <td>${rsv.passenger.lastName}</td>
    <td>${rsv.flight.flightId}</td> <td>${rsv.flight.flightNumber}</td> <td>${rsv.flight.departureCity}</td> <td>${rsv.flight.arrivalCity}</td>
    <td>${rsv.checkedIn}</td>
    </tr>
    </table>

</c:when>
<c:otherwise>
    <h1>Sorry, online check in was not successful. Please contact us.</h1>
</c:otherwise>
</c:choose>
</div>

<div align="center">
<%@ include file="footer.jsp" %>
</div>

</div>
<script src="js/bootstrap.bundle.js"></script>
</body>
</html>