package model;

public class Document {
	private int id;
    private String titre;
    private String contenu; 
    private int proprietaireId;
    
    //constructeur 
    public Document() {}
    
    
    public Document(int id, String titre, String contenu, int proprietaireId) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.proprietaireId = proprietaireId;
    }
    
    // GETTERS / SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public int getProprietaireId() { return proprietaireId; }
    public void setProprietaireId(int proprietaireId) { this.proprietaireId = proprietaireId; }
}
