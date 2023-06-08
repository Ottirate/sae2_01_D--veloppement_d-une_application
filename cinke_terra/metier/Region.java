package cinke_terra.metier;

import java.util.ArrayList;

public class Region 
{
	private String         nom;
	private ArrayList<Ile> lstIle;

	public Region (String nom)
	{
		this.nom = nom;
		this.lstIle = new ArrayList<Ile>();
	}
	
	public boolean ajouterIle(Ile i)
	{
		if (i == null || this.lstIle.contains(i))
			return false;

		this.lstIle.add(i);
		return true;
	}
	
	public String toString()
	{
		String sRep = " -" + this.nom + '\n';

		for (Ile i : this.lstIle)
			sRep += '\t' + i.toString() + '\n';

		return sRep;
	} 

}
