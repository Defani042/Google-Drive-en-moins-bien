package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
				u.setAdmin(rs.getBoolean("admin"));
			 	}
			//fermeuture 
			pst.close();
			connexion.close();
			 

		} catch (Exception e) {
			e.printStackTrace();
		}

		return u;
	}
	public ArrayList<Utilisateur> getTousLesUtilisateurs(String idDocument) {
		ArrayList<Utilisateur> liste = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(ParamBD.bdURL,ParamBD.bdLogin,ParamBD.bdPassword);
			String sql = """
					SELECT *
					FROM utilisateur u
					WHERE u.id NOT IN (

					        SELECT l.id_utilisateur
	                        FROM lecture l
	                        WHERE l.id_document = ?

	                        UNION

	                        SELECT e.id_utilisateur
	                        FROM ecriture e
	                        WHERE e.id_document = ?

	                        UNION

	                        SELECT d.proprietaire_id
	                        FROM document d
	                        WHERE d.id = ?
	                    )
	                """;
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, idDocument);
			ps.setString(2, idDocument);
			ps.setString(3, idDocument);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Utilisateur u = new Utilisateur(rs.getInt("id"),rs.getString("login"));
				liste.add(u);
			}
			//fermeuture 
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
	}
	public void modifierUtilisateur(int id,String login,String mdp){
		try {
			Connection conn = DriverManager.getConnection(ParamBD.bdURL,ParamBD.bdLogin,ParamBD.bdPassword);
			String sql =
	                "UPDATE utilisateur " +
	                "SET login = ?, password = ? " +
	                "WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,login);
			ps.setString(2,mdp);
			ps.setInt(3,id);
			ps.executeUpdate();
			
			//fermeuture 
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Utilisateur> recupererUtilisateurs(int idActuel) {

	    ArrayList<Utilisateur> users = new ArrayList<>();

	    try {

	        Connection conn = DriverManager.getConnection(
	                ParamBD.bdURL,
	                ParamBD.bdLogin,
	                ParamBD.bdPassword
	        );

	        String sql =
	                "SELECT id, login FROM utilisateur WHERE id != ?";

	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, idActuel);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            Utilisateur u = new Utilisateur();
	            u.setId(rs.getInt("id"));
	            u.setLogin(rs.getString("login"));

	            users.add(u);
	        }

	        rs.close();
	        ps.close();
	        conn.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return users;
	}
    // Supprimer un utilisateur par son id
    public void supprimerUtilisateur(int id) {
        try {
            Connection conn = DriverManager.getConnection(
                    ParamBD.bdURL,
                    ParamBD.bdLogin,
                    ParamBD.bdPassword
            );

            String sql = "DELETE FROM utilisateur WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

