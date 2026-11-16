/*!
 * \file Carte.java
 * 
 * Implémente une carte du jeu avec son nombre et sa famille.
 */

package model;

public class Carte {
	private final int numero;
	private final Famille couleur;
	
	/*!
	 * \brief Créé une nouvelle carte d'une valeur et d'une couleur.
	 * 
	 *\param indice Valeur de la carte.
	 *\param famille Couleur de la carte.
	 * */
	public Carte(int indice, Famille famille) {
		numero = indice;
		couleur = famille;
	}
	
	/*!
	 * \brief Créé une carte e valeeur nulle, pour la gestion des erreurs. 
	 * */
	public Carte() {
		this(0, Famille.VERT);
	}
	
	/*!
	 * \brief Accesseur pour la valeur de la carte
	 */
	public int getNum() {
		return numero;
	}
	
	/*!
	 * \brief Accesseur pour la couleur de la carte
	 */
	public Famille getFamille() {
		return couleur; 
	}
}
