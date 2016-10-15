<html>
<head>
    <%@include file="../partials/header.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.scss">
</head>

<body>

<div>
    <h1 id="app-title">Event Manager</h1>
</div>


    <div class="form" id="form-main">
        <div id="form-div">

            ${wrongCredentials}

            <ul class="tab-group">
                <li class="tab active"><a href="#login">Se connecter</a></li>
                <li class="tab "><a href="#signup">S'inscrire</a></li>
            </ul>


            <div class="tab-content">

                <div id="login">
                    <form action="" method="post">

                        <div class="row">
                            <div class="input-field col s12">
                                <label for="email-login">Adresse mail</label>
                                <input class="validate " id="email-login" type="email" required autocomplete="off">
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-field col s12">
                                <label for="password-login">Mot de passe</label>
                                <input id="password-login" type="password" class="validate"  required autocomplete="off">
                            </div>
                            <p class="forgot"><a href="#">Mot de passe oublié?</a></p>
                        </div>

                        <button type="submit" class="waves-effect waves-light btn" id="button-login" >Connexion</button>
                    </form>
                </div>

                <div id="signup">

                    <form action="" method="post">

                        <div class="top-row input-field col s12">
                            <div class="field-wrap">
                                <label>
                                    Prénom
                                </label>
                                <input type="text" class="validate" required autocomplete="off"/>
                            </div>

                            <div class="field-wrap">
                                <label>
                                    Nom
                                </label>
                                <input type="text" class="validate" required autocomplete="off"/>
                            </div>
                        </div>

                        <div class="input-field col s12">
                            <label for="email-subscribe">Adresse mail</label>
                            <input class="validate" id="email-subscribe" type="email" required autocomplete="off">
                        </div>


                        <div class="input-field col s12">
                            <label for="password-subscribe">Mot de passe</label>
                            <input id="password-subscribe" type="password" class="validate" required autocomplete="off">
                        </div>

                        <button type="submit" class="waves-effect waves-light btn" id="button-subscribe">S'inscrire</button>

                    </form>

                </div>


            </div>

        </div>
    </div>


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

    $('.tab a').on('click', function (e) {

        e.preventDefault();

        $(this).parent().addClass('active');
        $(this).parent().siblings().removeClass('active');

        target = $(this).attr('href');

        $('.tab-content > div').not(target).hide();

        $(target).fadeIn(600);

    });

</script>

</body>
</html>
