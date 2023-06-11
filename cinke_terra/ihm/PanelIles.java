package cinke_terra.ihm;

import cinke_terra.Controleur;
import cinke_terra.metier.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class PanelIles extends JPanel
{
	private int id;

	private boolean estNouvelleManche;	
	private Ile ile1;
	private Ile ile2;

	public static String NOM_CHEMIN = "../resources/iles/";
	private ArrayList<ImageIcon> lstImgIles;

	private Controleur ctrl;

	private int maxX;
	private int maxY;
	private double coef;
	private ArrayList<Polygon> polygons;

	public PanelIles(Controleur ctrl, int id) 
	{
		this.ctrl = ctrl;
		this.id   = id;
		this.estNouvelleManche = true;

		this.setBackground( new Color(182, 211, 229) );
		

		//Création des images
		this.declarerImage();


		/*     Activation      */
		GereSouris gereSouris = new GereSouris();

		this.addMouseListener(gereSouris);
		this.addMouseMotionListener(gereSouris);
	}

	private void declarerImage()
	{
		this.lstImgIles = new ArrayList<>();
		for (Ile i : this.ctrl.getIles(this.id)) 
			this.lstImgIles.add( new ImageIcon(PanelIles.NOM_CHEMIN + i.getNom() + ".png"));

		//On regarde le max X et Y
		List<Ile> lstIles = this.ctrl.getIles(this.id);
		for (Ile i : lstIles) 
		{
			int x = this.lstImgIles.get(lstIles.indexOf(i)).getIconHeight() + i.getXImages();
			int y = this.lstImgIles.get(lstIles.indexOf(i)).getIconWidth () + i.getYImages();

			maxX = Math.max(x, maxX);
			maxY = Math.max(y, maxY);
		}

		
	}


	/*-----------------------------------*/
	/*      DESSINS DES COMPOSANTS       */
	/*-----------------------------------*/
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		List<Ile> lstIles = this.ctrl.getIles(this.id);
		Graphics2D g2 = (Graphics2D) g;

		/*                               */
		/* CALCUL DU COEF DE PROPORTIONS */
		/*                               */
		
		int hauteur    = this.ctrl.getHauteur(this.id) - 40 ;
		int largeur    = this.ctrl.getLargeur(this.id) - 20 ;
		
		double coef1   = hauteur * 1.0 / (this.maxY);
		double coef2   = largeur * 1.0 / (this.maxX);
		double oldCoef = this.coef;
		
		this.coef = Math.min(coef1, coef2);

		if (this.coef < 0.3) this.coef = 0.3;

		if ( oldCoef != this.coef ) this.updateShape();

		//Afficher l'ile de début en surligné pour savoir ou on commence
		if (this.estNouvelleManche) this.ile1 = this.ctrl.getIleDebut(this.id);

		this.estNouvelleManche = false;


		//Afficher les routes
		for (Chemin c : this.ctrl.getChemins(this.id)) 
		{
			// Point
			Ile i1 = c.getIleA();
			Ile i2 = c.getIleB();

			// Coords
			int x1 = (int)(i1.getXPoint() * this.coef);
			int y1 = (int)(i1.getYPoint() * this.coef);

			int x2 = (int) (i2.getXPoint() * this.coef);
			int y2 = (int) (i2.getYPoint() * this.coef);

			// Ligne
			if (c.getCouleur() == null)
			{
				g2.setColor(new Color(255, 255, 0, 40));
				g2.setStroke(new BasicStroke(5f));
				g2.drawLine(x1, y1, x2, y2);
				
				g2.setColor(new Color(255, 0, 0, 40));
				g2.setStroke(new BasicStroke(3f));
				g2.drawLine(x1, y1, x2, y2);
			}
			else
			{
				g2.setColor(c.getCouleur());
				g2.setStroke(new BasicStroke(5f));
				g2.drawLine(x1, y1, x2, y2);
			}
		}

		g2.setColor(Color.BLACK);

		Color colFeutre = this.ctrl.getColFeutre(this.id);
		// Afficher les iles
		
		if (this.ile1 != null)
		{
			for (Chemin c : this.ile1.getCheminAutour())
				if (this.ctrl.estColoriable(c, this.id))
				{
					this.drawPolygonePossible(c.getIleA(), g2);
					this.drawPolygonePossible(c.getIleB(), g2);
				}
				
		}

		for (Ile i : this.ctrl.getIles(this.id)) 
		{
			//Contour si selectionné 
			if (i == this.ile1 || i == this.ile2)
			{
				Polygon p = this.trouverPolygon(i);
				g2.setColor(colFeutre.brighter());
				g2.fill(p);
				g2.setStroke(new BasicStroke(5f));
				g2.drawPolygon(p);
			}

			// Images
			ImageIcon img = this.lstImgIles.get(lstIles.indexOf(i));

			int larg = img.getIconWidth();
			int lon  = img.getIconHeight();
			Image reImage = img.getImage().getScaledInstance((int)(larg*this.coef), (int)(lon*this.coef), Image.SCALE_DEFAULT);
			ImageIcon newImage = new ImageIcon(reImage);

			newImage.paintIcon(this, g, (int) (i.getXImages() * this.coef), (int)(i.getYImages()*this.coef));


			// Noms des iles
			g2.setColor(Color.BLACK);
			g2.drawString( i.getNom(), (int) (i.getXNom() * this.coef) , (int) (i.getYNom() * this.coef));
		}

	}

	private void drawPolygonePossible(Ile i, Graphics2D g2)
	{
		Polygon p = this.trouverPolygon(i);

		g2.setColor(new Color(255,255,200)); //par défauts

		if(i.getCoul().equals("Rouge")) g2.setColor(new Color(204,  80, 124));
		if(i.getCoul().equals("Vert" )) g2.setColor(new Color( 20, 186,  67));
		if(i.getCoul().equals("Jaune")) g2.setColor(new Color(217, 215,  41));
		if(i.getCoul().equals("Brun" )) g2.setColor(new Color(189, 187, 140));

		g2.fill(p);
		g2.setStroke(new BasicStroke(5f));
		g2.drawPolygon(p); 
	} 
/*
Couleurs exactes : 

vert  = Color.decode("#49064C")
rouge = Color.decode("#9D7E89")
jaune = Color.decode("#BFA759")
brun  = Color.decode("#8D8C70")
*/

	/**Mise a jour des polygones des iles. */
	public void updateShape()
	{
		this.polygons = new ArrayList<>();

		int cpt = 0;

		for (ImageIcon i : this.lstImgIles)
		{
			Ile ile = this.ctrl.getIles(this.id).get(cpt++);

			// Reset du polygone de l'image
			Polygon p = new Polygon();

			// Création d'une BufferedImage
			BufferedImage img = new BufferedImage(i.getImage().getWidth(null), i.getImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
			Graphics2D    bGr = img.createGraphics();

			bGr.drawImage(i.getImage(), 0, 0, null);
			bGr.dispose();

			// On note tout les points sur les bords droit puis gauche
			for (int y = 0; y < img.getHeight(); y++)
			{
				for (int x = img.getWidth() - 1; x >= 0; x--)
				{
					if ( img.getRGB(x, y) != 0 )
					{
						p.addPoint( (int) ( (x + ile.getXImages()) * this.coef ), (int) ( (y + ile.getYImages()) * this.coef ) );
						break;
					}
				}
			}

			for (int y = img.getHeight() - 1; y >= 0; y--)
			{
				for (int x = 0; x < img.getWidth(); x++)
				{
					if ( img.getRGB(x, y) != 0 )
					{
						p.addPoint( (int) ( (x + ile.getXImages()) * this.coef ), (int) ( (y + ile.getYImages()) * this.coef ) );
						break;
					}
				}
			}

			this.polygons.add(p);
		}
	}

	public Polygon trouverPolygon(Ile i)
	{
		return this.polygons.get( this.ctrl.getIles(this.id).indexOf( i ) );
	}


	
	/*-----------------------------------*/
	/*       GESTIONS DE LA SOURIS       */
	/*-----------------------------------*/
	private class GereSouris extends MouseAdapter
	{
		private Ile ile1;
		private Ile ile2;

		/**Constructeur. */
		public GereSouris()
		{
			super();

			PanelIles.this.updateShape();
			PanelIles.this.polygons = polygons;
		}

		/**Evement souris. */
		public void mousePressed(MouseEvent e)
		{
			//PanelIles.this.ctrl.test();

			int posX = e.getX();
			int posY = e.getY();

			Ile i = trouverIle( posX, posY );
			Controleur ctrl =  PanelIles.this.ctrl; // raccourci inhumain de l'argumentation

			if ( i == null )
			{
				this.ile1 = this.ile2 = null;
			} 
			else 
			{
				this.ile2 = this.ile1;
				this.ile1 = i;
			}

			if ( this.ile1 != null && this.ile2 != null  && this.ile1 != this.ile2)
			{
				Chemin c = ctrl.trouverChemin(this.ile1, this.ile2, PanelIles.this.id);
				
				if ( !ctrl.colorier(c, PanelIles.this.id) )
				{
					this.ile2 = null;
					this.ile1 = i;
				}
			}

			PanelIles.this.ile1 = this.ile1;
			PanelIles.this.ile2 = this.ile2;
			PanelIles.this.repaint();
		
		}

		/**Cherche l'ile clique. */
		private Ile trouverIle(int x, int y)
		{
			for (Polygon p : PanelIles.this.polygons)
				if ( p != null  && p.contains(x, y) )
					return PanelIles.this.ctrl.getIles(PanelIles.this.id).get( PanelIles.this.polygons.indexOf(p) );

			return null;
		}
	}
}
