package jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import cartes.Carte;
import cartes.JeuDeCartes;

public class Jeu {
    private List<Joueur> joueurs = new ArrayList<>();
    private Sabot sabot;
    private Iterator<Joueur> iterator;

    public void inscrire(Joueur... nouveauxJoueurs) {
        Collections.addAll(joueurs, nouveauxJoueurs);
    }
    
    public Jeu() {
        JeuDeCartes jeuDeCartes = new JeuDeCartes();
        Carte[] cartes = jeuDeCartes.donnerCartes();
        List<Carte> listeCartes = Arrays.asList(cartes);
        Collections.shuffle(listeCartes);
        sabot = new Sabot(listeCartes.toArray(new Carte[0]));
    }
    
    
    public List<Joueur> classement() {
        TreeSet<Joueur> classement = new TreeSet<>(new Comparator<Joueur>() {
            @Override
            public int compare(Joueur j1, Joueur j2) {
                return Integer.compare(j2.getZoneDeJeu().donnerKmParcourus(), j1.getZoneDeJeu().donnerKmParcourus());
            }
        });
        classement.addAll(joueurs);
        return new ArrayList<>(classement);
    }


    public void distribuerCartes() {
        for (int i = 0; i < 6; i++) {
            for (Joueur joueur : joueurs) {
                joueur.prendreCarte(sabot);
            }
        }
    }

    public Joueur donnerJoueurSuivant() {
        if (iterator == null || !iterator.hasNext()) {
            iterator = joueurs.iterator();
        }
        return iterator.next();
    }

    public String jouerTour(Joueur joueur) {
        Carte cartePiochee = joueur.prendreCarte(sabot);
        Coup coup = joueur.choisirCoup(new HashSet<>(joueurs));
        joueur.retirerDeLaMain(coup.getCarteJouee());
        if (coup.getJoueurCible() == null) {
            sabot.ajouterCarte(coup.getCarteJouee());
        } else {
            coup.getJoueurCible().getZoneDeJeu().deposer(coup.getCarteJouee());
        }
        return String.format("%s a pioche %s\n%s", joueur, cartePiochee, coup);
    }

    public String lancer() {
        StringBuilder resultat = new StringBuilder();
        while (true) {
            for (Joueur joueur : joueurs) {
                resultat.append(jouerTour(joueur)).append("\n");
                if (joueur.getZoneDeJeu().donnerKmParcourus() >= 1000) {
                    resultat.append("Jeu termine\nClassement final:\n");
                    for (Joueur j : classement()) {
                        resultat.append(j.toString()).append(" - ").append(j.getZoneDeJeu().donnerKmParcourus()).append(" km\n");
                    }
                    return resultat.toString();
                }
            }
            if (sabot.estVide()) {
                resultat.append("Sabot vide, fin de partie\nClassement final:\n");
                for (Joueur j : classement()) {
                    resultat.append(j.toString()).append(" - ").append(j.getZoneDeJeu().donnerKmParcourus()).append(" km\n");
                }
                return resultat.toString();
            }
        }
    }

}
