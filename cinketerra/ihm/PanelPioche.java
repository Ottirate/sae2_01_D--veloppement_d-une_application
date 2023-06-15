/*
* Auteur : Équipe 1
* Date   : juin 2023
* */


/*      Paquetage      */
package cinketerra.ihm;




/*       Imports       */
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;

import cinketerra.Controleur;
import cinketerra.metier.Carte;
import cinketerra.metier.PaquetDeCarte;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.Cursor;
import java.awt.Font;



/**
 * Panel affichant la pioche
 */
public class PanelPioche extends JPanel
{




	/* Attributs de Classe */
	/*      Constants      */
	private final static double COEF_CARTE    = 0.667;
	private final static int    POS_Y_CARTE   = 50;
	private final static int    MARGE_X_CARTE = 50;
	private final static int    ESPACEMENT    = 15;




	/*      Attributs      */
	private Controleur ctrl;

	private int largCarte;
	private int longCarte;
	private double coef;

	private GereSouris gs;
	
	private boolean isBlocked;

	private int           carteRelevee;     // indice de la carte que l'on sélectionne
	private PaquetDeCarte paquetDeBase;

	private List<Rectangle> ensCartePioche;
	private Graphics2D      g2;




	/*    Constructeur     */
	public PanelPioche( Controleur ctrl )
	{
		this.ctrl         = ctrl;
		this.carteRelevee = -1;
		this.isBlocked    = false;
		this.paquetDeBase = new PaquetDeCarte();

		// Images
		ImageIcon img = new ImageIcon( this.ctrl.getImage(0) );

		this.largCarte  = (int) (img.getIconWidth ()*PanelPioche.COEF_CARTE);
		this.longCarte  = (int) (img.getIconHeight()*PanelPioche.COEF_CARTE);

		this.setLayout(new BorderLayout());

		this.repaint();

		gs = new GereSouris();
		
		this.addMouseListener      (gs);
		this.addMouseMotionListener(gs);
	}




	/*      Méthodes       */
	public void initPioche()
	{
		gs.init();
		this.repaint();
	}

	public void bloquerPioche(boolean bloque)
	{
		this.isBlocked    = bloque;
		this.carteRelevee = -1;
	}



	
	/*     Paint/Draw      */
	public void paintComponent( Graphics g )
	{
		ImageIcon img, img2;

		super.paintComponent(g);

		this.g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


		// Calcul du coefficient
		int hauteur     = this.ctrl.getHauteurPioche() - 40 ;
		int largeur     = this.ctrl.getLargeurPioche() - 20 ;

		double coef1    = hauteur * 1.0 / ( this.longCarte * 1.5 );
		double coef2    = largeur * 1.0 / ( this.largCarte * 1.5 );
		double lastCoef = this.coef;

		this.coef = Math.min(coef1, coef2);

		if (this.coef < 0.3) this.coef = 0.3;
		if (this.coef != lastCoef) gs.init();


		//dessiner l'ensemble des cartes
		int cptPioche   = 0;

		this.g2.setFont(new Font("", Font.BOLD, 12));

		for( int cpt = 0; cpt < this.ctrl.getNbCarteTotal(); cpt++ )
		{
			// Image
			img  = this.redimImage(new ImageIcon( this.ctrl.getImage(cpt)), (int)(this.largCarte), (int)(this.longCarte), false);

			// Surélévation de la pioche
			if ( this.ctrl.carteCachee( cpt ) && cptPioche == this.carteRelevee)
			{
				img.paintIcon(this, this.g2, this.calculPosCartePioche(cptPioche), PanelPioche.POS_Y_CARTE - 10);
				cptPioche++;
			}
			// Pioche
			else if ( this.ctrl.carteCachee( cpt ) )
			{
				img .paintIcon(this, this.g2, this.calculPosCartePioche  (cptPioche), PanelPioche.POS_Y_CARTE);
				cptPioche++;
			}
			// Main
			else if (this.ctrl.getCarte(cpt).equals(this.ctrl.getDerniereCartePiochee()))
			{
				this.g2.drawString("Main :", 725, 20);
				img.paintIcon(this, this.g2, 725, PanelPioche.POS_Y_CARTE);
			}
		}


		// Cartes restantes
		for (int cptB = 0 ; cptB < this.paquetDeBase.getNbCarteTotal(); cptB++)
			for (int cptC = 0 ; cptC < this.ctrl.getNbCarteTotal(); cptC++ )
				if (this.paquetDeBase.getCarte(cptB).equals(this.ctrl.getCarte(cptC)) && this.ctrl.getCarte(cptC).estCache())
				{
					img2 = this.redimImage(new ImageIcon( this.ctrl.getImageRetournee(this.paquetDeBase.getCarte(cptB))), (int)(this.largCarte/2), (int)(this.longCarte/2), false);

					if (cptB < 5)
						img2.paintIcon(this, this.g2, this.calculPosCarteDefausse(cptB%5), PanelPioche.POS_Y_CARTE);
					else
						img2.paintIcon(this, this.g2, this.calculPosCarteDefausse(cptB%5), PanelPioche.POS_Y_CARTE + (int) ( (this.longCarte/2 + 10) * this.coef ));
				}


		// Intitulés
		if (cptPioche   > 0)
		{
			this.g2.drawString("Cartes restantes : ", this.calculPosCarteDefausse(4), 20);
			this.g2.drawString("Pioche : "          , this.calculPosCartePioche  (0        ), 20);
		}

		for (Rectangle rectangle : ensCartePioche) {
			g2.draw(rectangle);
		}
	}
	
	public int calculPosCartePioche  ( int indice )
	{
		return PanelPioche.MARGE_X_CARTE + indice * (int) (PanelPioche.ESPACEMENT * this.coef);
	}

	public int calculPosCarteDefausse( int indice )
	{
		return (int)(this.getWidth() - (indice+1)*(( this.largCarte/2 ) * this.coef + PanelPioche.ESPACEMENT ) - PanelPioche.MARGE_X_CARTE);
	}

	private ImageIcon redimImage(ImageIcon img, double width, double height, boolean isStatic)
	{
		if (!isStatic)
		{
			width  = 1.0 * width  * this.coef;
			height = 1.0 * height * this.coef;
		}

		Image ogImage = img.getImage();
		Image reImage = ogImage.getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);

		return new ImageIcon(reImage);
	}

	/*      Scénario       */

	// Forcer la pioche
	public void forcePioche( Carte c )
	{
		List<Carte> lstCachee = new ArrayList<>();

		for (Carte carte : this.ctrl.getCartes()) if (carte.estCache()) lstCachee.add(carte);

		int indice = lstCachee.indexOf(c);

		if (indice >= 0)
		{
			this.ctrl.piocher(indice);

			this.gs.init();

			this.repaint();
		}
	}


	/**
	* Classe Privée : 
	*     - Gestion de la souris
	*/
	private class GereSouris extends MouseAdapter
	{


		/*    Constructeur     */
		public GereSouris()
		{
			super();
			
			this.init();
		}


		/*      Méthodes       */
		public void init()
		{
			//liste de carte
			PanelPioche.this.ensCartePioche = new ArrayList<>();

			for (int i = 0, cpt = 0 ; i < PanelPioche.this.ctrl.getNbCarteTotal() ; i++)
			{
				if (PanelPioche.this.ctrl.getCarte(i).estCache())
				{
					PanelPioche.this.ensCartePioche.add(
						
						new Rectangle(
							PanelPioche.this.calculPosCartePioche(cpt),
							PanelPioche.POS_Y_CARTE,

							(int) (PanelPioche.this.largCarte * PanelPioche.this.coef),
							(int) (PanelPioche.this.longCarte * PanelPioche.this.coef)
						)

					);

					cpt++;
				}
			}
		}


		/*     Activation      */
		public void mousePressed(MouseEvent e)
		{
			if (PanelPioche.this.isBlocked) return;

			int posX = e.getX();
			int posY = e.getY();

			Integer indice = trouverCarte(posX, posY);

			if (indice != null)
			{
				PanelPioche.this.ctrl.piocher(indice);
				PanelPioche.this.ensCartePioche.remove(PanelPioche.this.ensCartePioche.size() - 1);

				PanelPioche.this.repaint();
			}
		}

		public void mouseMoved(MouseEvent e)
		{
			if (PanelPioche.this.isBlocked) return;

	 		int posX = e.getX();
			int posY = e.getY();

			Integer indice = trouverCarte(posX, posY);

			if (indice != null)
			{
				PanelPioche.this.carteRelevee = indice;
				PanelPioche.this.setCursor( new Cursor( Cursor.HAND_CURSOR ));
			}
			else
			{
				PanelPioche.this.carteRelevee = -1;
				PanelPioche.this.setCursor( new Cursor( Cursor.DEFAULT_CURSOR   ));
			}

			PanelPioche.this.repaint();
		}

		//Cherche l'ile clique
		private Integer trouverCarte(int x, int y)
		{
			for (int i = PanelPioche.this.ensCartePioche.size() - 1; i >= 0 ; i--) // parcours à l'envers pour correspondre au positionnement par IHM
			{
				Rectangle r = PanelPioche.this.ensCartePioche.get(i);

				if ( r.contains(x, y) )
				{
					return i;
				}
			}

			return null;
		}
	}
}