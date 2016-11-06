/**
 * 
 */
package mediatheque.document;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import util.InvariantBroken;

/**
 * @author Nicolas
 *
 */
public class AudioTest 
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
		Audio au = new Audio("Code", localisation, "Titre", "Auteur", "Année", genre, "Classification");
		int nbEmpruntsTotal_avant = au.getNbEmprunts();
		assertTrue(au.emprunter());
		assertTrue(au.getNbEmprunts() == (nbEmpruntsTotal_avant + 1));
	}

	@Test
	public void testAudio() throws OperationImpossible, InvariantBroken 
	{
		Audio au = new Audio("Code", localisation, "Titre", "Auteur", "Année", genre, "Classification");
		
		assertTrue(au.getCode().equals("Code"));
		assertSame(au.getLocalisation(), localisation);
		assertTrue(au.getTitre().equals("Titre"));
		assertTrue(au.getAuteur().equals("Auteur"));
		assertTrue(au.getAnnee().equals("Année"));
		assertSame(au.getGenre(), genre);
		assertTrue(au.getClassification().equals("Classification"));
	}
	
	@Test(expected=OperationImpossible.class)
	public void testAudioClassificationNull() throws OperationImpossible, InvariantBroken 
	{
		new Audio("Code", localisation, "Titre", "Auteur", "Année", genre, null);
	}

}
