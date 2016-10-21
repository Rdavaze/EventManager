<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@include file="../partials/header.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/menu.scss">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/events.scss">
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body>

<div class="events-page">
    <div class="menu">
        <jsp:include page="../partials/menu.jspf"/>
    </div>
    <div class="events">
        <h1 id="events-page-title">Évènements</h1>

        <div class="events-content">
            <div class="row">
                <div class="col s4 event">
                    <span class="event-title">Titre</span>
                    <p class="event-element">Date</p>
                    <p class="event-element">Lieu</p>
                    <p class="event-element">Organisateur</p>
                </div>
                <div class="col s4 event">
                    <span class="event-title">Titre</span>
                    <p class="event-element">Date</p>
                    <p class="event-element">Lieu</p>
                    <p class="event-element">Organisateur</p>
                </div>
            </div>
            <div class="row">
                <div class="col s4 event">
                    <span class="event-title">Titre</span>
                    <p class="event-element">Date</p>
                    <p class="event-element">Lieu</p>
                    <p class="event-element">Organisateur</p>
                </div>
                <div class="col s4 event">
                    <span class="event-title">Titre</span>
                    <p class="event-element">Date</p>
                    <p class="event-element">Lieu</p>
                    <p class="event-element">Organisateur</p>
                </div>

                <%--<p>--%>
                <%--<c:forEach var="event" items="${pageContext.request.getAttribute(\"events\")}">--%>
                <%--Event <c:out value="${event.getLabel()}"/> <br>--%>
                <%--</c:forEach>--%>
                <%--</p>--%>
            </div>

        </div>

        <ul class="pagination changePage">
            <li class="disabled"><a href="#!"><i class="material-icons chevron">chevron_left</i></a></li>
            <li class="active"><a href="#!">1</a></li>
            <li class="waves-effect"><a href="#!">2</a></li>
            <li class="waves-effect"><a href="#!">3</a></li>
            <li class="waves-effect"><a href="#!">4</a></li>
            <li class="waves-effect"><a href="#!"><i class="material-icons chevron">chevron_right</i></a></li>
        </ul>

    </div>

</body>

</html>
