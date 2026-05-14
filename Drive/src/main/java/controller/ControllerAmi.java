package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utilisateur;

import java.io.IOException;
import java.util.ArrayList;

import dao.AmiDao;
import dao.UtilisateurDao;

/**
 * Servlet implementation class ControllerAmi
 */
@WebServlet("/Ami")
public class ControllerAmi extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");

        if (u == null) {
            response.sendRedirect("Connexion");
            return;
        }

        AmiDao amiDao = new AmiDao();
        UtilisateurDao userDao = new UtilisateurDao();

        // liste des personnes suivies
        ArrayList<Utilisateur> amis =amiDao.recupererAmi(u.getId());

        // tous les utilisateurs sauf moi
        ArrayList<Utilisateur> utilisateurs = userDao.recupererUtilisateurs(u.getId());

        // envoi vers JSP des amis + utilisateurs 
        request.setAttribute("amis", amis);
        request.setAttribute("utilisateurs", utilisateurs);

        request.getRequestDispatcher("/WEB-INF/views/Amis.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");

        if (u == null) {
            response.sendRedirect("Connexion");
            return;
        }

        String action = request.getParameter("action");
        int idCible = Integer.parseInt(request.getParameter("id"));

        AmiDao dao = new AmiDao();

        // FOLLOW
        //on test si l'action du form est follow
        if ("follow".equals(action)) {

            if (!dao.estDejaAmi(u.getId(), idCible)) {
            	//ajout de l'ami (uniquement de mon coté)
                dao.devenirAmi(u.getId(), idCible);
            }
        }

      //on test si l'action du form est unfollow
        if ("unfollow".equals(action)) {
        	//supresion de l'ami (uniqument de mon coté)
            dao.supprimerAmi(u.getId(), idCible);
        }

        response.sendRedirect("Ami");
    }
}