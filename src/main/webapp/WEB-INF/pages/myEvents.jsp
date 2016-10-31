<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@include file="../partials/header.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/menu.scss">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/myEvents.scss">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/myEventModal.scss">
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body>

<div class="events-page">
    <jsp:include page="../partials/menu.jspf"/>

    <div class="events">
        <h1 id="events-page-title">Mon agenda</h1>

        <div class="events-content row">
            <c:forEach var="event" items="${pageContext.request.getAttribute(\"events\")}" varStatus="loop">
                <div class="col s4 event">
                    <span class="event-title"><c:out value="${event.getLabel()}"/></span>
                    <a href="${pageContext.request.contextPath}/events/${event.getId()}/delete"
                       class="btn waves-effect waves-light delete"><i
                            class="material-icons">delete</i></a>
                    <p class="event-element"><c:out value="${event.getDateBegin()}"/></p>
                    <p class="event-element"><c:out value="${event.getLocation()}"/></p>
                    <button data-target="${loop.index+1}" class="btn modal-trigger waves-effect waves-light edit"><i
                            class="material-icons left">mode_edit</i>editer
                    </button>
                </div>
            </c:forEach>

        </div>

        <ul class="pagination changePage">
            <li class="disabled"><a href="#!"><i class="material-icons chevron">chevron_left</i></a></li>
            <li id="page1" class="waves-effect"><a
                    href="${pageContext.request.contextPath}/events/myEvents?index=1">1</a>
            </li>
            <li id="page2" class="waves-effect"><a
                    href="${pageContext.request.contextPath}/events/myEvents?index=7">2</a>
            </li>
            <li id="page3" class="waves-effect"><a
                    href="${pageContext.request.contextPath}/events/myEvents?index=11">3</a></li>
            <li id="page4" class="waves-effect"><a
                    href="${pageContext.request.contextPath}/events/myEvents?index=15">4</a></li>
            <li class="disabled"><a href="#!"><i class="material-icons chevron">chevron_right</i></a></li>
        </ul>

    </div>

    <div id="1" class="modal">
        <jsp:include page="../partials/myEventModal.jspf">
            <jsp:param name="label" value="${pageContext.request.getAttribute(\"events\")[0].getLabel()}"/>
            <jsp:param name="location" value="${pageContext.request.getAttribute(\"events\")[0].getLocation()}"/>
            <jsp:param name="description" value="${pageContext.request.getAttribute(\"events\")[0].getDescription()}"/>
            <jsp:param name="publish" value="${pageContext.request.getAttribute(\"events\")[0].isVisible()}"/>
            <jsp:param name="date-begin" value="${pageContext.request.getAttribute(\"events\")[0].getDateBegin()}"/>
            <jsp:param name="date-end" value="${pageContext.request.getAttribute(\"events\")[0].getDateEnd()}"/>
            <jsp:param name="time-begin" value="${pageContext.request.getAttribute(\"events\")[0].getDateBegin()}"/>
            <jsp:param name="time-end" value="${pageContext.request.getAttribute(\"events\")[0].getDateEnd()}"/>
        </jsp:include>
    </div>
    <div id="2" class="modal">
        <jsp:include page="../partials/myEventModal.jspf">
            <jsp:param name="label" value="${pageContext.request.getAttribute(\"events\")[1].getLabel()}"/>
            <jsp:param name="location" value="${pageContext.request.getAttribute(\"events\")[1].getLocation()}"/>
            <jsp:param name="description" value="${pageContext.request.getAttribute(\"events\")[1].getDescription()}"/>
            <jsp:param name="publish" value="${pageContext.request.getAttribute(\"events\")[1].isVisible()}"/>
            <jsp:param name="date-begin" value="${pageContext.request.getAttribute(\"events\")[1].getDateBegin()}"/>
            <jsp:param name="date-end" value="${pageContext.request.getAttribute(\"events\")[1].getDateEnd()}"/>
            <jsp:param name="time-begin" value="${pageContext.request.getAttribute(\"events\")[1].getDateBegin()}"/>
            <jsp:param name="time-end" value="${pageContext.request.getAttribute(\"events\")[1].getDateEnd()}"/>
        </jsp:include>
    </div>
    <div id="3" class="modal">
        <jsp:include page="../partials/myEventModal.jspf">
            <jsp:param name="label" value="${pageContext.request.getAttribute(\"events\")[2].getLabel()}"/>
            <jsp:param name="location" value="${pageContext.request.getAttribute(\"events\")[2].getLocation()}"/>
            <jsp:param name="description" value="${pageContext.request.getAttribute(\"events\")[2].getDescription()}"/>
            <jsp:param name="publish" value="${pageContext.request.getAttribute(\"events\")[2].isVisible()}"/>
            <jsp:param name="date-begin" value="${pageContext.request.getAttribute(\"events\")[2].getDateBegin()}"/>
            <jsp:param name="date-end" value="${pageContext.request.getAttribute(\"events\")[2].getDateEnd()}"/>
            <jsp:param name="time-begin" value="${pageContext.request.getAttribute(\"events\")[2].getDateBegin()}"/>
            <jsp:param name="time-end" value="${pageContext.request.getAttribute(\"events\")[2].getDateEnd()}"/>
        </jsp:include>
    </div>
    <div id="4" class="modal">
        <jsp:include page="../partials/myEventModal.jspf">
            <jsp:param name="label" value="${pageContext.request.getAttribute(\"events\")[3].getLabel()}"/>
            <jsp:param name="location" value="${pageContext.request.getAttribute(\"events\")[3].getLocation()}"/>
            <jsp:param name="description" value="${pageContext.request.getAttribute(\"events\")[3].getDescription()}"/>
            <jsp:param name="publish" value="${pageContext.request.getAttribute(\"events\")[3].isVisible()}"/>
            <jsp:param name="date-begin" value="${pageContext.request.getAttribute(\"events\")[3].getDateBegin()}"/>
            <jsp:param name="date-end" value="${pageContext.request.getAttribute(\"events\")[3].getDateEnd()}"/>
            <jsp:param name="time-begin" value="${pageContext.request.getAttribute(\"events\")[3].getDateBegin()}"/>
            <jsp:param name="time-end" value="${pageContext.request.getAttribute(\"events\")[3].getDateEnd()}"/>
        </jsp:include>
    </div>

</div>


<%@include file="../partials/footer.jspf" %>

<script>
    $(document).ready(function () {
        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
        $('.modal-trigger').leanModal();

        var index = Number($_GET('index'));
        var id_page;

        switch (index) {

            case 1 :
                id_page = 1;
                break;
            case 7 :
                id_page = 2;
                break;
            case 11 :
                id_page = 3;
                break;
            case 15 :
                id_page = 4;
                break;
        }

        $('#page' + id_page).addClass('active');

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


    $('.datepicker').pickadate({
        monthsFull: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
        monthsShort: ['Jan', 'Fev', 'Mar', 'Avr', 'Mai', 'Jun', 'Jul', 'Aou', 'Sep', 'Oct', 'Nov', 'Dec'],
        weekdaysFull: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'],
        weekdaysShort: ['Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam'],
        weekdaysLetter: ['D', 'L', 'M', 'M', 'J', 'V', 'S'],
        today: 'aujourd\'hui',
        clear: 'effacer',
        formatSubmit: 'yyyy/mm/dd',
        firstDay: 1,
        labelMonthNext: 'Prochain mois',
        labelMonthPrev: 'Mois précédent',
        labelMonthSelect: 'Sélectionner un mois',
        labelYearSelect: 'Sélectionner une année',
        format: 'dd mmmm yyyy'
    });
    $('.timepicker').pickatime({
        default: 'now',
        twelvehour: false, // change to 12 hour AM/PM clock from 24 hour
        donetext: 'OK',
        autoclose: true
    });

</script>

</body>

</html>
