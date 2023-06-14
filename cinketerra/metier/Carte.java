/*
* Auteur : Équipe 1
* Date   : juin 2023
* */


/*      Paquetage      */
package cinketerra.metier;


/*       Imports       */
import java.awt.Color;


/**
 * Représentation des cartes avec leurs bordures et leurs couleurs.
 */
public enum Carte
{
	/* Cartes blanches */
	B_MULTI_COLOR ( "Multi", Color.white ),
	B_VERT        ( "Vert" , Color.white ),
	B_ROUGE       ( "Rouge", Color.white ),
	B_JAUNE       ( "Jaune", Color.white ),
	B_BRUN        ( "Brun" , Color.white ),

	/* Cartes noires */
	N_MULTI_COLOR ( "Multi", Color.black ),
	N_VERT        ( "Vert" , Color.black ),
	N_ROUGE       ( "Rouge", Color.black ),
	N_JAUNE       ( "Jaune", Color.black ),
	N_BRUN        ( "Brun" , Color.black );


	/*      Attributs      */
	private String  couleur;
	private Color   contour;
	
	private boolean estCache;

	
	/*    Constructeur     */
	private Carte(String couleur, Color contour)
	{
		this.couleur  = couleur;
		this.contour  = contour;
		this.estCache = true;
	}


	/*     Accesseurs      */
	public String getCouleur() { return this.couleur; }
	public Color  getContour() { return this.contour; }
	
	public boolean estCache() { return this.estCache; }

	
	/*     Modifieurs      */
	public void setCache(boolean etat) { this.estCache = etat; }
}