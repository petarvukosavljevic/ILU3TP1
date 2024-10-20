package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Carte;

public class MainJoueur {
    private List<Carte> cartesEnMain = new ArrayList<>();

    public void prendre(Carte carte) {
        cartesEnMain.add(carte);
    }

    public void jouer(Carte carte) {
        assert cartesEnMain.contains(carte);
        cartesEnMain.remove(carte);
    }

    @Override
    public String toString() {
        return cartesEnMain.toString();
    }

    public List<Carte> getCartes() {
        return cartesEnMain;
    }
}
