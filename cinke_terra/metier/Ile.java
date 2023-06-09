package cinke_terra.metier;

import java.util.List;
import java.util.ArrayList;

public class Ile 
{
	private String nom;
	private Region reg;
	private String coul;

	private int    xPoint;
	private int    yPoint;

	private int    xImages;
	private int    yImages;

	private List<Chemin> lstChemins;
	
	public Ile (Region reg, String nom, String coul, int xP, int yP, int xI, int yI)
	{
		this.nom     = nom ;
		this.reg     = reg ;
		this.coul    = coul;

		this.xPoint  = xP;
		this.yPoint  = yP;
		
		this.xImages = xI;
		this.yImages = yI;

		this.lstChemins = new ArrayList<>();

		//Ajouter l'île à la Region
		reg.ajouterIle(this);
	}

	public String getNom    () { return this.nom;     }
	public String getCoul   () { return this.coul;    }
	public Region getReg    () { return this.reg;     }

	public int    getxImages() { return this.xImages; }
	public int    getyImages() { return this.yImages; }
	
	public int    getxPoint () { return this.xPoint;  }
	public int    getyPoint () { return this.yPoint;  }

	public String toString ()
	{
		return String.format("%-15s", this.nom    ) +
		       String.format("(%3d,", this.xPoint ) + 
		       String.format("%3d)" , this.yPoint ) ;
	} 
}
