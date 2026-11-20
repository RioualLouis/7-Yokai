package bot;

import java.util.List;

import engine.Aleatoire;
import model.Strategie;

public class BotAleatoire implements Strategie{
	private Aleatoire random;
	
	public BotAleatoire(Aleatoire rng) {
		random = rng;
	}
	
	/*!
	 * \brief Renvoit trois indice correspondant aux position des cartes à échanger dans la main du joueur.
	 * 
	 * \return Un tablau de trois indices aléatoires entre 1 et 12.
	 */
	public int[] choisirCartesEchange() {
		int[] selection = random.randomSample(3, 0, 12);
		
		return selection;
	}
	
	/*!
	 * \brief Retourne l'index de la carte choisie dans la liste des index des cartes jouables.
	 * 
	 * \param indicesJouables Liste des indices des cartes jouables dans la main du joueur.
	 * 
	 *  \return L'indice choisi aléatoirement dans la liste d'indices.
	 */
	public int choisirCarte(List<Integer> indicesJouables) {
		return indicesJouables.get(random.nextInt(indicesJouables.size()));
	}
}
