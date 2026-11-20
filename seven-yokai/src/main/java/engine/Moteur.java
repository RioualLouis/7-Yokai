package engine;

import java.util.List;

import bot.BotAleatoire;
import model.Equipe;
import model.Joueur;
import model.Manche;

public class Moteur {
	private Joueur[] participants = new Joueur[4];
	private int nbParticipants = 0;
	private Equipe[] equipes = new Equipe[2];
	private int nbEquipes = 0;
	private Aleatoire rng;
	
	public Moteur(Aleatoire rng) {
		this.rng = rng;
	}
	
	/*!
	 * \brief Accesseur du nombre de participants.
	 * 
	 * \return Le nombre d participants dans la partie.
	 */
	public int getNbParticipants() {
		return nbParticipants;
	}
	
	/*!
	 * \brief Accesseur d'un participant de la partie.
	 * 
	 * \param id La position du participant dans le tableau.
	 * 
	 * \return Le joueur correspondant à la position dans le tableau donnée.
	 */
	public Joueur getParticipant(int id) {
		return participants[id];
	}
	
	/*!
	 * \brief Accesseur du nombre d'équipes.
	 * 
	 * \return Le nombre d'équipes dans la partie.
	 */
	public int getNbEquipes() {
		return nbEquipes;
	}
	
	/*!
	 * \brief Accesseur d'une équipe de la partie.
	 * 
	 * \param id La position de l'équipe dans le tableau.
	 * 
	 * \return L'équipe correspondant à la position dans le tableau donnée.
	 */
	public Equipe getEquipe(int id) {
		return equipes[id];
	}
	
	/*!
	 * \brief Ajoute un participant à la partie.
	 * 
	 * Le participant rejoint une équipe existante, ou en créé une nouvelle s'il n'y en a pas.
	 */
	public void ajouterParticipant() throws Exception {
		Equipe equipeJoueur = null;
		
		for (Equipe equipe : equipes) {
			if (equipe != null && equipe.getNbMembres() < 2) {
				equipeJoueur = equipe;
				equipe.ajouteMembre();
			}
		}
		
		if (equipeJoueur == null) {
			equipeJoueur = new Equipe();
			equipeJoueur.ajouteMembre();
			equipes[nbEquipes++] = equipeJoueur;
		}
		
		participants[nbParticipants++] = new Joueur(new BotAleatoire(rng), equipeJoueur);
	}
	
	/*!
	 * \brief Teste si la condition de fin de partie est rencontrée.
	 * 
	 * \return Un booléen indiquant si la partie est finie ou non.
	 */
	private boolean finPartie() {
		for (Equipe equipe : equipes) {
			if (equipe.getManchesRemportees() == 2) return true;
		}
		return false;
	}
	
	/*!
	 * \brief Lance une partie de jeu jusqu'à ce qu'un condition de victoire soit rencontrée.
	 * 
	 *  \param seed La graine à utiliser pour initialiser l'aléatoire.
	 */
	public Joueur[] lancerPartie() throws Exception {
		Joueur dernierVainqueur = null;
		
		while(!finPartie()) {
			Manche mancheActuelle = new Manche(List.of(participants), dernierVainqueur, rng);
			dernierVainqueur = mancheActuelle.jouerManche();
			
			for (Equipe equipe: equipes) {
				equipe.prepNouvelleManche();
			}
		}
		
		Joueur[] gagnants = new Joueur[2];
		int n = 0;
		for (int i = 0 ; i < participants.length ; i++) {
			if (participants[i].getEquipe().getManchesRemportees() == 2) gagnants[n++] = participants[i];
		}
		
		return gagnants;
	}
}
