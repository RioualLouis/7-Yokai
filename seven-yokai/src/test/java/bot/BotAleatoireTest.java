package bot;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import engine.Aleatoire;

class BotAleatoireTest {

	@Test
	void choisirCarteRenvoieUnIndiceJouable() {
		// Le contrat de Strategie : renvoyer l'indice de la carte, pas sa position dans la liste.
		assertEquals(5, new BotAleatoire(new Aleatoire(0)).choisirCarte(List.of(5)));
	}

	@Test
	void lesCartesEchangeesSontDistinctes() {
		BotAleatoire bot = new BotAleatoire(new Aleatoire(0));
		for (int n = 0; n < 200; n++) {
			Set<Integer> s = new HashSet<>();
			for (int i : bot.choisirCartesEchange()) {
				s.add(i);
				}
			assertEquals(3, s.size());
		}
	}

	@Test
	void laDerniereCarteDeLaMainPeutEtreEchangee() {
		BotAleatoire bot = new BotAleatoire(new Aleatoire(0));
		Set<Integer> vus = new HashSet<>();
		for (int n = 0; n < 500; n++) for (int i : bot.choisirCartesEchange()) vus.add(i);
		assertTrue(vus.contains(11));
	}
}
