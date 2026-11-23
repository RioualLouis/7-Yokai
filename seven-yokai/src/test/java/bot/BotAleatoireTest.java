package bot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import engine.Aleatoire;
import engine.VueJoueur;

class BotAleatoireTest {

	@Deprecated
	@Test
	void lesCartesEchangeesSontDistinctes() {
		BotAleatoire bot = new BotAleatoire(new Aleatoire(0));
		for (int n = 0; n < 200; n++) {
			Set<Integer> s = new HashSet<>();
			for (int i : bot.choisirCartesEchange(mock(VueJoueur.class))) {
				s.add(i);
			}
			assertEquals(3, s.size());
		}
	}

	@Deprecated
	@Test
	void laDerniereCarteDeLaMainPeutEtreEchangee() {
		BotAleatoire bot = new BotAleatoire(new Aleatoire(0));
		Set<Integer> vus = new HashSet<>();
		for (int n = 0; n < 500; n++)
			for (int i : bot.choisirCartesEchange(mock(VueJoueur.class)))
				vus.add(i);
		assertTrue(vus.contains(11));
	}
}
