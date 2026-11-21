package engine;

import java.util.List;

import model.Carte;
import model.Famille;

public record VueJoueur(Phase phase, int idJoueur, int idPartenaire, List<String> noms, List<Carte> main,
		List<Integer> indicesJouables, int valeurAtout, Famille couleurAtout, Famille familePli,
		List<CartePosee> pliCourant, List<PliTermine> historique, Score scoreEquipe, Score scoreAdverse) {

	public enum Phase {
		ECHANGE, JEU
	}

	public record CartePosee(int idJoueur, Carte carte) {
	}

	public record PliTermine(List<CartePosee> cartes, int idVainqueur, int nbBoss) {
	}

	public record Score(int nbPlis, int nbBoss, int nbManches) {
	}

}
