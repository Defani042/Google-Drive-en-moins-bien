<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/Header.jsp">
	<jsp:param name="titre" value="Accueil"/>
</jsp:include>

	<section class="section">
	  <div class="container">
	
	    <!-- HERO -->
	    <div class="has-text-centered mb-6">
	      <h1 class="title is-2">Bienvenue 👋</h1>
	      <p class="subtitle">
	        Votre espace de stockage de fichiers simplifié
	      </p>
	    </div>
	
	    <!-- USER INFO -->
	    <div class="box has-text-centered">
	      <p class="title is-5">
	        Bonjour 
	        <strong>
	          ${sessionScope.utilisateur.login}
	        </strong>
	      </p>
	
	      <p class="has-text-grey">
	        Vous êtes connecté avec succès.
	      </p>
	    </div>
	
	    <!-- ACTIONS -->
	    <div class="columns is-centered">
	      
	      <div class="column is-3">
	        <div class="box has-text-centered">
	          <p class="title is-6">📁 Mes fichiers</p>
	          <p class="mb-3">Accéder à vos documents</p>
	          <a href="${pageContext.request.contextPath}/ListeDocument"
	          	 class="button is-primary is-fullwidth">
	            Ouvrir
	          </a>
	        </div>
	      </div>
	
	      <div class="column is-3">
	        <div class="box has-text-centered">
	          <p class="title is-6">⬆️ Créer un fichier</p>
	          <p class="mb-3">Créer un nouveau fichier</p>
	          <a href="${pageContext.request.contextPath}/Document"
	          	 class="button is-link is-fullwidth">
	            Nouveau
	          </a>
	        </div>
	      </div>
	
	      <div class="column is-3">
	        <div class="box has-text-centered">
	          <p class="title is-6">🚪 Déconnexion</p>
	          <p class="mb-3">Quitter votre session</p>
	          <a href="${pageContext.request.contextPath}/Deconnexion"
	             class="button is-danger is-fullwidth">
	            Logout
	          </a>
	        </div>
	      </div>
	
	    </div>
	
	  </div>
	</section>
		
<jsp:include page="/WEB-INF/views/Footer.jsp"></jsp:include>