package cinke_terra;

import cinke_terra.ihm.FrameGame;
import cinke_terra.ihm.FrameCartes;
import cinke_terra.metier.*;
import java.util.List;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import java.awt.Toolkit;

public class Controleur 
{
	private Mappe metier;
	private FrameGame   ihmMappe;
	private FrameCartes ihmPioche;

	public Controleur() 
	{
		this.metier    = new Mappe();
		this.ihmMappe  = new FrameGame(this);
		this.ihmPioche = new FrameCartes(this);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int) (screen.getHeight()) - 3 * 45;
		int largeur = (int) (screen.getWidth ());

		this.ihmMappe .setSize( largeur - 90, (int) ( 0.75 * hauteur ) );
		this.ihmPioche.setSize( largeur - 90, (int) ( 0.25 * hauteur ) );
		
		this.ihmMappe .setLocation( 45, 45 );
		this.ihmPioche.setLocation( 45, 90 + this.ihmMappe.getHeight() );

		// this.ihmMappe .setSize((int)((70.0/100) * largeur), hauteur);
		// this.ihmPioche.setSize((int)((30.0/100) * largeur), hauteur);
		
		// this.ihmMappe .setLocation(0,0);
		// this.ihmPioche.setLocation(this.ihmMappe.getWidth(), 0);
	}

	public List<Ile> getIles() 
	{
		return this.metier.getIles();
	}

	public List<Carte> getCartes()
	{
		return this.metier.getCartes();
	}

	public List<Chemin> getChemins() 
	{
		return this.metier.getChemins();
	}

	public int getHauteur() {return this.ihmMappe.getHeight();}

	public int getLargeur() {return this.ihmMappe.getWidth ();}

	public String getImage(int indice)
	{
		String sRet = "../resources/cartes/";
		Carte c = this.metier.getCarte(indice);

		if (c == null) System.out.println("ouais");

		if (c != null && !c.estCache()) {
			sRet += (c.getContour().equals(Color.white) ? "blanc_" : "noir_") + c.getCouleur().toLowerCase() + ".png";
			System.out.println(c.estCache()); }
		else
			sRet += "carte_dos.png";

		return sRet;
	}

	public boolean carteCachee(int indice)
	{
		if (this.metier.getCarte(indice) == null) return false;

		return this.metier.getCarte(indice).estCache();
	}

	public void piocher( int indice )
	{
		this.metier.piocher( indice );
		this.ihmMappe.maj();
	}

	public Carte getCarte(int indice)
	{
		return this.metier.getCarte(indice);
	}

	public int getNbCarteTotal()
	{
		return this.metier.getNbCarteTotal();
	}

	public Carte getDerniereCartePiochee()
	{
		return this.metier.getDerniereCartePiochee();
	}

	public Chemin  trouverChemin (Ile i1, Ile i2) {return this.metier.trouverChemin(i1,i2);}
	public boolean colorier (Chemin c) { return this.metier.colorier(c);}
	public boolean estColoriable(Chemin c) { return this.metier.estColoriable(c);}
	public Color   getColFeutre() { return this.metier.getColFeutre();}

	public Ile getIleDebut() {return this.metier.getIleDebut();}

	public static void main(String[] args) {
		new Controleur();
	}
}