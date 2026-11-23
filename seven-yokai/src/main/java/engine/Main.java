package engine;

import bot.BotAleatoire;
import model.Joueur;
import ui.AffichageConsole;

public class Main {
	public static void main(String[] args) throws Exception {
		Aleatoire random = new Aleatoire(14);
		Moteur moteurDeJeu = new Moteur(random);

		String[] pseudosBot = new String[4];
		int[] selectedNames = random.randomSample(4, 0, Joueur.TROMBINOSCOPE.length);
		for (int i = 0; i < pseudosBot.length; i++)
			pseudosBot[i] = Joueur.TROMBINOSCOPE[selectedNames[i]];

		for (int i = 0; i < 4; i++) {
			// Type du joueur à modifier pour suivre les paramètres de la partie (bot ou
			// joueur physique)
			moteurDeJeu.ajouterParticipant(pseudosBot[i], new BotAleatoire(random));
		}

		// Ajout des observateurs
		AffichageConsole affichageConsole = new AffichageConsole();
		moteurDeJeu.attacher(affichageConsole);

		Joueur[] gagnants = moteurDeJeu.lancerPartie();

		System.out.println(
				"Les gagnants sont ... Joueur " + gagnants[0].getName() + " et Joueur " + gagnants[1].getName());
	}
}
