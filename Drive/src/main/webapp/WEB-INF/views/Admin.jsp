<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/Header.jsp">
    <jsp:param name="titre" value="Admin"/>
</jsp:include>

<section class="section">
<div class="container">
    <h1 class="title">🛠 Gestion des utilisateurs</h1>

    <c:forEach var="user" items="${utilisateurs}">
        <div class="box">
            <p class="title is-6">${user.login}</p>

            <form method="post" action="${pageContext.request.contextPath}/Admin">
                <input type="hidden" name="id" value="${user.id}">
                <button class="button is-danger is-small">
                    ❌ Supprimer
                </button>
            </form>
        </div>
    </c:forEach>

</div>
</section>

<jsp:include page="/WEB-INF/views/Footer.jsp"/>