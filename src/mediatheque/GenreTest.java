package mediatheque;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GenreTest 
{

	@Before
	public void setUp() throws Exception 
	{
		
	}

	@After
	public void tearDown() throws Exception 
	{
		
	}

	@Test
	public void testGenre() 
	{
		Genre genre = new Genre("Nom");
		
		assertTrue(genre.getNom().equals("Nom"));
		assertEquals(1, genre.getNbEmprunts());
	}

	@Test
	public void testEmprunter() 
	{
		Genre genre = new Genre("Nom");
		int nbEmprunts_avant = genre.getNbEmprunts();
		
		genre.emprunter();
		
		assertEquals((nbEmprunts_avant + 1), genre.getNbEmprunts());
	}

}
