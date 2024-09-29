package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte>{
	private Carte[] cartes;
	private int nbCartes;
	private int modCount;
	private Iterator<Carte> iterator;

	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		this.nbCartes = cartes.length;
		this.modCount = 0;
		this.iterator = iterator();
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) throws IllegalStateException {
		if (nbCartes == cartes.length) {
			throw new IllegalStateException("Capacite maximale atteinte");
		}
		this.cartes[nbCartes++] = carte;
		modCount++;
	}
	
	@Override
	public Iterator<Carte> iterator() {
		// TODO Auto-generated method stub
		return new SabotIterator();
	}
	
	private class SabotIterator implements Iterator<Carte> {
		private int currentIndex = 0;
		private int expectedModCount = modCount;

		private void checkForConcurrentMod() {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException("Modification concurrente detectee");
			}
		}
		
		@Override
		public boolean hasNext() {
			checkForConcurrentMod();
			return currentIndex < nbCartes;
		}

		@Override
		public Carte next() {
			checkForConcurrentMod();
			if (!hasNext()) {
				throw new NoSuchElementException("Pas de carte suivante");
			}
			return cartes[currentIndex++];
		}
		
		@Override
		public void remove() {
			checkForConcurrentMod();
			if (currentIndex <= 0) {
				throw new IllegalStateException("Impossible de supprimer avant de faire next()");
			}
			for (int i = currentIndex; i < nbCartes; i++) {
				cartes[i-1] = cartes[i];
			}
			cartes[--nbCartes] = null;
			modCount++;
			expectedModCount++;
			currentIndex--;
		}
		
	}
	
	public Carte piocher() {
		if (estVide()) {
			throw new NoSuchElementException("Le sabot est vide");
		}
		Carte premiereCarte = iterator.next();
		iterator.remove();
		return premiereCarte;
	}
	
	

}
