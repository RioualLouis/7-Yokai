/*!
 * \file Aleatoire.java
 * 
 * Contient de quoi générer une graine aléatoire utilisable par les 
 * éléments aléatoires du jeu.  
 */

package engine;

import java.util.Random;

public class Aleatoire {
	private final long seed;
	private final Random random;

	/*!
	 * \brief Construit un objet aléatoire à partir d'une graine donnée.
	 * 
	 * \param inputSeed Graine donnée.
	 */
	public Aleatoire(long inputSeed) {
		seed = inputSeed;
		random = new Random(inputSeed);
	}
	
	/*!
	 * \brief Construit un objet aléatoire.
	 */
	public Aleatoire() {
		this(System.nanoTime());
	}

	/*!
	 * \brief Récupère la graine de l'objet.
	 */
	public long getSeed() {
		return seed;
	}
	
	/*!
	 * \brief Génère un entier aléatoire entre 0 compris et la limite comprise.
	 * 
	 * \param limite Limite haute de la plage de génération.
	 */
	public int nextInt(int limite) {
		return random.nextInt(limite);
	}
}
