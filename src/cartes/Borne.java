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
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Carte) {
			Carte carte = (Carte) obj;
			return this.toString().equals(carte.toString());
		}
		return false;
	}
	
	public int getKm() {
		return km;
	}
	
	 public int compareTo(Borne other) {
	        return Integer.compare(this.km, other.km);
	    }
	
	
}
