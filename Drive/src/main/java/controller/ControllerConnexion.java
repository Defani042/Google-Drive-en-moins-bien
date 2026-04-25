package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utilisateur;

import java.io.IOException;

import dao.ParamBD;
import dao.UtilisateurDao;

/**
 * Servlet implementation class ControllerConnexion
 */
@WebServlet("/Connexion")
public class ControllerConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Connexion.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récupération des données du forlulaire
		String login = request.getParameter("login");
        String password = request.getParameter("password");
        //connexion de l'utilisateur 
        UtilisateurDao dao = new UtilisateurDao();
        Utilisateur u = dao.seConnecter(login, password);
        //stockage dans les var de session
        HttpSession session = request.getSession();
        session.setAttribute("utilisateur", u);
        response.sendRedirect(request.getContextPath() + "/Accueil");
      
	}
	public void init() {
		ParamBD.init(getServletContext());
	}

}
