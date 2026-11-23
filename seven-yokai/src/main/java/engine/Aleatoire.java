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

	/*
	 * ! \brief Construit un objet aléatoire à partir d'une graine donnée.
	 * 
	 * \param inputSeed Graine donnée.
	 */
	public Aleatoire(long inputSeed) {
		seed = inputSeed;
		random = new Random(inputSeed);
	}

	/*
	 * ! \brief Construit un objet aléatoire.
	 */
	public Aleatoire() {
		this(System.nanoTime());
	}

	/*
	 * ! \brief Récupère la graine de l'objet.
	 */
	public long getSeed() {
		return seed;
	}

	/*
	 * ! \brief Génère un entier aléatoire entre 0 compris et la limite
	 * non-comprise.
	 * 
	 * \param limite Limite haute de la plage de génération.
	 */
	public int nextInt(int limite) {
		if (limite < 0)
			throw new IllegalArgumentException("La borne doit être positive.");
		return random.nextInt(limite);
	}

	/*
	 * ! \brief Génère un échantillon d'entiers aléatoires entre le début de plage
	 * compris et la limite de plage non-comprise.
	 * 
	 * \param tailleEchantillon Taille de l'échatillon retourné. \param debutPlage
	 * Début de la plage de valeur dans laquelle sont tirés les entiers. \param
	 * finPlage Fin de la plage de valeur dans laquelle sont tirés les entiers.
	 * 
	 * \return Un tableau de la taille demandée contenant autant d'entiers
	 * aléatoires distincts dans la plage demandée.
	 */
	public int[] randomSample(int tailleEchantillon, int debutPlage, int finPlage) {
		int[] plage = new int[finPlage - debutPlage];
		int[] echantillon = new int[tailleEchantillon];
		for (int i = debutPlage; i < finPlage; i++)
			plage[i] = i;

		// Mélange de la plage par l'algorithme de Fisher-Yates
		for (int i = plage.length - 1; i > 1; i--) {
			int j = random.nextInt(i + 1);
			int tmp = plage[i];
			plage[i] = plage[j];
			plage[j] = tmp;
		}

		for (int i = 0; i < echantillon.length; i++)
			echantillon[i] = plage[i];

		return echantillon;
	}
}
