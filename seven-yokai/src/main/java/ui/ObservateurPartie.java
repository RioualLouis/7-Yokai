package ui;

import model.Carte;
import model.Famille;
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
	 */
	default void mancheRemportee() {}
	
	/*!
	 * \brief Permet à l'observateur de réaliser une action lorsque l'atout est révélé.
	 * 
	 * \param atout Famille atout pour cette manche.
	 */
	default void atoutRevele(Famille atout) {}
}
