/*!
 * \file Strategie.java
 * Interface de stratégie pour un joueur. Chaque stratégie a sa propre manière de choisir ses
 * cartes à échanger au début d'une manche et sa propre manière de choisir sa carte à jouer. 
 */

package model;

import java.util.List;

public interface Strategie {
	
	/*!
	 * \brief Choix des cartes à échanger au début d'un manche.
	 * 
	 *  \return Tableau d'indices des cartes à échanger avec son partenaire.
	 */
	public int[] choisirCartesEchange();
	
	/*!
	 * \brief Choix de carte à jouer lors du tour du joueur.
	 * 
	 *  \return Indice de la carte à jouer.
	 */
	public int choisirCarte(List<Integer> indicesJouables);
}
