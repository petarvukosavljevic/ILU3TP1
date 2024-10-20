package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
    private List<Carte> limites = new ArrayList<>();
    private List<Carte> bataille = new ArrayList<>();
    private List<Borne> bornes = new ArrayList<>();

    public int donnerLimitationVitesse() {
        if (limites.isEmpty() || limites.get(limites.size() - 1) instanceof FinLimite) {
            return 200;
        } else {
            return 50;
        }
    }

    public int donnerKmParcourus() {
        int totalKm = 0;
        for (Borne borne : bornes) {
            totalKm += borne.getKm();
        }
        return totalKm;
    }

    public void deposer(Carte carte) {
        if (carte instanceof Borne) {
            bornes.add((Borne) carte);
        } else if (carte instanceof Limite) {
            limites.add(carte);
        } else {
            bataille.add(carte);
        }
    }

    public boolean estDepotBorneAutorise() {
        return peutAvancer() && donnerKmParcourus() + 100 <= 1000 && donnerLimitationVitesse() >= 100;
    }

    public boolean estDepotFeuVertAutorise() {
        if (bataille.isEmpty()) {
            return true;
        }
        Carte topCarte = bataille.get(bataille.size() - 1);
        return topCarte instanceof Attaque && ((Attaque) topCarte).getType().equals(Type.FEU);
    }

    public boolean estDepotBatailleAutorise(Bataille bataille) {
        if (bataille instanceof Attaque) {
            if (!this.bataille.isEmpty() && this.bataille.get(this.bataille.size() - 1) instanceof Attaque) {
                return false; 
            }
            return !peutAvancer();
        } else if (bataille instanceof Parade) {
            Parade parade = (Parade) bataille;
            if (parade.getType().equals(Type.FEU)) {
                return estDepotFeuVertAutorise();
            } else {
                if (!this.bataille.isEmpty() && this.bataille.get(this.bataille.size() - 1) instanceof Attaque) {
                    Attaque topAttaque = (Attaque) this.bataille.get(this.bataille.size() - 1);
                    return topAttaque.getType().equals(parade.getType());
                }
            }
        }
        return false;
    }

    public boolean estDepotLimiteAutorise(Limite limite) {
        if (limite instanceof DebutLimite) {
            return limites.isEmpty() || limites.get(limites.size() - 1) instanceof FinLimite;
        } else if (limite instanceof FinLimite) {
            return !limites.isEmpty() && limites.get(limites.size() - 1) instanceof DebutLimite;
        }
        return false;
    }

    public boolean estDepotAutorise(Carte carte) {
        if (carte instanceof Borne) {
            return estDepotBorneAutorise();
        } else if (carte instanceof Limite) {
            return estDepotLimiteAutorise((Limite) carte);
        } else if (carte instanceof Bataille) {
            return estDepotBatailleAutorise((Bataille) carte);
        }
        return false;
    }

    public boolean peutAvancer() {
        if (!bataille.isEmpty()) {
            Carte topCarte = bataille.get(bataille.size() - 1);
            return topCarte instanceof Parade && ((Parade) topCarte).getType().equals(Type.FEU);
        }
        return false;
    }
}
