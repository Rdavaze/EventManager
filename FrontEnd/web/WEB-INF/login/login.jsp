<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../commons/header.jspf"%>
<body>


<div class="form">

    <ul class="tab-group">
        <li class="tab active"><a href="#login">Se connecter</a></li>
        <li class="tab "><a href="#signup">S'inscrire</a></li>
    </ul>

    <div class="tab-content">

        <div id="login">

            <form action="/" method="post">

                <div class="field-wrap">
                    <label>
                        Adresse mail
                    </label>
                    <input type="email"required autocomplete="off"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Mot de passe
                    </label>
                    <input type="password"required autocomplete="off"/>
                </div>

                <p class="forgot"><a href="#">Mot de passe oublié?</a></p>

                <button class="button button-block" style="margin-top:30px;"/>Se connecter</button>

            </form>

        </div>

        <div id="signup">

            <form action="/" method="post">

                <div class="top-row">
                    <div class="field-wrap">
                        <label>
                            Prénom
                        </label>
                        <input type="text" required autocomplete="off" />
                    </div>

                    <div class="field-wrap">
                        <label>
                            Nom
                        </label>
                        <input type="text"required autocomplete="off"/>
                    </div>
                </div>

                <div class="field-wrap">
                    <label>
                        Adresse mail
                    </label>
                    <input type="email"required autocomplete="off"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Mot de passe
                    </label>
                    <input type="password"required autocomplete="off"/>
                </div>

                <button type="submit" class="button button-block" style="margin-top: 30px"/>S'inscrire</button>

            </form>

        </div>



    </div>

</div>


<script>

    $('.form').find('input, textarea').on('keyup blur focus', function (e) {

        var $this = $(this),
                label = $this.prev('label');

        if (e.type === 'keyup') {
            if ($this.val() === '') {
                label.removeClass('active highlight');
            } else {
                label.addClass('active highlight');
            }
        } else if (e.type === 'blur') {
            if( $this.val() === '' ) {
                label.removeClass('active highlight');
            } else {
                label.removeClass('highlight');
            }
        } else if (e.type === 'focus') {

            if( $this.val() === '' ) {
                label.removeClass('highlight');
            }
            else if( $this.val() !== '' ) {
                label.addClass('highlight');
            }
        }

    });

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
