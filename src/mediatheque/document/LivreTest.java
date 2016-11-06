package mediatheque.document;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import util.InvariantBroken;

public class LivreTest {

	Localisation localisation;
	Genre genre;
	
	@Before
	public void setUp() throws Exception 
	{
		localisation = new Localisation("Salle", "Rayon");
		genre = new Genre("Nom");
	}

	@After
	public void tearDown() throws Exception 
	{
		localisation = null;
		genre = null;
	}

	@Test
	public void testEmprunter() throws OperationImpossible, InvariantBroken 
	{
		Livre li = new Livre("Code", localisation, "Titre", "Auteur", "Année", genre, 220);
		assertTrue(li.emprunter());
	}

	@Test
	public void testLivre() throws OperationImpossible, InvariantBroken 
	{
		Livre li = new Livre("Code", localisation, "Titre", "Auteur", "Année", genre, 220);
		
		assertTrue(li.getCode().equals("Code"));
		assertSame(li.getLocalisation(), localisation);
		assertTrue(li.getTitre().equals("Titre"));
		assertTrue(li.getAuteur().equals("Auteur"));
		assertTrue(li.getAnnee().equals("Année"));
		assertSame(li.getGenre(), genre);
		// On peut pas tester le nombre de pages car pas de getter...
	}
	
	@Test(expected=OperationImpossible.class)
	public void testLivreNbPagesNegatif() throws OperationImpossible, InvariantBroken 
	{
		new Livre("Code", localisation, "Titre", "Auteur", "Année", genre, -10);
	}

	@Test
	public void testInvariantLivre() throws OperationImpossible, InvariantBroken 
	{
		Livre li = new Livre("Code", localisation, "Titre", "Auteur", "Année", genre, 220);
		assertTrue(li.invariant());
	}

}
