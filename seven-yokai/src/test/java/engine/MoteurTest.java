package engine;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import model.Joueur;
import model.Strategie;

class MoteurTest {

	@Test
	void ajouterQuatreParticipants() {
		Moteur moteur = new Moteur(new Aleatoire(42));
		assertDoesNotThrow(() -> {
			for (int i = 0; i < 4; i++)
				moteur.ajouterParticipant("J" + i, mock(Strategie.class));
		});
	}

	@Test
	void unePartieRenvoieDeuxGagnantsDeLaMemeEquipe() throws Exception {
		Moteur moteur = new Moteur(new Aleatoire(42));
		for (int i = 0; i < 4; i++)
			moteur.ajouterParticipant("J" + i, mock(Strategie.class));
		Joueur[] gagnants = moteur.lancerPartie();
		assertEquals(2, gagnants.length);
		assertNotNull(gagnants[0]);
		assertSame(gagnants[0].getEquipe(), gagnants[1].getEquipe());
	}
}
