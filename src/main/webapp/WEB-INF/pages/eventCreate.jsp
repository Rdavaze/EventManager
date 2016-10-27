<html>
<head>
    <%@include file="../partials/header.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/menu.scss">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/eventCreate.scss">
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body>
<div class="create-event-page">
    <jsp:include page="../partials/menu.jspf"/>

    <div class="create-event">
        <h1 id="create-event-title">Créer un évènement</h1>

        <form id="create-event-content" method="POST" action="${pageContext.request.contextPath}/events/create">

            <div class="row">
                <div class="input-field col s6 create-event-value">
                    <input id="event-title" type="text" class="validate" name="label">
                    <label for="event-title">Titre</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6 create-event-value">
                    <input id="event-place" type="text" class="validate" name="location">
                    <label for="event-place">Lieu</label>
                </div>
                <div class="input-field col s6 create-event-value">
                    <input id="event-date" type="date" class="datepicker" name="date">
                    <label for="event-date">Date</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6 create-event-value">
                    <textarea id="event-description" maxlength="80" type="text" name="description"
                              class="validate materialize-textarea"></textarea>
                    <label for="event-description">Description</label>
                </div>
            </div>
            <div class="row">
                <div class="switch col s6 create-event-value">
                    <label>
                        <p id="publish">Publier </p> Non
                        <input type="checkbox" name="visible">
                        <span class="lever"></span>
                        Oui
                    </label>
                </div>
            </div>

            <button type="submit" class="waves-effect waves-light btn" id="button-create-event">Créer</button>


        </form>
    </div>


</div>

<script>
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

</script>

</body>
</html>
