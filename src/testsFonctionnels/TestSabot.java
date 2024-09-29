package testsFonctionnels;

import java.util.Iterator;

import cartes.Attaque;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Parade;
import cartes.Type;
import jeu.Sabot;

public class TestSabot {
	
	public static void main(String[] args) {
		Carte[] cartes = {
				new Borne(25),
				new Borne(50),
				new Borne(75),
				new Borne(100),
				new Borne(200),
				new Attaque(Type.FEU),
				new Parade(Type.FEU),
				new Botte(Type.FEU),
				new Attaque(Type.ESSENCE),
				new Parade(Type.ESSENCE),
				new Botte(Type.ESSENCE),
				new Attaque(Type.CREVAISON),
				new Parade(Type.CREVAISON),
				new Botte(Type.CREVAISON),
				new Attaque(Type.ACCIDENT),
				new Parade(Type.ACCIDENT),
				new Botte(Type.ACCIDENT),
				new DebutLimite(),
				new FinLimite()
		};
		Sabot sabot = new Sabot(cartes);
		
// EXERCICE 2.a)		
//		while (true) {
//			try {
//				System.out.println("je pioche " + sabot.piocher());
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//				break;
//			}
//		}
//		
		
// EXERCICE 2.b)
//		Iterator<Carte> iterator = sabot.iterator();
//		while (true) {
//			try {
//				System.out.println("je pioche " + iterator.next());
//				iterator.remove();
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//				break;
//			}
//		}

// EXERCICE 2.c)
		Iterator<Carte> iterator = sabot.iterator();
//		System.out.println("je pioche " + sabot.piocher());
		while (true) {
			try {
				sabot.ajouterCarte(new Botte(Type.ACCIDENT));
				System.out.println("je pioche " + iterator.next());
				iterator.remove();
			}
			catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		
	}

}
