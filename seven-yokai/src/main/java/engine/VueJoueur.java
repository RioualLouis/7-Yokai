package engine;

import java.util.ArrayList;
import java.util.List;

import model.Carte;
import model.Equipe;
import model.Famille;
import model.Joueur;
import model.Manche;
import model.Pli;

public record VueJoueur(Phase phase, int idJoueur, int idPartenaire, List<String> noms, List<Carte> main,
		List<Integer> indicesJouables, int valeurAtout, Famille couleurAtout, Famille familePli,
		List<CartePosee> pliCourant, List<PliTermine> historique, Score scoreEquipe, Score scoreAdverse) {

	// Permet au liste entrées d'être non modifiables
	public VueJoueur {
		noms = List.copyOf(noms);
		main = List.copyOf(main);
		indicesJouables = List.copyOf(indicesJouables);
		pliCourant = List.copyOf(pliCourant);
		historique = List.copyOf(historique);
	}

	public enum Phase {
		ECHANGE, JEU
	}

	public record CartePosee(int idJoueur, Carte carte) {
	}

	public record PliTermine(List<CartePosee> cartes, int idVainqueur, int nbBoss) {
	}

	public record Score(int nbPlis, int nbBoss, int nbManches) {
	}

	public static VueJoueur construireVue(int idJoueur, Phase phase, Manche manche, Pli pli,
			List<Integer> indicesJouables) {
		List<Joueur> participants = manche.getParticipants();
		int idPartenaire = (idJoueur + 2) % 4;
		List<String> noms = new ArrayList<String>();
		for (Joueur joueur : participants)
			noms.add(joueur.getName());
		List<Carte> main = participants.get(idJoueur).getMain();
		int valeurAtout = manche.getAtout().num();
		Famille couleurAtout = manche.getAtout().famille();
		Famille famillePli = (pli.getPliEnCours().size() > 0) ? pli.getPliEnCours().get(0).carte().famille() : null;
		List<CartePosee> pliCourant = pli.getPliEnCours();
		List<PliTermine> historique = manche.getHistorique();
		Equipe equipeJoueur = participants.get(idJoueur).getEquipe();
		Equipe equipeAdverse = participants.get(idJoueur + 1).getEquipe();
		Score scoreEquipe = new Score(equipeJoueur.getPlisGagnes(), equipeJoueur.getBossGagnes(),
				equipeJoueur.getManchesRemportees());
		Score scoreAdverse = new Score(equipeAdverse.getPlisGagnes(), equipeAdverse.getBossGagnes(),
				equipeAdverse.getManchesRemportees());

		return new VueJoueur(phase, idJoueur, idPartenaire, noms, main, indicesJouables, valeurAtout, couleurAtout,
				famillePli, pliCourant, historique, scoreEquipe, scoreAdverse);
	}
}
