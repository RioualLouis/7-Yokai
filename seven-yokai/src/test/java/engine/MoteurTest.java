package engine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Joueur;

class MoteurTest {

	@Test
	void ajouterQuatreParticipants() {
		Moteur moteur = new Moteur(new Aleatoire(42));
		assertDoesNotThrow(() -> {
			for (int i = 0; i < 4; i++) moteur.ajouterParticipant();
		});
	}

	@Test
	void unePartieRenvoieDeuxGagnantsDeLaMemeEquipe() throws Exception {
		Moteur moteur = new Moteur(new Aleatoire(42));
		for (int i = 0; i < 4; i++) moteur.ajouterParticipant();
		Joueur[] gagnants = moteur.lancerPartie();
		assertEquals(2, gagnants.length);
		assertNotNull(gagnants[0]);
		assertSame(gagnants[0].getEquipe(), gagnants[1].getEquipe());
	}
}
