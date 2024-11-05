package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.Limite;

public class Coup implements Comparable<Coup>{
    private Joueur joueurCourant;
    private Carte carteJouee;
    private Joueur joueurCible;

    public Coup(Joueur joueurCourant, Carte carteJouee, Joueur joueurCible) {
        this.joueurCourant = joueurCourant;
        this.carteJouee = carteJouee;
        this.joueurCible = joueurCible;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public Carte getCarteJouee() {
        return carteJouee;
    }

    public Joueur getJoueurCible() {
        return joueurCible;
    }

    public boolean estValide() {
        if (carteJouee instanceof Attaque || carteJouee instanceof Limite) {
            return joueurCible != null && !joueurCourant.equals(joueurCible);
        }
        return joueurCible == null || joueurCourant.equals(joueurCible);
    }

    @Override
    public String toString() {
        return joueurCible == null ? String.format("defausse la carte %s", carteJouee)
                : String.format("depose la carte %s dans la zone de jeu de %s", carteJouee, joueurCible);
    }
    
    @Override
    public int compareTo(Coup coup) {
        Joueur cibleDuCoup = coup.getJoueurCible();
        if (joueurCourant.equals(joueurCible) && joueurCourant.equals(cibleDuCoup)) {
            return 0;
        }
        if (joueurCible == null && cibleDuCoup == null) {
            return 0;
        }
        if (joueurCourant.equals(joueurCible)) {
            return 1;
        }
        if (joueurCourant.equals(cibleDuCoup)) {
            return -1;
        }
        return joueurCible.compareTo(cibleDuCoup);
    }
}
