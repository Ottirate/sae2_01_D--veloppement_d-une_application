/*
* Auteur : Équipe 1
* Date   : juin 2023
* */

/*      Paquetage      */
package cinketerra.metier;

/*       Imports       */
import java.util.List;
import java.util.ArrayList;

import java.awt.Color;


/**
 * Représentation des iles.
 */
public class Ile 
{


	/*      Attributs      */
	private String nom;
	private Region reg;
	private String coul;

	private int    xPoint;
	private int    yPoint;

	private int    xImages;
	private int    yImages;

	private int    xNom;
	private int    yNom;

	private List<Chemin> lstChemins;
	

	/*    Constructeur     */
	public Ile (Region reg, String nom, String coul, int xP, int yP, int xI, int yI, int xN, int yN)
	{
		this.nom     = nom ;
		this.reg     = reg ;
		this.coul    = coul;

		this.xPoint  = xP;
		this.yPoint  = yP;
		
		this.xImages = xI;
		this.yImages = yI;
		
		this.xNom    = xN;
		this.yNom    = yN;

		this.lstChemins = new ArrayList<>();

		//Ajouter l'île à la Region
		reg.ajouterIle(this);
	}



	/*     Accesseurs      */
	public String getNom    () { return this.nom;     }
	public String getCoul   () { return this.coul;    }
	public Region getReg    () { return this.reg;     }

	public int    getXImages() { return this.xImages; }
	public int    getYImages() { return this.yImages; }
	
	public int    getXPoint () { return this.xPoint;  }
	public int    getYPoint () { return this.yPoint;  }
	
	public int    getXNom   () { return this.xNom;    }
	public int    getYNom   () { return this.yNom;    }

	public List<Chemin> getCheminAutour () { return this.lstChemins; }
	public int getNbCheminsColorie(Color color)
	{
		return (int) this.lstChemins.stream().filter(c -> c.getCouleurPrim() != null && c.getCouleurPrim().equals(color)).count();
	}
	

	/*     Modifieurs      */
	public void addChemin (Chemin c) { this.lstChemins.add(c); }


	/*      Méthodes       */
	public String toString ()
	{
		return String.format("%-15s", this.nom    ) +
		       String.format("(%3d,", this.xPoint ) + 
		       String.format("%3d)" , this.yPoint ) ;
	} 
}
