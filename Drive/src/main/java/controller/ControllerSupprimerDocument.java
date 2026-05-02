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
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récupération de l'utilisateur
		HttpSession session = request.getSession();
	    Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
	    //véréfication de l'utilisateur
	    if (u == null) {
	        response.sendRedirect("Connexion");
	        return;
	    }
	    int id = Integer.parseInt(request.getParameter("id"));

        DocumentDao dao = new DocumentDao();
        dao.deleteDocument(id, u.getId());
        response.sendRedirect(request.getContextPath() + "/ListeDocument");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
