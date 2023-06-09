package cinke_terra.metier;

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
		Collections.shuffle(this.ensCarte);

		this.nbNoiresPiochees = 0;
	}

	/*
	public void melanger()
	{
		Collections.shuffle(this.ensCarte);
	}
	*/

	/**
	 * Pioche une {@code Carte} à l'indice {@code indice} voulu.
	 * 
	 * @param indice - l'indice de la {@code Carte}
	 */
	public void piocher(int indice)
	{
		if (indice >= 0 && indice < this.ensCarte.size())
		{
			System.out.println("on rentre " + this.ensCarte.size());
			Carte c = this.ensCarte.get(indice);
			c.setCache(false);
			System.out.println(c);

			this.derniereCartePiochee = c;

			if ( c.getContour().equals(Color.black) ) this.nbNoiresPiochees++;
			System.out.println("on sors " + this.ensCarte.size());
		}
	}

	/**
	 * Réinitialise le paquet de carte à son état d'origine.
	 */
	public void reinitialiser()
	{
		this.ensCarte = new ArrayList<>(Arrays.asList(Carte.values()));
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

	/**
	 * Retourne la {@code Carte} à l'indice {@code i} donné.
	 * 
	 * @param i - l'indice de la {@code Carte} voulue
	 * @return une carte ou {@code null}
	 */
	public Carte getCarte(int i)
	{
		//System.out.println("i = " + i);
		//System.out.println("size = " + this.ensCarte.size());

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
}