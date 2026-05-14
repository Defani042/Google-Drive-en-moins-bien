package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Utilisateur;

public class AmiDao {
	// récupération des ami d'un utilisateur
	public ArrayList<Utilisateur> recupererAmi(int idU) {

	    ArrayList<Utilisateur> amis = new ArrayList<>();

	    try {

	        Connection conn = DriverManager.getConnection(
	                ParamBD.bdURL,
	                ParamBD.bdLogin,
	                ParamBD.bdPassword
	        );

	        String sql =
	                "SELECT u.id, u.login " +
	                "FROM utilisateur u " +
	                "JOIN ami a ON u.id = a.id_utilisateur2 " +
	                "WHERE a.id_utilisateur1 = ?";

	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, idU);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            Utilisateur u = new Utilisateur();
	            u.setId(rs.getInt("id"));
	            u.setLogin(rs.getString("login"));

	            amis.add(u);
	        }

	        rs.close();
	        ps.close();
	        conn.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return amis;
	}
	//ajout de la relation ami dans la bdd
	public void devenirAmi(int idU1,int idU2) {
		try {
			Connection connexion = DriverManager.getConnection(ParamBD.bdURL,ParamBD.bdLogin,ParamBD.bdPassword);
			String sql = "INSERT INTO ami (id_utilisateur1,id_utilisateur2) "
					+ 	 "VALUES (?, ?);";
			PreparedStatement ps = connexion.prepareStatement(sql);
	        ps.setInt(1, idU1);
	        ps.setInt(2, idU2);
	        ps.executeUpdate();
	        ps.close();
	        connexion.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//vérifier si on est pas amis
	public boolean estDejaAmi(int idU1, int idU2) {

	    boolean existe = false;

	    try {

	        Connection conn = DriverManager.getConnection(ParamBD.bdURL,ParamBD.bdLogin,ParamBD.bdPassword);
	        String sql =
	                "SELECT COUNT(*) FROM ami " +
	                "WHERE id_utilisateur1 = ? AND id_utilisateur2 = ?";

	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, idU1);
	        ps.setInt(2, idU2);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            existe = rs.getInt(1) > 0;
	        }

	        rs.close();
	        ps.close();
	        conn.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return existe;
	}
	//supression d'un ami
	public void supprimerAmi(int idU,int idA){
		try {
			Connection conn = DriverManager.getConnection(ParamBD.bdURL,ParamBD.bdLogin,ParamBD.bdPassword);
			String sql = "DELETE FROM ami "
					+ "WHERE id_utilisateur1 = ? AND id_utilisateur2 = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, idU);
	        ps.setInt(2, idA);
	        ps.executeUpdate();
	        ps.close();
	        conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
