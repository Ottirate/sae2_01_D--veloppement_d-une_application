package cinke_terra;

import cinke_terra.ihm.FrameGame;
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
		String sRet = "";
		Carte c = this.metier.getCarte(indice);

		sRet += c.getContour().equals(Color.white) ? "blanc_" : "noir_";
		
		sRet += 
		
		return "";
	}

	public boolean carteCachee(int indice)
	{
		return this.metier.getCarte(indice).estCache();
	}


	public static void main(String[] args) {
		new Controleur();
	}
}