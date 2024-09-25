<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/bootstrap.css" rel="stylesheet">
<title>Flight Reservation</title>
</head>
<style>
	.error{
		color:red;
		font-style:italic;
		font-weight:bold;
	}
	
	.inv{
	}
</style>
<script>
function reserveWithBags(url) {
    var numBags = document.getElementById('numBags').value;
    window.location.href = url + numBags;
}
</script>

<body>
<%@ include file="menuNoSec.jsp" %>
<div align="center">
<h1>Save Passenger for Reservation</h1>

<f:form action="savePassengerForReservation" method="post" modelAttribute="passenger">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <f:input type="hidden" path="passengerId" value="${retrievedPassenger.passengerId}"/> </td>
    <td> <f:errors path="passengerId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>First Name:</b> </td>
    <td> <f:input path="firstName" value="${retrievedPassenger.firstName}"/> </td>
    <td> <f:errors path="firstName" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Last Name:</b> </td>
    <td> <f:input path="lastName" value="${retrievedPassenger.lastName}"/> </td>
    <td> <f:errors path="lastName" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Email:</b> </td>
    <td> <f:input path="email" value="${retrievedPassenger.email}"/> </td>
    <td> <f:errors path="email" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Phone:</b> </td>
    <td> <f:input path="mobileNo" value="${retrievedPassenger.mobileNo}"/> </td>
    <td> <f:errors path="mobileNo" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Gender:</b> </td>
    <td>
        <c:forEach items="${genders}" var="g">
            <c:choose>
                <c:when test="${passenger.gender.equals(g)}">
                    <f:radiobutton path="gender" value="${g}" label="${g}" checked="true" class="form-check-input"></f:radiobutton>
                </c:when>
                <c:otherwise>
                    <f:radiobutton path="gender" value="${g}" label="${g}" class="form-check-input"></f:radiobutton>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </td>
    <td> <f:errors path="gender" cssClass="error"/> </td>
    </tr>

    <tr>
    <td> <b>Date of Birth:</b> </td>  <!-- A Calendar -->
    <td> <f:input type="date" path="DOB" value="<%=java.time.LocalDate.now()%>"/> </td>
    <td> <f:errors path="DOB" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Identification Type:</b> </td>
    <td>
        <c:forEach items="${identificationTypes}" var="t">
            <c:choose>
                <c:when test="${retrievedPassenger.identificationType.equals(t)}">
                    <f:radiobutton path="identificationType" value="${t}" label="${t}" checked="true"></f:radiobutton>
                </c:when>
                <c:otherwise>
                    <f:radiobutton path="identificationType" value="${t}" label="${t}"></f:radiobutton>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </td>
    <td> <f:errors path="identificationType" cssClass="error"/> </td>
    </tr>

    <tr>
    <td colspan="3" align="center"> <input type="submit" value="Save Passenger" class="btn btn-primary"/> </td>
    </tr>

</table>
</f:form>



<br>
<div class=container-md>
<c:if test="${not empty retrievedPassenger}">
    <table border="1" class="table table-striped">
        <thead><tr>
            <td>ID</td> <td>First Name</td> <td>Last Name</td> <td>Email</td> 
            <td>Phone</td> <td>Gender</td> <td>DOB</td> <td>ID Type</td> <td>Action</td>
        </tr></thead>
        <tr>
        <td>${retrievedPassenger.passengerId}</td> <td>${retrievedPassenger.firstName}</td>
        <td>${retrievedPassenger.lastName}</td> <td>${retrievedPassenger.email}</td>
        <td>${retrievedPassenger.mobileNo}</td> <td>${retrievedPassenger.gender}</td>
        <td>${retrievedPassenger.DOB}</td> <td>${retrievedPassenger.identificationType}</td>
        <td><a href="${pageContext.request.contextPath}/updatePassengerForReservation?passengerId=${retrievedPassenger.passengerId}"> Update </a></td>
        </tr>
    </table>
    
    <f:form>
	<table>
		<tr>
		    <td> <b>How Many Checked In Bags?</b> </td>
		    <td> <input type="number" id="numBags"/> </td>
    	</tr>
	</table>
</f:form>
</c:if>
</div>

<br>
<div class=container-md>
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
</div>

<br>
<c:if test="${not empty retrievedPassenger && not empty selectedFlight}">
        <c:url var="reservationURL" value="/makeReservation">
        <c:param name="passengerId" value="${retrievedPassenger.passengerId}" />
        <c:param name="flightId" value="${selectedFlight.flightId}" />
        <c:param name="numBags" value="" />
    </c:url>
    <a href="#" onclick="reserveWithBags('${reservationURL}');" class="btn btn-primary">
        Reserve
    </a>

</c:if>

<%@ include file="footer.jsp" %>

</div>

<script src="js/bootstrap.bundle.js"></script>
</body>
</html>