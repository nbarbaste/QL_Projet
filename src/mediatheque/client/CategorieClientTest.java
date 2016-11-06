package mediatheque.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CategorieClientTest {
	static final double delta = 0.000001;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCategorieClientStringIntDoubleDoubleDoubleBoolean() {
		String nom = "nom";
		int max = 3;
		double cot = 3;
		double coefDuree = 3;
		double coefTarif = 3;
		boolean codeReducActif = false;
		CategorieClient cat = new CategorieClient(nom, max, cot, coefDuree, coefTarif, codeReducActif);
		
		assertEquals(nom,cat.getNom());
		assertEquals(max,cat.getNbEmpruntMax());
		assertEquals(cot,cat.getCotisation(),delta);
		assertEquals(coefDuree,cat.getCoefDuree(),delta);
		assertEquals(coefTarif,cat.getCoefTarif(),delta);
		assertEquals(codeReducActif,cat.getCodeReducUtilise());
	}

	@Test
	public void testCategorieClientString() {
		String nom = "nom";
		CategorieClient cat = new CategorieClient(nom);
		assertEquals(nom,cat.getNom());
	}

}
