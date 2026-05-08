<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/Header.jsp">
	<jsp:param name="titre" value="Droits"/>
</jsp:include>
	<section class="section">
		<div class="container">
			<h1 class="title has-text-centered" >Modifier les droit sur le fichier "${titreDoc}"</h1>
			<hr>
			<h2 class="title has-text-centered" >Utilisateurs avec des droits d'accès</h2>
		</div>
		<table class="table is-fullwidth">
		    <thead>
		        <tr>
		            <th>Utilisateur</th>
		            <th>Lecture</th>
		            <th>Écriture</th>
		        </tr>
		    </thead>
		    <tbody>
		    <c:forEach var="u" items="${userAvecDroit}">
		        <tr>
		            <td>${u.login}</td>
		            <td>
		                <c:choose>
						    <c:when test="${u.droitLecture}">
						        <form method="post" action="Droits">
						            <input type="hidden" name="action" value="supprimerLecture">
						            <input type="hidden" name="id_user" value="${u.id}">
						            <input type="hidden" name="id_doc" value="${idDoc}">
						            <button class="button is-danger">
						                Supprimer
						            </button>
						        </form>
						    </c:when>
						    <c:otherwise>
						        <form method="post" action="Droits">
						            <input type="hidden" name="action" value="ajouterLecture">
						            <input type="hidden" name="id_user" value="${u.id}">
						            <input type="hidden" name="id_doc" value="${idDoc}">
						            <button class="button is-success">
						                Ajouter
						            </button>
						        </form>
						    </c:otherwise>
						</c:choose>
		            </td>
		            <td>
		                <c:choose>
						    <c:when test="${u.droitEcriture}">
						        <form method="post" action="Droits">
						            <input type="hidden" name="action" value="supprimerEcriture">
						            <input type="hidden" name="id_user" value="${u.id}">
						            <input type="hidden" name="id_doc" value="${idDoc}">
						            <button class="button is-danger">
						                Supprimer
						            </button>
						        </form>
						    </c:when>
						    <c:otherwise>
						        <form method="post" action="Droits">
						            <input type="hidden" name="action" value="ajouterEcriture">
						            <input type="hidden" name="id_user" value="${u.id}">
						            <input type="hidden" name="id_doc" value="${idDoc}">
						            <button class="button is-success">
						                Ajouter
						            </button>
						        </form>
						    </c:otherwise>
						</c:choose>
		            </td>
		        </tr>
		    </c:forEach>
		    </tbody>
		</table>
		<hr>
		<h2 class="title has-text-centered">
		    Ajouter des droits
		</h2>
		<table class="table is-fullwidth">
		    <thead>
		        <tr>
		            <th>Utilisateur</th>
		            <th>Lecture</th>
		            <th>Écriture</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach var="u" items="${tousLesUsers}">
		            <tr>
		                <td>${u.login}</td>
		                <td>
		                    <form method="post" action="Droits">
		                        <input type="hidden" name="id_doc" value="${idDoc}">
		                        <input type="hidden" name="id_user" value="${u.id}">
		                        <input type="hidden" name="action" value="ajouterLecture">
		                        <button class="button is-info">
		                            Ajouter
		                        </button>
		                    </form>
		                </td>
		                <td>
		                    <form method="post" action="Droits">
		                        <input type="hidden" name="id_doc" value="${idDoc}">
		                        <input type="hidden" name="id_user" value="${u.id}">
		                        <input type="hidden" name="action" value="ajouterEcriture">
		                        <button class="button is-warning">
		                            Ajouter
		                        </button>
		                    </form>
		                </td>
		            </tr>
		        </c:forEach>
		    </tbody>
		</table>
	</section>
<jsp:include page="/WEB-INF/views/Footer.jsp"></jsp:include>