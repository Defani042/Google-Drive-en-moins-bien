package model;

import java.io.Serializable;

public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String login;
	private boolean droitLecture;
	private boolean droitEcriture;
	
	//constructeur
	public Utilisateur(int idu, String lo) {
		id = idu;
		login = lo;
	}
	public Utilisateur() {};
	
	
	//getter et setter
	public String getLogin() {
		return login;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public boolean isDroitLecture() {
		return droitLecture;
	}
	
	public void setDroitLecture(boolean droitLecture) {
		this.droitLecture = droitLecture;
	}
	 
	public boolean isDroitEcriture() {
		return droitEcriture;
	 }
	 
	public void setDroitEcriture(boolean droitEcriture) {
		this.droitEcriture = droitEcriture;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}

