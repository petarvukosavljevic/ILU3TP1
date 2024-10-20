package jeu;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;

public class Jeu {
	    private Sabot sabot;

	    public Jeu() {
	        JeuDeCartes jeuDeCartes = new JeuDeCartes();
	        Carte[] tableauCartes = jeuDeCartes.donnerCartes();
	        List<Carte> listeCartes = Arrays.asList(tableauCartes);
	        Collections.shuffle(listeCartes);
	        Carte[] cartesMelangees = listeCartes.toArray(new Carte[0]);
	        this.sabot = new Sabot(cartesMelangees);
	    }
	    
	    public Sabot getSabot() {
	        return sabot;
	    }
	}

