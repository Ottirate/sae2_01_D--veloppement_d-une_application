package cinke_terra.ihm;

import cinke_terra.Controleur;
import cinke_terra.metier.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class PanelIles extends JPanel
{
	public static String NOM_CHEMIN = "../resources/iles/";
	private ArrayList<ImageIcon> lstImgIles;

	private Controleur ctrl;

	private int maxX;
	private int maxY;
	private double coef;

	public PanelIles(Controleur ctrl) 
	{
		this.ctrl = ctrl;

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
		for (Ile i : this.ctrl.getIles()) 
			this.lstImgIles.add( new ImageIcon(PanelIles.NOM_CHEMIN + i.getNom() + ".png"));

		//On regarde le max X et Y
		List<Ile> lstIles = this.ctrl.getIles();
		for (Ile i : lstIles) 
		{
			int x = this.lstImgIles.get(lstIles.indexOf(i)).getIconHeight() + i.getXImages();
			int y = this.lstImgIles.get(lstIles.indexOf(i)).getIconWidth () + i.getYImages();

			maxX = Math.max(x, maxX);
			maxY = Math.max(y, maxY);
		}

		
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		List<Ile> lstIles = this.ctrl.getIles();
		Graphics2D g2 = (Graphics2D) g;

		/*                               */
		/* CALCUL DU COEF DE PROPORTIONS */
		/*                               */
		
		int hauteur = this.ctrl.getHauteur() - 40 ;
		int largeur = this.ctrl.getLargeur() - 20 ;
		
		double coef1 = hauteur * 1.0 / (this.maxY);
		double coef2 = largeur * 1.0 / (this.maxX);
		
		this.coef = Math.min(coef1, coef2);

		if (this.coef < 0.3) this.coef = 0.3;


		//Afficher les routes
		for (Chemin c : this.ctrl.getChemins()) 
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

		// Afficher les iles
		for (Ile i : this.ctrl.getIles()) 
		{
			// Images
			ImageIcon img = this.lstImgIles.get(lstIles.indexOf(i));

			int larg = img.getIconWidth();
			int lon  = img.getIconHeight();

			Image reImage = img.getImage().getScaledInstance((int)(larg*this.coef), (int)(lon*this.coef), Image.SCALE_DEFAULT);

			ImageIcon newImage = new ImageIcon(reImage);
			newImage.paintIcon(this, g, (int) (i.getXImages() * this.coef), (int)(i.getYImages()*this.coef));

			// Noms des iles
			// this.ajouterTexte(this.lstImgIles.get(lstIles.indexOf(i)), i.getNom());
			g2.drawString( i.getNom(), (int) (i.getXNom() * this.coef) , (int) (i.getYNom() * this.coef));
		}
	}

	/*       Classe        */
	/*     GereSouris      */
	private class GereSouris extends MouseAdapter
	{
		public void mousePressed(MouseEvent e)
		{
			int posX = e.getX();
			int posY = e.getY();

			Ile i = trouverIle( posX, posY );

			System.out.println(i);
		}

		private Ile trouverIle(int x, int y)
		{
			for (Ile i : PanelIles.this.ctrl.getIles())
			{
				Polygon ile = new Polygon();

				
			}

			return null;
		}
	}
}
