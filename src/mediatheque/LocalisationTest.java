package mediatheque;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LocalisationTest 
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
	public void testLocalisation() 
	{
		Localisation loc = new Localisation("Salle", "Rayon");
		
		assertTrue(loc.getSalle().equals("Salle"));
		assertTrue(loc.getRayon().equals("Rayon"));
	}

}
