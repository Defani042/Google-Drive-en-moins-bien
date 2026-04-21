package model;

public class Utilisateur {
	private int id;
	private String login;
	
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
}

