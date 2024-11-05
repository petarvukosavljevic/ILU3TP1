package strategies;

import java.util.Set;
import java.util.TreeSet;

import cartes.Attaque;
import cartes.Botte;
import cartes.Carte;
import cartes.Type;
import jeu.Coup;
import jeu.Joueur;

public interface Presse extends Strategie, Priorite {
	
	private int comparerCartes(Joueur joueur, Carte carte1, Carte carte2) {
	    Integer comparaison = donnerPrioriteLimites(carte1, carte2);
	    if (comparaison != null) return comparaison;

	    comparaison = donnerPrioriteBornes(carte1, carte2);
	    if (comparaison != null) return comparaison;

	    Carte carteSommet = joueur.getZoneDeJeu().getPileBataille();
	    if (carteSommet instanceof Attaque attaque) {
	        Type typeProbleme = attaque.getType();
	        if (joueur.getZoneDeJeu().getBottes().contains(new Botte(typeProbleme))) {
	            typeProbleme = Type.FEU;
	        }
	        comparaison = donnerPrioriteBottes(typeProbleme, carte1, carte2);
	        if (comparaison != null) return comparaison;
	    }

	    comparaison = donnerPrioriteParades(carte1, carte2);
	    return comparaison != null ? comparaison : (Math.random() > 0.5 ? 1 : -1);
	}


	@Override
    default Set<Coup> trierCoups(Set<Coup> coups) {
        TreeSet<Coup> coupsTries = new TreeSet<>();
        coupsTries.addAll(coups);
        return coupsTries;
    }
}
