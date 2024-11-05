package testsFonctionnels;

import java.util.Set;
import jeu.Jeu;
import jeu.Joueur;
import jeu.Coup;
import strategies.Presse;
import strategies.Strategie;

public class TestJeu {
    public static void main(String[] args) {
        Jeu jeu = new Jeu();

        Joueur joueur1 = new Joueur("Jack");
        Joueur joueur2 = new Joueur("Bill");
        Joueur joueur3 = new Joueur("Luffy");

        Strategie strategieJoueurPresse = new Presse() {
            @Override
            public Coup selectionnerCoup(Set<Coup> coups) {
                return trierCoups(coups).iterator().next();
            }

            @Override
            public Coup selectionnerDefausse(Set<Coup> coups) {
                return trierCoups(coups).iterator().next();
            }
        };
        joueur1.setStrategie(strategieJoueurPresse);
        
        joueur2.setStrategie(new Strategie() {
            @Override
            public Coup selectionnerCoup(Set<Coup> coups) {
                return trierCoups(coups).iterator().next();
            }

            @Override
            public Coup selectionnerDefausse(Set<Coup> coups) {
                return trierCoups(coups).iterator().next();
            }
        });

        joueur3.setStrategie(new Strategie() {
            @Override
            public Coup selectionnerCoup(Set<Coup> coups) {
                return trierCoups(coups).iterator().next();
            }

            @Override
            public Coup selectionnerDefausse(Set<Coup> coups) {
                return trierCoups(coups).iterator().next();
            }
        });

        jeu.inscrire(joueur1, joueur2, joueur3);
        jeu.distribuerCartes();
        String resultat = jeu.lancer();
        System.out.println(resultat);
    }
}
