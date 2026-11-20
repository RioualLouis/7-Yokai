/*!
 * \file Equipe.java
 * 
 * Equipe dont 2 joueurs peuvent faire partie. Garde en mémoire les acquis
 * de l'équipe pour vérifier les conditions de fin de manche. 
 */

package model;

public class Equipe {
	private int bossGagnes = 0;
	private int plisGagnes = 0;
	private int manchesRemportees = 0;
	private int nbMembres = 0;
	
	/*!
	 * \brief Accesseur pour le nombre de boss gagnés par l'équipe
	 */
	public int getBossGagnes() {
		return bossGagnes;
	}
	
	/*!
	 * \brief Accesseur pour le nombre de plis gagnés par l'équipe
	 */
	public int getPlisGagnes() {
		return plisGagnes;
	}
	
	/*!
	 * \brief Accesseur pour le nombre de manches gagnés par l'équipe
	 */
	public int getManchesRemportees() {
		return manchesRemportees;
	}
	
	/*!
	 * \brief Augmente le nombre de boss gagnés par l'équipe.
	 * 
	 * \param nombre Valeur à incrémenter au nombre de boss gagnés.
	 */
	public void attribuerBoss(int nombre) {
		bossGagnes += nombre;
	}
	
	/*!
	 * \brief Augmente le nombre de plis remportés par l'équipe.
	 * 
	 * \param nombre Valeur à incrémenter au nombre de plis gagnés.
	 */
	public void attribuerPli(int nombre) {
		if (nombre < 0) throw new IllegalArgumentException("Impossible d'ajouter un nombre de plis négatifs.");
		plisGagnes += nombre;
	}
	
	/*!
	 * \brief Augmente le nombre de manches remportés par l'équipe.
	 */
	public void incrementerManchesGagnees() {
		manchesRemportees++;
	}
	
	/*!
	 * \brief Ajoute un membre à l'équipe.
	 */
	public void ajouteMembre() throws Exception {
		if (nbMembres == 2) throw new IllegalStateException("Equipe pleine. Impossible d'ajouter un nouveau membre."); 
		nbMembres++;
	}
	
	/*!
	 * \brief Accesseur nombre de membres de l'équipe.
	 */
	public int getNbMembres() {
		return nbMembres;
	}
	
	/*!
	 * \brief Réinitialise les atributs dépendants du pli avant une nouvelle manche. 
	 */
	public void prepNouvelleManche() {
		this.bossGagnes = 0;
		this.plisGagnes = 0;
	}
}
