package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Document;
import model.Utilisateur;
import model.Message;

import java.io.IOException;
import java.util.ArrayList;

import dao.DocumentDao;
import dao.MessageDao;

/**
 * Servlet implementation class ControllerDocument
 */
@WebServlet("/Document")
@jakarta.servlet.annotation.MultipartConfig
public class ControllerDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// récupération de l'utilisateur dans la variable de session
		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
		//si utilisateur null redirection vers Connexion
		  if (u == null) {
	            response.sendRedirect("Connexion");
	            return;
	     }
		DocumentDao dao = new DocumentDao();
		Document doc;
		// récupérer id du document
		String idParam = request.getParameter("id");
	    if (idParam == null || idParam.isEmpty()) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID document manquant");
	        return;
	    }
	    int id = Integer.parseInt(idParam);
	    ArrayList<Message> messages = MessageDao.getMessagesByDocument(id);
	    request.setAttribute("messages", messages);
	    //récupération du document
	    doc = dao.getDocument(id);
		// envoyer à la JSP
		request.setAttribute("doc", doc);
		request.setAttribute("lecture", false);
		//sinon page d'acceuille
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Document.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    String action = request.getParameter("action");
	    Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
	    // check si utilisateur est null
	    if (u == null) {
	        response.sendRedirect("Connexion");
	        return;
	    }
	    

	    // Vérifier si téléchargement
	    if ("download".equals(request.getParameter("action"))) {
	    	int id = Integer.parseInt(request.getParameter("id"));
		    DocumentDao dao = new DocumentDao();
		    Document doc = dao.getDocument(id);
		    
		 // indique au navigateur que c'est un fichier à télécharger
		 response.setContentType("application/octet-stream");
		 // Ajoute un en-tête HTTP "Content-Disposition" pour forcer le téléchargement.
		 // Le "filename" spécifie le nom suggéré pour le fichier téléchargé.
		 // Ici, on remplace les espaces dans le titre par des underscores et ajoute l'extension ".txt".
		 response.setHeader("Content-Disposition", "attachment; filename=\"" + doc.getTitre().replaceAll("\\s", "_") + ".txt\"");
		 // Écrit le contenu du document dans le flux de sortie de la réponse.
		 response.getOutputStream().write(doc.getContenu().getBytes("UTF-8"));
		 // Vide le flux de sortie pour s'assurer que tous les octets sont envoyés au client.
		 response.getOutputStream().flush();
	        return;
	    }
		if ("save".equals(action)) {
		    //récupération de l'id du doc + contenue
		    int id = Integer.parseInt(request.getParameter("id"));
		    String contenu = request.getParameter("contenu");
		    //save du document
		    DocumentDao dao = new DocumentDao();
		    dao.sauvegarderDocument(id, contenu);
		    System.out.println("contenu=[" + contenu + "]");
		    // redirection vers le document
		    response.sendRedirect("Document?id=" + id);
	    }
	    if ("titre".equals(action)) {
	    	int id = Integer.parseInt(request.getParameter("id"));
	    	String nouveauTitre = request.getParameter("titre");
	    	DocumentDao dao = new DocumentDao();
	    	dao.changerTitre(id, nouveauTitre);
	    	
	    	// redirection vers le document
		    response.sendRedirect("Document?id=" + id);
	    }
	    if ("message".equals(action)) {
	    	int id = Integer.parseInt(request.getParameter("id"));
	    	String message = request.getParameter("message");
	    	Message m = new Message(id,u.getId(), message);
	    	MessageDao.sauvegardeMessage(m);
	    	// redirection vers le document
		    response.sendRedirect("Document?id=" + id);
	    }
	    if ("lecture".equals(action)) {
	    	DocumentDao dao = new DocumentDao();
			Document doc;
			// récupérer id du document
			String idParam = request.getParameter("id");
		    if (idParam == null || idParam.isEmpty()) {
		        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID document manquant");
		        return;
		    }
		    int id = Integer.parseInt(idParam);
		    ArrayList<Message> messages = MessageDao.getMessagesByDocument(id);
		    request.setAttribute("messages", messages);
		    //récupération du document
		    doc = dao.getDocument(id);
			// envoyer à la JSP
			request.setAttribute("doc", doc);
			request.setAttribute("lecture", true);
			//sinon page d'acceuille
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Document.jsp");
			rd.forward(request, response);
			
			return;
	    }
	    if ("upload".equals(action)) {
	        try {
	        	System.out.println("UPLOAD");
	            Part fichierPart = request.getPart("fichier");
	            if (fichierPart != null && fichierPart.getSize() > 0) {
	                String nomFichier = fichierPart.getSubmittedFileName();
	                String contenu = new String(fichierPart.getInputStream().readAllBytes(), "UTF-8");

	                Document doc = new Document();
	                doc.setTitre(nomFichier.replaceAll("\\.txt$", ""));
	                doc.setContenu(contenu);
	                doc.setProprietaireId(u.getId());

	                // Création dans la BDD
	                int idDoc = new DocumentDao().creerDocument(doc.getTitre(), doc.getContenu(), u.getId());
	                doc.setId(idDoc);
	                System.out.println("Document créé avec ID = " + doc.getId());

	                response.sendRedirect("Document?id=" + doc.getId());
	            } else {
	                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Fichier manquant");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de l'import du fichier");
	        }
	    }
	    return;
	}
}
