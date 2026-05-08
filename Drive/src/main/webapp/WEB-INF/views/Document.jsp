<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/Header.jsp">
	<jsp:param name="titre" value="Document"/>
</jsp:include>
	<section class="section">
	  <div class="container">
	
	    <!-- FORMULAIRE TITRE -->
	     <form method="post" name="titre" action="${pageContext.request.contextPath}/Document">
	    	<!-- <h1 class="title">${doc.titre}</h1> -->
	    	<input class ="title "type="text" name="titre" value="${doc.titre}" style="background-color: transparent;">
	    	<input type="hidden" name="action" value="titre">
	    	<input type="hidden" name="id" value="${doc.id}">
	        <button class="button is-primary" type="submit">
	            🖊️ Modifier
	        </button>
	    </form>
	    
	    <!-- FORMULAIRE DROITS -->
	    <form method="post" name="droits" action="${pageContext.request.contextPath}/Droits">
	    	<input type="hidden" name="id_doc" value="${doc.id}">
	    	<button class="button is-primary" type="submit">
	            👤 Modifier les droits
	        </button>
	    </form>
	    
	    <br>
	
	    <!-- EDITEUR -->
	    <div id="editor" style="height: 400px; border: 1px solid #ccc;"></div>
	
	    <!-- CONTENU INITIAL -->
	    <textarea id="docContent"style="display:none;">${doc.contenu}</textarea>
	
	    <!-- FORMULAIRE SAUVEGARDE -->
	    <form method="post" name="save" action="${pageContext.request.contextPath}/Document">
	
	        <input type="hidden" name="contenu" id="hiddenContent">
	        <input type="hidden" name="action" value="save">
	        <input type="hidden" name="id" value="${doc.id}">
	
	        <div class="mt-4">
	            <button class="button is-primary" type="submit">
	                💾 Sauvegarder
	            </button>
	        </div>
	
	    </form>
	
	  </div>
	</section>
<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<script src="${pageContext.request.contextPath}/js/editor.js"></script>


<jsp:include page="/WEB-INF/views/Footer.jsp"></jsp:include>