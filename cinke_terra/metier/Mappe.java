package cinke_terra.metier;

/** Lecture */
import java.util.Scanner;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

/** Listes */
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/** AWT */
import java.awt.Color;
import java.awt.geom.Line2D;

/**
 * Représentation de la carte de l'archipel.
 */
public class Mappe 
{
	/*----------------------------------*/
	/*           CONSTANTES             */
	/*----------------------------------*/

	/** Chemin relatif du fichier de données */
	private static final String      NOM_FICHIER = "../resources/data.csv";

	/** Liste de constantes de couleurs */
	private static final List<Color> COLORS      = new ArrayList<>(Arrays.asList( Color.RED, Color.BLUE));

	/*----------------------------------*/
	/*           ATTRIBUTS              */
	/*----------------------------------*/

	/* Liste de toutes les îles */
	private List<Ile>     lstIles;

	/** Liste de toutes les régions */
	private List<Region>  lstRegions;

	/** List de tout les chemins */
	private List<Chemin>  lstChemins;

	/** Île de départ */
	private Ile           ileDeDepart;

	/** Liste des chemins coloriés */
	private List<Chemin>  lstCheminColorie;

	/** Paquet de cartes */
	private PaquetDeCarte paquet;

	/** Couleur du feutre */
	private Color         feutre;

	/**
	 * Constructeur sans paramètres qui initialise l'objet.
	 */
	public Mappe() 
	{
		this.initialise();
	}

	/**
	 * Initialise et lit le fichier csv.
	 */
	public void initialise()
	{
		this.lstRegions       = new ArrayList<>();
		this.lstIles          = new ArrayList<>();
		this.lstChemins       = new ArrayList<>();
		this.lstCheminColorie = new ArrayList<>();
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

	/**
	 * Initialise la manche et définit la couleur du feutre.
	 */
	public void initialiserManche()
	{
		this.paquet = new PaquetDeCarte();
		this.feutre = COLORS.remove(0);

		if (this.feutre.equals(Color.RED))
			this.ileDeDepart = this.getIleId("Ticó");
		else
			this.ileDeDepart = this.getIleId("Mutaa");

	}
	
	public Chemin trouverChemin (Ile i1, Ile i2) 
	{
		for (Chemin c1 : i1.getCheminAutour())
			for (Chemin c2 : i2.getCheminAutour())
				if (c1 == c2)
					return c1;

		return null;
	}

	/**
	 * Retourne la liste des îles.
	 * 
	 * @return la liste des îles de la mappe
	 */
	public List<Ile> getIles() { return this.lstIles; }
	
	/**
	 * Retourne la liste des chemins.
	 * 
	 * @return la liste des chemins de la mappe
	 */
	public List<Chemin> getChemins() { return this.lstChemins; }

	/**
	 * Retourne un objet {@code Ile} associé à un objet {@code String}.
	 * 
	 * @param nom - le nom de l'île souhaitée
	 * @return une {@code Ile} en fonction du nom, {@code null} si inexistante
	 */
	public Ile getIleId(String nom) 
	{
		return this.lstIles.stream()
		       .filter(i -> i.getNom().equals(nom))
			   .findFirst()
			   .orElse(null);
	}

	/**
	 * Retourne une {@code Carte} en fonction de son indice.
	 * 
	 * @param indice - l'indice associé à une carte
	 * @return une {@code Carte}
	 */
	public Carte getCarte(int indice) { return this.paquet.getCarte(indice); }

	/**
	 * Retourne le nombre total de cartes.
	 * 
	 * @return le nombre de cartes
	 */
	public int getNbCarteTotal() { return this.paquet.getNbCarteTotal(); }

	/**
	 * Retourne le nombre total de cartes.
	 * 
	 * @return le nombre de cartes
	 */
	public Ile getIleDebut() { return this.ileDeDepart; }

	/**
	 * Retourne une {@code List} de {@code Carte} de toutes
	 * les cartes du paquet.
	 * 
	 * @return une liste de cartes
	 */
	public List<Carte> getCartes()
	{
		return this.paquet.getCartes();
	}

	public Carte getDerniereCartePiochee()
	{
		return this.paquet.getDerniereCartePiochee();
	}

	/**
	 * Pioche une carte parmi le paquet à un indice voulu. 
	 * 
	 * @param indice - l'indice de la carte à piocher
	 */
	public void piocher(int indice)
	{
		this.paquet.piocher(indice);
	}



	/*----------------------------------*/
	/*           COLORIAGE              */
	/*----------------------------------*/

	/**
	 * Colorie un {@code Chemin}, seulement si il est coloriable.
	 * 
	 * @param c - le chemin à colorier
	 * @return {@code vrai} si il a été colorié, sinon {@code faux}
	 * @see {@link Mappe#estColoriable(Chemin)}
	 */
	public boolean colorier(Chemin c) 
	{
		if (!this.estColoriable(c))
			return false;

		c.setCouleur(this.feutre);
		this.lstCheminColorie.add(c);
		this.paquet.carteJouer();
		return true;
	}

	/**
	 * Indique si un {@code Chemin} est coloriable ou non.
	 * <br><br>
	 * Pour qu'un chemin soit coloriable, il doit respecter ces conditions :
	 * <ul>
	 *   <li>Il ne doit pas être {@code null} ;</li>
	 *   <li>Il ne doit pas déjà être colorié ;</li>
	 *   <li>Il ne doit pas croiser une arête déjà coloriée ;</li>
	 *   <li>Il ne doit pas former un cycle ;</li>
	 *   <li>Il ne doit pas avoir plus d'un seul chemin séléctionné et de la même couleur autour de lui ;</li>
	 * </ul>
	 * <br><br>
	 * @param c - le chemin à tester
	 * @return {@code vrai} si le chemin est coloriable, autrement {@code faux}
	 * @see {@link Mappe#colorier(Chemin)}
	 */
	public boolean estColoriable(Chemin c) 
	{
		/* Si le chemin n'existe pas ou que on peut pas jouer de carte */
		if (c == null || this.paquet.getDerniereCartePiochee() == null) return false;
		System.out.println("Le chemin existe et on a une carte");

		/* Si le chemin est déjà colorié */
		if (c.getCouleur() != null) return false;
		System.out.println("Le chemin n'est pas coloré");

		Ile ileA = c.getIleA();
		Ile ileB = c.getIleB();

		/* Dans le cas où il s'agit du premier trait */
		if (this.lstCheminColorie.size() == 0)
			if (ileA == this.ileDeDepart && this.bonneCouleur(ileB) ||
			    ileB == this.ileDeDepart && this.bonneCouleur(ileA)) //Bonne ile : Okay
				return true;
			else
				return false;
		System.out.println("Le chemin n'est pas le premier trait");

		/* Si le chemin croise une arête déjà coloriée */
		if (this.cheminCroise(c)) return false;
		System.out.println("Le chemin ne croise rien");

		/* Si le chemin forme un cycle */
		if (this.aCycle(c)) return false;
		System.out.println("Le chemin ne forme pas de cycle");

		/* Si c'est une extrémité ou si la direction est pas une bonne couleur */
		if (this.cheminsColorieAutour(ileB) && this.bonneCouleur(ileA))
			return true;

		if (this.cheminsColorieAutour(ileA) && this.bonneCouleur(ileB))
			return true;
			
		System.out.println("Le chemin a plus d'un chemin autour de la deuxième île");

		return false;
	}

	private boolean cheminsColorieAutour (Ile i)
	{
		int nbColorie = 0;

		for (Chemin chemin : i.getCheminAutour())
			if (chemin.getCouleur() == this.feutre)
				nbColorie ++;

		System.out.println(nbColorie + " " + i);

		return nbColorie == 1;
	}

	private boolean aCycle(Chemin a1)
	{ 
	
		for (Chemin a : a1.getIleA().getCheminAutour())
				if ( this.feutre.equals(a.getCouleur()) )
					for (Chemin a2 : a1.getIleB().getCheminAutour())
						if ( this.feutre.equals(a2.getCouleur()) ) return true;

						
		return false;
	}

	private boolean cheminCroise(Chemin c1) 
	{
		int x1 = c1.getIleA().getXPoint();
		int y1 = c1.getIleA().getYPoint();
		int x2 = c1.getIleB().getXPoint();
		int y2 = c1.getIleB().getYPoint();

		for (Chemin c2 : this.lstChemins) 
		{
			if (c2.getCouleur() != null && !c1.ileIdentique(c2)) 
			{
				int x3 = c2.getIleA().getXPoint();
				int y3 = c2.getIleA().getYPoint();
				int x4 = c2.getIleB().getXPoint();
				int y4 = c2.getIleB().getYPoint();

				if (Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4))
					return true;
			}

		}

		return false;
	}
	

	private boolean bonneCouleur(Ile i)
	{
		String coul = this.paquet.getDerniereCartePiochee().getCouleur();

		if (coul == null) return false;

		return coul.equals("Multi") || coul.equals(i.getCoul());
	}

	/**
	 * Retourne la couleur du stylo.
	 * 
	 * @return la couleur du stylo
	 */
	public Color getColFeutre() { return this.feutre; }
}