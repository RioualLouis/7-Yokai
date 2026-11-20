package ui;

import model.Carte;
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
		System.out.println(joueur + " joue la carte " + carte.getNum() + " " + carte.getFamille());
	}
	
	/*!
	 * \brief Affiche le joueur gagnant du pli dans la console.
	 * 
	 * \param joueur Joueur gagnant du pli.
	 */
	@Override
	public void pliRemporte(Joueur joueur) {
		System.out.println(joueur + " Remporte le pli !");
	}
	
	/*!
	 * \brief Affiche le joueur gagnant de la manche dans la console.
	 * 
	 * \param joueur Joueur gagnant de la manche.
	 */
	@Override
	public void mancheRemportee(Joueur joueur) {
		System.out.println(joueur + " Remporte la manche !");
	}
}
