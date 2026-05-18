<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/Header.jsp">
	<jsp:param name="titre" value="Profil"/>
</jsp:include>
<c:if test="${not empty erreur}">
    <div class="notification is-danger">
        ${erreur2}
    </div>
</c:if>
<section class="section">
    <div class="container">
        <div class="columns is-centered">
            <div class="column is-half">
                <div class="box">
                    <h1 class="title has-text-centered">
                        👤 Modifier le profil
                    </h1>
                    <!-- FORMULAIRE -->
                    <form method="post"
                          action="${pageContext.request.contextPath}/Profil">
                        <!-- NOM -->
                        <div class="field">
                            <label class="label">
                                Nom d'utilisateur
                            </label>
                            <div class="control">
                                <input class="input"
                                       type="text"
                                       name="nom"
                                       value="${sessionScope.utilisateur.login}"
                                       required>
                            </div>
                        </div>
                        <!-- MOT DE PASSE -->
                        <div class="field">
                            <label class="label">
                                Nouveau mot de passe
                            </label>
                            <div class="control">
                               	<input class="input"
                                       type="password"
                                       name="password"
                                       placeholder="Nouveau mot de passe">
                            </div>
                        </div>
                        <!-- CONFIRMATION -->
                        <div class="field">
                            <label class="label">
                                Confirmation du mot de passe
                            </label>
                            <div class="control">

                                <input class="input"
                                       type="password"
                                       name="confirmPassword"
                                       placeholder="Confirmer le mot de passe">
                            </div>
                        </div>
                        <!-- BOUTON -->
                        <div class="field mt-5">
                            <div class="control has-text-centered">
                                <button class="button is-primary is-medium"
                                        type="submit">
                                    💾 Enregistrer
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="/WEB-INF/views/Footer.jsp"></jsp:include>