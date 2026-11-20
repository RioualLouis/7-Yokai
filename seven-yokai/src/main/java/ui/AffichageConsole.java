package ui;

import model.Carte;
import model.Famille;
import model.Joueur;

public class AffichageConsole implements ObservateurPartie {
	
	/*!
	 * \brief Affiche qui a posé quelle carte dans la console.
	 * 
	 * \param joueur Joueur ayant joué la carte.
	 * \param carte Carte jouée par le joueur.
	 */
	@Override
	public void cartePosee(Joueur joueur, Carte carte) {
		System.out.println(joueur.getName() + " joue la carte " + carte.getNum() + " " + carte.getFamille());
	}
	
	/*!
	 * \brief Affiche le joueur gagnant du pli dans la console.
	 * 
	 * \param joueur Joueur gagnant du pli.
	 */
	@Override
	public void pliRemporte(Joueur joueur) {
		System.out.println(joueur.getName() + " Remporte le pli !\n");
	}
	
	/*!
	 * \brief Affiche la fin de la manche dans la console.
	 */
	@Override
	public void mancheRemportee() {
		System.out.println("== Manche terminée ! ==\n");
	}
	
	/*!
	 * \brief Annonce l'atout pour cette manche.
	 * 
	 * \param atout La famille atout pour cette manche.
	 */
	@Override
	public void atoutRevele(Famille atout) {
		System.out.println("La famille atout pour cette manche est " + atout + " !");
	}
}
