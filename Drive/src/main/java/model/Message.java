package model;

import java.sql.Timestamp;

public class Message {
    private int idDocument;
    private int idUtilisateur;
    private String message;
    private String nomUtilisateur;
    
    // constructeur vide
    public Message() {
    }

    // constructeur complet
    public Message(int idDocument,int idUtilisateur,String message) {
        this.idDocument = idDocument;
        this.idUtilisateur = idUtilisateur;
        this.message = message;
    }

    // getters setters

    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
    
    
}