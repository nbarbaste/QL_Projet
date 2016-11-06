package mediatheque.document;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import util.InvariantBroken;

public class VideoTest 
{

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
		Video vi = new Video("Code", localisation, "Titre", "Auteur", "Ann�e", genre, 120, "Mention L�gale");
		int nbEmpruntsTotal_avant = vi.getNbEmprunts();
		assertTrue(vi.emprunter());
		assertEquals((nbEmpruntsTotal_avant + 1), vi.getNbEmprunts());
	}


	@Test
	public void testVideo() throws OperationImpossible, InvariantBroken 
	{
		Video vi = new Video("Code", localisation, "Titre", "Auteur", "Ann�e", genre, 120, "Mention L�gale");
		
		assertTrue(vi.getCode().equals("Code"));
		assertSame(localisation, vi.getLocalisation());
		assertTrue(vi.getTitre().equals("Titre"));
		assertTrue(vi.getAuteur().equals("Auteur"));
		assertTrue(vi.getAnnee().equals("Ann�e"));
		assertSame(genre, vi.getGenre());
		assertEquals(120, vi.getDureeFilm());
		assertTrue(vi.getMentionLegale().equals("Mention L�gale"));
	}
	
	@Test(expected=OperationImpossible.class)
	public void testVideoDureeZero() throws OperationImpossible, InvariantBroken 
	{
		new Video("Code", localisation, "Titre", "Auteur", "Ann�e", genre, 0, "Mention L�gale");
	}
	
	@Test(expected=OperationImpossible.class)
	public void testVideoMentionLegaleNull() throws OperationImpossible, InvariantBroken 
	{
		new Video("Code", localisation, "Titre", "Auteur", "Ann�e", genre, 120, null);
	}
	
	@Test(expected=InvariantBroken.class)
	public void testVideoDureeNegative() throws OperationImpossible, InvariantBroken 
	{
		new Video("Code", localisation, "Titre", "Auteur", "Ann�e", genre, -10 , "Mention L�gale");
	}

	@Test(expected=InvariantBroken.class)
	public void testInvariantVideoDureeNegative() throws OperationImpossible, InvariantBroken 
	{
		new Video("Code", localisation, "Titre", "Auteur", "Ann�e", genre, -10 , "Mention L�gale");
	}
	
	@Test
	public void testInvariantOK() throws OperationImpossible, InvariantBroken 
	{
		Video vi = new Video("Code", localisation, "Titre", "Auteur", "Ann�e", genre, 120, "Mention L�gale");
		assertTrue(vi.invariantVideo());
	}

}
