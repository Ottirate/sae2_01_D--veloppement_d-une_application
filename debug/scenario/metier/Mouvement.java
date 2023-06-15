/*
* Auteur : Équipe 1
* Date   : juin 2023
* */


/*      Paquetage      */
package debug.scenario.metier;


/*       Imports       */
import java.awt.Color;


/**
 * Représentation des événement/mouvement produit lors de la partie.
 */
public class Mouvement 
{


	/*      Attributs      */
	private String info;
	private String data;

	
	/*    Constructeur     */
	public Mouvement (int id, Chemin c)
	{
		String nom1 =  c.getIleA().getNom();
		String nom2 =  c.getIleB().getNom();

		String coul = "[inconuue]";

		if (c.getCouleurPrim() == Color.RED ) coul = "rouge";
		if (c.getCouleurPrim() == Color.BLUE) coul = "bleu" ;

		this.info = "Le joueur " + id + " a capturé la route de " + nom1 + " à " + nom2 + " en " + coul + ".";
		this.data = ""+ id + '\t' + nom1 + '\t' + nom2 + '\t' + coul;
	}

	public Mouvement (Carte c)
	{
		String contour= "";
		if (c.getContour() == Color.BLACK) contour = "primaire"  ;
		if (c.getContour() == Color.WHITE) contour = "secondaire";

		this.info = "La carte " + c.getCouleur().toLowerCase() + " " + contour + " a été pioché";
		this.data = c.getCouleur() + '\t' + contour;
	}

	public Mouvement (String info, String data)
	{
		this.info = info;
		this.data = data;
	}


	/*     Accesseurs      */
	public String toString() { return this.info; }
	public String toData  () { return this.data; }
}
