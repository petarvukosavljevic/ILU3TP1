package cartes;

public class Borne extends Carte {
	private int km;

	public Borne(int km) {
		super();
		this.km = km;
	}
	
	public String toString() {
		return this.km + "KM";
	}
}
