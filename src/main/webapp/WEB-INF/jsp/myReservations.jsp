<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/bootstrap.css" rel="stylesheet">
    <title>My Trips</title>
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
<h1>My Trips</h1>

<f:form action="passengersReservations" method="get" modelAttribute="passenger">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>Passenger:</b> </td>
    <td>
    <f:select path="passengerId" class="form-select">
        <c:forEach items="${passengers}" var="p">
                <c:choose>
                    <c:when test="${selectedPassenger.equals(p)}">
                        <f:option value="${p.passengerId}" label="ID: ${p.passengerId}, Name: ${p.firstName} ${p.lastName}" selected="true"></f:option>
                    </c:when>
                    <c:otherwise>
                        <f:option value="${p.passengerId}" label="ID: ${p.passengerId}, Name: ${p.firstName} ${p.lastName}"></f:option>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="passengerId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td colspan="3" align="center"> <input type="submit" value="Select" class="btn btn-primary"/> </td>
    </tr>
    
</table>
</f:form>

<br>
<div class=container-md>
<c:choose>
<c:when test="${not empty reservationsOfPassenger}">
    <table border="1" class="table table-striped">
        <thead><tr>
            <td>Ticket Number</td> <td>Passenger ID</td> <td>First Name</td> <td>Last Name</td>
            <td>Flight ID</td> <td>Flight Number</td> <td>Departure</td> <td>Arrival</td>
            <td>Checked In</td>
            <sec:authorize access="hasAuthority('ADMIN')"> 
            <td>Check In</td>
            </sec:authorize>
        </tr></thead>

        <c:forEach items="${reservationsOfPassenger}" var="r">
            <tr>
            <td>${r.ticketNumber}</td> <td>${r.passenger.passengerId}</td> <td>${r.passenger.firstName}</td> <td>${r.passenger.lastName}</td>
            <td>${r.flight.flightId}</td> <td>${r.flight.flightNumber}</td> <td>${r.flight.departureCity}</td> <td>${r.flight.arrivalCity}</td>
            <td>${r.checkedIn}</td>
            <sec:authorize access="hasAuthority('ADMIN')"> 
            <td> <a href="${pageContext.request.contextPath}/checkIn?ticketNumber=${r.ticketNumber}"> Check In </a> </td>
            </sec:authorize>
            </tr>
        </c:forEach>
    </table>
</c:when>
<c:otherwise>
    <c:if test="${not empty selectedPassenger}">
        No reservation found for the passenger.
    </c:if>
</c:otherwise>
</c:choose>
</div>

<sec:authorize access="hasAuthority('ADMIN')">
<div class=container-md>
<c:if test="${not empty reservations}">
    <table border="1" class="table table-striped">
        <thead><tr>
            <td>Ticket Number</td> <td>Passenger ID</td> <td>First Name</td> <td>Last Name</td>
            <td>Flight ID</td> <td>Flight Number</td> <td>Departure</td> <td>Arrival</td>
            <td>Checked In</td>
        </tr></thead>

        <c:forEach items="${reservations}" var="r">
            <tr>
            <td>${r.ticketNumber}</td> <td>${r.passenger.passengerId}</td> <td>${r.passenger.firstName}</td> <td>${r.passenger.lastName}</td>
            <td>${r.flight.flightId}</td> <td>${r.flight.flightNumber}</td> <td>${r.flight.departureCity}</td> <td>${r.flight.arrivalCity}</td>
            <td>${r.checkedIn}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
</sec:authorize>

<%@ include file="footer.jsp" %>

</div>
<script src="js/bootstrap.bundle.js"></script>
</body>
</html>