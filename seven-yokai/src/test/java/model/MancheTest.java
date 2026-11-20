package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bot.BotAleatoire;
import engine.Aleatoire;

class MancheTest {
	private List<Joueur> joueurs;

	@BeforeEach
	void setUp() {
		Aleatoire rng = new Aleatoire(42);
		Equipe a = new Equipe(), b = new Equipe();
		joueurs = List.of(new Joueur(new BotAleatoire(rng), a), new Joueur(new BotAleatoire(rng), b),
				new Joueur(new BotAleatoire(rng), a), new Joueur(new BotAleatoire(rng), b));
	}

	@Test
	void constructionDUneManche() {
		assertDoesNotThrow(() -> new Manche(joueurs, null, new Aleatoire(42), List.of()));
	}

	@Test
	void chaqueJoueurRecoitDouzeCartes() throws Exception {
		new Manche(joueurs, null, new Aleatoire(42), List.of());
		for (Joueur j : joueurs) assertEquals(12, j.getMain().size());
	}

	@Test
	void deuxManchesSuccessivesSontPossibles() {
		// La pioche statique ne doit pas conserver les cartes de la manche précédente.
		assertDoesNotThrow(() -> {
			new Manche(joueurs, null, new Aleatoire(1), List.of());
			new Manche(joueurs, null, new Aleatoire(2), List.of());
		});
	}
}
