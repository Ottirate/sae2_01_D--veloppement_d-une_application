package cinketerra.metier;

import java.awt.Color;

/**
 * Représente un chemin entre deux instances d'{@code Ile}.
 */
public class Chemin
{
	/** La première île */
	private Ile ileA;

	/** La deuxième île */
	private Ile ileB;
	
	/** Le bonus éventuel du chemin */
	private int bonus;

	/** La couleur du chemin */
	private Color color;

	/**
	 * Constructeur qui crée un {@code Chemin} avec une île de départ,
	 * une île d'arrivée et un bonus.
	 * 
	 * @param ileA - l'île de départ
	 * @param ileB - l'île d'arrivée
	 * @param bonus - le bonus (ou le malus) éventuel
	 */
	public Chemin(Ile ileA, Ile ileB, int bonus)
	{
		this.ileA  = ileA ;
		this.ileB  = ileB ;
		this.bonus = bonus;

		this.color = null ;

		ileA.addChemin(this);
		ileB.addChemin(this);
	}

	/**
	 * Définit la couleur du chemin en fonction de la valeur de {@code c}.
	 * 
	 * @param c - la nouvelle couleur du chemin
	 */
	public void setCouleur(Color c)
	{
		this.color = c;
	}
	
	/**
	 * Retourne le bonus du chemin.
	 * 
	 * @return le bonus, peut être négatif
	 */
	public int getBonus()
	{
		return this.bonus;
	}
	
	/**
	 * Retourne l'île de départ du chemin.
	 * 
	 * @return l'île de départ
	 */
	public Ile getIleA()
	{
		return this.ileA;
	}
	
	/**
	 * Retourne l'île d'arrivée du chemin.
	 * 
	 * @return l'île d'arrivée
	 */
	public Ile getIleB()
	{
		return this.ileB;
	}

	/**
	 * Retourne la couleur du chemin.
	 * 
	 * @return la couleur du chemin
	 */
	public Color getCouleur()
	{
		return this.color;
	}

	/**
	 * Retourne {@code vrai} si le chemin est colorié,
	 * autrement {@code faux}.
	 * 
	 * @return {@code vrai} si le chemin est déjà colorié, sinon {@code faux}.
	 */
	public boolean estColorie()
	{
		return this.color != null;
	}

	/**
	 * Détermine si l'une des île de cette instance est identique
	 * à un autre chemin {@code c}.
	 * 
	 * @param c - l'autre chemin à comparer
	 * @return {@code vrai} si l'une des îles est identique, sinon {@code faux}
	 */
	public boolean ileIdentique(Chemin c)
	{
		return c.ileA == this.ileA ||
		       c.ileB == this.ileA ||
		       c.ileA == this.ileB ||
		       c.ileB == this.ileB ;
	}
	
}