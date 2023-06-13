package cinketerra.metier;

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
	/** Taille maximale d'un paquet */
	private static final int TAILLE = 10;

	/** Liste des cartes */
	private List<Carte> ensCarte;

	/** Nombre de cartes noires piochées */
	private int         nbNoiresPiochees;

	private Carte       derniereCartePiochee;

	/**
	 * Constructeur par défaut sans paramètre qui crée un paquet de carte.
	 */
	public PaquetDeCarte()
	{
		this.ensCarte = new ArrayList<>(Arrays.asList(Carte.values()));
	}

	public void melanger()
	{
		Collections.shuffle(this.ensCarte);
	}

	/**
	 * Pioche une {@code Carte} à l'indice {@code indice} voulu.
	 * 
	 * @param indice - l'indice de la {@code Carte}
	 */
	public void piocher(int indice)
	{
		if (indice >= 0 && indice < this.ensCarte.size())
		{
			Carte c = null;;

			/*c = this.ensCarte.get(indice);

			if (!c.estCache()) this.piocher(indice+1);
			else
			{
				c.setCache(false);
				this.derniereCartePiochee = c;
				if ( c.getContour().equals(Color.black) ) this.nbNoiresPiochees++;
			}*/

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

	/**
	 * Réinitialise le paquet de carte à son état d'origine.
	 */
	public void reinitialiser()
	{
		for (Carte c : this.ensCarte)
		{
			c.setCache(true);
		}

		this.melanger();

		this.nbNoiresPiochees     = 0;
		this.derniereCartePiochee = null;
	}

	/**
	 * Retourne le nombre de cartes noires piochées.
	 * 
	 * @return le nombre de cartes noires piochées
	 */
	public int getNbNoiresPiochees()
	{
		return this.nbNoiresPiochees;
	}

	public Carte getDerniereCartePiochee()
	{
		return this.derniereCartePiochee;
	}

	public void carteJouer () {this.derniereCartePiochee = null;}

	/**
	 * Retourne la {@code Carte} à l'indice {@code i} donné.
	 * 
	 * @param i - l'indice de la {@code Carte} voulue
	 * @return une carte ou {@code null}
	 */
	public Carte getCarte(int i)
	{
		if (i >= 0 && i < this.ensCarte.size()) return this.ensCarte.get(i);

		return null;
	}

	/**
	 * Retourne le nombre de carte maximum.
	 * 
	 * @return le nombre de carte maximum
	 */
	public int getNbCarteTotal()
	{
		return PaquetDeCarte.TAILLE;
	}

	/**
	 * Retourne la liste des cartes du paquet.
	 * 
	 * @return la liste des cartes disponibles de ce paquet
	 */
	public List<Carte> getCartes()
	{
		return this.ensCarte;
	}

	public int getNbCarteRestante()
	{
		int nbCarteRestante = 0;

		for (Carte c : this.ensCarte)
			if (c.estCache()) nbCarteRestante++;

		return nbCarteRestante;

	}
}