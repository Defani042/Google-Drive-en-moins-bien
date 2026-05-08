package model;

public class Utilisateur {
	private int id;
	private String login;
	private boolean droitLecture;
	private boolean droitEcriture;
	
	//constructeur
	public Utilisateur(int idu, String lo) {
		id = idu;
		login = lo;
	}
	
	
	//getter et setter
	public String getLogin() {
		return login;
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
}

