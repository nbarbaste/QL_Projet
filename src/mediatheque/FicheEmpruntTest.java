package mediatheque;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.document.Audio;
import mediatheque.document.Document;
import util.Datutil;
import util.InvariantBroken;

public class FicheEmpruntTest 
{
	Mediatheque m;
	CategorieClient cat;
	Client c;
	Audio au;
	Localisation localisation;
	Genre genre;

	@Before
	public void setUp() throws Exception 
	{
		String nom = "Nom";
		int max = 3;
		double cot = 3;
		double coefDuree = 3;
		double coefTarif = 3;
		boolean codeReducActif = false;
		cat = new CategorieClient(nom, max, cot, coefDuree, coefTarif, codeReducActif);
		m = new Mediatheque("Nom");
		c = new Client("Nom", "Prenom", "Adresse", cat);
		au = new Audio("Code", localisation, "Titre", "Auteur", "Année", genre, "Classification");
	}

	@After
	public void tearDown() throws Exception 
	{
		m = null;
		cat = null;
		c = null;
		au = null;
		localisation = null;
		genre = null;
		
	}

	@Test
	public void testFicheEmprunt() throws OperationImpossible, InvariantBroken 
	{
		FicheEmprunt ficheEmprunt = new FicheEmprunt(m, c, au);
		
		// assertSame(ficheEmprunt.getMediatheque(), m);
		assertSame(c, ficheEmprunt.getClient());
		assertSame(au, ficheEmprunt.getDocument());
		assertEquals(Datutil.dateDuJour(), ficheEmprunt.getDateEmprunt());
		assertEquals(au.dureeEmprunt(), ficheEmprunt.getDureeEmprunt());
		assertEquals(c.dateRetour(Datutil.dateDuJour(), au.dureeEmprunt()), ficheEmprunt.getDateLimite());
		assertFalse(ficheEmprunt.getDepasse());
	}

	@Test
	public void testVerifier() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testCorrespond() throws OperationImpossible, InvariantBroken 
	{
		FicheEmprunt ficheEmprunt = new FicheEmprunt(m, c, au);
		
		assertTrue(ficheEmprunt.correspond(c, au));
	}

	@Test
	public void testRestituer() 
	{
		fail("Not yet implemented");
	}

}
