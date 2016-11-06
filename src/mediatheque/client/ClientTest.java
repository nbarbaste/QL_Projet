package mediatheque.client;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.FicheEmprunt;
import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.Mediatheque;
import mediatheque.OperationImpossible;
import mediatheque.document.Audio;
import util.Datutil;
import util.InvariantBroken;

public class ClientTest {
	
	public Mediatheque med;
	public Localisation loc;
	public Genre genre;
	public Audio audio;
	public CategorieClient cat1, cat2;
	public Client cl1, cl2;

	@Before
	public void setUp() throws Exception , OperationImpossible{
		med = new Mediatheque("SuperMedia");
		loc = new Localisation("salle","rayon");
		genre = new Genre("n");
		audio = new Audio("code", loc, "titre", "auteur", "annee", genre, "classification");
		cat1 = new CategorieClient("cat1",1,1,1,1,false);
		cat2= new CategorieClient("cat2",2,2,2,2,false);
		cl1 = new Client("nom","prenom","adresse",cat1,1);
		cl2 = new Client("nom","prenom","adresse",cat2,2);
	}

	@After
	public void tearDown() throws Exception {
		med = null;
		loc = null;
		genre = null;
		audio = null;
		cat1 = null;
		cat2 = null;
		cl1 = null;
		cl2 = null;
	}

	@Test
	public void testClientStringStringStringCategorieClient() throws OperationImpossible {
		String nom = "nom";
		String prenom = "prenom";
		String adresse = "adresse";
		CategorieClient catClient = new CategorieClient("categorie");
		
		Client cl = new Client(nom,prenom,adresse,catClient);
		
		assertTrue(cl.getNom().equals(nom));
		assertTrue(cl.getPrenom().equals(prenom));
		assertTrue(cl.getAdresse().equals(adresse));
		assertSame(cl.getCategorie(),catClient);
	}

	@Test
	public void testClientStringStringStringCategorieClientInt() throws OperationImpossible {
		String nom = "nom";
		String prenom = "prenom";
		String adresse = "adresse";
		CategorieClient catClient = new CategorieClient("categorie");
		int code = 1;
		
		Client cl = new Client(nom,prenom,adresse,catClient,code);
		
		assertTrue(cl.getNom().equals(nom));
		assertTrue(cl.getPrenom().equals(prenom));
		assertTrue(cl.getAdresse().equals(adresse));
		assertSame(cl.getCategorie(),catClient);
		assertEquals(cl.getReduc(),code);
	}

	@Test
	public void testClientStringString() throws OperationImpossible{
		String nom = "nom";
		String prenom = "prenom";
		
		Client cl = new Client(nom,prenom);
		
		assertTrue(cl.getNom().equals(nom));
		assertTrue(cl.getPrenom().equals(prenom));
	}

	@Test
	public void testADesEmpruntsEnCours() throws OperationImpossible{
		Client cl = new Client("nom","prenom");
		assertFalse(cl.aDesEmpruntsEnCours());
		cl.emprunter();
		assertTrue(cl.aDesEmpruntsEnCours());
	}

	@Test
	public void testPeutEmprunter() throws OperationImpossible{
		Client cl = new Client("nom","prenom");
		assertTrue(cl.peutEmprunter());
		cl.emprunter();
		cl.marquer();
		assertFalse(cl.peutEmprunter());
	}

	@Test
	public void testEmprunterFicheEmprunt() throws OperationImpossible, InvariantBroken{
		Client cl = new Client("nom","prenom");
		FicheEmprunt fiche = new FicheEmprunt(med,cl,audio);
		
		int effectues = cl.getNbEmpruntsEffectues();
        int enCours = cl.getNbEmpruntsEnCours();
        
        cl.emprunter(fiche);
        
        assertEquals(effectues+1,cl.getNbEmpruntsEffectues());
        assertEquals(enCours+1,cl.getNbEmpruntsEnCours());
	}

	@Test
	public void testEmprunter() throws OperationImpossible{
		Client cl = new Client("nom","prenom");
		int effectues = cl.getNbEmpruntsEffectues();
        int enCours = cl.getNbEmpruntsEnCours();
        
        cl.emprunter();
        
        assertEquals(effectues+1,cl.getNbEmpruntsEffectues());
        assertEquals(enCours+1,cl.getNbEmpruntsEnCours());
	}

	@Test(expected=OperationImpossible.class)
	public void testMarquerImpossible() throws OperationImpossible{
		Client cl = new Client("nom","prenom");
		cl.marquer();
	}
	
	@Test
	public void testMarquer() throws OperationImpossible{
		Client cl = new Client("nom","prenom");
		int ret;
		
		cl.emprunter();
		ret = cl.getNbEmpruntsEnRetard();
		
		cl.marquer();
		assertEquals(ret+1,cl.getNbEmpruntsEnRetard());
	}

	@Test(expected=OperationImpossible.class)
	public void testRestituerFicheEmpruntImpossible() throws OperationImpossible, InvariantBroken{
		Client cl = new Client("nom","prenom");
		FicheEmprunt fiche = new FicheEmprunt(med,cl,audio);
		
		cl.restituer(fiche);
	}
	
	@Test
	public void testRestituerFicheEmprunt() throws OperationImpossible, InvariantBroken{
		Client cl = new Client("nom","prenom");
		FicheEmprunt fiche = new FicheEmprunt(med,cl,audio);
		int emp;
		
		cl.emprunter(fiche);
		emp = cl.getNbEmpruntsEnCours();
		
		cl.restituer(fiche);
		assertEquals(cl.getNbEmpruntsEnCours(),emp-1);
	}
	
	
	
	@Test(expected=OperationImpossible.class)
	public void testRestituerFicheBooleanImpossible() throws OperationImpossible{
		Client cl = new Client("nom","prenom");
		
		cl.emprunter();
		cl.restituer(true);
	}
	
	@Test
	public void testRestituerFicheBoolean() throws OperationImpossible, InvariantBroken{
		Client cl = new Client("nom","prenom");
		FicheEmprunt fiche = new FicheEmprunt(med,cl,audio);
		int emp, ret;
		
		cl.emprunter(fiche);
		cl.marquer();
		
		emp = cl.getNbEmpruntsEnCours();
		ret = cl.getNbEmpruntsEnRetard();
		cl.restituer(true);
		
		assertEquals(cl.getNbEmpruntsEnCours(),emp-1);
		assertEquals(cl.getNbEmpruntsEnRetard(),ret-1);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testDateRetour() throws OperationImpossible{
		Client cl = new Client("nom","prenom");
		
		Date jour = new Date(2010,10,10);
		int duree = 7;
		Date nouveauJour = cl.dateRetour(jour,duree);
		
		assertTrue(nouveauJour.equals(Datutil.addDate(jour, duree)));
	}

	@Test
	public void testSommeDue() {
		int tarif = 15;
		assertEquals((int)cl1.sommeDue(tarif),(int)cat1.getCoefTarif()*tarif);
		assertEquals((int)cl2.sommeDue(tarif),(int)cat2.getCoefTarif()*tarif);
	}

	@Test
	public void testNbMaxEmprunt() {
		assertEquals(cat1.getNbEmpruntMax(),cl1.nbMaxEmprunt());
		assertEquals(cat2.getNbEmpruntMax(),cl2.nbMaxEmprunt());
	}

}
