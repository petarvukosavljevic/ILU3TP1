package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;

import cartes.Carte;

public class GestionCartes {

	public static Carte extraire(List<Carte> cartes) {
	    Random rand = new Random();
	    return cartes.remove(rand.nextInt(cartes.size()));
	}

	public static Carte extraireAvecIterator(List<Carte> cartes) {
	    Random rand = new Random();
	    ListIterator<Carte> it = cartes.listIterator();
	    int index = rand.nextInt(cartes.size());
	    while (it.hasNext()) {
	        if (index == 0) {
	            Carte carte = it.next();
	            it.remove();
	            return carte;
	        }
	        it.next();
	        index--;
	    }
	    return null;
	}
	
	
	public static List<Carte> melanger(List<Carte> cartes) {
	    List<Carte> melange = new ArrayList<>();
	    while (!cartes.isEmpty()) {
	        melange.add(extraire(cartes));
	    }
	    return melange;
	}
	
	
	public static boolean verifierMelange(List<Carte> liste1, List<Carte> liste2) {
	    if (liste1.size() != liste2.size()) {
	        return false;
	    }
	    for (Carte carte : liste1) {
	        if (Collections.frequency(liste1, carte) != Collections.frequency(liste2, carte)) {
	            return false;
	        }
	    }
	    return true;
	}
	
	
	
	public static List<Carte> rassembler(List<Carte> cartes) {
	    Map<Carte, Integer> compteur = new HashMap<>();
	    for (Carte carte : cartes) {
	        compteur.put(carte, compteur.getOrDefault(carte, 0) + 1);
	    }

	    List<Carte> rassemblée = new ArrayList<>();
	    for (Map.Entry<Carte, Integer> entry : compteur.entrySet()) {
	        for (int i = 0; i < entry.getValue(); i++) {
	            rassemblée.add(entry.getKey());
	        }
	    }
	    return rassemblée;
	}


	public static boolean verifierRassemblement(List<Carte> listeCartes) {
		if(listeCartes.isEmpty()) return true;
		ListIterator<Carte> it1 = listeCartes.listIterator();
		while (it1.hasNext()) {
			ListIterator<Carte> it1temp = it1;
			it1.next();
			if (it1temp == it1) it1.next();
			else {
				ListIterator<Carte> it2 = it1;
				it2.next();
				while (it2.hasNext()) {
					if (it2 == it1) {
						return false;
					}
					else {
						it2.next();
					}
				}
			}
		}
		return true;
	}


	
}
