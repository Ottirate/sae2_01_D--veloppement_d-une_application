package cinke_terra.metier;

import java.awt.Color;

public enum Carte
{
	// Cartes blanches
	B_MULTI_COLOR ( "Multi", Color.white ),
	B_VERT        ( "Vert" , Color.white ),
	B_ROUGE       ( "Rouge", Color.white ),
	B_JAUNE       ( "Jaune", Color.white ),
	B_BRUN        ( "Brun" , Color.white ),

	// Cartes noires
	N_MULTI_COLOR ( "Multi", Color.black ),
	N_VERT        ( "Vert" , Color.black ),
	N_ROUGE       ( "Rouge", Color.black ),
	N_JAUNE       ( "Jaune", Color.black ),
	N_BRUN        ( "Brun" , Color.black );

	private String  couleur;
	private Color   contour;
	private boolean estCache;

	private Carte(String couleur, Color contour)
	{
		this.couleur  = couleur;
		this.contour  = contour;
		this.estCache = true;
	}

	public String getCouleur()
	{
		return this.couleur;
	}

	public Color getContour()
	{
		return this.contour;
	}

	public boolean estCache()
	{
		return this.estCache;
	}

}

/*
Couleurs exactes : 

vert  = Color.decode("#49064C")
rouge = Color.decode("#9D7E89")
jaune = Color.decode("#BFA759")
brun  = Color.decode("#8D8C70")
*/