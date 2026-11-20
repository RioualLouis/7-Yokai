package engine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void lePointDEntreeTermineSansErreur() {
		assertTimeoutPreemptively(java.time.Duration.ofSeconds(5), () -> Main.main(new String[0]));
	}
}
