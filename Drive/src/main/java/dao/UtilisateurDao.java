package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Utilisateur;

public class UtilisateurDao {
	//Inscription d'un utilisateur 
	 public void InscriptionUtilisateur(String login, String password) {
		 //connexion à la BDD
		 try {
			Connection connexion = DriverManager.getConnection(ParamBD.bdURL,ParamBD.bdLogin,ParamBD.bdPassword);
			String sql = "INSERT INTO utilisateur (login,password) "
					   + "VALUES (?, ?);";
			PreparedStatement pst = connexion.prepareStatement(sql);
			pst.setString(1,login);
			pst.setString(2,password);
			pst.executeUpdate();
			//fermeuture 
			pst.close();
			connexion.close();
			System.out.println("Ajout de "+login+" à la BDD!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Erreur lors de la création du compte de "+login+" !");
		}
	 }
	 //connexion d'un utilisateur 
	 public Utilisateur seConnecter(String login, String password) {
	        Utilisateur u = null;
	      //connexion à la BDD
	        try {
	        	Connection connexion = DriverManager.getConnection(ParamBD.bdURL,ParamBD.bdLogin,ParamBD.bdPassword);

	            String sql = "SELECT * FROM utilisateur "
	            		+ "WHERE login = ? "
	            		+ "AND password = ?";
	            PreparedStatement pst = connexion.prepareStatement(sql);

	            pst.setString(1, login);
	            pst.setString(2, password);

	            ResultSet rs = pst.executeQuery();

	            if (rs.next()) {
	                u = new Utilisateur(rs.getInt("id"),rs.getString("login")); 
	            }
	            //fermeuture 
				pst.close();
				connexion.close();
				

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return u;
	    }
}
