/*
* Auteur : Équipe 1
* Date   : juin 2023
* */


/*      Paquetage      */
package debug.scenario.metier;


/*       Imports       */
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * Représente un paquet de carte.
 */
public class PaquetDeCarte
{


	/* Attributs de Classe */
	/*      Constants      */
	private static final int TAILLE = 10;


	/*      Attributs      */
	private List<Carte> ensCarte;

	private int         nbNoiresPiochees;
	private Carte       derniereCartePiochee;

	
	/*    Constructeur     */
	public PaquetDeCarte()
	{
		this.ensCarte = new ArrayList<>(Arrays.asList(Carte.values()));
	}

	
	/*     Accesseurs      */
	public int   getNbNoiresPiochees    () { return this.nbNoiresPiochees    ; }
	public Carte getDerniereCartePiochee() { return this.derniereCartePiochee; }
	public int   getNbCarteTotal        () { return PaquetDeCarte.TAILLE     ; }

	public List<Carte> getCartes() { return this.ensCarte; }

	public int getNbCarteRestante()
	{
		int nbCarteRestante = 0;

		for (Carte c : this.ensCarte)
			if (c.estCache()) nbCarteRestante++;

		return nbCarteRestante;
	}

	public Carte getCarte(int i)
	{
		if (i >= 0 && i < this.ensCarte.size()) return this.ensCarte.get(i);
		return null;
	}


	/*     Modifieurs      */
	public void carteJouer () {this.derniereCartePiochee = null;}


	/*      Méthodes       */
	public void melanger() 
	{
		Collections.shuffle(this.ensCarte);
	}

	public void piocher(int indice)
	{
		if (indice >= 0 && indice < this.ensCarte.size())
		{
			Carte c = null;

			int cpt  = 0;
			int cpt2 = 0;

			while (cpt < PaquetDeCarte.TAILLE)
			{
				c = this.ensCarte.get(cpt);
				
				if (!c.estCache()) cpt2++;

				if (cpt == cpt2 + indice)
				{
					c.setCache(false);
					this.derniereCartePiochee = c;
					if ( c.getContour().equals(Color.black) ) this.nbNoiresPiochees++;
					Mappe.addAction(new Mouvement(c));
					return;
				}

				cpt++;
			}
		}
	}

	public void reinitialiser()
	{
		for (Carte c : this.ensCarte)
		{
			c.setCache(true);
		}

		this.nbNoiresPiochees     = 0;
		this.derniereCartePiochee = null;
	}
}