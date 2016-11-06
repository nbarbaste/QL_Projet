package mediatheque;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.document.Audio;
import util.InvariantBroken;

public class LettreRappelTest 
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
	public void testLettreRappel() throws OperationImpossible, InvariantBroken 
	{
		String nomMedia = "Non Media";
		FicheEmprunt ficheEmprunt = new FicheEmprunt(m, c , au);
		
		LettreRappel lettre = new LettreRappel(nomMedia, ficheEmprunt);
		
		assertEquals(ficheEmprunt.getDateLimite(), lettre.getDateRappel());
	}

	@Test
	public void testRelancer() 
	{
		fail("Not yet implemented");
	}

}
