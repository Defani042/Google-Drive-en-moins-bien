package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
	}

}
