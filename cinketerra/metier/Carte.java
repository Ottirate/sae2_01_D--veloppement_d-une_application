package cinketerra.metier;

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

	/** Couleur de fond de la carte */
	private String  couleur;

	/** Couleur de bordure de la carte */
	private Color   contour;

	/** État de la carte */
	private boolean estCache;

	/**
	 * Constructeur qui définit les cartes avec leurs couleurs et leurs bordures.
	 * 
	 * @param couleur - la couleur de fond de la carte
	 * @param contour - la couleur de la bordure de la carte
	 */
	private Carte(String couleur, Color contour)
	{
		this.couleur  = couleur;
		this.contour  = contour;
		this.estCache = true;
	}

	/**
	 * Retourne la couleur de la carte.
	 * 
	 * @return la couleur de la carte
	 */
	public String getCouleur()
	{
		return this.couleur;
	}

	/**
	 * Retourne la couleur de la bordure de la carte.
	 * 
	 * @return la couleur de la bordure de la carte
	 */
	public Color getContour()
	{
		return this.contour;
	}

	/**
	 * Retourne un booléen en fonction de l'état de la carte.
	 * 
	 * @return {@code vrai} si la carte est cachée, sinon {@code faux}
	 */
	public boolean estCache()
	{
		return this.estCache;
	}

	/**
	 * Définit l'état de la carte en fonction de la valeur de {@code etat}.
	 * 
	 * @param etat - le nouvel état de la carte
	 */
	public void setCache(boolean etat)
	{
		this.estCache = etat;
	}

}