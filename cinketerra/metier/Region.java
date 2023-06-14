/*
* Auteur : Équipe 1
* Date   : juin 2023
* */


/*      Paquetage      */
package cinketerra.metier;


/*       Imports       */
import java.util.ArrayList;
import java.util.List;

/**
 * Représente une région d'île.
 */
public class Region 
{

	
	/*      Attributs      */
	private String    nom;
	private List<Ile> lstIle;


	/*    Constructeur     */
	public Region (String nom)
	{
		this.nom = nom;
		this.lstIle = new ArrayList<>();
	}
	

	/*     Accesseurs      */
	public List<Ile> getIles() { return this.lstIle; }
	public String    getNom () { return this.nom;    }


	/*      Méthodes       */
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
