package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EquipeTest {

	@Test
	void compteurs() {
		Equipe e = new Equipe();
		e.attribuerPli(2);
		e.attribuerBoss(1);
		e.incrementerManchesGagnees();
		assertEquals(2, e.getPlisGagnes());
		assertEquals(1, e.getBossGagnes());
		assertEquals(1, e.getManchesRemportees());
	}

	@Test
	void refuseUnTroisiemeMembre() throws Exception {
		Equipe e = new Equipe();
		e.ajouteMembre();
		e.ajouteMembre();
		assertThrows(IllegalStateException.class, e::ajouteMembre);
	}

	@Test
	void refuseUnNombreDePlisNegatif() {
		assertThrows(IllegalArgumentException.class, () -> new Equipe().attribuerPli(-1));
	}
}
