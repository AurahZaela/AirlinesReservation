<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- tag library provided by JSP JSTL -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> <!-- tag library provided by spring -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> <!-- tag library provided by spring security -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/bootstrap.css" rel="stylesheet">
<title>Reservation Confirmation</title>
    <style>
        .error{
            color:red;
            font-style:italic;
            font-weight:bold;
        }
    </style>
</head>

<body>
<%@ include file="menuNoSec.jsp" %>

<div align="center">

<c:choose>
<c:when test="${reservationSuccess}">

    <h1>Reservation Confirmation</h1>
    Your Reservation details below. Thank you.<br>
    
    <div class=container-md>
    <br><img src="icons/person.svg" alt="passenger" width="24" height="24"> <b>Passenger Detail</b> <br>
    <c:if test="${not empty retrievedPassenger}">
        <table border="1" class="table table-striped">
            <thead><tr>
                <td>ID</td> <td>First Name</td> <td>Last Name</td> <td>Email</td> 
                <td>Phone</td> <td>Gender</td> <td>DOB</td> <td>ID Type</td>
            </tr></thead>
            <tr>
            <td>${retrievedPassenger.passengerId}</td> <td>${retrievedPassenger.firstName}</td>
            <td>${retrievedPassenger.lastName}</td> <td>${retrievedPassenger.email}</td>
            <td>${retrievedPassenger.mobileNo}</td> <td>${retrievedPassenger.gender}</td>
            <td>${retrievedPassenger.DOB}</td> <td>${retrievedPassenger.identificationType}</td>
            </tr>
        </table>
    </c:if>
    
    <br><img src="icons/airplane.svg" alt="flight" width="24" height="24"> <b>Your Flight</b> <br>
    <table border="1" class="table table-striped">
        <thead><tr>
            <td>ID</td> <td>Flight Number</td> <td>Departure</td> <td>Arrival</td>
            <td>Price</td> <td>Capacity</td> <td>Booked</td> <td>Date</td>
            <td>Time</td> <td>Airlines</td>
        </tr></thead>
        <tr>
        <td>${selectedFlight.flightId}</td> <td>${selectedFlight.flightNumber}</td>
        <td>${selectedFlight.departureCity}</td> <td>${selectedFlight.arrivalCity}</td>
        <td>${selectedFlight.ticketPrice}</td> <td>${selectedFlight.capacity}</td>
        <td>${selectedFlight.booked}</td> <td>${selectedFlight.departureDate}</td>
        <td>${selectedFlight.departureTime}</td> <td>${selectedFlight.operatingAirlines.airlinesName}</td>
        </tr>
    </table>
    
    <br><img src="icons/ticket.svg" alt="ticket" width="24" height="24"> <b>Your Ticket</b> <br>
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
    </div>

</c:when>
<c:otherwise>
    <h1>Sorry, Reservation was not successful. It may be full</h1>
</c:otherwise>
</c:choose>

<%@ include file="footer.jsp" %>

</div>
<script src="js/bootstrap.bundle.js"></script>
</body>
</html>