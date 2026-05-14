<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/Header.jsp">
    <jsp:param name="titre" value="Amis"/>
</jsp:include>

<section class="section">

<div class="container">

    <h1 class="title">👥 Gestion des amis</h1>

    <div class="columns">

        <!-- AMIS -->
        <div class="column">

            <h2 class="title is-4">Mes amis</h2>

            <c:forEach var="a" items="${amis}">

                <div class="box has-text-centered">

                    <p class="title is-6">
                        ${a.login}
                    </p>

                    <form method="post"
                          action="${pageContext.request.contextPath}/Ami">

                        <input type="hidden" name="id" value="${a.id}">
                        <input type="hidden" name="action" value="unfollow">

                        <button class="button is-danger is-small">
                            ❌ Supprimer
                        </button>

                    </form>
                </div>
            </c:forEach>
        </div>
        <!-- UTILISATEURS -->
        <div class="column">

            <h2 class="title is-4">Utilisateurs</h2>

            <c:forEach var="u" items="${utilisateurs}">

                <div class="box">

                    <p class="title is-6">
                        ${u.login}
                    </p>

                    <form method="post"
                          action="${pageContext.request.contextPath}/Ami">
                        <input type="hidden" name="id" value="${u.id}">
                        <input type="hidden" name="action" value="follow">
                        <button class="button is-primary is-small">
                            ➕ Ajouter
                        </button>
                    </form>
                </div>
            </c:forEach>

        </div>

    </div>

</div>
</section>
<jsp:include page="/WEB-INF/views/Footer.jsp"/>