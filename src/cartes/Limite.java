package cartes;

public abstract class Limite extends Carte {
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Carte) {
			Carte carte = (Carte) obj;
			return this.toString().equals(carte.toString());
		}
		return false;
	}
}
