package cartes;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class JeuDeCartes {
    private Map<Carte, Integer> typeDeCartes;

    public JeuDeCartes() {
        typeDeCartes = new HashMap<>();
        typeDeCartes.put(new Borne(25), 10);
        typeDeCartes.put(new Borne(50), 10);
        typeDeCartes.put(new Borne(75), 10);
        typeDeCartes.put(new Borne(100), 12);
        typeDeCartes.put(new Borne(200), 4);
        typeDeCartes.put(new Attaque(Type.FEU), 5);
        typeDeCartes.put(new Parade(Type.FEU), 14);
        typeDeCartes.put(new Botte(Type.FEU), 1);
        typeDeCartes.put(new Attaque(Type.ESSENCE), 3);
        typeDeCartes.put(new Parade(Type.ESSENCE), 6);
        typeDeCartes.put(new Botte(Type.ESSENCE), 1);
        typeDeCartes.put(new Attaque(Type.CREVAISON), 3);
        typeDeCartes.put(new Parade(Type.CREVAISON), 6);
        typeDeCartes.put(new Botte(Type.CREVAISON), 1);
        typeDeCartes.put(new Attaque(Type.ACCIDENT), 3);
        typeDeCartes.put(new Parade(Type.ACCIDENT), 6);
        typeDeCartes.put(new Botte(Type.ACCIDENT), 1);
        typeDeCartes.put(new DebutLimite(), 4);
        typeDeCartes.put(new FinLimite(), 6);
    }

    public Map<Carte, Integer> getTypeDeCartes() {
        return typeDeCartes;
    }
    

    public String affichageJeuCartes() {
        StringBuilder returnString = new StringBuilder();
        for (Entry<Carte, Integer> entry : typeDeCartes.entrySet()) {
            String ligne = entry.getValue() + " " + entry.getKey().toString() + "\n";
            returnString.append(ligne);
        }
        return returnString.toString();
    }

    public Carte[] donnerCartes() {
        int totalCartes = typeDeCartes.values().stream().mapToInt(Integer::intValue).sum();
        Carte[] cartes = new Carte[totalCartes];
        int index = 0;
        for (Entry<Carte, Integer> entry : typeDeCartes.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                cartes[index++] = entry.getKey();
            }
        }
        return cartes;
    }
}

