/*!
 * \file Famille.java
 * 
 * Contient l'énumération des couleurs de cartes différentes. Chaque couleur 
 * contient le début et la fin de sa plage de valeur.
 */

package model;

public enum Famille {
	VERT(1, 7),
	GRIS(2, 8),
	ROSE(3, 9),
	JAUNE(4, 10),
	VIOLET(5, 11),
	ROUGE(6, 12),
	BLEU(7, 13);
	
	private final int debut;
	private final int fin;
	
	/*!
	 * \brief Constructeur pour une famille. Assignes les limites de plage de valeurs.
	 * 
	 *  \param i Début de la plage.
	 *  \param j Fin de la plage.
	 */
	private Famille(int i, int j) {
		debut = i;
		fin = j;
	}
	
	/*!
	 * \brief Accesseur du début de plage.
	 */
	public int getDebut() {
		return debut;
	}
	
	/*!
	 * \brief Accesseur de fin de plage.
	 */
	public int getFin() {
		return fin;
	}
}
