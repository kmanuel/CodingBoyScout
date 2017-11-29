<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Scouts</title>
</head>
<body>
<div class="container">

    <h1>Scouts</h1>

    <c:if test="${not empty scouts}">
        <ul id="scoutList" class="list-group">
            <c:forEach var="scout" items="${scouts}">
                <li class="list-group-item">
                        ${scout.name} - ${scout.points}
                </li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty scouts}">
        <div class="panel panel-warning">
            <span id="noneAvailableMessage">There are currently no Scouts. Let's fix some warnings!</span>
        </div>
    </c:if>
</div>

<script src="/webjars/jquery/3.2.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>