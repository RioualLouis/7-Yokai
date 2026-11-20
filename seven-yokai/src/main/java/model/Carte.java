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
	 * \brief Créé une carte e valeur nulle, pour la gestion des erreurs. 
	 * */
	public Carte() {
		this(0, Famille.VERT);
	}
	
	/*!
	 * \brief Implément le test d'égalité entre deux cartes.
	 * 
	 *  \param o Objet à tester.
	 *  
	 *  \return Si ls deux objets sont égaux du point de vu des cartes.
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Carte)) return false;
		
		return ( (this.numero == ((Carte) o).numero) && (this.couleur == ((Carte) o).couleur) );
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
