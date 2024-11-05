package jeu;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import cartes.Carte;
import strategies.Strategie;

public class Joueur {
    private String nom;
    private ZoneDeJeu zoneDeJeu = new ZoneDeJeu();
    private MainJoueur mainJoueur = new MainJoueur();
    private Strategie strategie = new Strategie() {
        @Override
        public Coup selectionnerCoup(Set<Coup> coups) {
            return trierCoups(coups).iterator().next();
        }

        @Override
        public Coup selectionnerDefausse(Set<Coup> coups) {
            return trierCoups(coups).iterator().next();
        }
    };

    public Joueur(String nom) {
        this.nom = nom;
    }
    
    public ZoneDeJeu getZoneDeJeu() {
        return zoneDeJeu;
    }


    public Carte prendreCarte(Sabot sabot) {
        Carte carte = sabot.piocher();
        mainJoueur.prendre(carte);
        return carte;
    }


    public void retirerDeLaMain(Carte carte) {
        mainJoueur.jouer(carte);
    }

    public void setStrategie(Strategie strategie) {
        this.strategie = strategie;
    }

    public Set<Coup> coupsPossibles(Set<Joueur> participants) {
        Set<Coup> coups = new HashSet<>();
        for (Carte carte : mainJoueur.getCartes()) {
            for (Joueur participant : participants) {
                Coup coup = new Coup(this, carte, participant);
                if (coup.estValide()) {
                    coups.add(coup);
                }
            }
        }
        return coups;
    }

    public Set<Coup> coupsDefausse() {
        Set<Coup> coups = new HashSet<>();
        for (Carte carte : mainJoueur.getCartes()) {
            coups.add(new Coup(this, carte, null));
        }
        return coups;
    }

    public Coup choisirCoup(Set<Joueur> participants) {
        Set<Coup> coupsPossibles = coupsPossibles(participants);
        return coupsPossibles.isEmpty() ? strategie.selectionnerDefausse(coupsDefausse())
                                        : strategie.selectionnerCoup(coupsPossibles);
    }

    public String afficherEtatJoueur() {
        return String.format("Bottes: %s, Limitation: %s, Pile de bataille: %s, Main: %s", 
            zoneDeJeu.getBottes(),
            zoneDeJeu.donnerLimitationVitesse() < 200, 
            zoneDeJeu.getPileBataille(),
            mainJoueur);
    }
    
    public int compareTo(Joueur other) {
        int kmComparison = Integer.compare(this.getZoneDeJeu().donnerKmParcourus(), other.getZoneDeJeu().donnerKmParcourus());
        return kmComparison == 0 ? this.nom.compareTo(other.nom) : kmComparison;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Joueur joueur = (Joueur) obj;
        return nom.equals(joueur.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return nom;
    }
}
