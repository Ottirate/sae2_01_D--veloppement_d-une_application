package cinke_terra.metier;

import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.awt.Color;

public class PaquetDeCarte
{
	private List<Carte> ensCarte;
	private int         nbNoiresPiochees;

	public PaquetDeCarte()
	{
		this.ensCarte = Arrays.asList(Carte.values());
		Collections.shuffle(this.ensCarte);

		this.nbNoiresPiochees = 0;
	}

	/*
	public void melanger()
	{
		Collections.shuffle(this.ensCarte);
	}
	*/

	public Carte piocher()
	{
		Carte c = this.ensCarte.remove(0);
		
		if (c.getContour().equals(Color.black)) this.nbNoiresPiochees++;

		return c;
	}

	public void reinitialiser()
	{
		this.ensCarte = Arrays.asList(Carte.values());
	}

	public int getNbNoiresPiochees()
	{
		return this.nbNoiresPiochees;
	}

	public Carte getCarte(int i)
	{
		return this.ensCarte.get(i);
	}
}