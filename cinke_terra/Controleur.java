package cinke_terra;

import cinke_terra.ihm.FrameGame;
import cinke_terra.ihm.FrameCartes;
import cinke_terra.metier.*;
import java.util.List;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Toolkit;

public class Controleur 
{
	private static int NB_JOUEUR = 2;

	// Joueur 1
	private Mappe       metier1;
	private FrameGame   ihmMappe1;

	// Joueur 2
	private Mappe       metier2;
	private FrameGame   ihmMappe2;

	//Pioche Commune
	private FrameCartes ihmPioche;

	public Controleur() 
	{
		PaquetDeCarte p = new PaquetDeCarte();

		this.metier1    = new Mappe(this,p);
		this.ihmMappe1  = new FrameGame(this, 1);

		if (Controleur.NB_JOUEUR == 2)
		{
			this.metier2    = new Mappe(this,p);
			this.ihmMappe2  = new FrameGame(this, 2);
		}

		this.ihmPioche = new FrameCartes(this);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int) (screen.getHeight()) - 3 * 45;
		int largeur = (int) (screen.getWidth ());

		if (Controleur.NB_JOUEUR == 2)
		{
			this.ihmMappe1 .setSize( (int) (largeur * 0.5) - 90, (int) ( 0.75 * hauteur ) );
			this.ihmMappe2 .setSize( (int) (largeur * 0.5) - 90, (int) ( 0.75 * hauteur ) );
			this.ihmMappe2 .setLocation( (int)(largeur * 0.5) + 45, 45 );
		}
		else
		{
			this.ihmMappe1 .setSize( largeur  - 90, (int) ( 0.75 * hauteur ) );
		}

		this.ihmPioche.setSize( largeur - 90, (int) ( 0.25 * hauteur ) );
		
		this.ihmMappe1 .setLocation( 45, 45 );
		this.ihmPioche.setLocation( 45, 90 + this.ihmMappe1.getHeight() );
	}

	public List<Ile> getIles(int id) 
	{
		if (id == 1) return this.metier1.getIles();
		else         return this.metier2.getIles();
	}

	public List<Carte> getCartes()
	{
		return this.metier1.getCartes(); //Le paquet est commun, on prend donc celui du joueur 1
	}

	public List<Chemin> getChemins(int id) 
	{
		if (id == 1) return this.metier1.getChemins();
		else         return this.metier2.getChemins();
	}

	public int getHauteur(int id) 
	{
		if (id == 1) return this.ihmMappe1.getHeight();
		else         return this.ihmMappe2.getHeight();
	}

	public int getLargeur(int id) 
	{
		if (id == 1) return this.ihmMappe1.getWidth();
		else         return this.ihmMappe2.getWidth();
	}

	public int getLargeurPioche() { return this.ihmPioche.getWidth();}

	public String getImage(int indice)
	{
		String sRet = "../resources/cartes/";
		Carte c = this.metier1.getCarte(indice);

		if (c != null && !c.estCache())
			sRet += (c.getContour().equals(Color.white) ? "blanc_" : "noir_") + c.getCouleur().toLowerCase() + ".png";
		else
			sRet += "carte_dos.png";

		return sRet;
	}

	public boolean carteCachee(int indice)
	{
		if (this.metier1.getCarte(indice) == null) return false;

		return this.metier1.getCarte(indice).estCache();
	}

	public void piocher( int indice )
	{
		this.metier1.piocher( indice );
		this.ihmMappe1.maj();

		if (Controleur.NB_JOUEUR == 2)
		{
			this.metier2.piocher();
			this.ihmMappe2.maj();
		}
	}

	public Carte getCarte(int indice)
	{
		return this.metier1.getCarte(indice);
	}

	public int getNbCarteTotal()
	{
		return this.metier1.getNbCarteTotal();
	}

	public Carte getDerniereCartePiochee()
	{
		return this.metier1.getDerniereCartePiochee();
	}


	public Chemin  trouverChemin (Ile i1, Ile i2, int id) 
	{
		if (id == 1) return this.metier1.trouverChemin(i1,i2);
		else         return this.metier2.trouverChemin(i1,i2);
	}

	public boolean colorier (Chemin c, int id) 
	{ 
		if (id == 1) return this.metier1.colorier(c);
		else         return this.metier2.colorier(c);
	}

	public boolean estColoriable(Chemin c, int id) 
	{ 
		if (id == 1) return this.metier1.estColoriable(c);
		else         return this.metier2.estColoriable(c);
	}

	public Color   getColFeutre(int id) 
	{ 
		if (id == 1) return this.metier1.getColFeutre();
		else         return this.metier2.getColFeutre();
	}

	public Ile getIleDebut(int id) 
	{
		if (id == 1) return this.metier1.getIleDebut();
		else         return this.metier2.getIleDebut();
	}


	public void majIHM ()
	{
		if (this.ihmPioche != null) this.ihmPioche.maj();
		if (this.ihmMappe1 != null) this.ihmMappe1.maj();
		if (this.ihmMappe2 != null) this.ihmMappe2.maj();
	}

	public void initialiserManche ()
	{
		this.metier1.initialiserManche();
		if (this.metier2 != null) this.metier2.initialiserManche();
	}
	public static void main(String[] args) {
		new Controleur();
	}
}