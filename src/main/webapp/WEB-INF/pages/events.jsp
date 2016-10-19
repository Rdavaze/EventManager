<%@ page import="fr.eventmanager.model.Event" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@include file="../partials/header.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/menu.scss">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/profile.scss">
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body>

<div class="events-page">
    <div class="menu">
        <jsp:include page="../partials/menu.jspf"/>
    </div>
    <div class="events">
        <p>
            <c:forEach var="event" items="${pageContext.request.getAttribute(\"events\")}">
                Event <c:out value="${event.getLabel()}"/> <br>
            </c:forEach>
        </p>
    </div>
</div>

</body>

</html>
