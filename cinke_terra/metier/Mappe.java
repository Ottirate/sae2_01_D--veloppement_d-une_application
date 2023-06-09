package cinke_terra.metier;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Mappe 
{
	private static final String NOM_FICHIER = "../src/data.csv";

	private ArrayList<Ile>    lstIles;
	private ArrayList<Region> lstRegions;

	public Mappe ()
	{
		this.initialise();
	}

	public void initialise ()
	{
		this.lstRegions = new ArrayList<Region>();
		this.lstIles = new ArrayList<Ile>();
		try 
		{
			Scanner scan = new Scanner(new FileInputStream(Mappe.NOM_FICHIER),StandardCharsets.UTF_8);
			

			while (scan.hasNextLine()) 
			{
				String s = scan.nextLine();
				String[] ensInfo = s.split("\t");

				if (ensInfo.length == 1 && !ensInfo[0].equals(""))
					this.lstRegions.add(new Region(ensInfo[0]));
				
				if (ensInfo.length > 1)
					this.lstIles.add(new Ile(this.lstRegions.get(this.lstRegions.size() - 1)   ,  //Dernière region
							         ensInfo[0]                  , ensInfo[1]                  ,  //Nom et Couleur
							         Integer.parseInt(ensInfo[2]), Integer.parseInt(ensInfo[3]),  //Coord point
							         Integer.parseInt(ensInfo[5]), Integer.parseInt(ensInfo[6])));//Coord image
			
			}
			for (Region r : this.lstRegions)
				System.out.println(r);

		} catch (Exception e) {
				System.out.println("Nom fichier invalide : " + Mappe.NOM_FICHIER);
		}
	}
	
	public ArrayList<Ile> getIles() { return this.lstIles; }
}
