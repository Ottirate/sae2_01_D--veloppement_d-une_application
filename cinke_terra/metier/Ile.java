package cinke_terra.metier;

public class Ile 
{
	private String nom;
	private Region reg;
	private String coul;

	private int    xPoint;	
	private int    yPoint;
	
	private int    xImages;	
	private int    yImages;
	
	public Ile (Region reg, String nom, String coul, int xP, int yP, int xI, int yI)
	{
		this.nom     = nom ;
		this.reg     = reg ;
		this.coul    = coul;

		this.xPoint  = xP;
		this.yPoint  = yP;
		
		this.xImages = xI;
		this.yImages = yI;

		//Ajouter l'île à la Region
		reg.ajouterIle(this);
	}

	public String toString ()
	{
		return String.format("%-15s", this.nom    ) +
		       String.format("(%3d,", this.xPoint ) + 
		       String.format("%3d)" , this.yPoint ) ;
	} 
}
