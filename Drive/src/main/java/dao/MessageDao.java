package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Message;

public class MessageDao {
	
	//mettre un message dans la BDD
	public static void sauvegardeMessage(Message m) {
		 try {

			 Connection conn = DriverManager.getConnection(
		                ParamBD.bdURL,
		                ParamBD.bdLogin,
		                ParamBD.bdPassword
		        );

		        String sql = """
		            INSERT INTO message (id_document, id_utilisateur, message)
		            VALUES (?, ?, ?)
		        """;

		        PreparedStatement ps = conn.prepareStatement(sql);

		        ps.setInt(1, m.getIdDocument());
		        ps.setInt(2, m.getIdUtilisateur());
		        ps.setString(3, m.getMessage());

		        ps.executeUpdate();

		        ps.close();
		        conn.close();

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
	public static ArrayList<Message> getMessagesByDocument(int idDocument) {

		ArrayList<Message> messages = new ArrayList<>();

	    try {

	    	Connection conn = DriverManager.getConnection(
	                ParamBD.bdURL,
	                ParamBD.bdLogin,
	                ParamBD.bdPassword
	        );

	        String sql = """
	            SELECT id_document, id_utilisateur, message,login
	            FROM message, utilisateur
	            WHERE id_utilisateur = utilisateur.id
	            AND id_document = ?
	            ORDER BY message.id ASC
	        """;

	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, idDocument);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            Message m = new Message();

	            m.setIdDocument(rs.getInt("id_document"));
	            m.setIdUtilisateur(rs.getInt("id_utilisateur"));
	            m.setMessage(rs.getString("message"));
	            m.setNomUtilisateur(rs.getString("login"));

	            messages.add(m);
	        }

	        rs.close();
	        ps.close();
	        conn.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return messages;
	}
	
}
