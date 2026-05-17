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

import dao.UtilisateurDao;

@WebServlet("/Admin")
public class ControllerAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");

        if (u == null || !u.isAdmin()) {
            // redirige si pas connecté ou pas admin
            response.sendRedirect("Connexion");
            return;
        }

        // récupère tous les utilisateurs sauf lui
        UtilisateurDao dao = new UtilisateurDao();
        ArrayList<Utilisateur> utilisateurs = dao.recupererUtilisateurs(u.getId());

        request.setAttribute("utilisateurs", utilisateurs);
        request.getRequestDispatcher("/WEB-INF/views/Admin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");

        if (u == null || !u.isAdmin()) {
            response.sendRedirect("Connexion");
            return;
        }

        // récupération de l'id à supprimer
        int idSupprimer = Integer.parseInt(request.getParameter("id"));

        // suppression
        UtilisateurDao dao = new UtilisateurDao();
        dao.supprimerUtilisateur(idSupprimer); // assure-toi que cette méthode existe

        response.sendRedirect("Admin");
    }
    
    
}