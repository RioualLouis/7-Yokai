package engine;

import java.util.List;

import bot.BotAleatoire;
import model.Equipe;
import model.Joueur;
import model.Manche;

public class Moteur {
	private static Joueur[] participants = new Joueur[4];
	private static Equipe[] equipes = new Equipe[2];
	private static Aleatoire rng;
	
	/*!
	 * \brief Ajoute un participant à la partie.
	 * 
	 * Le participant rejoint une équipe existante, ou en créé une nouvelle s'il n'y en a pas.
	 */
	public static void ajouterParticipant() {
		Equipe equipeJoueur = null;
		
		for (Equipe equipe : equipes) {
			if (equipe != null && equipe.getNbMembres() < 2) equipeJoueur = equipe;
		}
		
		if (equipeJoueur == null) {
			equipeJoueur = new Equipe();
			equipeJoueur.ajouteMembre();
			equipes[equipes.length] = equipeJoueur;
		}
		
		participants[participants.length] = new Joueur(new BotAleatoire(rng), equipeJoueur);
	}
	
	/*!
	 * \brief Teste si la condition de fin de partie est rencontrée.
	 * 
	 * \return Un booléen indiquant si la partie est finie ou non.
	 */
	private static boolean finPartie() {
		for (Joueur joueur : participants) {
			if (joueur.getEquipe().getManchesRemportees() == 2) return true;
		}
		return false;
	}
	
	/*!
	 * \brief Lance une partie de jeu jusqu'à ce qu'un condition de victoire soit rencontrée.
	 * 
	 *  \param seed La graine à utiliser pour initialiser l'aléatoire.
	 */
	public static Joueur[] lancerPartie(long seed) throws Exception {
		rng = (seed < 0) ? new Aleatoire() : new Aleatoire(seed);
		Joueur dernierVainqueur = null;
		
		while(!finPartie()) {
			Manche mancheActuelle = new Manche(List.of(participants), dernierVainqueur, rng);
			dernierVainqueur = mancheActuelle.jouerManche();
		}
		
		Joueur[] gagnants = new Joueur[2];
		for (int i = 0 ; i < participants.length ; i++) {
			if (participants[i].getEquipe().getManchesRemportees() == 2) gagnants[i] = participants[i];
		}
		
		return gagnants;
	}
}
