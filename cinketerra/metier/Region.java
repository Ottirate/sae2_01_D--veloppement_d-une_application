package cinketerra.metier;

import java.util.ArrayList;
import java.util.List;

public class Region 
{
	private String    nom;
	private List<Ile> lstIle;

	public Region (String nom)
	{
		this.nom = nom;
		this.lstIle = new ArrayList<>();
	}
	
	public boolean ajouterIle(Ile i)
	{
		if (i == null || this.lstIle.contains(i))
			return false;

		this.lstIle.add(i);
		return true;
	}

	public boolean contien(Ile i)
	{
		return this.lstIle.stream()
			.anyMatch(ile -> ile.equals(i));
	}
	
	public String toString()
	{
		String sRep = " -" + this.nom + '\n';

		for (Ile i : this.lstIle)
			sRep += '\t' + i.toString() + '\n';

		return sRep;
	} 

}
