package model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bot.BotAleatoire;
import engine.Aleatoire;

class MancheTest {
	private List<Joueur> joueurs;
	private List<Equipe> equipes;

	@BeforeEach
	void setUp() {
		Aleatoire rng = new Aleatoire(42);
		Equipe a = new Equipe(), b = new Equipe();
		joueurs = List.of(new Joueur(1, new BotAleatoire(rng), a, "J1"), new Joueur(2, new BotAleatoire(rng), b, "J2"),
				new Joueur(3, new BotAleatoire(rng), a, "J3"), new Joueur(4, new BotAleatoire(rng), b, "J4"));
		equipes = List.of(a, b);
	}

	@Test
	void constructionDUneManche() {
		assertDoesNotThrow(() -> new Manche(joueurs, equipes, null, new Aleatoire(42), List.of()));
	}

	@Deprecated
	@Test
	void chaqueJoueurRecoitDouzeCartes() throws Exception {
		new Manche(joueurs, equipes, null, new Aleatoire(42), List.of());
		for (Joueur j : joueurs)
			assertEquals(12, j.getMain().size());
	}

	@Deprecated
	@Test
	void deuxManchesSuccessivesSontPossibles() {
		// La pioche statique ne doit pas conserver les cartes de la manche précédente.
		assertDoesNotThrow(() -> {
			new Manche(joueurs, equipes, null, new Aleatoire(1), List.of());
			new Manche(joueurs, equipes, null, new Aleatoire(2), List.of());
		});
	}
}
