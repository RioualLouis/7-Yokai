/*!
 * \file Joueur.java
 * 
 * Classe pour définir un joueur. Chque joueur possède une stratégie propre et fait 
 * partie d'une équipe.
 */

package model;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
	private static final int TAILLE_MAIN = 12;
	
	private final Strategie type;
	private Equipe equipe;
	private List<Carte> main;
	
	/*!
	 * \brief Constructeur d'un joueur.
	 * 
	 * \param jType Stratégie du joueur.
	 * \param jEquipe Equipe à laquelle appartient le joueur
	 * 
	 * La main d'un joueur ne peut contenir que 12 cartes au maximum.
	 */
	public Joueur(Strategie jType, Equipe jEquipe) {
		type = jType;
		equipe = jEquipe;
		main = new ArrayList<>();
	}
	
	/*!
	 * \brief Accesseur de l'équipe du joueur.
	 */
	public Equipe getEquipe() {
		return equipe;
	}
	
	/*!
	 * \brief Accesseur d'une carte à partir de son indice dans la main du joueur.
	 * 
	 * \param indice Indice de la carte voulue dans la main du joueur.
	 */
	public Carte getCarte(int indice) {
		return main.get(indice);
	}
	
	/*!
	 * \brief Accesseur de la main du joueur
	 */
	public List<Carte> getMain() {
		return main;
	}
	
	/*!
	 * \brief Accesseur de la stratégie du joueur.
	 */
	public Strategie getStrategie() {
		return type;
	}
	
	/*!
	 * \brief Constitue la main du joueur à partir d'une séléction de 12 cartes piochées.
	 * 
	 *  \param mainPiochee Selection de 12 cartes obtenue via la pioche.
	 *  
	 *  Les cartes sont triées en fonction de leur couleur (Vert < Bleu), puis de leur valeur.
	 */
	public void constituerMain(Carte[] mainPiochee) throws Exception {
		if (mainPiochee.length != TAILLE_MAIN) throw new IllegalArgumentException("Nombre de cartes incorrect.");
		
		for (int i = 0 ; i < TAILLE_MAIN ; i++) {
			main.add(mainPiochee[i]);
		}
		
		triMain(main);
	}
	
	private void triMain(List<Carte> main) {
		for (int i = 1 ; i < main.size() ; i++) {
			Carte key = main.get(i);
			int j = i - 1;
			
			while (j >= 0 && (
					main.get(j).getFamille().getFin() > key.getFamille().getFin() || (
						main.get(j).getFamille().getFin() == key.getFamille().getFin() &&
						main.get(j).getNum() > key.getNum())
					) 
					) {
				main.set(j + 1, main.get(j));
				j--;
			}
			main.set(j + 1, key);
		}
	}
	
	/*!
	 * \brief Modifie une carte à un indice donné dans la main du joueur.
	 * 
	 * \param indice Indice de la carte à remplacer par la selection.
	 * \param selection Nouvelle carte à insérer dans la main du joueur.
	 */
	public void remplacerCartes(int i, Carte selection) throws Exception {
		if (i >= main.size()) throw new ArrayIndexOutOfBoundsException("Indice hors limites");
		
		if (main.get(i).getNum() == 0) throw new NullPointerException("Aucune carte à cet emplacement dans la main.");
		
		main.remove(i);
		main.add(i, selection);
	} 
	
	public void retirerCarte(int i) {
		main.remove(i);
	}
}
