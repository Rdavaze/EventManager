<html>
<head>
    <%@include file="../partials/header.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forgot.scss">
</head>
<body>

<div>
    <h1 id="app-title">Event Manager</h1>
</div>


<div class="form" id="form-main">
    <div id="form-div">
        <h1 id="forgot-title">Récupération de mot de passe</h1>
        ${mailNotExist}

        <form action="${pageContext.request.contextPath}/login/changePwd" method="post">

            <div class="row">
                <div class="input-field col s12 ">
                    <label for="email-forgot">Adresse mail</label>
                    <input class="validate mail-forgot" id="email-forgot" type="email" name="forgot-email" required
                           autocomplete="off">
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <label for="password-forgot">Mot de passe</label>
                    <input id="password-forgot" type="password" class="validate" name="forgot-password" required
                           autocomplete="off">
                </div>
            </div>

            <button type="submit" class="waves-effect waves-light btn" id="button-forgot">Enregistrer</button>
    </div>
</div>


<%@include file="../partials/footer.jspf" %>

</body>
</html>
