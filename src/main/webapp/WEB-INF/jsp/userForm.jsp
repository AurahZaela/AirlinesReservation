<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet">
    <title>User Form</title>
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
<h1>User Form</h1>

<f:form action="saveUser" method="post" modelAttribute="user">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>ID:</b> </td>
    <td> <f:input path="userId" value="${retrievedUser.userId}"/> </td>
    <td> <f:errors path="userId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Username:</b> </td>
    <td> <f:input path="username" value="${retrievedUser.username}"/> </td>
    <td> <f:errors path="username" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Password:</b> </td>
    <td> <f:input path="password"/> </td>
    <td> <f:errors path="password" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Roles:</b> </td>
    <td>
    <!-- Having model.addAttribute("user", retrievedUser) and letting user form to be filled in by path without specifying value -->
    <!-- led to error due to user.role to be filled in selection form of String - cannot convert Object (Role) to String. -->
    
    <c:forEach items="${ListofAllRoles}" var="r">
        <c:choose>
            <c:when test="${selectedRoles.contains(r)}">
                <f:checkbox path="roles" value="${r.roleId}" label="${r.roleName}" checked="true" class="form-check-input"/>
            </c:when>
            <c:otherwise>
                <f:checkbox path="roles" value="${r.roleId}" label="${r.roleName}" class="form-check-input"/>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    </td>
    <td> <f:errors path="roles" cssClass="error"/> </td>
    </tr>


    <tr>
    <td colspan="3" align="center"> <input type="submit" value="Submit" class="btn btn-primary"/> </td>
    </tr>

</table>
</f:form>

<div class=container-md>
<c:if test="${not empty users}">
    <table border="1" class="table table-striped">
        <thead><tr> <td>ID</td> <td>Username</td> <td>Roles</td> <td>Action</td> </tr></thead>

        <c:forEach items="${users}" var="u">
            <tr>
            <td>${u.userId}</td> <td>${u.username}</td>
            <td>
                <c:forEach items="${u.roles}" var="r">
                    ${r.roleName}
                </c:forEach>
            </td>
            <td> <a href="${pageContext.request.contextPath}/updateUser?userId=${u.userId}"> Update </a> | <a href="${pageContext.request.contextPath}/deleteUser?userId=${u.userId}"> Delete </a> </td>
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