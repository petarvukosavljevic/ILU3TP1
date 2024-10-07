package cartes;

public abstract class Probleme extends Carte {
	private Type type;

	protected Probleme(Type type) {
		super();
		this.type = type;
	}
	
	public Type getType() {
		return this.type;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Carte) {
			Probleme carte = (Probleme) obj;
			return this.toString().equals(carte.toString());
		}
		return false;
	}
	
	
	
	
}
