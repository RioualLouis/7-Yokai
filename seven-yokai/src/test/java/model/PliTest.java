package model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import engine.VueJoueur;

class PliTest {

	/** Joueur simulé possédant une seule carte, qu'il joue forcément. */
	private static Joueur joueur(Carte carte, Equipe equipe) {
		List<Carte> main = new ArrayList<>(List.of(carte));
		Strategie strat = mock(Strategie.class);
		when(strat.choisirCarte(mock(VueJoueur.class))).thenReturn(0);
		Joueur j = mock(Joueur.class);
		when(j.getMain()).thenReturn(main);
		when(j.getCarte(anyInt())).thenAnswer(inv -> main.get(inv.getArgument(0)));
		when(j.getStrategie()).thenReturn(strat);
		when(j.getEquipe()).thenReturn(equipe);
		return j;
	}

	private static List<Joueur> table(Carte... cartes) {
		Equipe a = new Equipe(), b = new Equipe();
		List<Joueur> js = new ArrayList<>();
		for (int i = 0; i < cartes.length; i++)
			js.add(joueur(cartes[i], i % 2 == 0 ? a : b));
		return js;
	}

	@Deprecated
	@Test
	void jouerPliNePlantePas() {
		assertDoesNotThrow(() -> new Pli(List.of()).jouerPli(mock(Manche.class)));
	}

	@Deprecated
	@Test
	void unAtoutBatUneCarteHauteDeLaFamilleDemandee() {
		List<Joueur> js = table(new Carte(4, Famille.JAUNE), new Carte(6, Famille.ROUGE), new Carte(10, Famille.JAUNE),
				new Carte(5, Famille.JAUNE));
		assertSame(js.get(1), new Pli(List.of()).jouerPli(mock(Manche.class)));
	}

	@Deprecated
	@Test
	void uneCarteHorsFamilleEtHorsAtoutNeGagnePas() {
		List<Joueur> js = table(new Carte(3, Famille.VERT), new Carte(13, Famille.BLEU), new Carte(2, Famille.VERT),
				new Carte(4, Famille.VERT));
		assertSame(js.get(3), new Pli(List.of()).jouerPli(mock(Manche.class)));
	}

	@Test
	void lesCartesJoueesQuittentLaMain() {
		List<Joueur> js = table(new Carte(3, Famille.VERT), new Carte(4, Famille.VERT), new Carte(5, Famille.VERT),
				new Carte(6, Famille.VERT));
		new Pli(List.of()).jouerPli(mock(Manche.class));
		for (Joueur j : js)
			verify(j).retirerCarte(0);
	}

	@Deprecated
	@Test
	void lesBossSontAttribuesALEquipeGagnante() {
		Joueur vainqueur = new Pli(List.of()).jouerPli(mock(Manche.class));
		assertEquals(1, vainqueur.getEquipe().getBossGagnes());
	}
}
