package cartes;

public abstract class Limite extends Carte {
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Carte) {
			Limite carte = (Limite) obj;
			return this.toString().equals(carte.toString());
		}
		return false;
	}
}
