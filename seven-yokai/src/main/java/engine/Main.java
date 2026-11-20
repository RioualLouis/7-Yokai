package engine;

import model.Joueur;
import ui.AffichageConsole;

public class Main {
	public static void main(String[] args) throws Exception {
		
		Moteur moteurDeJeu = new Moteur(new Aleatoire(-1));
		
		for (int i = 0 ; i < 4 ; i++) {
			moteurDeJeu.ajouterParticipant();
		}
		
		// Ajout des observateurs
		AffichageConsole affichageConsole = new AffichageConsole();
		moteurDeJeu.attacher(affichageConsole);
		
		Joueur[] gagnants = moteurDeJeu.lancerPartie();
		
		System.out.println("Les gagnants sont ... Joueur " + gagnants[0] + " et Joueur " + gagnants[1]);
	}
}

