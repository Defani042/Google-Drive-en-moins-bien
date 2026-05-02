<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/Header.jsp">
	<jsp:param name="titre" value="Liste Document"/>
</jsp:include>

<h2 class="title has-text-centered">📄 Mes documents</h2>

<div class="columns is-multiline">

<c:forEach var="doc" items="${documents}">
    <div class="column is-4">
        <div class="box">

            <!-- TITRE -->
            <p class="title is-6 has-text-centered">
                ${doc.titre}
            </p>


            <!-- ACTIONS -->
            <div class="buttons is-centered mt-3">
                <a href="${pageContext.request.contextPath}/Document?id=${doc.id}"
                   class="button is-link is-light">
                    📖 Ouvrir
                </a>

               	<form method="post" action="${pageContext.request.contextPath}/SupprimerDocument">
   					<input type="hidden" name="id" value="${doc.id}">
    				<button class="button is-danger is-light">
       						 🗑 Supprimer
    				</button>
				</form>
            </div>

        </div>
    </div>
</c:forEach>

</div>

<jsp:include page="/WEB-INF/views/Footer.jsp"></jsp:include>