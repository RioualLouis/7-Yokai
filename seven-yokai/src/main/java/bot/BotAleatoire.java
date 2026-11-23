package bot;

import engine.Aleatoire;
import engine.VueJoueur;
import model.Strategie;

public class BotAleatoire implements Strategie {
	private Aleatoire random;

	public BotAleatoire(Aleatoire rng) {
		random = rng;
	}

	/*
	 * ! \brief Renvoit trois indice correspondant aux position des cartes à
	 * échanger dans la main du joueur.
	 * 
	 * \return Un tablau de trois indices aléatoires entre 1 et 12.
	 */
	public int[] choisirCartesEchange(VueJoueur vue) {
		int[] selection = random.randomSample(3, 0, vue.main().size());

		return selection;
	}

	/*
	 * ! \brief Retourne l'index de la carte choisie dans la liste des index des
	 * cartes jouables.
	 * 
	 * \param vue Une vue du joueur.
	 * 
	 * \return L'indice choisi aléatoirement dans la liste d'indices.
	 */
	public int choisirCarte(VueJoueur vue) {
		return vue.indicesJouables().get(random.nextInt(vue.indicesJouables().size()));
	}
}
