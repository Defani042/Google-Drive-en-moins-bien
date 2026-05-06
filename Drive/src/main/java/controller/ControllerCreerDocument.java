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

import dao.DocumentDao;

/**
 * Servlet implementation class ControllerCreerDocument
 */
@WebServlet("/CreerDocument")
public class ControllerCreerDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idDoc;
		// récupération de l'utilisateur dans la variable de session
		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
		//si utilisateur null redirection vers Connexion
		if (u == null) {
            response.sendRedirect("Connexion");
            return;
		}
		// création du document dans la BDD
		DocumentDao dao = new DocumentDao();
		idDoc = dao.creerDocument("Nouveau Document","",u.getId());
		// récupération du document et enregistrement dans la session
		Document doc = dao.getDocument(idDoc);
		request.setAttribute("doc", doc);
		response.sendRedirect("Document?id="+idDoc);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
