package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class PliTest {

	/** Joueur simulé possédant une seule carte, qu'il joue forcément. */
	private static Joueur joueur(Carte carte, Equipe equipe) {
		List<Carte> main = new ArrayList<>(List.of(carte));
		Strategie strat = mock(Strategie.class);
		when(strat.choisirCarte(anyList())).thenReturn(0);
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
		for (int i = 0; i < cartes.length; i++) js.add(joueur(cartes[i], i % 2 == 0 ? a : b));
		return js;
	}

	@Test
	void jouerPliNePlantePas() {
		List<Joueur> js = table(new Carte(3, Famille.VERT), new Carte(4, Famille.VERT),
				new Carte(5, Famille.VERT), new Carte(6, Famille.VERT));
		assertDoesNotThrow(() -> new Pli().jouerPli(js, 0, Famille.ROUGE));
	}

	@Test
	void unAtoutBatUneCarteHauteDeLaFamilleDemandee() {
		List<Joueur> js = table(new Carte(4, Famille.JAUNE), new Carte(6, Famille.ROUGE),
				new Carte(10, Famille.JAUNE), new Carte(5, Famille.JAUNE));
		assertSame(js.get(1), new Pli().jouerPli(js, 0, Famille.ROUGE));
	}

	@Test
	void uneCarteHorsFamilleEtHorsAtoutNeGagnePas() {
		List<Joueur> js = table(new Carte(3, Famille.VERT), new Carte(13, Famille.BLEU),
				new Carte(2, Famille.VERT), new Carte(4, Famille.VERT));
		assertSame(js.get(3), new Pli().jouerPli(js, 0, Famille.ROUGE));
	}

	@Test
	void lesCartesJoueesQuittentLaMain() {
		List<Joueur> js = table(new Carte(3, Famille.VERT), new Carte(4, Famille.VERT),
				new Carte(5, Famille.VERT), new Carte(6, Famille.VERT));
		new Pli().jouerPli(js, 0, Famille.ROUGE);
		for (Joueur j : js) verify(j).retirerCarte(0);
	}

	@Test
	void lesBossSontAttribuesALEquipeGagnante() {
		List<Joueur> js = table(new Carte(7, Famille.VERT), new Carte(2, Famille.VERT),
				new Carte(3, Famille.VERT), new Carte(4, Famille.VERT));
		Joueur vainqueur = new Pli().jouerPli(js, 0, Famille.ROUGE);
		assertEquals(1, vainqueur.getEquipe().getBossGagnes());
	}
}
