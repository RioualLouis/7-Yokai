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
		System.out.println(StringFormat.joueur(joueur.getName()) + StringFormat.std(" joue la carte ") + carte.num() + " " + StringFormat.famille(carte.famille()));
	}
	
	/*!
	 * \brief Affiche le joueur gagnant du pli dans la console.
	 * 
	 * \param joueur Joueur gagnant du pli.
	 */
	@Override
	public void pliRemporte(Joueur joueur) {
		System.out.println(StringFormat.joueur(joueur.getName()) + StringFormat.std(" Remporte le pli !\n"));
	}
	
	/*!
	 * \brief Affiche la fin de la manche dans la console.
	 */
	@Override
	public void mancheRemportee() {
		System.out.println(StringFormat.emph("== Manche terminée ! ==\n"));
	}
	
	/*!
	 * \brief Annonce l'atout pour cette manche.
	 * 
	 * \param atout La famille atout pour cette manche.
	 */
	@Override
	public void atoutRevele(Carte atout) {
		System.out.println(StringFormat.std("La famille atout pour cette manche est : ") + atout.num() + StringFormat.famille(atout.famille()));
	}
}
