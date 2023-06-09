package cinke_terra.metier;

public class Chemin
{
	
	private Ile ileA;
	private Ile ileB;
	
	private int bonus;
	
	public Chemin(Ile ileA, Ile ileB, int bonus)
	{
		this.ileA = ileA;
		this.ileB = ileB;
		this.bonus = bonus;
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
	
}