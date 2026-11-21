package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarteTest {

	@Test
	void accesseurs() {
		Carte c = new Carte(5, Famille.ROSE);
		assertEquals(5, c.num());
		assertEquals(Famille.ROSE, c.famille());
	}

	@Test
	void deuxCartesIdentiquesSontEgales() {
		// Sans equals/hashCode, List.indexOf/contains ne peuvent pas comparer des cartes.
		assertEquals(new Carte(5, Famille.ROSE), new Carte(5, Famille.ROSE));
	}
}
