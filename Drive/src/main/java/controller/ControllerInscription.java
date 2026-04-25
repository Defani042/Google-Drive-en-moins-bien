package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utilisateur;

import java.io.IOException;

import dao.ParamBD;
import dao.UtilisateurDao;

@WebServlet("/Inscription")
public class ControllerInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Inscription.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récupération des données du forlulaire
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		//création de l'utilisateur 
        UtilisateurDao dao = new UtilisateurDao();
        dao.InscriptionUtilisateur(login, password);
        // redirection login
        response.sendRedirect(request.getContextPath() + "/Connexion");
	}
	public void init() {
		ParamBD.init(getServletContext());
	}

}
