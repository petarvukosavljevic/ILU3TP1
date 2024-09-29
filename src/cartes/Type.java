package cartes;

public enum Type {
    FEU ("Feu Vert", "Feu Rouge", "Vehicule Prioritaire"), 
    ESSENCE ("Panne", "Essence", "Citerne"), 
    CREVAISON ("Crevaison", "Roue Secours", "Increvable"), 
    ACCIDENT ("Accident", "Reparations", "As du Volant");


    private String Attaque;
    private String Parade;
    private String Botte;
    Type(String Attaque, String Parade, String Botte) {
        this.Attaque = Attaque;
        this.Parade = Parade;
        this.Botte = Botte;
    }
	public String getAttaque() {
		return Attaque;
	}
	public String getParade() {
		return Parade;
	}
	public String getBotte() {
		return Botte;
	}

    
}