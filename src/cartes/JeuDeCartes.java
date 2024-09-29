package cartes;

public class JeuDeCartes {
	private Configuration[] typeDeCartes = {
			new Configuration(new Borne(25), 10),
            new Configuration(new Borne(50), 10),
            new Configuration(new Borne(75), 10),
            new Configuration(new Borne(100), 12),
            new Configuration(new Borne(200), 4),
            new Configuration(new Attaque(Type.FEU), 5),
            new Configuration(new Parade(Type.FEU), 14),
            new Configuration(new Botte(Type.FEU), 1),
            new Configuration(new Attaque(Type.ESSENCE), 3),
            new Configuration(new Parade(Type.ESSENCE), 6),
            new Configuration(new Botte(Type.ESSENCE), 1),
            new Configuration(new Attaque(Type.CREVAISON), 3),
            new Configuration(new Parade(Type.CREVAISON), 6),
            new Configuration(new Botte(Type.CREVAISON), 1),
            new Configuration(new Attaque(Type.ACCIDENT), 3),
            new Configuration(new Parade(Type.ACCIDENT), 6),
            new Configuration(new Botte(Type.ACCIDENT), 1),
            new Configuration(new DebutLimite(), 4),
            new Configuration(new FinLimite(), 6)
	};
	
	public String affichageJeuCartes() {
		StringBuilder returnString = new StringBuilder();
		for(int i=0; i < typeDeCartes.length; i++) {
			String ligne = typeDeCartes[i].getNbExemplaires() + " " + typeDeCartes[i].getCarte().toString() + "\n";
			returnString.append(ligne);
		}
		return returnString.toString();
	}
	
	private class Configuration extends Carte{
		private int nbExemplaires;
		private Carte carte;
		
		private Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}
		
		public Carte getCarte() {
			return carte;
		}
		
		
		public int  getNbExemplaires() {
			return nbExemplaires;
		}
		 
	}
}
