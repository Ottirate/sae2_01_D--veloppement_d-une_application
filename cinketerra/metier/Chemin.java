/*
* Auteur : Équipe 1
* Date   : juin 2023
* */


/*      Paquetage      */
package cinketerra.metier;


/*       Imports       */
import java.awt.Color;


/**
 * Représentation des routes maritimes entres les îles.
 */
public class Chemin
{


	/*      Attributs      */

	private Ile ileA;
	private Ile ileB;
	
	private int bonus;

	private Color coulrPrim;
	private Color coulSec  ;


	/*    Constructeur     */
	public Chemin(Ile ileA, Ile ileB, int bonus)
	{
		this.ileA  = ileA ;
		this.ileB  = ileB ;
		this.bonus = bonus;

		this.coulrPrim = null ;
		this.coulSec   = null ;

		ileA.addChemin(this);
		ileB.addChemin(this);
	}


	/*     Modifieurs      */
	public void setCouleurPrim(Color c) { this.coulrPrim = c; }
	public void setCouleurSec (Color c) { this.coulSec   = c; }
	
	
	/*     Accesseurs      */
	public int getBonus() { return this.bonus; }

	public Ile getIleA () { return this.ileA; }
	public Ile getIleB () { return this.ileB; }

	public Color getCouleurPrim() { return this.coulrPrim; }
	public Color getCouleurSec () { return this.coulSec  ; }

	public boolean estColorie  () { return this.coulrPrim != null && this.coulSec != null ; }


	/*      Méthodes       */
	public boolean ileIdentique(Chemin c)
	{
		return c.ileA == this.ileA ||
		       c.ileB == this.ileA ||
		       c.ileA == this.ileB ||
		       c.ileB == this.ileB ;
	}	
}