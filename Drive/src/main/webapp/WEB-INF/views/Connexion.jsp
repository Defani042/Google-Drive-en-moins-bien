<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/Header.jsp">
	<jsp:param name="titre" value="connexion"/>
</jsp:include>
<section class="section">
  <div class="container">
    <div class="columns is-centered">
      <div class="column is-4">

        <div class="box">
          <h1 class="title has-text-centered">Connexion</h1>

          <form method="post" action="${pageContext.request.contextPath}/Connexion">

            <!-- LOGIN -->
            <div class="field">
              <label class="label">Login</label>
              <div class="control">
                <input class="input" type="text" name="login" placeholder="Votre login" required>
              </div>
            </div>

            <!-- MOT DE PASSE -->
            <div class="field">
              <label class="label">Mot de passe</label>
              <div class="control">
                <input class="input" type="password" name="password" placeholder="Votre mot de passe" required>
              </div>
            </div>

            <!-- BOUTON -->
            <div class="field">
              <button class="button is-primary is-fullwidth">
                Se connecter
              </button>
            </div>

          </form>

          <!-- LIEN INSCRIPTION -->
          <p class="has-text-centered">
            Pas de compte ?
            <a href="${pageContext.request.contextPath}/Inscription">
              Créer un compte
            </a>
          </p>

        </div>

      </div>
    </div>
  </div>
</section>
		
		
<jsp:include page="/WEB-INF/views/Footer.jsp"></jsp:include>