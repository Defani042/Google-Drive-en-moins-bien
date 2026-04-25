<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/Header.jsp">
	<jsp:param name="titre" value="Inscription"/>
</jsp:include>
<section class="section">
  <div class="container">
    <div class="columns is-centered">
      <div class="column is-4">
        
        <div class="box">
          <h1 class="title has-text-centered">Creer un Nouveau Compte</h1>

          <form method="post" action="${pageContext.request.contextPath}/Inscription">
            
            <div class="field">
              <label class="label">Login</label>
              <div class="control has-icons-left">
                <input class="input" type="text" name="login" placeholder="Votre login" required>
                <span class="icon is-small is-left">
                  👤
                </span>
              </div>
            </div>

            <div class="field">
              <label class="label">Mot de passe</label>
              <div class="control has-icons-left">
                <input class="input" type="password" name="password" placeholder="Votre mot de passe" required>
                <span class="icon is-small is-left">
                  🔒
                </span>
              </div>
            </div>

            <div class="field">
              <button class="button is-primary is-fullwidth">
                Créer le compte
              </button>
            </div>

          </form>
           <!-- LIEN CONNEXION -->
          <p class="has-text-centered">
            Vous avez un compte ?
            <a href="${pageContext.request.contextPath}/Connexion">
              Se connecter
            </a>
          </p>
        </div>

      </div>
    </div>
  </div>

<jsp:include page="/WEB-INF/views/Footer.jsp"></jsp:include>