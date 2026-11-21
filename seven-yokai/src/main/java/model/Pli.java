/*!
 * \file Pli.java
 * 
 * Gère l'organisation et le déroulement d'un pli.
 */

package model;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.List;

import ui.ObservateurPartie;

public class Pli {
	private int plusHaute = 0;
	private int plusHauteAtout = 0;
	private boolean asEnJeu = false;
	private int nombreBoss = 0;
	private Joueur vainqueur;
	private List<Carte> PliEnCours;
	
	private List<ObservateurPartie> observateurs;
	
	public Pli(List<ObservateurPartie> obs) {
		this.observateurs = obs;
	}
	
	/*!
	 * \brief Accesseur de la carte la plus haute du pli.
	 */
	public int getPlusHaute() {
		return plusHaute;
	}
	
	/*!
	 * \brief Accesseur de la carte de la famille atout la plus haute.
	 */
	public int getPlusHauteAtout() {
		return plusHauteAtout;
	}
	
	/*!
	 * \brief Accesseur du nombre de boss dans le pli.
	 */
	public int getNombreBoss() {
		return nombreBoss;
	}
	
	/*!
	 * \brief Accesseur du vainqueur du pli.
	 */
	public Joueur getVainqueur() {
		return vainqueur;
	}
	
	/*!
	 * \brief Vérifier si une valeur est la nouvelle valeur la plus haute du pli,
	 * et remplacer la valeur la plus haute par celle ci si c'est le cas.
	 * 
	 * \param nouvValeur Valeur de la carte jouée.
	 * 
	 * \return Si la valeur maximum du pli a été modifiée ou pas.
	 */
	public boolean modifierPlusHaute(int nouvValeur) {
		boolean estModifie = nouvValeur > plusHaute; 
		if (estModifie) plusHaute = nouvValeur;
		return estModifie;
	}
	
	/*!
	 * \brief Vérifier si une valeur est la nouvelle valeur la plus haute du pli
	 * pour la famille atout et remplacer la valeur la plus haute par 
	 * celle ci si c'est le cas.
	 * 
	 * \param nouvValeur Valeur de la carte jouée.
	 * 
	 * \return Si la valeur maximum du pli pour la famille de l'atout a été modifiée ou pas.
	 */
	public boolean modifierPlusHauteAtout(int nouvValeur) {
		boolean estModifie = nouvValeur > plusHauteAtout; 
		if (estModifie) plusHauteAtout = nouvValeur;
		return estModifie;
	}
	
	/*!
	 * \brief Incrémente le nombre de boss dans le pli. 
	 */
	public void incrementerNbBoss() {
		nombreBoss++;
	}
	
	/*!
	 * \brief Ajoute la carte en haut du pli en cours.
	 * 
	 * \param carte Carte à ajouter.
	 */
	private void ajouterCartePli(Carte carte) {
		PliEnCours.add(carte);
	}
	
	/*!
	 * \brief Donne une copie du pli en cours.
	 * 
	 * \return La copie du plis en cours.
	 */
	public List<Carte> getPliEnCours() {
		List<Carte> res = new ArrayList<Carte>();
		res.addAll(PliEnCours);
		return res;
	}
	
	/*!
	 * \brief Attribue la victoire à l'équipe qui a posé la carte actuelle. 
	 */
	public void attribuerVictoire(Joueur joueurVictorieux) {
		vainqueur = joueurVictorieux;
	}
	
	/*!
	 * \brief Filtre parmis les cartes du joueur pour retourner une liste d'indices jouables.
	 * 
	 *  \param joueur Joueur dont le tour est actif.
	 *  \param famillePli Famille jouée par le premier joueur du pli.
	 *  \param atout Famille atout lors de cette manche
	 *  
	 *  \return Une liste d'indices jouables dans le contexte du pli.
	 */
	private List<Integer> filtreChoix(Joueur joueur, Famille famillePli, Famille atout) {
		List<Carte> mainJoueur = joueur.getMain();
		List<Integer> indicesJouables = new ArrayList<>();
		
		// Famille en cours check
		if (famillePli != null) {
			for (Carte carte : mainJoueur) {
				if (carte.famille() == famillePli) indicesJouables.add(mainJoueur.indexOf(carte));
			}
			
			if (indicesJouables.size() > 0) return indicesJouables;
		}
		
		// Famille atout check
		for (Carte carte : mainJoueur) {
			if (carte.famille() == atout) indicesJouables.add(mainJoueur.indexOf(carte));
		}
		
		if (indicesJouables.size() > 0) return indicesJouables;
		
		// Sinon on donne toute la main
		for (Carte carte : mainJoueur) {
			indicesJouables.add(mainJoueur.indexOf(carte));
		}
		
		return indicesJouables;	 
	}
	
	/*!
	 * \brief Joue un pli complet.
	 * 
	 * \param joueurs Liste des participants.
	 * \param premierJoueur Index du premier joueur.
	 * 
	 * \return Le vainqueur du pli.
	 */
	public Joueur jouerPli(List<Joueur> joueurs, int premierJoueur, Famille atout) {
		Famille famillePli = null;
		
		for (int i = 0 ; i < joueurs.size() ; i++) {
			int indiceJoueur = (i + premierJoueur) % joueurs.size(); 
			Joueur joueur = joueurs.get(indiceJoueur); 
			
			// Choix de la carte du joueur
			List<Integer> indicesJouables = filtreChoix(joueur, famillePli, atout); 
			
			int choix = joueur.getStrategie().choisirCarte(indicesJouables);
			Carte carteJouee =  joueur.getCarte(choix);
			joueur.retirerCarte(choix);
			
			// Envoi aux observateurs :
			this.observateurs.forEach(o -> o.cartePosee(joueur, carteJouee));
			
			ajouterCartePli(carteJouee);
			
			int valeur = carteJouee.num();
			
			// Première carte du pli
			if (famillePli == null) famillePli = carteJouee.famille();
			
			// Cas de l'As
			if (valeur == 1 && carteJouee.famille() == Famille.VERT) {
				attribuerVictoire(joueur);
				asEnJeu = true;
			}
			
			// Inrémenter le nombre de boss dans le pli si un a été ajouté.
			if (valeur == 7) incrementerNbBoss();
			
			// Changer le maximum joué pendant cette manche et éventuellement attribuer une nouvelle équipe victorieuse.
			if (carteJouee.famille() == atout) {
				boolean changementValeurMax = modifierPlusHauteAtout(valeur);
				if (changementValeurMax && !asEnJeu) attribuerVictoire(joueur);
				
			} else if (carteJouee.famille() == famillePli) {
				boolean changementValeurMax = modifierPlusHaute(valeur);
				if (changementValeurMax && !asEnJeu && plusHauteAtout == 0) attribuerVictoire(joueur);
			}
		}
		// Les boss du pli reviennent à l'équipe du gagnant du pli.
		vainqueur.getEquipe().attribuerBoss(nombreBoss);
		
		// Envoi aux observateurs :
		this.observateurs.forEach(o -> o.pliRemporte(this.vainqueur));
		
		return vainqueur;
	}
}
