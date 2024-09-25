<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/bootstrap.css" rel="stylesheet">
<title>Airlines Form</title>
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
	<h1>Airlines Form</h1>
		<f:form action="saveAirline" method="POST" modelAttribute="airline">
			<table border="1">
				<tr>
					<td>Airline ID</td><td><f:input type="text" path="airlinesId" value="${airline.getAirlinesId()}"/></td> 
				</tr>
				<tr>
					<td>Airline Name</td><td><f:input type="text" path="airlinesName" value="${airline.getAirlinesName()}"/></td>
					 <td><f:errors path="airlinesName" cssStyle="color:red;" ></f:errors></td>
				</tr>
				<tr>
					<td>Airline Code</td><td><f:input type="text" path="airlinesCode" value="${airline.getAirlinesCode()}"/></td> 
					<td><f:errors path="airlinesCode" cssStyle="color:red"></f:errors></td>
				</tr>
				
				 <tr>
	                <td colspan="2" align="center"><input type="submit" value="submit"/></td>
	            </tr>
			</table>
		</f:form>
	<p/>
	<h1>List of Airlines</h1>
	<table border="1">
		<tr>
			<th>Airline Id</th><th>Airline Name</th><th>Airline Code</th> <sec:authorize access="isAuthenticated()"><th>Action</th></sec:authorize>
		</tr>
		<tr>
			<c:forEach items="${airlines}" var="airline">
			
			<td>${airline.getAirlinesId()}</td>
			<td>${airline.getAirlinesName()}</td>
			<td>${airline.getAirlinesCode()}</td>
			
			<sec:authorize access="isAuthenticated()">
			<td>
				<a href="deleteAirline?airlinesId=${airline.getAirlinesId()}">Delete</a>
                <a href="updateAirline?airlinesId=${airline.getAirlinesId()}">Update</a>
			</td>
			</sec:authorize>
		</tr>
	</c:forEach>
	</table>
<%@ include file="footer.jsp" %>
</div>



<script src="js/bootstrap.bundle.js"></script>
</body>
</html>