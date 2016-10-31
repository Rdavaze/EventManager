<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@include file="../partials/header.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/menu.scss">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/eventsBrowse.scss">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/eventModal.scss">
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body>

<div class="events-page">
    <jsp:include page="../partials/menu.jspf"/>

    <div class="events">
        <h1 id="events-page-title">Évènements</h1>

        <div class="events-content row">
            <c:forEach var="event" items="${pageContext.request.getAttribute(\"events\")}">
                <div class="col s4 event">
                    <span class="event-title"><c:out value="${event.getLabel()}"/></span>
                    <button data-target="modal-event1" class="btn modal-trigger waves-effect waves-light btn-event"><i
                            class="material-icons">library_add</i></button>
                    <p class="event-element"><c:out value="${event.getDateBegin()}"/></p>
                    <p class="event-element"><c:out value="${event.getLocation()}"/></p>
                    <p class="event-element"><c:out value="${event.getCreator()}"/></p>
                </div>
            </c:forEach>
        </div>

        <ul class="pagination changePage">
            <li class="disabled"><a href="#!"><i class="material-icons chevron">chevron_left</i></a></li>
            <li id="page1" class="waves-effect"><a href="${pageContext.request.contextPath}/events/browse?index=1">1</a>
            </li>
            <li id="page2" class="waves-effect"><a href="${pageContext.request.contextPath}/events/browse?index=2">2</a>
            </li>
            <li id="page3" class="waves-effect"><a
                    href="${pageContext.request.contextPath}/events/browse?index=3">3</a></li>
            <li id="page4" class="waves-effect"><a
                    href="${pageContext.request.contextPath}/events/browse?index=4">4</a></li>
            <li class="disabled"><a href="#!"><i class="material-icons chevron">chevron_right</i></a></li>
        </ul>

    </div>



</div>


<%@include file="../partials/footer.jspf" %>

<script>
    $(document).ready(function () {
        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
        $('.modal-trigger').leanModal();

        var index = Number($_GET('index'));

        $('#page' + index).addClass('active');

    });

    function $_GET(param) {
        var vars = {};
        window.location.href.replace(location.hash, '').replace(
                /[?&]+([^=&]+)=?([^&]*)?/gi, // regexp
                function (m, key, value) { // callback
                    vars[key] = value !== undefined ? value : '';
                }
        );

        if (param) {
            return vars[param] ? vars[param] : null;
        }
        return vars;
    }

</script>
</body>

</html>
