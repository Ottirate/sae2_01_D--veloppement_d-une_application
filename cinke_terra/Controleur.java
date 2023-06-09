package cinke_terra;

import cinke_terra.ihm.FrameGame;
import cinke_terra.ihm.FrameCartes;
import cinke_terra.metier.*;
import java.util.List;
import java.awt.Color;

public class Controleur {
	private Mappe metier;
	private FrameGame ihm;

	public Controleur() 
	{
		this.metier = new Mappe();
		this.ihm = new FrameGame(this);
		new FrameCartes(this);
	}

	public List<Ile> getIles() {
		return this.metier.getIles();
	}

	public List<Chemin> getChemins() {
		return this.metier.getChemins();
	}

	public int getHauteur() {return this.ihm.getHeight();}

	public int getLargeur() {return this.ihm.getWidth ();}

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