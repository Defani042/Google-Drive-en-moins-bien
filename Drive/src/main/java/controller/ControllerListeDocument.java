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
import java.util.LinkedList;

import dao.DocumentDao;
@WebServlet("/ListeDocument")
public class ControllerListeDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récupération des informations de la session
		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
		 if (u == null) {
		        response.sendRedirect("Connexion");
		        return;
		 }
		//récupération de la liste
		 DocumentDao dao = new DocumentDao();
		 LinkedList<Document> docs = dao.getDocumentsByUser(u.getId());
		 
		 System.out.println("User ID: " + u.getId());
		 System.out.println("Nb docs: " + docs.size());
		 //enregistrement dans les variables de session
		 request.setAttribute("documents", docs);
		 request.getRequestDispatcher("/WEB-INF/views/ListeDocument.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
