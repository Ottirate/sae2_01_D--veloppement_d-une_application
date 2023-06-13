package cinketerra.ihm;

import java.util.List;
import java.util.ArrayList;

import javax.swing.*;

import cinketerra.Controleur;
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

@SuppressWarnings("unused")
public class PanelPioche extends JPanel
{
	private Controleur ctrl;
	private Graphics2D g2;

	private int nbCarteTotal;

	private int largCarte;
	private int longCarte;

	private int carteRelevee;     // indice de la carte que l'on sélectionne
	
	private List<Rectangle> ensCartePioche;

	private GereSouris gs;

	private boolean isBlocked;

	private PaquetDeCarte paquetDeBase;

	private final static double COEF_CARTE    = 0.667;
	private final static int    POS_Y_CARTE   = 50;
	private final static int    MARGE_X_CARTE = 50;
	private final static int    ESPACEMENT    = 15;

	//getImage( int )
	//carteCachee( int )
	public PanelPioche( Controleur ctrl )
	{
		this.ctrl         = ctrl;
		this.nbCarteTotal = this.ctrl.getNbCarteTotal();
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

	public void paintComponent( Graphics g )
	{
		ImageIcon img, img2;

		super.paintComponent(g);

		this.g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		//System.out.println("Dimension Preferred == " + this.getPreferredSize());

		//dessiner l'ensemble des cartes
		int cptPioche   = 0;
		int cptDefausse = 0;

		this.g2.setFont(new Font("", Font.BOLD, 12));

		for( int cpt = 0; cpt < this.ctrl.getNbCarteTotal(); cpt++ )
		{
			// Image
			img  = this.redimImage(new ImageIcon( this.ctrl.getImage(cpt)), (int)(this.largCarte), (int)(this.longCarte));

			
			//int coefPanel = this.longCarte / this.getHeight;

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
				//System.out.println( "coord x image " +cptPioche+ " posée : " + this.calculPosCartePioche( cptPioche ) );
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
					img2 = this.redimImage(new ImageIcon( this.ctrl.getImageRetournee(this.paquetDeBase.getCarte(cptB))), (int)(this.largCarte/2), (int)(this.longCarte/2));

					if (cptB < 5)
						img2.paintIcon(this, this.g2, this.calculPosCarteDefausse(cptB%5), PanelPioche.POS_Y_CARTE-10);
					else
						img2.paintIcon(this, this.g2, this.calculPosCarteDefausse(cptB%5), PanelPioche.POS_Y_CARTE*3 - 15);

					System.out.println(cptB);
				}

		// Intitulés
		if (cptPioche   > 0)
		{
			this.g2.drawString("Cartes restantes : ", this.calculPosCarteDefausse(4), 20);
			this.g2.drawString("Pioche : "          , this.calculPosCartePioche  (0        ), 20);
		}
	}
	
	public int calculPosCartePioche  ( int indice ){ return PanelPioche.MARGE_X_CARTE + indice*PanelPioche.ESPACEMENT;                                                   }
	public int calculPosCarteDefausse( int indice ){ return (int)(this.getWidth() - (indice+1)*((this.largCarte/2)+PanelPioche.ESPACEMENT) - PanelPioche.MARGE_X_CARTE); }

	// Méthode redimensionnant une image (paramètres : l'image, la longueur et la hauteur)
	private ImageIcon redimImage(ImageIcon img, int width, int height)
	{
		Image ogImage = img.getImage();
		Image reImage = ogImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		return new ImageIcon(reImage);
	}


	/*-----------------------------------*/
	/*       GESTIONS DE LA SOURIS       */
	/*-----------------------------------*/
	
	private class GereSouris extends MouseAdapter
	{
		/**Constructeur. */
		public GereSouris()
		{
			super();
			
			this.init();
		}

		public void init()
		{
			//liste de carte
			PanelPioche.this.ensCartePioche = new ArrayList<>();

			for (int i = 0 ; i < PanelPioche.this.ctrl.getNbCarteTotal() ; i++)
				PanelPioche.this.ensCartePioche.add(new Rectangle(PanelPioche.this.calculPosCartePioche(i), PanelPioche.POS_Y_CARTE, PanelPioche.this.largCarte, PanelPioche.this.longCarte));
		}

		/**
		 * Lorsque la souris est cliquée.
		 * <br><br>
		 * {@inheritDoc}
		 */
		public void mousePressed(MouseEvent e)
		{
			if (PanelPioche.this.isBlocked) return;

			int posX = e.getX();
			int posY = e.getY();

			Integer indice = trouverCarte(posX, posY);

			if (indice != null)
			{

				//PanelPioche.this.ctrl.piocher(indice);
				//Carte c = null;//PanelPioche.this.ctrl.getCarte(indice);

				PanelPioche.this.ctrl.piocher(indice);
				PanelPioche.this.ensCartePioche.remove(PanelPioche.this.ensCartePioche.size() - 1);

				/*if (c != null)
				{
					c.setCache(false);
					PanelPioche.this.ensCartePioche.remove(PanelPioche.this.ensCartePioche.size() - 1);

					PanelPioche.this.repaint();
				}*/

				PanelPioche.this.repaint();
			}
		}

		/**
		 * {@inheritDoc}
		 */
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

		// public void mouseExited(MouseEvent e)
		// {
	 	// 	int posX = e.getX();
		// 	int posY = e.getY();
			
		// 	Integer indice = trouverCarte(posX, posY);

		// 	if (indice != null && indice == PanelPioche.this.carteRelevee)
		// 	{
		// 		System.out.println("testExit " + indice);
		// 		PanelPioche.this.carteRelevee = indice;
		// 	}

		// }

		/**Cherche l'ile clique. */
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
