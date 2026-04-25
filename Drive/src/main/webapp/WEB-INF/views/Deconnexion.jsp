<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/Header.jsp">
	<jsp:param name="titre" value="déconnexion"/>
</jsp:include>	
<section class="section">
  <div class="container">

    <div class="columns is-centered">
      <div class="column is-5">

        <div class="box has-text-centered">

          <!-- ICON -->
          <div class="mb-4">
            <span style="font-size: 48px;">👋</span>
          </div>

          <!-- MESSAGE -->
          <h1 class="title is-4">Vous avez été déconnecté</h1>

          <p class="subtitle is-6 has-text-grey">
            À bientôt 👋 Votre session a été fermée avec succès.
          </p>

          <hr>

          <!-- ACTIONS -->
          <div class="buttons is-centered">

            <a class="button is-primary"
               href="${pageContext.request.contextPath}/Connexion">
              Se connecter
            </a>

            <a class="button is-light"
               href="${pageContext.request.contextPath}/Inscription">
              Créer un compte
            </a>

          </div>

        </div>

      </div>
    </div>

  </div>
</section>

	
<jsp:include page="/WEB-INF/views/Footer.jsp"></jsp:include>
