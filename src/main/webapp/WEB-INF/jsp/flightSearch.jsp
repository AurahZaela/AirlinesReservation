<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/bootstrap.css" rel="stylesheet">
<title>Search Your Flight</title>

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
	<h1>Find Your Trip</h1>
	<f:form action="flightSearchDetails" method="GET" modelAttribute="flight">
		<table>
		
			<tr>
			<td>Departure City</td>
			<td>
				<f:select path="departureCity" class="form-select">
					<c:forEach items="${listOfDepCity}" var="dCity">
						<option value="${dCity}">${dCity}</option>
					</c:forEach>
				</f:select>
				</td>
			</tr>
			
			<tr>
			
			<td>Arrival City</td>
			<td>
				<f:select path="arrivalCity" class="form-select">
					<c:forEach items="${listOfArrCity}" var="aCity">
						<option value="${aCity}">${aCity}</option>
					</c:forEach>
				</f:select>
				</td>
			</tr>
		 <tr>
   				 <td colspan="3" align="center"> <input type="submit" value="Search" class="btn btn-primary"/> </td>
    	</tr>
			
		</table>
	</f:form>
	<div class=container-md>
<c:if test="${not empty listOfSearchedFlights}">
    <table border="1" class="table table-striped">
        <thead><tr>
            <td>ID</td> <td>Flight Number</td> <td>Departure</td> <td>Arrival</td>
            <td>Price</td> <td>Capacity</td> <td>Booked</td> <td>Date</td>
            <td>Time</td> <td>Airlines</td> <td>Reserve</td>
        </tr></thead>

        <c:forEach items="${listOfSearchedFlights}" var="f">
            <tr>
            <td>${f.flightId}</td> <td>${f.flightNumber}</td> <td>${f.departureCity}</td> <td>${f.arrivalCity}</td>
            <td>${f.ticketPrice}</td> <td>${f.capacity}</td> <td>${f.booked}</td> <td>${f.departureDate}</td>
            <td>${f.departureTime}</td><td>${f.operatingAirlines.airlinesName}</td>
            <td><a href="${pageContext.request.contextPath}/flightReservation?flightId=${f.flightId}"> Reserve </a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
	
<%@ include file="footer.jsp" %>
</div>
<script src="js/bootstrap.bundle.js"></script>
</body>
</html>