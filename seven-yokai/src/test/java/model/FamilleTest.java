package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FamilleTest {

	@Test
	void chaqueFamilleContientSeptCartes() {
		for (Famille f : Famille.values()) assertEquals(7, f.getFin() - f.getDebut() + 1, f.name());
	}

	@Test
	void chaqueFamilleContientUnBoss7() {
		for (Famille f : Famille.values()) assertTrue(f.getDebut() <= 7 && 7 <= f.getFin(), f.name());
	}
}
