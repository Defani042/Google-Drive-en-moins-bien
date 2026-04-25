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

import java.io.IOException;

import dao.DocumentDao;

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
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Document.jsp");
		//si utilisateur null redirection vers Connexion
		if(u == null) {
			//redirection vers le controler d'inscription
			rd = request.getRequestDispatcher("/WEB-INF/views/Connexion.jsp");
		}
		DocumentDao dao = new DocumentDao();
		Document doc;
		// récupérer id du document
		 String idParam = request.getParameter("id");
		// CAS 1 : nouveau document
	    if (idParam == null || idParam.isEmpty()) {
	        doc = new Document();
	        doc.setTitre("Nouveau document");
	        doc.setContenu("");
	        dao.creerDocument("Nouveau Doc","",u.getId());
	    }
	    // CAS 2 : document existant
	    else {

	        int id = Integer.parseInt(idParam);

	        
	        doc = dao.getDocument(id);
	    }


		// envoyer à la JSP
		request.setAttribute("doc", doc);

		//sinon page d'acceuille
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
	    // check si utilisateur est null
	    if (u == null) {
	        response.sendRedirect("Connexion");
	        return;
	    }
	    //récupération de l'id du doc + contenue
	    int id = Integer.parseInt(request.getParameter("id"));
	    String contenu = request.getParameter("contenu");
	    //save du document
	    DocumentDao dao = new DocumentDao();
	    dao.sauvegarderDocument(id, contenu);

	    // redirection vers le document
	    response.sendRedirect("Document?id=" + id);
	}

}
