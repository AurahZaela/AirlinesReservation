<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet">
    <title>Home</title>
</head>

<body>
<%@ include file="menuNoSec.jsp" %>

<div align="center">

<h1>Home</h1>
<sec:authorize access="!isAuthenticated()">
<a href="login">Log In</a>
<br>
<a href="register">Register</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<h5>Welcome, ${username}.</h5>
<br>With Role:  <sec:authentication property="principal.authorities"/> --%>
</sec:authorize>

<%@ include file="footer.jsp" %>

</div>
<script src="js/bootstrap.bundle.js"></script>
</body>

</html>