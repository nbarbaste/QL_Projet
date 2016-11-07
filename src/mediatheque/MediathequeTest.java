package mediatheque;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.document.Audio;
import util.InvariantBroken;

public class MediathequeTest {
	String nom = "Nom";
	String prenom = "Prenom";
	String adresse = "Adresse";
	String nomGenre = "Genre";
	String nomCat = "Cat";
	String salle = "Salle";
	String rayon = "Rayon";
	int max = 3;
	double cot = 3;
	double coefD = 3;
	double coefT = 3;
	boolean codeR = false;
	Mediatheque med;
	Localisation localisation;
	Genre genre;
	Audio au;
	CategorieClient cat;
	Client cl;

	@Before
	public void setUp() throws Exception {
		med = new Mediatheque("nom");
		genre = new Genre(nomGenre);
		localisation = new Localisation(salle,rayon);
		au = new Audio("Code", localisation, "Titre", "Auteur", "Année", genre, "Classification");
		cat = new CategorieClient(nomCat,max,cot,coefD,coefT,codeR);
		cl = new Client(nom,prenom,adresse,cat,1);
	}

	@After
	public void tearDown() throws Exception {
		med = null;
		au = null;
		genre = null;
		localisation = null;
		cat = null;
		cl = null;
	}

	@Test
	public void testMediatheque() {
		Mediatheque med1 = new Mediatheque(nom);
		
		assertEquals(nom,med1.getNom());
	}
	
	@Test
	public void testChercherGenreEchec() throws OperationImpossible {
		Mediatheque med1 = new Mediatheque(nom);
		
		assertEquals(null,med1.chercherGenre(nomGenre));
	}

	@Test
	public void testChercherGenre() throws OperationImpossible {
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterGenre(nomGenre);
		
		assertEquals(genre,med1.chercherGenre(nomGenre));
	}

	@Test(expected=OperationImpossible.class)
	public void testSupprimerGenreImpossible1() throws OperationImpossible {
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.supprimerGenre(nomGenre);
	}
	
	@Test(expected=OperationImpossible.class)
	public void testSupprimerGenreImpossible2() throws OperationImpossible, InvariantBroken{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterGenre(nomGenre);
		med1.ajouterDocument(au);
		med1.supprimerGenre(nomGenre);
	}
	
	@Test
	public void testSupprimerGenre() throws OperationImpossible, InvariantBroken{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterGenre(nomGenre);
		med1.supprimerGenre(nomGenre);
		
		assertEquals(null,med1.chercherGenre(nomGenre));
	}
	
	@Test(expected=OperationImpossible.class)
	public void testAjouterGenreImpossible() throws OperationImpossible{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterGenre(nomGenre);
		med1.ajouterGenre(nomGenre);
	}

	@Test
	public void testAjouterGenre() throws OperationImpossible{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterGenre(nomGenre);
		
		assertEquals(genre,med1.chercherGenre(nomGenre));
	}

	@Test(expected=OperationImpossible.class)
	public void testModifierGenreImpossible() throws OperationImpossible{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.modifierGenre(nomGenre,"nouveau");
	}
	
	@Test
	public void testModifierGenre() throws OperationImpossible{
		String nouveau = "nouveau";
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterGenre(nomGenre);
		med1.modifierGenre(nomGenre,nouveau);
		
		assertEquals(null,med1.chercherGenre(nomGenre));
		assertEquals(new Genre(nouveau),med1.chercherGenre(nouveau));
	}
	
	@Test(expected=OperationImpossible.class)
	public void testSupprimerLocalisationImpossible1() throws OperationImpossible{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.supprimerLocalisation(salle,rayon);
	}
	
	@Test(expected=OperationImpossible.class)
	public void testSupprimerLocalisationImpossible2() throws OperationImpossible, InvariantBroken{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterLocalisation(salle,rayon);
		med1.ajouterDocument(au);
		med1.supprimerLocalisation(salle,rayon);
	}

	@Test
	public void testSupprimerLocalisation() throws OperationImpossible{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterLocalisation(salle,rayon);
		med1.supprimerLocalisation(salle,rayon);
		
		assertEquals(null,med1.chercherLocalisation(salle,rayon));
	}

	@Test
	public void testChercherLocalisationEchec() throws OperationImpossible {
		Mediatheque med1 = new Mediatheque(nom);
		
		assertEquals(null,med1.chercherLocalisation(salle,rayon));
	}

	@Test
	public void testChercherLocalisation() throws OperationImpossible {
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterLocalisation(salle,rayon);
		
		assertEquals(localisation,med1.chercherLocalisation(salle,rayon));
	}

	@Test(expected=OperationImpossible.class)
	public void testAjouterLocalisationImpossible() throws OperationImpossible{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterLocalisation(salle,rayon);
		med1.ajouterLocalisation(salle,rayon);
	}

	@Test
	public void testAjouterLocalisation() throws OperationImpossible{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterLocalisation(salle,rayon);
		
		assertEquals(localisation,med1.chercherLocalisation(salle,rayon));
	}

	@Test(expected=OperationImpossible.class)
	public void testModifierLocalisationImpossible() throws OperationImpossible{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.modifierLocalisation(localisation,"newS","newR");
	}
	
	@Test
	public void testModifierLocalisation() throws OperationImpossible{
		String newS = "nouveauS";
		String newR = "nouveauR";
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterLocalisation(salle,rayon);
		med1.modifierLocalisation(localisation,newS,newR);
		
		assertEquals(null,med1.chercherLocalisation(salle,rayon));
		assertEquals(new Localisation(newS,newR),med1.chercherLocalisation(newS,newR));
	}

	@Test
	public void testChercherCatClientEchec() throws OperationImpossible {
		Mediatheque med1 = new Mediatheque(nom);
		
		assertEquals(null,med1.chercherCatClient(nomCat));
	}

	@Test
	public void testChercherCatClient() throws OperationImpossible {
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterCatClient(nomCat,max,cot,coefD,coefT,codeR);
		
		assertEquals(cat,med1.chercherCatClient(nomCat));
	}
	
	@Test(expected=OperationImpossible.class)
	public void testSupprimerCatClientImpossible1() throws OperationImpossible{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.supprimerCatClient(nomCat);
	}
	
	@Test(expected=OperationImpossible.class)
	public void testSupprimerCatClientImpossible2() throws OperationImpossible{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterCatClient(nomCat,max,cot,coefD,coefT,codeR);
		med1.inscrire(nomCat,prenom,adresse,nom);
		med1.supprimerCatClient(nomCat);
	}

	@Test
	public void testSupprimerCatClient() throws OperationImpossible{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterCatClient(nomCat,max,cot,coefD,coefT,codeR);
		med1.supprimerCatClient(nomCat);
		
		assertEquals(null,med1.chercherCatClient(nomCat));
	}

	@Test(expected=OperationImpossible.class)
	public void testAjouterCatClientImpossible() throws OperationImpossible{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterCatClient(nomCat,max,cot,coefD,coefT,codeR);
		med1.ajouterCatClient(nomCat,max,cot,coefD,coefT,codeR);
	}

	@Test
	public void testAjouterCatClient() throws OperationImpossible{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterCatClient(nomCat,max,cot,coefD,coefT,codeR);
		
		assertEquals(cat,med1.chercherCatClient(nomCat));
	}

	@Test(expected=OperationImpossible.class)
	public void testModifierCatClientImpossible() throws OperationImpossible{
		String nnomCat = "NouveauNom";
		int nmax = 4;
		double ncot = 4;
		double ncoefD = 4;
		double ncoefT = 4;
		boolean ncodeR = false;
		
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.modifierCatClient(cat,nnomCat,nmax,ncot,ncoefD,ncoefT,ncodeR);
	}
	
	@Test
	public void testModifierCatClient() throws OperationImpossible{
		String nnomCat = "NouveauNom";
		int nmax = 4;
		double ncot = 4;
		double ncoefD = 4;
		double ncoefT = 4;
		boolean ncodeR = false;
		
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterCatClient(nomCat,max,cot,coefD,coefT,codeR);
		med1.modifierCatClient(cat,nnomCat,nmax,ncot,ncoefD,ncoefT,ncodeR);
		
		assertEquals(null,med1.chercherCatClient(nomCat));
		assertEquals(new CategorieClient(nnomCat,nmax,ncot,ncoefD,ncoefT,ncodeR),med1.chercherCatClient(nomCat));
	}

	@Test
	public void testChercherDocument() throws OperationImpossible 
	{
		Mediatheque med1 = new Mediatheque(nom);
		String codeDoc = au.getCode();
		
		med1.ajouterGenre(au.getGenre().getNom());
		med1.ajouterLocalisation(au.getLocalisation().getSalle(), au.getLocalisation().getRayon());
		med1.ajouterDocument(au);
		
		assertSame(au, med1.chercherDocument(codeDoc));
	}

	@Test
	public void testAjouterDocumentOK() throws OperationImpossible 
	{
		Mediatheque med1 = new Mediatheque(nom);
		String codeDoc = au.getCode();
		
		med1.ajouterGenre(au.getGenre().getNom());
		med1.ajouterLocalisation(au.getLocalisation().getSalle(), au.getLocalisation().getRayon());
		med1.ajouterDocument(au);
		
		assertSame(au, med1.chercherDocument(codeDoc));
	}
	
	@Test(expected=OperationImpossible.class)
	public void testAjouterDocumentSansGenreDansMed() throws OperationImpossible 
	{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterLocalisation(au.getLocalisation().getSalle(), au.getLocalisation().getRayon());
		med1.ajouterDocument(au);
	}

	@Test(expected=OperationImpossible.class)
	public void testAjouterDocumentSansLocalisationDansMed() throws OperationImpossible 
	{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterGenre(au.getGenre().getNom());
		med1.ajouterDocument(au);
	}
	
	@Test(expected=OperationImpossible.class)
	public void testAjouterDocumentDejaExistant() throws OperationImpossible 
	{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterGenre(au.getGenre().getNom());
		med1.ajouterLocalisation(au.getLocalisation().getSalle(), au.getLocalisation().getRayon());
		med1.ajouterDocument(au);
		med1.ajouterDocument(au);	
	}
	
	@Test
	public void testRetirerDocumentOK() throws OperationImpossible 
	{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterGenre(au.getGenre().getNom());
		med1.ajouterLocalisation(au.getLocalisation().getSalle(), au.getLocalisation().getRayon());
		med1.ajouterDocument(au);
		
		med1.retirerDocument(au.getCode());
		
		assertEquals(null, med1.chercherDocument(au.getCode()));
	}
	
	@Test(expected=OperationImpossible.class)
	public void testRetirerDocumentEmprunte() throws OperationImpossible, InvariantBroken 
	{
		Mediatheque med1 = new Mediatheque(nom);
		Audio auEmprunte = new Audio("Code", localisation, "Titre", "Auteur", "Année", genre, "Classification");
		
		auEmprunte.emprunter();
		med1.ajouterGenre(auEmprunte.getGenre().getNom());
		med1.ajouterLocalisation(auEmprunte.getLocalisation().getSalle(), auEmprunte.getLocalisation().getRayon());
		med1.ajouterDocument(auEmprunte);
		
		med1.retirerDocument(auEmprunte.getCode());
	}
	
	@Test(expected=OperationImpossible.class)
	public void testRetirerDocumentNonExistant() throws OperationImpossible 
	{
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterGenre(au.getGenre().getNom());
		med1.ajouterLocalisation(au.getLocalisation().getSalle(), au.getLocalisation().getRayon());
		med1.ajouterDocument(au);
		
		med1.retirerDocument("Code2");
	}

	@Test
	public void testMetEmpruntableOK() throws OperationImpossible, InvariantBroken {
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterGenre(au.getGenre().getNom());
		med1.ajouterLocalisation(au.getLocalisation().getSalle(), au.getLocalisation().getRayon());
		med1.ajouterDocument(au);
		
		med1.metEmpruntable(au.getCode());
		
		assertTrue(med1.chercherDocument(au.getCode()).estEmpruntable());
	}
	
	@Test(expected=OperationImpossible.class)
	public void testMetEmpruntableDocNull() throws OperationImpossible, InvariantBroken {
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.metEmpruntable(au.getCode());
	}

	@Test
	public void testMetConsultableOK() throws OperationImpossible, InvariantBroken {
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterGenre(au.getGenre().getNom());
		med1.ajouterLocalisation(au.getLocalisation().getSalle(), au.getLocalisation().getRayon());
		med1.ajouterDocument(au);
		
		med1.metConsultable(au.getCode());
		
		//assertTrue(med1.chercherDocument(au.getCode()).estConsultable());
		// manque le getteur de consultable...
	}
	
	@Test(expected=OperationImpossible.class)
	public void testMetConsultableDocNull() throws OperationImpossible, InvariantBroken {
		Mediatheque med1 = new Mediatheque(nom);

		med1.metConsultable(au.getCode());
	}

	@Test
	public void testEmprunterOK() throws OperationImpossible, InvariantBroken {
		Mediatheque med1 = new Mediatheque(nom);
		med1.ajouterCatClient(nomCat,max,cot,coefD,coefT,codeR);
		med1.inscrire(nom,prenom,adresse,nomCat);
		
		med1.ajouterGenre(au.getGenre().getNom());
		med1.ajouterLocalisation(au.getLocalisation().getSalle(), au.getLocalisation().getRayon());
		med1.ajouterDocument(au);
		med1.chercherDocument(au.getCode()).metEmpruntable();
		
		med1.emprunter(nom, prenom, au.getCode());
	}
	
	@Test(expected=OperationImpossible.class)
	public void testEmprunterClientNonExistant() throws OperationImpossible, InvariantBroken {
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterGenre(au.getGenre().getNom());
		med1.ajouterLocalisation(au.getLocalisation().getSalle(), au.getLocalisation().getRayon());
		med1.ajouterDocument(au);
		
		med1.emprunter(nom, prenom, au.getCode());
	}
	
	@Test(expected=OperationImpossible.class)
	public void testEmprunterDocNull() throws OperationImpossible, InvariantBroken {
		Mediatheque med1 = new Mediatheque(nom);
		med1.ajouterCatClient(nomCat,max,cot,coefD,coefT,codeR);
		med1.inscrire(nom,prenom,adresse,nomCat);
		
		med1.emprunter(nom, prenom, au.getCode());
	}

	@Test
	public void testRestituer() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerifier() {
		fail("Not yet implemented");
	}
	
	@Test(expected=OperationImpossible.class)
	public void testInscrireStringStringStringStringImpossible() throws OperationImpossible {
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterCatClient(nomCat,max,cot,coefD,coefT,codeR);
		med1.inscrire(nom,prenom,adresse,nomCat);
		med1.inscrire(nom,prenom,adresse,nomCat);
	}
	
	@Test
	public void testInscrireStringStringStringString() throws OperationImpossible {
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterCatClient(nomCat,max,cot,coefD,coefT,codeR);
		med1.inscrire(nom,prenom,adresse,nomCat);
		
		assertEquals(cl,med1.chercherClient(nom,prenom));
	}
	
	@Test(expected=OperationImpossible.class)
	public void testInscrireStringStringStringStringIntImpossible() throws OperationImpossible {
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterCatClient(nomCat,max,cot,coefD,coefT,codeR);
		med1.inscrire(nom,prenom,adresse,nomCat,1);
		med1.inscrire(nom,prenom,adresse,nomCat,1);
	}

	@Test
	public void testInscrireStringStringStringStringInt() throws OperationImpossible {
		Mediatheque med1 = new Mediatheque(nom);
		
		med1.ajouterCatClient(nomCat,max,cot,coefD,coefT,codeR);
		med1.inscrire(nom,prenom,adresse,nomCat,1);
		
		assertEquals(cl,med1.chercherClient(nom,prenom));
	}

}
