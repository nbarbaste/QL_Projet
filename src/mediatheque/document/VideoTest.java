package mediatheque.document;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import util.InvariantBroken;

public class VideoTest {

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
		Video vi = new Video("Code", localisation, "Titre", "Auteur", "Année", genre, 120, "Mention Légale");
		int nbEmpruntsTotal_avant = vi.getNbEmprunts();
		assertTrue(vi.emprunter());
		assertTrue(vi.getNbEmprunts() == (nbEmpruntsTotal_avant + 1));
	}


	@Test
	public void testVideo() throws OperationImpossible, InvariantBroken 
	{
		Video vi = new Video("Code", localisation, "Titre", "Auteur", "Année", genre, 120, "Mention Légale");
		
		assertTrue(vi.getCode().equals("Code"));
		assertSame(vi.getLocalisation(), localisation);
		assertTrue(vi.getTitre().equals("Titre"));
		assertTrue(vi.getAuteur().equals("Auteur"));
		assertTrue(vi.getAnnee().equals("Année"));
		assertSame(vi.getGenre(), genre);
		assertEquals(vi.getDureeFilm(), 120);
		assertTrue(vi.getMentionLegale().equals("Mention Légale"));
	}
	
	@Test(expected=OperationImpossible.class)
	public void testVideoDureeZero() throws OperationImpossible, InvariantBroken 
	{
		new Video("Code", localisation, "Titre", "Auteur", "Année", genre, 0, "Mention Légale");
	}
	
	@Test(expected=OperationImpossible.class)
	public void testVideoMentionLegaleNull() throws OperationImpossible, InvariantBroken 
	{
		new Video("Code", localisation, "Titre", "Auteur", "Année", genre, 120, null);
	}
	
	@Test(expected=InvariantBroken.class)
	public void testVideoDureeNegative() throws OperationImpossible, InvariantBroken 
	{
		new Video("Code", localisation, "Titre", "Auteur", "Année", genre, -10 , "Mention Légale");
	}

	@Test(expected=InvariantBroken.class)
	public void testInvariantVideoDureeNegative() throws OperationImpossible, InvariantBroken 
	{
		Video vi = new Video("Code", localisation, "Titre", "Auteur", "Année", genre, -10 , "Mention Légale");
	}

}
