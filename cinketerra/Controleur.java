package cinketerra;

import java.util.List;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cinketerra.ihm.FrameAnnonce;
import cinketerra.ihm.FrameCartes;
import cinketerra.ihm.FrameDebut;
import cinketerra.ihm.FrameGame;
import cinketerra.metier.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.Toolkit;

public class Controleur implements WindowStateListener
{
	public static int NB_JOUEUR = 0;

	// Joueur 1
	private Mappe       metier1;
	private FrameGame   ihmMappe1;

	// Joueur 2
	private Mappe       metier2;
	private FrameGame   ihmMappe2;

	//Pioche Commune
	private FrameCartes ihmPioche;

	public Controleur()
	{
		FrameDebut frameD = new FrameDebut(this);
		
		boolean debutDePartie = false;
		while (!debutDePartie)
		{
			System.out.print(""); // Pour une raison inconnue, s'il n'est pas là ça ne marche plus ! /!\ TOUCHE PAS
			if (Controleur.NB_JOUEUR != 0)
			{
				debutDePartie = true;
				frameD.dispose();
			}
		}

		PaquetDeCarte p = new PaquetDeCarte();

		this.metier1    = new Mappe(this, p);
		this.ihmMappe1  = new FrameGame(this, 1);

		if (Controleur.NB_JOUEUR == 2)
		{
			this.metier2    = new Mappe    (this, p);
			this.ihmMappe2  = new FrameGame(this, 2);
		}

		this.ihmPioche = new FrameCartes(this);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int hauteur = (int) (screen.getHeight()) - 3 * 45;
		int largeur = (int) (screen.getWidth ());

		if (Controleur.NB_JOUEUR == 2)
		{
			this.ihmMappe1 .setSize( (int) (largeur * 0.5) - 90, (int) ( 0.75 * hauteur ) );
			this.ihmMappe2 .setSize( (int) (largeur * 0.5) - 90, (int) ( 0.75 * hauteur ) );
			this.ihmMappe2 .setLocation( (int)(largeur * 0.5) + 45, 45 );
			this.ihmMappe2.addWindowStateListener(this);
		}
		else
		{
			this.ihmMappe1 .setSize( largeur  - 90, (int) ( 0.75 * hauteur ) );
		}

		this.ihmPioche.setSize( largeur - 60, (int) ( 0.30 * hauteur ) );
		
		this.ihmMappe1 .setLocation( 45, 45 );
		this.ihmPioche.setLocation( 30, 55 + this.ihmMappe1.getHeight() );

		this.ihmMappe1.addWindowStateListener(this);
		this.ihmPioche.addWindowStateListener(this);
	}

	public List<Ile> getIles(int id) 
	{
		if (id == 1) return this.metier1.getIles();
		else         return this.metier2.getIles();
	}

	public List<Carte> getCartes()
	{
		return this.metier1.getCartes(); //Le paquet est commun, on prend donc celui du joueur 1
	}

	public List<Chemin> getChemins(int id) 
	{
		if (id == 1) return this.metier1.getChemins();
		else         return this.metier2.getChemins();
	}

	public int getHauteur(int id) 
	{
		if (id == 1) return this.ihmMappe1.getHeight();
		else         return this.ihmMappe2.getHeight();
	}

	public List<Region> getRegions(int id) 
	{
		if (id == 1) return this.metier1.getRegions();
		else         return this.metier2.getRegions();
	}

	public int getLargeur(int id) 
	{
		if (id == 1) return this.ihmMappe1.getWidth();
		else         return this.ihmMappe2.getWidth();
	}
	
	public int getLargeurPioche() { return this.ihmPioche.getWidth(); }
	public int getHauteurPioche() { return this.ihmPioche.getHeight(); }
	
	public String getImage(int indice)
	{
		String sRet = "./resources/cartes/";
		Carte c = this.metier1.getCarte(indice);

		if (c != null && !c.estCache())
			sRet += (c.getContour().equals(Color.white) ? "blanc_" : "noir_") + c.getCouleur().toLowerCase() + ".png";
		else
			sRet += "carte_dos.png";

		return sRet;
	}

	public String getImageRetournee(Carte c)
	{
		String sRet = "./resources/cartes/";

		sRet += (c.getContour().equals(Color.white) ? "blanc_" : "noir_") + c.getCouleur().toLowerCase() + ".png";

		return sRet;
	}

	public boolean carteCachee(int indice)
	{
		if (this.metier1.getCarte(indice) == null) return false;

		return this.metier1.getCarte(indice).estCache();
	}

	public void piocher( int indice )
	{
		this.metier1.piocher( indice );

		if (Controleur.NB_JOUEUR == 2)
		{
			this.metier2.piocher();
			this.ihmMappe2.maj();
		}

		if (this.getNbCarteTotal() - this.getNbCarteRestante() == Mappe.getTourEvent("Biffurcation"))
		{
			// Montre qu'il y a biffurcation
			System.out.println("Ah l'batard -> biffurcation");
			JFrame f = new FrameAnnonce();
		}

		

		this.ihmMappe1.maj();
	}

	public Carte getCarte(int indice)
	{
		return this.metier1.getCarte(indice);
	}

	public int getNbCarteTotal()
	{
		return this.metier1.getNbCarteTotal();
	}

	public Carte getDerniereCartePiochee()
	{
		return this.metier1.getDerniereCartePiochee();
	}

	public int getNbCarteRestante()
	{
		return this.metier1.getNbCarteRestante();
	}

	public Chemin  trouverChemin (Ile i1, Ile i2, int id) 
	{
		if (id == 1) return this.metier1.trouverChemin(i1,i2);
		else         return this.metier2.trouverChemin(i1,i2);
	}

	public boolean colorier (Chemin c, int id) 
	{ 
		if (id == 1) return this.metier1.colorier(c, id);
		else         return this.metier2.colorier(c, id);
	}

	public boolean estColoriable(Chemin c, int id) 
	{ 
		if (id == 1) return this.metier1.estColoriable(c);
		else         return this.metier2.estColoriable(c);
	}

	public Color   getColFeutre(int id) 
	{ 
		if (id == 1) return this.metier1.getColFeutre();
		else         return this.metier2.getColFeutre();
	}

	public Ile getIleDebut(int id) 
	{
		if (id == 1) return this.metier1.getIleDebut();
		else         return this.metier2.getIleDebut();
	}


	public void majIHM ()
	{
		if (this.ihmPioche != null) this.ihmPioche.maj();
		if (this.ihmMappe1 != null) this.ihmMappe1.maj();
		if (this.ihmMappe2 != null) this.ihmMappe2.maj();

		for (Mouvement m : Mappe.getActions())
			System.out.println(m);
	}

	public void initialiserManche ()
	{
		//Metier
		this.metier1.initialiserManche();
		if (this.metier2 != null) this.metier2.initialiserManche();

		//IHM
		this.ihmPioche.hideButton();
		this.ihmPioche.initPioche();
		this.ihmMappe1.newManche();
		if (this.ihmMappe2 != null) this.ihmMappe2.newManche();

	}
	
	public String getImageRetournee(Carte c)
	{
		String sRet = "./resources/cartes/";

		sRet += (c.getContour().equals(Color.white) ? "blanc_" : "noir_") + c.getCouleur().toLowerCase() + ".png";

		return sRet;
	}

	public void showButton() {this.ihmPioche.showButton();}
	public void bloquerPioche(boolean bloque) { if (this.ihmPioche != null) this.ihmPioche.bloquerPioche(bloque); }

	public void finDePartie ()
	{
		String mess;
		Color c1 = this.getColFeutre(1);

		String[] tabScore1 = this.metier1.getScore().split("; ");
		
		// Score du joueur 1
		
		String rgb = "rgb(" + c1.getRed() + ", " + c1.getGreen() + ", " + c1.getBlue() + ")";
		
		mess  = "<html>Point du <font style='color: " + rgb + "'>Joueur 1 : " + tabScore1[0] + " points</font><br><ul>";
		mess += "<font style='color: " + rgb + "'> " + tabScore1[1].substring(15) + " points</font> (" + tabScore1[1].substring(0,13) + ")<br>";
		mess += "<font style='color: " + rgb + "'> " + tabScore1[2].substring(12) + " points</font> (" + tabScore1[2].substring(0,10) + ")</ul>";

		if (Controleur.NB_JOUEUR == 2)
		{
			Color c2 = this.getColFeutre(2);
			// Score du joueur 2
			String[] tabScore2 = this.metier2.getScore().split("; ");

			mess += "<br>Point du <font style='color: rgb(" + c2.getRed() + ", " + c2.getGreen() + ", " + c2.getBlue() + ")'>Joueur 2 : " + tabScore2[0] + " points</font> <br><ul>";
			mess +=   "<font style='color: rgb(" + c2.getRed() + ", " + c2.getGreen() + ", " + c2.getBlue() + ")'> " + tabScore2[1].substring(15) + " points</font> (" + tabScore2[1].substring(0,13) + ")<br>";
			mess +=   "<font style='color: rgb(" + c2.getRed() + ", " + c2.getGreen() + ", " + c2.getBlue() + ")'> " + tabScore2[2].substring(12) + " points</font> (" + tabScore2[2].substring(0,10) + ")</ul>";
		}

		JOptionPane.showConfirmDialog( null, mess+"</html>" , "Fin de la partie", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if (this.ihmMappe2 != null) this.ihmMappe2.dispose();

		this.ihmMappe1.dispose();
		this.ihmPioche.dispose();
	}

	public String getScore (int id)
	{
		if (id == 1) return this.metier1.getScore();
		else         return this.metier2.getScore();
	}

	@Override
	public void windowStateChanged(WindowEvent e)
	{
		if (JFrame.class.cast(e.getSource()).getState() == JFrame.ICONIFIED) {
			this.ihmMappe1.setState(JFrame.ICONIFIED);
			if (this.ihmMappe2 != null)
				this.ihmMappe2.setState(JFrame.ICONIFIED);
			this.ihmPioche.setState(JFrame.ICONIFIED);
		}

		if (JFrame.class.cast(e.getSource()).getState() == JFrame.NORMAL) {
			this.ihmMappe1.setState(JFrame.NORMAL);
			if (this.ihmMappe2 != null)
				this.ihmMappe2.setState(JFrame.NORMAL);
			this.ihmPioche.setState(JFrame.NORMAL);
		}
	}

	public static void main(String[] args)
	{
		new Controleur();
	}
}