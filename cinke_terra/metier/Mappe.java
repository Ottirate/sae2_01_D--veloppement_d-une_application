package cinke_terra.metier;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;
import java.awt.Color;

public class Mappe 
{
	private static final String      NOM_FICHIER = "../resources/data.csv";
	private static final List<Color> COLORS      = new ArrayList<>(Arrays.asList(
		Color.RED,
		Color.BLUE
	));

	private List<Ile>     lstIles;
	private List<Region>  lstRegions;
	private List<Chemin>  lstChemins;
	private PaquetDeCarte paquet;
	private Color         feutre;
	private Ile           ileDeDepart;

	public Mappe() {
		this.initialise();
	}

	public void initialise()
	{
		this.lstRegions = new ArrayList<>();
		this.lstIles    = new ArrayList<>();
		this.lstChemins = new ArrayList<>();
		Collections.shuffle(Mappe.COLORS);
		
		try
		{
			Scanner scan = new Scanner(new FileInputStream(Mappe.NOM_FICHIER), StandardCharsets.UTF_8);

			while (scan.hasNextLine()) {
				String s = scan.nextLine();
				String[] ensInfo = s.split("\t");

				// Si il y a juste le nom de la région
				if (ensInfo.length == 1 && !ensInfo[0].equals(""))
					this.lstRegions.add(new Region(ensInfo[0]));

				// Si il y a les infos d'une île
				if (ensInfo.length == 9)
					this.lstIles.add(new Ile(this.lstRegions.get(this.lstRegions.size() - 1), // Dernière region
							ensInfo[0], ensInfo[1], // Nom et Couleur
							Integer.parseInt(ensInfo[2]), Integer.parseInt(ensInfo[3]),   // Coords point
							Integer.parseInt(ensInfo[5]), Integer.parseInt(ensInfo[6]),   // Coords image
							Integer.parseInt(ensInfo[7]), Integer.parseInt(ensInfo[8]))); // Coords nom

				// Si il y a les infos pour un chemin
				if (ensInfo.length == 2)
					this.lstChemins.add(new Chemin(this.getIleId(ensInfo[0]), this.getIleId(ensInfo[1]), 0));

				// Si il y a les infos pour un chemin bonus
				if (ensInfo.length == 3)
					this.lstChemins.add(new Chemin(this.getIleId(ensInfo[0]), this.getIleId(ensInfo[1]),
							Integer.parseInt(ensInfo[2])));

			}
			for (Region r : this.lstRegions)
				System.out.println(r);

		} catch (Exception e) {
			System.out.println("Nom fichier invalide : " + Mappe.NOM_FICHIER);
		}

		this.initialiserManche();
	}

	public void initialiserManche()
	{
		this.paquet = new PaquetDeCarte();
		this.feutre = COLORS.remove(0);

		if (this.feutre.equals(Color.RED))
			this.ileDeDepart = this.getIleId("Ticó");
		else
			this.ileDeDepart = this.getIleId("Mutaa");

	}

	public List<Ile> getIles() {
		return this.lstIles;
	}

	public List<Chemin> getChemins() {
		return this.lstChemins;
	}

	public Ile getIleId(String nom) {
		return this.lstIles.stream()
		       .filter(i -> i.getNom().equals(nom))
			   .findFirst()
			   .orElse(null);
		// for (Ile i : this.lstIles)
		// if (i.getNom().equals(nom))
		// return i;

		// return null;
	}

	public Carte piocher()
	{
		return this.paquet.piocher();
	}

	public Carte getCarte(int indice)
	{
		return this.paquet.getCarte(indice);
	}

	public int getNbCarteTotal()
	{
		return this.paquet.getNbCarteTotal();
	}
}
