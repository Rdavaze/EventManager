<html>
<head>
    <%@include file="../partials/header.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/menu.scss">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/profile.scss">
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body>

<div class="profile-page">
    <jsp:include page="../partials/menu.jspf"/>
    
    <div class="profile">
        <h1 id="profile-title">Mon profil</h1>

        <form id="profile-content">
            <div class="row ">
                <div class="input-field col s6 profile-value">
                    <input id="first-name" type="text" class="validate valid" value="${profileFirstName}">
                    <label for="first-name">Prénom</label>
                </div>
                <div class="input-field col s6 profile-value">
                    <input id="name" type="text" class="validate valid" value="${profileName}">
                    <label for="name">Nom</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6 profile-value">
                    <input id="mail" type="text" class="validate valid" value="${profileMail}">
                    <label for="mail">Adresse mail</label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s6 profile-value">
                    <input id="password" type="password" class="validate valid" value="${profilePassword}">
                    <label for="password">Mot de passe</label>
                </div>
            </div>

            <button type="submit" class="waves-effect waves-light btn" id="button-save-profil">Enregistrer</button>

        </form>

    </div>

</body>

</html>
