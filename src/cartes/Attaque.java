package cartes;

public class Attaque extends Bataille {

	public Attaque(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return this.getType().getAttaque();
	}
}
