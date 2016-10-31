<html>
<head>
    <%@include file="../partials/header.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.scss">
</head>

<body>

<div>
    <h1 id="app-title">Event Manager</h1>
</div>


<div class="form content" id="form-main">
    <div id="form-div">

        <ul class="tab-group">
            <li class="tab active"><a href="#login">Se connecter</a></li>
            <li class="tab "><a href="#signup" id="signup-tab">S'inscrire</a></li>
        </ul>


        <div class="tab-content">

            <div id="login">
                ${wrongCredentialsLog}
                <form action="${pageContext.request.contextPath}/login/signin" method="post">

                    <div class="row">
                        <div class="input-field col s12">
                            <label for="email-login">Adresse mail</label>
                            <input class="validate " id="email-login" value="${email ? email : ""}" type="email"
                                   name="login-email" required autocomplete="on">
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s12">
                            <label for="password-login">Mot de passe</label>
                            <input id="password-login" type="password" class="validate" name="login-password" required
                                   autocomplete="off">
                        </div>
                    </div>

                    <button type="submit" class="waves-effect waves-light btn" id="button-login">Connexion</button>

                    <p class="forgot"><a href="${pageContext.request.contextPath}/login/forgot">Mot de passe
                        oublié?</a></p>

                </form>
            </div>

            <div id="signup">
                ${wrongCredentialsSub}
                <form action="${pageContext.request.contextPath}/login/signup" method="post">

                    <div class="top-row input-field col s12">
                        <div class="field-wrap">
                            <label>
                                Prénom
                            </label>
                            <input type="text" class="validate" name="subscribe-firstname" required autocomplete="off"/>
                        </div>

                        <div class="field-wrap">
                            <label>
                                Nom
                            </label>
                            <input type="text" class="validate" name="subscribe-lastname" required autocomplete="off"/>
                        </div>
                    </div>

                    <div class="input-field col s12">
                        <label for="email-subscribe">Adresse mail</label>
                        <input class="validate" id="email-subscribe" type="email" name="subscribe-email" required
                               autocomplete="off">
                    </div>


                    <div class="input-field col s12">
                        <label for="password-subscribe">Mot de passe</label>
                        <input id="password-subscribe" type="password" class="validate" name="subscribe-password"
                               required autocomplete="off">
                    </div>

                    <button type="submit" class="waves-effect waves-light btn" id="button-subscribe">S'inscrire</button>

                </form>

            </div>

        </div>

    </div>
</div>

<div class="content">
    <a href="${pageContext.request.contextPath}/events/browse" class="waves-effect waves-light btn" id="button-events">Voir
        les événements</a>
</div>


<%@include file="../partials/footer.jspf" %>

<script>

    /*$('.form').find('input, textarea').on('keyup blur focus', function (e) {

     var $this = $(this),
     label = $this.prev('label');

     if (e.type === 'keyup') {
     if ($this.val() === '') {
     label.removeClass('active highlight');
     } else {
     label.addClass('active highlight');
     }
     } else if (e.type === 'blur') {
     if ($this.val() === '') {
     label.removeClass('active highlight');
     } else {
     label.removeClass('highlight');
     }
     } else if (e.type === 'focus') {

     if ($this.val() === '') {
     label.removeClass('highlight');
     }
     else if ($this.val() !== '') {
     label.addClass('highlight');
     }
     }

     });*/

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
