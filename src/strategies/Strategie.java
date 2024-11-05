package strategies;

import java.util.Set;
import java.util.TreeSet;

import jeu.Coup;

public interface Strategie {

    default Set<Coup> trierCoups(Set<Coup> coups) {
        TreeSet<Coup> coupsTries = new TreeSet<>((c1, c2) -> c1.equals(c2) ? 0 : (Math.random() > 0.5 ? 1 : -1));
        coupsTries.addAll(coups);
        return coupsTries;
    }

    Coup selectionnerCoup(Set<Coup> coups);

    Coup selectionnerDefausse(Set<Coup> coups);
}
