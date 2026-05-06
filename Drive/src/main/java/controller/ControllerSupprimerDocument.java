package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utilisateur;

import java.io.IOException;

import dao.DocumentDao;

/**
 * Servlet implementation class ControllerSupprimerDocument
 */
@WebServlet("/SupprimerDocument")
public class ControllerSupprimerDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		// récupération de l'utilisateur dans la variable de session
	    HttpSession session = request.getSession();
	    Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
	    //si utilisateur null redirection vers Connexion
	    if (u == null) {
	        response.sendRedirect("Connexion");
	        return;
	    }
	    //récupération de l'id du document à supprimer
	    String idParam = request.getParameter("id");
	    if (idParam == null || idParam.isEmpty()) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID manquant");
	        return;
	    }
	    int id = Integer.parseInt(idParam);
	    // supression du document dans la base
	    DocumentDao dao = new DocumentDao();
	    dao.deleteDocument(id, u.getId());
	    //renvoie sur la liste des document 
	    response.sendRedirect(request.getContextPath() + "/ListeDocument");
	}

}
