package engine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AleatoireTest {

	@Test
	void memeGraineMemeSequence() {
		Aleatoire a = new Aleatoire(7), b = new Aleatoire(7);
		for (int i = 0; i < 20; i++) assertEquals(a.nextInt(100), b.nextInt(100));
		assertEquals(7, a.getSeed());
	}
}
