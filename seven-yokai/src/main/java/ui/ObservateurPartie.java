package ui;

import model.Carte;
import model.Joueur;

public interface ObservateurPartie {
	/*!
	 * \brief Permet à un observateur de réaliser une action en cas de carte posée.
	 * 
	 * \param joueur Joueur ayant posé la carte.
	 * \param carte Carte jouée par le joueur.
	 */
	default void cartePosee(Joueur joueur, Carte carte) {}
	
	/*!
	 * \brief Permet à un observateur de réaliser une action en cas de pli remporté.
	 * 
	 * \param gagnant Joueur ayant remporté le pli.
	 */
	default void pliRemporte(Joueur gagnant) {}
	
	/*!
	 * \brief Permet à un observateur de réaliser une action en cas de manche remportée.
	 * 
	 * \param vainqueur Joueur ayant remporté la manche.
	 */
	default void mancheRemportee(Joueur vainqueur) {}
}
