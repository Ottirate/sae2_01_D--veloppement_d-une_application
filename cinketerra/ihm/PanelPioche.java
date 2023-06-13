package cinketerra.ihm;

import java.util.List;
import java.util.ArrayList;

import javax.swing.*;

import cinketerra.Controleur;

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
		this.isBlocked = false;

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
			// Images
			ImageIcon img  = new ImageIcon( this.ctrl.getImage         (cpt) );
			ImageIcon img2 = new ImageIcon( this.ctrl.getImageRetournee(cpt) );
			
			//int coefPanel = this.longCarte / this.getHeight;

			Image ogImage = img.getImage();
			Image reImage = ogImage.getScaledInstance((int)(this.largCarte), (int)(this.longCarte), Image.SCALE_DEFAULT);

			ImageIcon newImage = new ImageIcon(reImage);

			// Scale de la deuxieme image
			Image ogImage2 = img2.getImage();
			Image reImage2 = ogImage2.getScaledInstance((int)(this.largCarte), (int)(this.longCarte), Image.SCALE_DEFAULT);

			ImageIcon newImage2 = new ImageIcon(reImage2);
			
			// Surélévation de la pioche
			if ( this.ctrl.carteCachee( cpt ) && cptPioche == this.carteRelevee)
			{
				newImage.paintIcon(this, this.g2, this.calculPosCartePioche(cptPioche), PanelPioche.POS_Y_CARTE - 10);
				cptPioche++;
			}
			// Pioche
			else if ( this.ctrl.carteCachee( cpt ) )
			{
				newImage .paintIcon(this, this.g2, this.calculPosCartePioche  (cptPioche), PanelPioche.POS_Y_CARTE);
				newImage2.paintIcon(this, this.g2, this.calculPosCarteDefausse(cptPioche+1), PanelPioche.POS_Y_CARTE);
				//System.out.println( "coord x image " +cptPioche+ " posée : " + this.calculPosCartePioche( cptPioche ) );
				cptPioche++;
			}
			// // Cartes restantes dans la pioche
			// else if (!this.ctrl.getCarte(cpt).equals(this.ctrl.getDerniereCartePiochee()))
			// {
				
			// }
			// Main
			else
			{
				int x = this.calculPosCarteDefausse(this.ctrl.getNbCarteRestante()) - 200;
				this.g2.drawString("Main :", x, 20);
				newImage.paintIcon(this, this.g2, x, PanelPioche.POS_Y_CARTE);
			}
		}

		if (cptDefausse > 0) this.g2.drawString("Défausse : ", this.calculPosCarteDefausse(cptDefausse), 20);
		if (cptPioche   > 0) this.g2.drawString("Pioche : "  , this.calculPosCartePioche  (0          ), 20);
	}
	
	public int calculPosCartePioche  ( int indice ){ return PanelPioche.MARGE_X_CARTE + indice*PanelPioche.ESPACEMENT; }
	public int calculPosCarteDefausse( int indice ){ return (int)(this.getWidth() - indice*(this.largCarte+PanelPioche.ESPACEMENT) - PanelPioche.MARGE_X_CARTE); }


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
