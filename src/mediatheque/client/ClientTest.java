package mediatheque.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.Mediatheque;
import mediatheque.OperationImpossible;
import mediatheque.document.Audio;
import mediatheque.document.Document;

public class ClientTest {

	@Before
	public void setUp() throws Exception {
		Mediatheque med = new Mediatheque("SuperMedia");
		Client monClient = new Client("Dupond","Jean");
		Audio myDoc = new Audio("code", new Localisation("salle","rayon"), "titre", "auteur", "annee", new Genre("n"), "classification");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClientStringStringStringCategorieClient() throws OperationImpossible {
		String nom = "Dupond";
		String prenom = "Jean";
		String adresse = "2 rue du moulin";
		CategorieClient catClient = new CategorieClient("Senior");
		
		Client monClient = new Client(nom,prenom,adresse,catClient);
		
		assertTrue(monClient.getNom().equals(nom));
		assertTrue(monClient.getPrenom().equals(prenom));
		assertTrue(monClient.getAdresse().equals(adresse));
		assertTrue(monClient.getCategorie()==catClient);
	}

	@Test
	public void testClientStringStringStringCategorieClientInt() throws OperationImpossible {
		String nom = "Dupond";
		String prenom = "Jean";
		String adresse = "2 rue du moulin";
		CategorieClient catClient = new CategorieClient(nom);
		int code = 1;
		
		Client monClient = new Client(nom,prenom,adresse,catClient,code);
		
		assertTrue(monClient.getNom().equals(nom));
		assertTrue(monClient.getPrenom().equals(prenom));
		assertTrue(monClient.getAdresse().equals(adresse));
		assertTrue(monClient.getCategorie().equals(catClient));
		assertTrue(monClient.getReduc()==code);
	}

	@Test
	public void testClientStringString() {
		String nom = "Dupond";
		String prenom = "Jean";
		
		Client monClient = new Client(nom,prenom);
		
		assertTrue(monClient.getNom().equals(nom));
		assertTrue(monClient.getPrenom().equals(prenom));
	}

	@Test
	public void testADesEmpruntsEnCours() {
		Client monClient = new Client("Dupond","Jean");
		assertFalse(monClient.aDesEmpruntsEnCours());
		monClient.emprunter();
		assertFalse(monClient.aDesEmpruntsEnCours());
	}

	@Test
	public void testPeutEmprunter() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmprunterFicheEmprunt() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmprunter() {
		fail("Not yet implemented");
	}

	@Test
	public void testMarquer() {
		fail("Not yet implemented");
	}

	@Test
	public void testRestituerFicheEmprunt() {
		fail("Not yet implemented");
	}

	@Test
	public void testRestituerBoolean() {
		fail("Not yet implemented");
	}

	@Test
	public void testAfficherStatistiques() {
		fail("Not yet implemented");
	}

	@Test
	public void testAfficherStatCli() {
		fail("Not yet implemented");
	}

	@Test
	public void testDateRetour() {
		fail("Not yet implemented");
	}

	@Test
	public void testSommeDue() {
		fail("Not yet implemented");
	}

	@Test
	public void testNbMaxEmprunt() {
		fail("Not yet implemented");
	}

}
