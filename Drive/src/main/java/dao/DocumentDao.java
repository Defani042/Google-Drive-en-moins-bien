package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Document;
import model.Utilisateur;


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
	public LinkedList<Document> getDocumentsLecture(int userId) {

		LinkedList<Document> docs = new LinkedList<Document>();

	    try  {
	    	Connection conn = DriverManager.getConnection(ParamBD.bdURL, ParamBD.bdLogin, ParamBD.bdPassword);
	    	String sql = "SELECT * FROM document,lecture WHERE id_document = document.id AND id_utilisateur = ?";
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
	public LinkedList<Document> getDocumentsEcriture(int userId) {

		LinkedList<Document> docs = new LinkedList<Document>();

	    try  {
	    	Connection conn = DriverManager.getConnection(ParamBD.bdURL, ParamBD.bdLogin, ParamBD.bdPassword);
	    	String sql = "SELECT * FROM document,ecriture WHERE id_document = document.id AND id_utilisateur = ?";
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
	//fonction de changement de titre
	public void changerTitre(int id, String nouveauTitre) {
	    try {
	    	//conection a la BDD
	        Connection conn = DriverManager.getConnection(ParamBD.bdURL, ParamBD.bdLogin, ParamBD.bdPassword);
	        //requete sql
	        String sql = "UPDATE document SET titre = ? WHERE id = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        //argument de la requette
	        pst.setString(1, nouveauTitre);
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
	public String rechercheTitreAvecID(String id) {
		String titre = "";
		System.out.println("idDoc = " + id);
		try  {
	    	Connection conn = DriverManager.getConnection(ParamBD.bdURL, ParamBD.bdLogin, ParamBD.bdPassword);
	        String sql = "SELECT titre FROM document WHERE id = ?;";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        //argument de la requette
	        pst.setString(1,id);
	        //exécution de la requette
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            titre = rs.getString("titre");
	        }
	        // fermeuture
	        rs.close();
	        pst.close();
	        conn.close();
	        return titre;
		 } catch (SQLException e) {
		        e.printStackTrace();
		 }
		return "";
	}
	public static ArrayList<Utilisateur> rechercheUtilisateursAvecDroits(String id) {
		ArrayList<Utilisateur> users = new ArrayList<>();
        try {
        Connection conn = DriverManager.getConnection(ParamBD.bdURL, ParamBD.bdLogin, ParamBD.bdPassword);
        String sql =
            "SELECT * FROM utilisateur,lecture WHERE id_utilisateur = id AND id_document = ? " +
            "UNION " +
            "SELECT * FROM utilisateur,ecriture WHERE id_utilisateur = id AND id_document = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        	Utilisateur u = new Utilisateur(
                    rs.getInt("id"),
                    rs.getString("login")
            );
        	u.setDroitLecture(aDroitLecture(String.valueOf(u.getId()),id));
        	u.setDroitEcriture(aDroitEcriture(String.valueOf(u.getId()),id));
            users.add(u);
        }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return users;
    }
	public void ajouterDroitLecture(String idUtilisateur, String idDocument) {
	    try {
	    	Connection conn = DriverManager.getConnection(ParamBD.bdURL,ParamBD.bdLogin,ParamBD.bdPassword);
	        String sql = "INSERT IGNORE INTO lecture(id_utilisateur, id_document) VALUES (?, ?)";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, idUtilisateur);
	        ps.setString(2, idDocument);
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void ajouterDroitEcriture(String idUtilisateur, String idDocument) {
	    try {
	    	Connection conn = DriverManager.getConnection(ParamBD.bdURL,ParamBD.bdLogin,ParamBD.bdPassword);
	        String sql = "INSERT IGNORE INTO ecriture(id_utilisateur, id_document) VALUES (?, ?)";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, idUtilisateur);
	        ps.setString(2, idDocument);
	        ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public static boolean aDroitLecture(String idUtilisateur, String idDocument) {
	    try {
	    	Connection conn = DriverManager.getConnection(ParamBD.bdURL,ParamBD.bdLogin,ParamBD.bdPassword);
	        String sql = """
	            SELECT *
	            FROM lecture
	            WHERE id_utilisateur = ?
	            AND id_document = ?
	        """;
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, idUtilisateur);
	        ps.setString(2, idDocument);
	        ResultSet rs = ps.executeQuery();
	        return rs.next();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	public static boolean aDroitEcriture(String idUtilisateur, String idDocument) {
	    try {
	    	Connection conn = DriverManager.getConnection(ParamBD.bdURL,ParamBD.bdLogin,ParamBD.bdPassword);
	        String sql = """
	            SELECT *
	            FROM ecriture
	            WHERE id_utilisateur = ?
	            AND id_document = ?
	        """;
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, idUtilisateur);
	        ps.setString(2, idDocument);
	        ResultSet rs = ps.executeQuery();
	        return rs.next();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	public void supprimerDroitLecture(String idUtilisateur, String idDocument) {
	    try {
	    	Connection conn = DriverManager.getConnection(ParamBD.bdURL,ParamBD.bdLogin,ParamBD.bdPassword);
	        String sql = """
	            DELETE FROM lecture
	            WHERE id_utilisateur = ?
	            AND id_document = ?
	        """;
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, idUtilisateur);
	        ps.setString(2, idDocument);
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void supprimerDroitEcriture(String idUtilisateur, String idDocument) {
	    try {
	    	Connection conn = DriverManager.getConnection(ParamBD.bdURL,ParamBD.bdLogin,ParamBD.bdPassword);
	        String sql = """
	            DELETE FROM ecriture
	            WHERE id_utilisateur = ?
	            AND id_document = ?
	        """;
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, idUtilisateur);
	        ps.setString(2, idDocument);
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
