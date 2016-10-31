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
            <c:forEach var="event" items="${pageContext.request.getAttribute(\"events\")}" varStatus="loop">
                <div class="col s4 event">
                    <span class="event-title"><c:out value="${event.getLabel()}"/></span>
                    <button data-target="${loop.index+1}" class="btn modal-trigger waves-effect waves-light btn-event">
                        <i
                                class="material-icons">library_add</i></button>
                    <p class="event-element"><c:out value="${event.parseDateBegin()}"/></p>
                    <p class="event-element"><c:out value="${event.getLocation()}"/></p>
                    <p class="event-element"><c:out value="${event.getCreator().getFullname()}"/></p>
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


<div id="1" class="modal">
    <jsp:include page="../partials/eventModal.jspf">
        <jsp:param name="id" value="${pageContext.request.getAttribute(\"events\")[0].getId()}"/>
        <jsp:param name="label" value="${pageContext.request.getAttribute(\"events\")[0].getLabel()}"/>
        <jsp:param name="location" value="${pageContext.request.getAttribute(\"events\")[0].getLocation()}"/>
        <jsp:param name="description" value="${pageContext.request.getAttribute(\"events\")[0].getDescription()}"/>
        <jsp:param name="datebegin" value="${pageContext.request.getAttribute(\"events\")[0].parseDateBegin()}"/>
        <jsp:param name="dateend" value="${pageContext.request.getAttribute(\"events\")[0].parseDateEnd()}"/>
        <jsp:param name="timebegin" value="${pageContext.request.getAttribute(\"events\")[0].parseTimeBegin()}"/>
        <jsp:param name="timeend" value="${pageContext.request.getAttribute(\"events\")[0].parseTimeEnd()}"/>
        <jsp:param name="creator"
                   value="${pageContext.request.getAttribute(\"events\")[0].getCreator().getFullname()}"/>

    </jsp:include>
</div>
<div id="2" class="modal">
    <jsp:include page="../partials/eventModal.jspf">
        <jsp:param name="id" value="${pageContext.request.getAttribute(\"events\")[1].getId()}"/>
        <jsp:param name="label" value="${pageContext.request.getAttribute(\"events\")[1].getLabel()}"/>
        <jsp:param name="location" value="${pageContext.request.getAttribute(\"events\")[1].getLocation()}"/>
        <jsp:param name="description" value="${pageContext.request.getAttribute(\"events\")[1].getDescription()}"/>
        <jsp:param name="datebegin" value="${pageContext.request.getAttribute(\"events\")[1].parseDateBegin()}"/>
        <jsp:param name="dateend" value="${pageContext.request.getAttribute(\"events\")[1].parseDateEnd()}"/>
        <jsp:param name="timebegin" value="${pageContext.request.getAttribute(\"events\")[1].parseTimeBegin()}"/>
        <jsp:param name="timeend" value="${pageContext.request.getAttribute(\"events\")[1].parseTimeEnd()}"/>
        <jsp:param name="creator"
                   value="${pageContext.request.getAttribute(\"events\")[1].getCreator().getFullname()}"/>

    </jsp:include>
</div>
<div id="3" class="modal">
    <jsp:include page="../partials/eventModal.jspf">
        <jsp:param name="id" value="${pageContext.request.getAttribute(\"events\")[2].getId()}"/>
        <jsp:param name="label" value="${pageContext.request.getAttribute(\"events\")[2].getLabel()}"/>
        <jsp:param name="location" value="${pageContext.request.getAttribute(\"events\")[2].getLocation()}"/>
        <jsp:param name="description" value="${pageContext.request.getAttribute(\"events\")[2].getDescription()}"/>
        <jsp:param name="datebegin" value="${pageContext.request.getAttribute(\"events\")[2].parseDateBegin()}"/>
        <jsp:param name="dateend" value="${pageContext.request.getAttribute(\"events\")[2].parseDateEnd()}"/>
        <jsp:param name="timebegin" value="${pageContext.request.getAttribute(\"events\")[2].parseTimeBegin()}"/>
        <jsp:param name="timeend" value="${pageContext.request.getAttribute(\"events\")[2].parseTimeEnd()}"/>
        <jsp:param name="creator"
                   value="${pageContext.request.getAttribute(\"events\")[2].getCreator().getFullname()}"/>

    </jsp:include>
</div>
<div id="4" class="modal">
    <jsp:include page="../partials/eventModal.jspf">
        <jsp:param name="id" value="${pageContext.request.getAttribute(\"events\")[3].getId()}"/>
        <jsp:param name="label" value="${pageContext.request.getAttribute(\"events\")[3].getLabel()}"/>
        <jsp:param name="location" value="${pageContext.request.getAttribute(\"events\")[3].getLocation()}"/>
        <jsp:param name="description" value="${pageContext.request.getAttribute(\"events\")[3].getDescription()}"/>
        <jsp:param name="datebegin" value="${pageContext.request.getAttribute(\"events\")[3].parseDateBegin()}"/>
        <jsp:param name="dateend" value="${pageContext.request.getAttribute(\"events\")[3].parseDateEnd()}"/>
        <jsp:param name="timebegin" value="${pageContext.request.getAttribute(\"events\")[3].parseTimeBegin()}"/>
        <jsp:param name="timeend" value="${pageContext.request.getAttribute(\"events\")[3].parseTimeEnd()}"/>
        <jsp:param name="creator"
                   value="${pageContext.request.getAttribute(\"events\")[3].getCreator().getFullname()}"/>

    </jsp:include>
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
