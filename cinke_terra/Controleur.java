package cinke_terra;

import cinke_terra.ihm.FrameGame;
import cinke_terra.metier.*;
import java.util.ArrayList;

public class Controleur
{
	private Mappe     metier;
	private FrameGame ihm;

	public Controleur ()
	{
		this.metier = new Mappe();
		this.ihm    = new FrameGame(this);
	}

	public ArrayList<Ile> getIles() { return this.metier.getIles(); }

	
	public static void main(String[] args) 
	{
		new Controleur();
	}
}