<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Form</title>
<meta charset="UTF-8">
<link href="css/bootstrap.css" rel="stylesheet">
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
<h1>Flight Form</h1>

<f:form action="saveFlight" method="post" modelAttribute="flight">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>ID:</b> </td>
    <td> <f:input path="flightId" value="${retrievedFlight.flightId}"/> </td>
    <td> <f:errors path="flightId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Flight Number:</b> </td>
    <td> <f:input path="flightNumber" value="${retrievedFlight.flightNumber}"/> </td>
    <td> <f:errors path="flightNumber" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Departure City:</b> </td>
    <td><f:input type="text" path="departureCity" value="${retrievedFlight.departureCity}"/></td>
    <td> <f:errors path="departureCity" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Arrival City:</b> </td>
 	<td><f:input type="text" path="arrivalCity" value="${retrievedFlight.arrivalCity}"/></td>
    <td> <f:errors path="arrivalCity" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Ticket Price:</b> </td>
    <td> <f:input path="ticketPrice" value="${retrievedFlight.ticketPrice}"/> </td>
    <td> <f:errors path="ticketPrice" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Capacity:</b> </td>
    <td> <f:input path="capacity" value="${retrievedFlight.capacity}"/> </td>
    <td> <f:errors path="capacity" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Booked Count:</b> </td>
    <td> <f:input path="booked" value="${retrievedFlight.booked}"/> </td>
    <td> <f:errors path="booked" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Departure Date:</b> </td>  <!-- A Calendar -->
    <td> <f:input type="date" path="departureDate" value="<%=java.time.LocalDate.now()%>"/> </td>
    <td> <f:errors path="departureDate" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Departure Time:</b> </td>
    <td> <f:input type="time" path="departureTime" value="<%=java.time.LocalTime.now()%>"/> </td>
    <td> <f:errors path="departureTime" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Operating Airlines:</b> </td>
    <td>
    <f:select path="operatingAirlines" class="form-select">
        <c:forEach items="${airlinesList}" var="a">
                <c:choose>
                    <c:when test="${selectedAirlines.equals(a)}">
                        <f:option value="${a.airlinesId}" label="${a.airlinesName}" selected="true"></f:option>
                    </c:when>
                    <c:otherwise>
                        <f:option value="${a.airlinesId}" label="${a.airlinesName}"></f:option>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="operatingAirlines" cssClass="error"/> </td>
    </tr>

    <tr>
    <td colspan="3" align="center"> <input type="submit" value="Submit" class="btn btn-primary"/> </td>
    </tr>

</table>
</f:form>

<div class=container-md>
<c:if test="${not empty flights}">
    <table border="1" class="table table-striped">
        <thead><tr>
            <td>ID</td> <td>Flight Number</td> <td>Departure</td> <td>Arrival</td>
            <td>Price</td> <td>Capacity</td> <td>Booked</td> <td>Date</td>
            <td>Time</td> <td>Airlines</td>
            <sec:authorize access="hasAnyRole('ADMIN')"><td>Action</td></sec:authorize>
        </tr></thead>

        <c:forEach items="${flights}" var="f">
            <tr>
            <td>${f.flightId}</td> <td>${f.flightNumber}</td> <td>${f.departureCity}</td> <td>${f.arrivalCity}</td>
            <td>${f.ticketPrice}</td> <td>${f.capacity}</td> <td>${f.booked}</td> <td>${f.departureDate}</td>
            <td>${f.departureTime}</td> <td>${f.operatingAirlines.airlinesName}</td>
            <sec:authorize access="hasAnyRole('ADMIN')">
            <td> <a href="${pageContext.request.contextPath}/updateFlight?flightId=${f.flightId}"> Update </a> | <a href="${pageContext.request.contextPath}/deleteFlight?flightId=${f.flightId}"> Delete </a> </td>
            </sec:authorize>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>
<%@ include file="footer.jsp" %>
<script src="js/bootstrap.bundle.js"></script>
</body>
</html>