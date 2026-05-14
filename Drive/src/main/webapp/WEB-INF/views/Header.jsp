<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${param.titre}</title>

    <!-- Bulma -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bulma.css">

    <!-- Quill -->
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">

    <!-- Style perso -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<!-- NAVBAR -->
<nav class="navbar is-dark" role="navigation">

    <!-- LEFT / LOGO -->
    <div class="navbar-brand">

        <a class="navbar-item" href="${pageContext.request.contextPath}/Accueil">
            <img src="${pageContext.request.contextPath}/img/home.svg"
                 alt="Accueil"
                 style="max-height:40px;">
        </a>

    </div>

    <!-- MENU CENTER -->
    <div class="navbar-menu is-active">

        <div class="navbar-start">

            <!-- Liste documents -->
            <a class="navbar-item"
               href="${pageContext.request.contextPath}/ListeDocument">

                <img src="${pageContext.request.contextPath}/img/liste.svg"
                     alt="Documents"
                     style="max-height:40px;">
            </a>

            <!-- Créer document -->
            <a class="navbar-item"
               href="${pageContext.request.contextPath}/CreerDocument">

                <img src="${pageContext.request.contextPath}/img/add.svg"
                     alt="Créer"
                     style="max-height:40px;">
            </a>

            <!-- Profil -->
            <a class="navbar-item"
               href="${pageContext.request.contextPath}/Profil">

                <img src="${pageContext.request.contextPath}/img/user.svg"
                     alt="Profil"
                     style="max-height:40px;">
            </a>
            
            <!-- ami -->
            <a class="navbar-item"
               href="${pageContext.request.contextPath}/Ami">

                <img src="${pageContext.request.contextPath}/img/friend.svg"
                     alt="Profil"
                     style="max-height:40px;">
            </a>

        </div>

        <!-- RIGHT -->
        <div class="navbar-end">

            <!-- Déconnexion -->
            <a class="navbar-item"
               href="${pageContext.request.contextPath}/Deconnexion">

                <img src="${pageContext.request.contextPath}/img/logout.svg"
                     alt="Déconnexion"
                     style="max-height:40px;">
            </a>

        </div>

    </div>

</nav>

