/*!
 * \file Manche.java
 * 
 * Contient tout ce qui se passe pendant une manche et le nécessaire pour sa préparation.
 */

package model;

import java.util.ArrayList;
import java.util.List;

import engine.Aleatoire;
import ui.ObservateurPartie;

public class Manche {
	private final Famille atout;
	private List<Joueur> joueurs;
	private int premierJoueur;
	private List<Carte> pioche;
	
	private List<ObservateurPartie> observateurs;
	
	/*!
	 * \brief Constructeur d'une manche. Gère la préparation d'une manche, mélange les cartes 
	 * et assigne les cartes aux joueurs.
	 * 
	 * \param atoutManche Couleur considérée comme l'atout pour cette manche.
	 * \param participants Liste des joueurs de la partie.
	 * 
	 * Le mélange des cartes utilise la graine de génération aléatoire de la partie pour la 
	 * reproductivité des parties.
	 */
	public Manche(List<Joueur> participants, Joueur dernierVainqueur, Aleatoire rng, List<ObservateurPartie> obs) throws Exception {
		joueurs = participants;
		
		this.observateurs = obs;
		
		// Pour déterminer le premier joueur en cas de non pioche de l'atout.
		Joueur premierJoueurReserve = null;
		
		pioche = buildPioche(rng);
		
		Carte[] mainPiochee = new Carte[12];
		for (Joueur joueur : participants) {
			for (int i = 0 ; i < mainPiochee.length ; i++) {
				mainPiochee[i] = pioche.removeLast();
				
				// Attribution du premier joueur si aucun gagnant précédent n'a été donné (première manche).
				if (dernierVainqueur == null && (mainPiochee[i].getNum() == 1 && mainPiochee[i].getFamille() == Famille.VERT)) dernierVainqueur = joueur;
				if (mainPiochee[i].getNum() == 13 && mainPiochee[i].getFamille() == Famille.BLEU) premierJoueurReserve = joueur;
			}
			joueur.constituerMain(mainPiochee);
		}
		
		premierJoueur = (dernierVainqueur == null) ? joueurs.lastIndexOf(premierJoueurReserve) : joueurs.lastIndexOf(dernierVainqueur);
		
		// Détermination de l'atout
		// Verification qu'une seule carte reste dans la pioche
		if (pioche.size() != 1) throw new Exception("Distribution non-conforme : une seule carte doit rester dans la pioche (" + pioche.size() + " restantes).");
		
		atout = pioche.get(0).getFamille();
	}
	
	/*!
	 * \brief Construit un pioche utilisable.
	 * 
	 * \param rng L'aléatoire utilisé ppour la partie.
	 * 
	 * \return Une liste contenant toutes les cartes.
	 */
	private static List<Carte> buildPioche(Aleatoire rng) {
		List<Carte> pioche = new ArrayList<>();
		
		for (Famille f : Famille.values()) {
			for (int numero = f.getDebut() ; numero <= f.getFin() ; numero++) {
				pioche.add(new Carte(numero, f));
			}
		}	
		
		melanger(pioche, rng);
		
		return pioche;
	}
	
	/*!
	 * \brief Mélange les cartes de la pioche en utilisant la méthode de Fisher-Yates.
	 * 
	 * \param pioche La listede carte à mélanger.
	 * \param seed Graine de génération aléatoire de la partie.
	 */
	private static void melanger(List<Carte> pioche, Aleatoire rng) {
		Carte tmp;
		int j;
		for (int i = pioche.size() - 1 ; i > 0 ; i--) {
			j = rng.nextInt(i + 1);
			tmp = pioche.get(i);
			pioche.set(i, pioche.get(j));
			pioche.set(j, tmp);
		}
	}
	
	/*!
	 * \brief Échange les cartes de deux joueurs lors de la phase préliminaire de la manche.
	 * 
	 * \param j1 Premier joueur.
	 * \param j2 Deuxième joueur.
	 * 
	 * Le choix des cartes à échanger appartient à la stratégie propre au joueur.
	 */
	public void echangerCartes(Joueur j1, Joueur j2) throws Exception {
		int[] choixJ1 = j1.getStrategie().choisirCartesEchange();
		int[] choixJ2 = j2.getStrategie().choisirCartesEchange();
		Carte tmp;
		
		for (int i = 0 ; i < 3 ; i++) {
			tmp = j1.getCarte(choixJ1[i]);
			j1.remplacerCartes(choixJ1[i], j2.getCarte(choixJ2[i]));
			j2.remplacerCartes(choixJ2[i], tmp);
		}
	}
	
	/*!
	 * \brief Déroulé complet de la manche.
	 */
	public Joueur jouerManche() throws Exception {
		int nombrePlis = 0;
		int conditionFin = 0;
		Joueur vainqueurPli = null;
		Equipe equipeGagnante = null;
		Pli pliCourant;
		
		// Les plis s'enchainent tant qu'une condition de fin de manche n'est pas remplie.
		while (conditionFin == 0) {
			pliCourant = new Pli(this.observateurs);
			
			vainqueurPli = pliCourant.jouerPli(joueurs, premierJoueur, atout);
			equipeGagnante = vainqueurPli.getEquipe();
			
			equipeGagnante.attribuerPli(1);
			
			nombrePlis++;
			conditionFin = (nombrePlis < 12) ? finDeManche() : 3;
			
			premierJoueur = joueurs.indexOf(vainqueurPli);
		}
		
		if (equipeGagnante == null) throw new Exception("Manche non conclue, absence de vainqueur.");
		
		// Attribution du point de manche en fonction de la condition de fin. 
		switch (conditionFin) {
		case 1 -> {
			for (Joueur j : joueurs) {
				if (j.getEquipe().getBossGagnes() == 4) j.getEquipe().incrementerManchesGagnees();
			}
		}
		case 2 -> { 
			for (Joueur j : joueurs) {
				if (j.getEquipe().getPlisGagnes() != 7) j.getEquipe().incrementerManchesGagnees();
			}
		}
		case 3 -> { 
			equipeGagnante.incrementerManchesGagnees();
		}
		}
		
		return vainqueurPli;
	}
	
	private int finDeManche() {
		boolean cond1 = false;
		boolean cond2 = false;
		
		for (Joueur j : joueurs) {
			cond1 = cond1 || j.getEquipe().getBossGagnes() == 4;
			cond2 = cond2 || j.getEquipe().getPlisGagnes() == 7;
		}
		
		if (cond1) return 1;
		if (cond2) return 2;
		
		return 0;
	}
}
