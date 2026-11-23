package engine;

import java.util.ArrayList;
import java.util.List;

import model.Equipe;
import model.Joueur;
import model.Manche;
import model.Strategie;
import ui.ObservateurPartie;

public class Moteur {
	private Joueur[] participants = new Joueur[4];
	private int nbParticipants = 0;
	private Equipe[] equipes = { new Equipe(), new Equipe() };
	private int nbEquipes = 0;
	private Aleatoire rng;

	private List<ObservateurPartie> observateurs = new ArrayList<ObservateurPartie>();

	public Moteur(Aleatoire rng) {
		this.rng = rng;
	}

	/*
	 * ! \brief Accesseur du nombre de participants.
	 * 
	 * \return Le nombre d participants dans la partie.
	 */
	public int getNbParticipants() {
		return nbParticipants;
	}

	/*
	 * ! \brief Accesseur d'un participant de la partie.
	 * 
	 * \param id La position du participant dans le tableau.
	 * 
	 * \return Le joueur correspondant à la position dans le tableau donnée.
	 */
	public Joueur getParticipant(int id) {
		return participants[id];
	}

	/*
	 * ! \brief Accesseur du nombre d'équipes.
	 * 
	 * \return Le nombre d'équipes dans la partie.
	 */
	public int getNbEquipes() {
		return nbEquipes;
	}

	/*
	 * ! \brief Accesseur d'une équipe de la partie.
	 * 
	 * \param id La position de l'équipe dans le tableau.
	 * 
	 * \return L'équipe correspondant à la position dans le tableau donnée.
	 */
	public Equipe getEquipe(int id) {
		return equipes[id];
	}

	/*
	 * ! \brief Ajoute un observateur à la partie.
	 * 
	 * \param observateur Un objet de type observateur à ajouter.
	 */
	public void attacher(ObservateurPartie observateur) {
		observateurs.add(observateur);
	}

	/*
	 * ! \brief Ajoute un participant à la partie.
	 * 
	 * \param nom Nom du joueur. \param type Stratégie du joueur, fournie par la
	 * classe main.
	 * 
	 * Le participant rejoint une équipe existante, ou en créé une nouvelle s'il n'y
	 * en a pas.
	 */
	public void ajouterParticipant(String nom, Strategie type) throws Exception {
		Equipe equipe = null;
		int slot = 0;

		switch (nbParticipants) {
		case 0 -> {
			slot = 1;
			equipe = equipes[0];
		}
		case 1 -> {
			slot = 1;
			equipe = equipes[1];
		}
		case 2 -> {
			slot = 2;
			equipe = equipes[0];
		}
		case 3 -> {
			slot = 2;
			equipe = equipes[1];
		}
		default -> throw new IllegalArgumentException("Unexpected value: " + nbParticipants);
		}

		Joueur joueur = new Joueur(nbParticipants, type, equipe, nom);
		equipe.ajouteMembre(joueur, slot);
		participants[nbParticipants++] = joueur;
	}

	/*
	 * ! \brief Teste si la condition de fin de partie est rencontrée.
	 * 
	 * \return Un booléen indiquant si la partie est finie ou non.
	 */
	private boolean finPartie() {
		for (Equipe equipe : equipes) {
			if (equipe.getManchesRemportees() == 2)
				return true;
		}
		return false;
	}

	/*
	 * ! \brief Lance une partie de jeu jusqu'à ce qu'un condition de victoire soit
	 * rencontrée.
	 * 
	 * \param seed La graine à utiliser pour initialiser l'aléatoire.
	 */
	public Joueur[] lancerPartie() throws Exception {
		Joueur dernierVainqueur = null;

		while (!finPartie()) {
			Manche mancheActuelle = new Manche(List.of(participants), List.of(equipes), dernierVainqueur, rng,
					observateurs);
			dernierVainqueur = mancheActuelle.jouerManche();

			for (Equipe equipe : equipes) {
				equipe.prepNouvelleManche();
			}
		}

		Joueur[] gagnants = new Joueur[2];
		int n = 0;
		for (int i = 0; i < participants.length; i++) {
			if (participants[i].getEquipe().getManchesRemportees() == 2)
				gagnants[n++] = participants[i];
		}

		return gagnants;
	}
}
