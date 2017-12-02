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

    <div class="row">
        <h1 class="text-center">Scouts</h1>
    </div>

    <div class="row">
        <div class="col-xs-8 col-xs-offset-2">
            <c:if test="${not empty scouts}">
                <ul id="scoutList" class="list-group col-xs-12">
                    <c:forEach var="scout" items="${scouts}" varStatus="loop">
                        <li class="list-group-item col-xs-12">
                            <div class="col-xs-12">
                                <span class="col-xs-1">
                                    <c:if test="${loop.index == 0}">
                                        <span class="glyphicon glyphicon-star"></span>
                                    </c:if>
                                    <c:if test="${loop.index == 1 or loop.index == 2}">
                                        <span class="glyphicon glyphicon-star-empty"></span>
                                    </c:if>
                                </span>
                                <span class="col-xs-8"><strong>${scout.name}</strong></span>
                                <span class="col-xs-1 col-xs-offset-1">${scout.points}</span>
                            </div>
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
    </div>
</div>

<script src="/webjars/jquery/3.2.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>