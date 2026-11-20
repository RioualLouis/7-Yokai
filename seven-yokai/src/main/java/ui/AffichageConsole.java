package ui;

import model.Carte;
import model.Joueur;

public class AffichageConsole implements ObservateurPartie {
	
	@Override
	public void cartePosee(Joueur joueur, Carte carte) {
		System.out.println(joueur + " joue la carte " + carte.getNum() + " " + carte.getFamille());
	}
	
	@Override
	public void pliRemporte(Joueur joueur) {
		System.out.println(joueur + " Remporte le pli !");
	}
	
	@Override
	public void mancheRemportee(Joueur joueur) {
		System.out.println(joueur + " Remporte la manche !");
	}
}
