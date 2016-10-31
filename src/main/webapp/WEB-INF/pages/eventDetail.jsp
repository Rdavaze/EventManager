<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../partials/header.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/menu.scss">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/eventDetail.scss">
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body>
<div class="detail-event-page">
    <jsp:include page="../partials/menu.jspf"/>

    <div class="detail-event">
        <h1 id="detail-event-title">${event.getLabel()}</h1>

        <div id="detail-event-content">
            <c:if test="${!event.isVisible()}">
                <div class="row not-visible">
                    <div class="col s12">
                        <p>L'événement n'est pas accessible au public</p>
                    </div>
                </div>
            </c:if>

            <c:if test="${event.isVisible()}">
                <div class="visible">
                    <div class="col s12">
                        <p>${event.getDescription()}</p>
                    </div>
                </div>

                <div class="visible">
                    <div class="col s12">
                        <c:if test="${event.getCreator() != null}">
                            <p>Publié par ${event.getCreator()}</p>
                        </c:if>
                        <c:if test="${event.getLocation() != null}">
                            <p>Lieu : ${event.getLocation()}</p>
                        </c:if>
                        <c:if test="${event.getDateBegin() != null}">
                            <p>Début : ${event.getDateBegin().toString()}</p>
                        </c:if>
                        <c:if test="${event.getDateEnd() != null}">
                            <p>Fin : ${event.getDateEnd().toString()}</p>
                        </c:if>
                    </div>
                </div>

                <div class="visible">
                    <div class="col s12">
                        <h4>Participants :</h4>
                        <ul>
                            <c:forEach var="attendee" items="${event.getAttendees()}">
                                <li>${attendee.toString()}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

                <a href="${pageContext.servletContext.contextPath}/events/unsubscribe/${event.getId()}"
                   class="waves-effect waves-light btn">
                    Se désinscrire
                </a>
            </c:if>
        </div>
    </div>
</div>


<%@include file="../partials/footer.jspf" %>

</body>
</html>
