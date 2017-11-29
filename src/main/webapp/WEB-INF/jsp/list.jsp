<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Scouts</title>
</head>
<body>

<h1>Scouts</h1>

<c:if test="${not empty scouts}">
    <ul id="scoutList">
        <c:forEach var="scout" items="${scouts}">
            <li>${scout.name} - ${scout.points}</li>
        </c:forEach>
    </ul>
</c:if>
<c:if test="${empty scouts}">
    <span id="noneAvailableMessage">There are currently no Scouts. Let's fix some warnings!</span>
</c:if>

</body>
</html>