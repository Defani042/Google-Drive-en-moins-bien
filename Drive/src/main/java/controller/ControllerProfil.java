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

import dao.UtilisateurDao;

@WebServlet("/Profil")
public class ControllerProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// récupération des informations de la session
		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
		if (u == null) {
			response.sendRedirect("Connexion");
			return;
		}
		request.setAttribute("utilisateur", u);
		request.getRequestDispatcher("/WEB-INF/views/Profil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

        Utilisateur u =
            (Utilisateur) session.getAttribute("utilisateur");

        if (u == null) {
            response.sendRedirect("Connexion");
            return;
        }
        // récupération form
        String login = request.getParameter("nom");
        String mdp = request.getParameter("password");
        String confirm =
            request.getParameter("confirmPassword");

        // vérification mdp
        if (!mdp.equals(confirm)) {

            request.setAttribute(
                "erreur",
                "Les mots de passe ne correspondent pas"
            );

            request.setAttribute("utilisateur", u);

            RequestDispatcher rd =
                request.getRequestDispatcher(
                    "/WEB-INF/views/Profil.jsp"
                );

            rd.forward(request, response);
            return;
        }

        // modification BDD
        UtilisateurDao dao = new UtilisateurDao();

        dao.modifierUtilisateur(
            u.getId(),
            login,
            mdp
        );

        // MAJ session
        u.setLogin(login);

        session.setAttribute("utilisateur", u);

        response.sendRedirect("Profil");
	}

}
