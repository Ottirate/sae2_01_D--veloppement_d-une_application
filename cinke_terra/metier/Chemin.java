package cinke_terra.metier;

import java.awt.Color;

public class Chemin
{
	
	private Ile ileA;
	private Ile ileB;
	
	private int bonus;

	private Color color;
	
	public Chemin(Ile ileA, Ile ileB, int bonus)
	{
		this.ileA  = ileA;
		this.ileB  = ileB;
		this.bonus = bonus;
		this.color = null;
	}

	public void setCouleur(Color c)
	{
		this.color = c;
	}
	
	public int getBonus()
	{
		return this.bonus;
	}
	
	public Ile getIleA()
	{
		return this.ileA;
	}
	
	public Ile getIleB()
	{
		return this.ileB;
	}

	public Color getCouleur()
	{
		return this.color;
	}
	
}