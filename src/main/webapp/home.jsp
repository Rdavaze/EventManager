<html>
<head>
    <%@include file="WEB-INF/partials/header.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.scss">
</head>

<body>

<div>
    <h1 id="app-title">Event Manager</h1>
</div>


<div class="form" id="form-main">
    <div id="form-div">
        <button type="submit" class="waves-effect waves-light btn" id="button-subscribe">S'inscrire / Connexion</button>
        <button type="submit" class="waves-effect waves-light btn" id="button-events">Voir les événements</button>
    </div>
</div>

<script>
    if ($_GET('usedMail')) {
        changeTab('#signup-tab');
    }

    $('.tab a').on('click', function (e) {

        e.preventDefault();
        console.log($(this));
        $(this).parent().addClass('active');
        $(this).parent().siblings().removeClass('active');

        target = $(this).attr('href');

        $('.tab-content > div').not(target).hide();

        $(target).fadeIn(600);

    });

    function changeTab(element) {

        $(element).parent().addClass('active');
        $(element).parent().siblings().removeClass('active');

        target = $(element).attr('href');

        $('.tab-content > div').not(target).hide();

        $(target).fadeIn(600);
    }


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
