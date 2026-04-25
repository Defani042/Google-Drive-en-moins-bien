<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/Header.jsp">
	<jsp:param name="titre" value="Document"/>
</jsp:include>

	<section class="section">
	  <div class="container">
	
	    <!-- TITRE -->
	    <h1 class="title">${doc.titre}</h1>
	
	    <!-- EDITEUR -->
	    <div id="editor" style="height: 400px; border: 1px solid #ccc;"></div>
	
	    <!-- CONTENU INITIAL -->
	    <textarea id="docContent" hidden>${doc.contenu}</textarea>
	
	    <!-- FORMULAIRE SAUVEGARDE -->
	    <form method="post" action="${pageContext.request.contextPath}/Document">
	
	        <input type="hidden" name="contenu" id="hiddenContent">
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