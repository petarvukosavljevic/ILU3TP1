package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
    private List<Carte> limites = new ArrayList<>();
    private List<Carte> bataille = new ArrayList<>();
    private List<Borne> bornes = new ArrayList<>();
    private HashSet<Botte> bottes = new HashSet<>();

    public int donnerKmParcourus() {
        int totalKm = 0;
        for (Borne borne : bornes) {
            totalKm += borne.getKm();
        }
        return totalKm;
    }

    public HashSet<Botte> getBottes() {
        return bottes;
    }

    public Carte getPileBataille() {
        return bataille.isEmpty() ? null : bataille.get(bataille.size() - 1);
    }

    
    public boolean estPrioritaire() {
        return bottes.contains(new Botte(Type.FEU));
    }

    public int donnerLimitationVitesse() {
        if (estPrioritaire()) {
            return 200;
        }
        return limites.isEmpty() || limites.get(limites.size() - 1) instanceof FinLimite ? 200 : 50;
    }

    public void deposer(Carte carte) {
        if (carte instanceof Borne) {
            bornes.add((Borne) carte);
        } else if (carte instanceof Limite) {
            limites.add(carte);
        } else if (carte instanceof Botte) {
            bottes.add((Botte) carte);
        } else {
            bataille.add(carte);
        }
    }

    public boolean peutAvancer() {
        if (estPrioritaire() && bataille.isEmpty()) {
            return true;
        }
        if (!bataille.isEmpty()) {
            Carte topCarte = bataille.get(bataille.size() - 1);
            return (topCarte instanceof Parade && estPrioritaire()) ||
                   (topCarte instanceof Attaque && ((Attaque) topCarte).getType().equals(Type.FEU) && estPrioritaire());
        }
        return false;
    }

    public boolean estDepotFeuVertAutorise() {
        if (estPrioritaire()) {
            return false;
        }
        if (bataille.isEmpty()) {
            return true;
        }
        Carte topCarte = bataille.get(bataille.size() - 1);
        return topCarte instanceof Attaque && ((Attaque) topCarte).getType().equals(Type.FEU);
    }

    public boolean estDepotLimiteAutorise(Limite limite) {
        return !estPrioritaire();
    }

    public boolean estDepotBatailleAutorise(Bataille bataille) {
        if (bataille instanceof Attaque && bottes.contains(new Botte(((Attaque) bataille).getType()))) {
            return false;
        }
        return true;
    }
    
    

    public boolean estDepotAutorise(Carte carte) {
        if (carte instanceof Botte) {
            return !bottes.contains(carte);
        } else if (carte instanceof Borne) {
            return estDepotFeuVertAutorise();
        } else if (carte instanceof Limite) {
            return estDepotLimiteAutorise((Limite) carte);
        } else if (carte instanceof Bataille) {
            return estDepotBatailleAutorise((Bataille) carte);
        }
        return false;
    }

}
