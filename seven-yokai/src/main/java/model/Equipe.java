/*!
 * \file Equipe.java
 * 
 * Equipe dont 2 joueurs peuvent faire partie. Garde en mémoire les acquis
 * de l'équipe pour vérifier les conditions de fin de manche. 
 */

package model;

public class Equipe {
	private Joueur slot1;
	private Joueur slot2;
	private int bossGagnes = 0;
	private int plisGagnes = 0;
	private int manchesRemportees = 0;
	private int nbMembres = 0;

	/*
	 * ! \brief Accesseur slot d'équipier n°1.
	 */
	public Joueur getSlot1() {
		return this.slot1;
	}

	/*
	 * ! \brief Accesseur slot d'équipier n°2.
	 */
	public Joueur getSlot2() {
		return this.slot2;
	}

	/*
	 * ! \brief Remplisseur du slot d'équipier n°1.
	 */
	private void fillSlot1(Joueur j) {
		this.slot1 = j;
	}

	/*
	 * ! \brief Remplisseur du slot d'équipier n°2.
	 */
	private void fillSlot2(Joueur j) {
		this.slot2 = j;
	}

	/*
	 * ! \brief Accesseur pour le nombre de boss gagnés par l'équipe
	 */
	public int getBossGagnes() {
		return bossGagnes;
	}

	/*
	 * ! \brief Accesseur pour le nombre de plis gagnés par l'équipe
	 */
	public int getPlisGagnes() {
		return plisGagnes;
	}

	/*
	 * ! \brief Accesseur pour le nombre de manches gagnés par l'équipe
	 */
	public int getManchesRemportees() {
		return manchesRemportees;
	}

	/*
	 * ! \brief Augmente le nombre de boss gagnés par l'équipe.
	 * 
	 * \param nombre Valeur à incrémenter au nombre de boss gagnés.
	 */
	public void attribuerBoss(int nombre) {
		bossGagnes += nombre;
	}

	/*
	 * ! \brief Augmente le nombre de plis remportés par l'équipe.
	 * 
	 * \param nombre Valeur à incrémenter au nombre de plis gagnés.
	 */
	public void attribuerPli(int nombre) {
		if (nombre < 0)
			throw new IllegalArgumentException("Impossible d'ajouter un nombre de plis négatifs.");
		plisGagnes += nombre;
	}

	/*
	 * ! \brief Augmente le nombre de manches remportés par l'équipe.
	 */
	public void incrementerManchesGagnees() {
		manchesRemportees++;
	}

	/*
	 * ! \brief Ajoute un membre à l'équipe.
	 * 
	 * \param joueur Joueur à ajouter à l'équipe. \param slot Place où ajouter le
	 * joueur.
	 */
	public void ajouteMembre(Joueur joueur, int slot) throws Exception {
		switch (slot) {
		case 1 -> {
			fillSlot1(joueur);
		}
		case 2 -> {
			fillSlot2(joueur);
		}
		default -> throw new IllegalArgumentException("Pas de place dans cette équipe.");
		}
		nbMembres++;
	}

	/*
	 * ! \brief Accesseur nombre de membres de l'équipe.
	 */
	public int getNbMembres() {
		return nbMembres;
	}

	/*
	 * ! \brief Réinitialise les atributs dépendants du pli avant une nouvelle
	 * manche.
	 */
	public void prepNouvelleManche() {
		this.bossGagnes = 0;
		this.plisGagnes = 0;
	}
}
