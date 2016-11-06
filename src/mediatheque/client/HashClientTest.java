package mediatheque.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HashClientTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHashClient() {
		String nom = "nom";
		String prenom = "prenom";
		HashClient hcl = new HashClient(nom,prenom);
		assertEquals(nom,hcl.getNom());
		assertEquals(prenom,hcl.getPrenom());
	}

}
