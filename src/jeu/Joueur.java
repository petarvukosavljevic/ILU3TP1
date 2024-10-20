package jeu;

import cartes.Carte;

public class Joueur {
    private String nom;
    private ZoneDeJeu zoneDeJeu;
    private MainJoueur mainJoueur;

    public Joueur(String nom) {
        this.nom = nom;
        this.zoneDeJeu = new ZoneDeJeu();
    }

    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof Joueur) {
	        Joueur joueur = (Joueur) obj;
	        return nom.equals(joueur.nom);
    	}
    	return false;
    }

    @Override
    public String toString() {
        return nom;
    }

    public ZoneDeJeu getZoneDeJeu() {
        return zoneDeJeu;
    }
    
    public void donner(Carte carte) {
        mainJoueur.prendre(carte);
    }

    public MainJoueur getMainJoueur() {
        return mainJoueur;
    }
    
    public Carte prendreCarte(Sabot sabot) {
        if (sabot.estVide()) {
            return null;
        } else {
            Carte carte = sabot.piocher();
            mainJoueur.prendre(carte);
            return carte;
        }
    }
    
    public int donnerKmParcourus() {
        return zoneDeJeu.donnerKmParcourus();
    }
    
    public void deposer(Carte carte) {
        zoneDeJeu.deposer(carte);
    }
    
    public boolean peutAvancer() {
        return zoneDeJeu.peutAvancer();
    }

    
    public boolean estDepotAutorise(Carte carte) {
        return zoneDeJeu.estDepotAutorise(carte);
    }


}
