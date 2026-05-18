<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/Header.jsp">
    <jsp:param name="titre" value="Document"/>
</jsp:include>

<section class="section">
    <div class="container">

        <!-- FORMULAIRE TITRE -->
        <c:choose>
            <c:when test="${!lecture}">
                <form method="post" name="titre" action="${pageContext.request.contextPath}/Document">
                    <input class="title" type="text" id="titre" name="titre" value="${doc.titre}" style="background-color: transparent;">
                    <input type="hidden" name="action" value="titre">
                    <input type="hidden" name="id" value="${doc.id}">
                    <button class="button is-primary" type="submit">🖊️ Modifier</button>
                </form>
            </c:when>
            <c:otherwise>
                <h1 class="title">${doc.titre}</h1>
            </c:otherwise>
        </c:choose>

        <br>

        <div class="columns is-variable is-3">

            <!-- COLONNE EDITEUR -->
            <div class="column is-8">

                <!-- FORMULAIRE DROITS ET UPLOAD -->
                <c:if test="${!lecture}">
                    <form method="post" name="droits" action="${pageContext.request.contextPath}/Droits">
                        <input type="hidden" name="id_doc" value="${doc.id}">
                        <button class="button is-primary" type="submit">👤 Modifier les droits</button>
                    </form>

                    <!-- BOUTON TELECHARGEMENT -->
                    <form method="post" action="${pageContext.request.contextPath}/Document" style="display:inline-block; margin-left:10px;">
                        <input type="hidden" name="id" value="${doc.id}" />
                        <input type="hidden" name="action" value="download" />
                        <button class="button is-link" type="submit">⬇ Télécharger</button>
                    </form>

                    <!-- BOUTON IMPORT -->
                    <form method="post" action="${pageContext.request.contextPath}/Document" enctype="multipart/form-data" style="display:inline-block; margin-left:10px;">
					    <input type="hidden" name="action" value="upload">
					    <input type="file" name="fichier" id="fichier" style="display:none;" onchange="this.form.submit()">
					    <label for="fichier" class="button is-link" style="cursor:pointer;">⬆ Importer un fichier</label>
					</form>
                </c:if>

                <!-- EDITEUR -->
                <div style="width:100%; overflow:hidden; margin-top:10px;">
                    <div id="editor" style="height:500px; border:1px solid #ccc;"></div>
                </div>

                <!-- CONTENU INITIAL -->
                <textarea id="docContent" style="display:none;">${doc.contenu}</textarea>

                <!-- FORMULAIRE SAUVEGARDE -->
                <form method="post" name="save" action="${pageContext.request.contextPath}/Document">
                    <input type="hidden" name="contenu" id="hiddenContent">
                    <input type="hidden" name="action" value="save">
                    <input type="hidden" name="id" value="${doc.id}">
                    <div class="mt-4">
                        <c:if test="${!lecture}">
                            <button class="button is-primary" type="submit">💾 Sauvegarder</button>
                        </c:if>
                    </div>
                </form>

            </div>

            <!-- COLONNE CHAT -->
            <div class="column is-4">
                <div class="box">
                    <h2 class="title is-5">Chat</h2>

                    <!-- MESSAGES -->
                    <div id="chat-box" style="height:400px; overflow-y:auto; margin-bottom:20px;">
                        <c:forEach var="msg" items="${messages}">
                            <div class="notification is-dark">
                                <strong>${msg.nomUtilisateur}</strong><br>
                                ${msg.message}
                            </div>
                        </c:forEach>
                    </div>

                    <!-- FORMULAIRE MESSAGE -->
                    <form method="post" action="${pageContext.request.contextPath}/Document">
                        <input type="hidden" name="id" value="${doc.id}">
                        <input type="hidden" name="action" value="message">
                        <input type="hidden" id="username" value="${sessionScope.utilisateur.login}">
                        <div class="field">
                            <div class="control">
                                <textarea class="textarea" id="content" name="message" placeholder="Votre message..." required></textarea>
                            </div>
                        </div>
                        <div class="field">
                            <div class="control">
                                <button class="button is-link is-fullwidth" onclick="diffuserMessage()">Envoyer</button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>

        </div> <!-- end columns -->

    </div> <!-- end container -->
</section>

<script>const lecture = ${lecture ? 'true' : 'false'};</script>
<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<script>const lecture = ${lecture};</script>
<script src="${pageContext.request.contextPath}/js/editor.js"></script>

<jsp:include page="/WEB-INF/views/Footer.jsp"></jsp:include>