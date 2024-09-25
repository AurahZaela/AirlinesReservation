<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet">
    <title>Reservation Form</title>
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
<h1>Reservation Form</h1>

<f:form action="saveReservation" method="post" modelAttribute="reservation">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>Ticket Number:</b> </td>
    <td> <f:input path="ticketNumber" value="${retrievedReservation.ticketNumber}"/> </td>
    <td> <f:errors path="ticketNumber" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Passenger:</b> </td>
    <td>
    <f:select path="passenger" class="form-select">
        <c:forEach items="${passengers}" var="p">
                <c:choose>
                    <c:when test="${selectedPassenger.equals(p)}">
                        <f:option value="${p.passengerId}" label="${p.passengerId} ${p.firstName} ${p.lastName}" selected="true"></f:option>
                    </c:when>
                    <c:otherwise>
                        <f:option value="${p.passengerId}" label="${p.passengerId} ${p.firstName} ${p.lastName}"></f:option>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="passenger" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Flight:</b> </td>
    <td>
    <f:select path="flight" class="form-select">
        <c:forEach items="${flights}" var="f">
                <c:choose>
                    <c:when test="${selectedFlight.equals(f)}">
                        <f:option value="${f.flightId}" label="${f.flightNumber} ${f.departureCity} to ${f.arrivalCity}" selected="true"></f:option>
                    </c:when>
                    <c:otherwise>
                        <f:option value="${f.flightId}" label="${f.flightNumber} ${f.departureCity} to ${f.arrivalCity}"></f:option>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="flight" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Checked Bags:</b> </td>
    <td> <f:input path="checkedBags" value="${retrievedReservation.checkedBags}"/> </td>
    <td> <f:errors path="checkedBags" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td colspan="3" align="center"> <input type="submit" value="Submit" class="btn btn-primary"/> </td>
    </tr>

</table>
</f:form>

<div class=container-md>
<c:if test="${not empty reservations}">
    <table border="1" class="table table-striped">
        <thead><tr>
            <td>Ticket Number</td> <td>Passenger ID</td> <td>First Name</td> <td>Last Name</td>
            <td>Flight ID</td> <td>Flight Number</td> <td>Departure</td> <td>Arrival</td>
            <td>Checked In</td> <td>Issued Date</td>
            <td>Action</td>
        </tr></thead>

        <c:forEach items="${reservations}" var="r">
            <tr>
            <td>${r.ticketNumber}</td> <td>${r.passenger.passengerId}</td> <td>${r.passenger.firstName}</td> <td>${r.passenger.lastName}</td>
            <td>${r.flight.flightId}</td> <td>${r.flight.flightNumber}</td> <td>${r.flight.departureCity}</td> <td>${r.flight.arrivalCity}</td>
            <td>${r.checkedIn}</td>
            <td> <a href="${pageContext.request.contextPath}/updateReservation?ticketNumber=${r.ticketNumber}"> Update </a> | <a href="${pageContext.request.contextPath}/deleteReservation?ticketNumber=${r.ticketNumber}"> Delete </a> </td>
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