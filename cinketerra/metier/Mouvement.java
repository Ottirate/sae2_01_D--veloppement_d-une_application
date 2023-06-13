package cinketerra.metier;

import java.awt.Color;

public class Mouvement 
{
	private String infoString;
	private String dataString;

	public Mouvement (int id, Chemin c)
	{
		//Nom des îles pour pas rappeller deux fois
		String nom1 =  c.getIleA().getNom();
		String nom2 =  c.getIleB().getNom();

		String coul = "[inconuue]";
		//Couleur du feutre
		if (c.getCouleur() == Color.RED ) coul = "rouge";
		if (c.getCouleur() == Color.BLUE) coul = "bleu" ;

		//String pour l'historique
		this.infoString = "Le joueur " + id + " à capturer la route de " + nom1 + " à " + nom2 + " en " + coul + ".";

		//String pour le fichier
		this.dataString = id + '\t' + nom1 + '\t' + nom2 + '\t' + coul;
	}

	public Mouvement (Carte c)
	{
		//Contour de la carte
		String contour= "";
		if (c.getContour() == Color.BLACK) contour = "primaire"  ;
		if (c.getContour() == Color.WHITE) contour = "secondaire";

		//String pour l'historique
		this.infoString = "La carte " + c.getCouleur() + " " + contour + " à été pioché";

		//String pour le fichier
		this.infoString = c.getCouleur() + '\t' + contour;
	}

	public Mouvement (String infoString, String dataString)
	{
		this.infoString = infoString;
		this.dataString = dataString;
	}


	public String toString() { return this.infoString; }	
	public String toData  () { return this.dataString; }
}
