package model;

import java.util.List;

public class Pli {
	private int plusHaute = 0;
	private int plusHauteAtout = 0;
	private boolean asEnJeu = false;
	private int nombreBoss = 0;
	private Joueur vainqueur;
	
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
	 * \brief Attribue la victoire à l'équipe qui a posé la carte actuelle. 
	 */
	public void attribuerVictoire(Joueur joueurVictorieux) {
		vainqueur = joueurVictorieux;
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
		for (int i = 0 ; i < joueurs.size() ; i++) {
			int indiceJoueur = (i + premierJoueur) % joueurs.size(); 
			Joueur joueur = joueurs.get(indiceJoueur); 
			
			// Choix de la carte du joueur
			int choix = joueur.getStrategie().choisirCarte();
			Carte carteJouee =  joueur.getCarte(choix);
			
			int valeur = carteJouee.getNum();
			
			// Cas de l'As
			if (valeur == 1 && carteJouee.getFamille() == Famille.VERT) {
				attribuerVictoire(joueur);
				asEnJeu = true;
			}
			
			// Inrémenter le nombre de boss dans le pli si un a été ajouté.
			if (valeur == 7) incrementerNbBoss();
			
			// Changer le maximum joué pendant cette manche et éventuellement attribuer une nouvelle équipe victorieuse.
			boolean changementValeurMax = (carteJouee.getFamille() == atout) ? modifierPlusHauteAtout(valeur) : modifierPlusHaute(valeur);
			if (changementValeurMax && !asEnJeu) {
				attribuerVictoire(joueur);
			}
		}
		return vainqueur;
	}
}
