package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JoueurTest {
	private Joueur joueur;
	private Carte[] cartes;

	@BeforeEach
	void setUp() {
		joueur = new Joueur(mock(Strategie.class), new Equipe(), "J0");
		cartes = new Carte[12];
		// Ordre volontairement désordonné : BLEU avant VERT, valeurs décroissantes.
		for (int i = 0; i < 6; i++) cartes[i] = new Carte(13 - i, Famille.BLEU);
		for (int i = 6; i < 12; i++) cartes[i] = new Carte(13 - i, Famille.VERT);
	}

	@Test
	void constituerMainRemplitLaMain() throws Exception {
		joueur.constituerMain(cartes);
		assertEquals(12, joueur.getMain().size());
	}

	@Test
	void constituerMainTrieLaMain() throws Exception {
		joueur.constituerMain(cartes);
		assertEquals(Famille.VERT, joueur.getCarte(0).getFamille());
		assertEquals(2, joueur.getCarte(0).getNum());
	}

	@Test
	void remplacerCartesConserveLIndice() throws Exception {
		joueur.constituerMain(cartes);
		Carte nouvelle = new Carte(4, Famille.JAUNE);
		joueur.remplacerCartes(0, nouvelle);
		assertSame(nouvelle, joueur.getCarte(0));
	}

	@Test
	void retirerCarte() throws Exception {
		joueur.constituerMain(cartes);
		joueur.retirerCarte(0);
		assertEquals(11, joueur.getMain().size());
	}
}
