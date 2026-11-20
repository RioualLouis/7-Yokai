package engine;

import model.Joueur;

public class Main {
	public static void main(String[] args) throws Exception {
		Moteur moteurDeJeu = new Moteur(new Aleatoire(-1));
		
		for (int i = 0 ; i < 4 ; i++) {
			moteurDeJeu.ajouterParticipant();
		}
		
		Joueur[] gagnants = moteurDeJeu.lancerPartie();
		
		System.out.println("Les gagnants sont ... Joueur " + gagnants[0] + " et Joueur " + gagnants[1]);
	}
}

