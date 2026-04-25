package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Document;


public class DocumentDao {
	
	//fonction de création d'un document dans la BDD
	public void creerDocument(String titre, String contenu, int userId) {
	    try {
	    	//conection a la BDD
	        Connection conn = DriverManager.getConnection(ParamBD.bdURL, ParamBD.bdLogin, ParamBD.bdPassword);
	        //requete sql
	        String sql = "INSERT INTO document (titre, contenu, proprietaire_id) VALUES (?, ?, ?)";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        //argument de la requette
	        pst.setString(1, titre);
	        pst.setString(2, contenu);
	        pst.setInt(3, userId);
	        //xécution de la requette 
	        pst.executeUpdate();
	        // fermeuture 
	        pst.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	//fonction de récupération d'un document 
	public Document getDocument(int id) {
	    Document doc = null;
	    try {
	    	//conection a la BDD
	        Connection conn = DriverManager.getConnection(ParamBD.bdURL, ParamBD.bdLogin, ParamBD.bdPassword);
	        //requete sql
	        String sql = "SELECT * FROM document WHERE id = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        //argument de la requette
	        pst.setInt(1, id);
	        //exécution de la requette 
	        ResultSet rs = pst.executeQuery();
	        //récupération des informations du document 
	        if (rs.next()) {
	            doc = new Document(
	                rs.getInt("id"),
	                rs.getString("titre"),
	                rs.getString("contenu"),
	                rs.getInt("proprietaire_id")
	            );
	        }
	        // fermeuture
	        pst.close();
	        conn.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return doc;
	}
	//fonction de sauvegarde du contenue 
	public void sauvegarderDocument(int id, String contenu) {
	    try {
	    	//conection a la BDD
	        Connection conn = DriverManager.getConnection(ParamBD.bdURL, ParamBD.bdLogin, ParamBD.bdPassword);
	        //requete sql
	        String sql = "UPDATE document SET contenu = ? WHERE id = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        //argument de la requette
	        pst.setString(1, contenu);
	        pst.setInt(2, id);
	        //exécution de la requette 
	        pst.executeUpdate();
	        // fermeuture
	        pst.close();
	        conn.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
