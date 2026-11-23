package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
	void refuseUnNombreDePlisNegatif() {
		assertThrows(IllegalArgumentException.class, () -> new Equipe().attribuerPli(-1));
	}
}
