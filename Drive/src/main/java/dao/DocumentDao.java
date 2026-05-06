package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.Document;


public class DocumentDao {
	
	//fonction de création d'un document dans la BDD
	public int creerDocument(String titre, String contenu, int userId) {
		 int id = -1;
	    try {
	    	//conection a la BDD
	        Connection conn = DriverManager.getConnection(ParamBD.bdURL, ParamBD.bdLogin, ParamBD.bdPassword);
	        //requete sql
	        String sql = "INSERT INTO document (titre, contenu, proprietaire_id) VALUES (?, ?, ?)";
	        // permet de récupérer l'id 
	        PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        //argument de la requette
	        pst.setString(1, titre);
	        pst.setString(2, contenu);
	        pst.setInt(3, userId);
	        //xécution de la requette 
	        pst.executeUpdate();
	        //recupération de l'id du document 
	        ResultSet rs = pst.getGeneratedKeys();
	        if (rs.next()) {
	            id = rs.getInt(1); 
	        }
	        // fermeuture 
	        pst.close();
	        conn.close();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return id;
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
	//fonction de récupération de la liste des Document d'un utilisateur 
	public LinkedList<Document> getDocumentsByUser(int userId) {

		LinkedList<Document> docs = new LinkedList<Document>();

	    try  {
	    	Connection conn = DriverManager.getConnection(ParamBD.bdURL, ParamBD.bdLogin, ParamBD.bdPassword);
	        String sql = "SELECT * FROM document WHERE proprietaire_id = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);

	        ps.setInt(1, userId);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Document doc = new Document(
	                rs.getInt("id"),
	                rs.getString("titre"),
	                rs.getString("contenu"),
	                userId
	            );
	            docs.add(doc);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return docs;
	}
	
	//fonction de supression d'un document 
	public void deleteDocument(int idU, int idDoc) {
		try  {
	    	Connection conn = DriverManager.getConnection(ParamBD.bdURL, ParamBD.bdLogin, ParamBD.bdPassword);
	        String sql = "DELETE FROM document WHERE id = ? AND proprietaire_id = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        //argument de la requette
	        pst.setInt(1,idU);
	        pst.setInt(2, idDoc);
	        //exécution de la requette 
	        int rows = pst.executeUpdate();
	        
	        if (rows > 0) {
	            System.out.println("Document supprimé !");
	        } else {
	            System.out.println("Aucun document supprimé (id ou user incorrect)");
	            System.out.println("Id Utilisateur: "+idU);
	            System.out.println("Id Document: "+idDoc);
	        }
	        // fermeuture
	        pst.close();
	        conn.close();
	        
		 } catch (SQLException e) {
		        e.printStackTrace();
		 }
		
	}

}
