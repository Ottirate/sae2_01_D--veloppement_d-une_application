package cinke_terra;

import cinke_terra.ihm.FrameGame;
import cinke_terra.ihm.FrameCartes;
import cinke_terra.metier.*;
import java.util.List;

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
		int hauteur = (int) (screen.getHeight());
		int largeur = (int) (screen.getWidth ());

		this.ihmMappe .setSize((int)((70.0/100) * largeur), hauteur);
		this.ihmPioche.setSize((int)((30.0/100) * largeur), hauteur);
		
		this.ihmMappe .setLocation(0,0);
		this.ihmPioche.setLocation(this.ihmMappe.getWidth(), 0);
	}

	public List<Ile> getIles() 
	{
		return this.metier.getIles();
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

		if (c.estCache())
			sRet += (c.getContour().equals(Color.white) ? "blanc_" : "noir_") + c.getCouleur().toLowerCase() + ".png";
		else
			sRet += "carte_dos.png";

		return sRet;
	}

	public boolean carteCachee(int indice)
	{
		return this.metier.getCarte(indice).estCache();
	}

	public int getNbCarteTotal()
	{
		return this.metier.getNbCarteTotal();
	}


	public static void main(String[] args) {
		new Controleur();
	}
}