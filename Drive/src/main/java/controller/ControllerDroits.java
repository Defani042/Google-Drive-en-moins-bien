package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Document;
import model.Utilisateur;

import java.io.IOException;
import java.util.ArrayList;

import dao.DocumentDao;
import dao.UtilisateurDao;

/**
 * Servlet implementation class ControllerDroits
 */
@WebServlet("/Droits")
public class ControllerDroits extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// récupération de l'utilisateur dans la variable de session
		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
		String idDoc = request.getParameter("id_doc");
		DocumentDao dao = new DocumentDao();
		String titreDoc = dao.rechercheTitreAvecID(idDoc);
		ArrayList<Utilisateur> userAvecDroit = DocumentDao.rechercheUtilisateursAvecDroits(idDoc);
		UtilisateurDao userDao = new UtilisateurDao();
		ArrayList<Utilisateur> tousLesUsers = userDao.getTousLesUtilisateurs(idDoc);
		//si utilisateur null redirection vers Connexion
		if (u == null) {
			response.sendRedirect("Connexion");
			return;
		}
		request.setAttribute("idDoc", idDoc);
		request.setAttribute("titreDoc", titreDoc);
		request.setAttribute("tousLesUsers", tousLesUsers);
		request.setAttribute("userAvecDroit", userAvecDroit);
		request.getRequestDispatcher("/WEB-INF/views/Droits.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idDoc = request.getParameter("id_doc");
	    String idUser = request.getParameter("id_user");
	    HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
		if (u == null) {
			response.sendRedirect("Connexion");
			return;
		}
		String action = request.getParameter("action");
	    DocumentDao dao = new DocumentDao();
	    
	    if(action == null) {
	    	doGet(request,response);
	    	return;
	    }
	    
	    switch (action) {
        case "ajouterLecture":
            dao.ajouterDroitLecture(idUser, idDoc);
            break;
        case "supprimerLecture":
            dao.supprimerDroitLecture(idUser, idDoc);
            break;
        case "ajouterEcriture":
            dao.ajouterDroitEcriture(idUser, idDoc);
            break;
        case "supprimerEcriture":
            dao.supprimerDroitEcriture(idUser, idDoc);
            break;
    }

	    response.sendRedirect("Droits?id_doc=" + idDoc);
	}

}
