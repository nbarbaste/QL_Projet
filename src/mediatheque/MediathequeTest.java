package mediatheque;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.document.Audio;
import util.InvariantBroken;

public class MediathequeTest {
	Mediatheque med;

	@Before
	public void setUp() throws Exception {
		med = new Mediatheque("nom");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMediatheque() {
		String nom = "nom";
		Mediatheque med1 = new Mediatheque(nom);
		
		assertEquals(nom,med1.getNom());
	}
	
	@Test
	public void testChercherGenreEchec() throws OperationImpossible {
		String nomGenre = "Genre";
		Mediatheque med1 = new Mediatheque("nom");
		
		assertEquals(null,med1.chercherGenre(nomGenre));
	}

	@Test
	public void testChercherGenre() throws OperationImpossible {
		String nomGenre = "Genre";
		Mediatheque med1 = new Mediatheque("nom");
		
		med1.ajouterGenre(nomGenre);
		
		assertEquals(new Genre(nomGenre),med1.chercherGenre(nomGenre));
	}

	@Test(expected=OperationImpossible.class)
	public void testSupprimerGenreImpossible1() throws OperationImpossible {
		String nomGenre = "Genre";
		Mediatheque med1 = new Mediatheque("nom");
		
		med1.supprimerGenre(nomGenre);
	}
	
	@Test(expected=OperationImpossible.class)
	public void testSupprimerGenreImpossible2() throws OperationImpossible, InvariantBroken{
		String nomGenre = "Genre";
		Mediatheque med1 = new Mediatheque("nom");
		Audio audio = new Audio("code",new Localisation("salle","rayon"),"titre","auteur","annee",new Genre(nomGenre),"class");
		
		med1.ajouterGenre(nomGenre);
		med1.ajouterDocument(audio);
		med1.supprimerGenre(nomGenre);
	}
	
	@Test
	public void testSupprimerGenre() throws OperationImpossible, InvariantBroken{
		String nomGenre = "Genre";
		Mediatheque med1 = new Mediatheque("nom");
		
		med1.ajouterGenre(nomGenre);
		med1.supprimerGenre(nomGenre);
		
		assertEquals(null,med1.chercherGenre(nomGenre));
	}
	
	@Test(expected=OperationImpossible.class)
	public void testAjouterGenreImpossible() throws OperationImpossible{
		String nomGenre = "Genre";
		Mediatheque med1 = new Mediatheque("nom");
		
		med1.ajouterGenre(nomGenre);
		med1.ajouterGenre(nomGenre);
		
		assertEquals(new Genre(nomGenre),med1.chercherGenre(nomGenre));
	}

	@Test
	public void testAjouterGenre() throws OperationImpossible{
		String nomGenre = "Genre";
		Mediatheque med1 = new Mediatheque("nom");
		
		med1.ajouterGenre(nomGenre);
		
		assertEquals(new Genre(nomGenre),med1.chercherGenre(nomGenre));
	}

	@Test(expected=OperationImpossible.class)
	public void testModifierGenreImpossible() throws OperationImpossible{
		String nomGenre = "Genre";
		Mediatheque med1 = new Mediatheque("nom");
		
		med1.modifierGenre(nomGenre,"nouveau");
	}
	
	@Test
	public void testModifierGenre() throws OperationImpossible{
		String nomGenre = "Genre";
		String nouveau = "nouveau";
		Mediatheque med1 = new Mediatheque("nom");
		
		med1.ajouterGenre(nomGenre);
		med1.modifierGenre(nomGenre,nouveau);
		
		assertEquals(null,med1.chercherGenre(nomGenre));
		assertEquals(new Genre(nouveau),med1.chercherGenre(nouveau));
	}
	
	@Test(expected=OperationImpossible.class)
	public void testSupprimerLocalisationImpossible1() throws OperationImpossible{
		String salle = "Salle";
		String rayon = "Rayon";
		Mediatheque med1 = new Mediatheque("nom");
		
		med1.supprimerLocalisation(salle,rayon);
	}
	
	@Test(expected=OperationImpossible.class)
	public void testSupprimerLocalisationImpossible2() throws OperationImpossible, InvariantBroken{
		String salle = "Salle";
		String rayon = "Rayon";
		Mediatheque med1 = new Mediatheque("nom");
		Audio audio = new Audio("code",new Localisation(salle,rayon),"titre","auteur","annee",new Genre("nom"),"class");
		
		med1.ajouterLocalisation(salle,rayon);
		med1.ajouterDocument(audio);
		med1.supprimerLocalisation(salle,rayon);
	}

	@Test
	public void testSupprimerLocalisation() throws OperationImpossible{
		String salle = "Salle";
		String rayon = "Rayon";
		Mediatheque med1 = new Mediatheque("nom");
		
		med1.ajouterLocalisation(salle,rayon);
		med1.supprimerLocalisation(salle,rayon);
		
		assertEquals(null,med1.chercherLocalisation(salle,rayon));
	}

	@Test
	public void testChercherLocalisationEchec() throws OperationImpossible {
		String salle = "Salle";
		String rayon = "Rayon";
		Mediatheque med1 = new Mediatheque("nom");
		
		assertEquals(null,med1.chercherLocalisation(salle,rayon));
	}

	@Test
	public void testChercherLocalisation() throws OperationImpossible {
		String salle = "Salle";
		String rayon = "Rayon";
		Mediatheque med1 = new Mediatheque("nom");
		
		med1.ajouterLocalisation(salle,rayon);
		
		assertEquals(new Localisation(salle,rayon),med1.chercherLocalisation(salle,rayon));
	}

	@Test
	public void testAjouterLocalisation() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifierLocalisation() {
		fail("Not yet implemented");
	}

	@Test
	public void testChercherCatClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testSupprimerCatClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testAjouterCatClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifierCatClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testChercherDocument() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testAjouterDocument() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testRetirerDocument() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testMetEmpruntable() {
		fail("Not yet implemented");
	}

	@Test
	public void testMetConsultable() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmprunter() {
		fail("Not yet implemented");
	}

	@Test
	public void testRestituer() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerifier() {
		fail("Not yet implemented");
	}

	@Test
	public void testInscrireStringStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testInscrireStringStringStringStringInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testResilier() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifierClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangerCategorie() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangerCodeReduction() {
		fail("Not yet implemented");
	}

	@Test
	public void testChercherClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitFromFile() {
		fail("Not yet implemented");
	}

}
