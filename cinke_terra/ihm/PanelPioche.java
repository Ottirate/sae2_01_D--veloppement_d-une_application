package cinke_terra.ihm;

// import cinke_terra.metier.Carte;
import cinke_terra.Controleur;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.*;

@SuppressWarnings("unused")
public class PanelPioche extends JPanel
{
	private Controleur ctrl;
	private Graphics2D g2;

	private int nbCarteTotal;

	private int largCarte;
	private int longCarte;
	
	private ArrayList<Rectangle> ensCartePioche;


	private final static double COEF_CARTE    = 0.667;
	private final static int    POS_Y_CARTE   = 50;
	private final static int    MARGE_X_CARTE = 50;
	private final static int    ESPACEMENT    = 10;

	//getImage( int )
	//carteCachee( int )
	public PanelPioche( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.nbCarteTotal = this.ctrl.getNbCarteTotal();

		// Images
		ImageIcon img = new ImageIcon( this.ctrl.getImage(0) );

		this.largCarte  = (int) (img.getIconWidth ()*PanelPioche.COEF_CARTE);
		this.longCarte  = (int) (img.getIconHeight()*PanelPioche.COEF_CARTE);

		this.setLayout(new BorderLayout());

		JLabel lbl = new JLabel("Pioche : ");
		lbl.setOpaque(false);
		this.add(lbl, BorderLayout.NORTH);
		
		this.repaint();

		this.addMouseListener(new GereSouris());
	}

	public void paintComponent( Graphics g )
	{
		super.paintComponent(g);

		this.g2 = (Graphics2D) g;

		//dessiner l'ensemble des cartes
		int cptPioche  = 0;
		int cptDefausse= 0;

		for( int cpt = 0; cpt < ctrl.getNbCarteTotal(); cpt++ )
		{
			// Images
			ImageIcon img = new ImageIcon( this.ctrl.getImage(cpt) );
			
			Image ogImage = img.getImage();
			Image reImage = ogImage.getScaledInstance((int)(this.largCarte), (int)(this.longCarte), Image.SCALE_DEFAULT);

			ImageIcon newImage = new ImageIcon(reImage);
			
			// Cas des cartes de la pioche
			if ( this.ctrl.carteCachee( cpt ) )
			{
				newImage.paintIcon(this, g, this.calculPosCartePioche(cptPioche), PanelPioche.POS_Y_CARTE);
				//System.out.println( "coord x image " +cptPioche+ " posée : " + this.calculPosCartePioche( cptPioche ) );
				cptPioche++;
			}
			else //cas des cartes du tas
			{
				newImage.paintIcon(this, g, this.calculPosCarteDefausse( ++cptDefausse ), PanelPioche.POS_Y_CARTE);
			}
		}


		for (Rectangle rectangle : ensCartePioche) {
			g2.draw(rectangle);
		}
	}
	
	public int calculPosCartePioche  ( int indice ){ return PanelPioche.MARGE_X_CARTE + indice*PanelPioche.ESPACEMENT; }
	public int calculPosCarteDefausse( int indice ){ return (int)(this.ctrl.getLargeur() - indice*(this.largCarte+PanelPioche.ESPACEMENT) - PanelPioche.MARGE_X_CARTE); }


	/*-----------------------------------*/
	/*       GESTIONS DE LA SOURIS       */
	/*-----------------------------------*/
	
	private class GereSouris extends MouseAdapter
	{
		/**Constructeur. */
		public GereSouris()
		{
			super();
			PanelPioche.this.ensCartePioche = new ArrayList<>();
			
			this.init();
		}

		private void init()
		{
			//liste de carte
			

			for (int i = 0 ; i < PanelPioche.this.ctrl.getNbCarteTotal() ; i++)
			{
				PanelPioche.this.ensCartePioche.add(new Rectangle(PanelPioche.this.calculPosCartePioche(i), PanelPioche.POS_Y_CARTE, PanelPioche.this.largCarte, PanelPioche.this.longCarte));

				//System.out.println( "i = " + i);
				//System.out.println( "x = " + PanelPioche.this.calculPosCartePioche(i) );
				//System.out.println( "y = " + PanelPioche.POS_Y_CARTE );
				//System.out.println( "longueur = " + PanelPioche.this.longCarte );
				//System.out.println( "largeur = " + PanelPioche.this.largCarte );
			}
		}

		/**Mise a jour des polygones des iles. */
		// private void retirer()
		// {
		// 	int cpt = 0;

		// 	for (ImageIcon i : PanelIles.this.lstImgIles)
		// 	{
		// 		System.out.println(" ok -> début ");
		// 		// Reset du polygone de l'image
		// 		Polygon p = this.polygons[cpt++];

		// 		if ( p == null ) p = new Polygon(); else p.reset();

		// 		// Création d'une BufferedImage
		// 		BufferedImage img = new BufferedImage(i.getImage().getWidth(null), i.getImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
		// 		Graphics2D    bGr = img.createGraphics();

		// 		bGr.drawImage(i.getImage(), 0, 0, null);
		// 		bGr.dispose();

		// 		// On note tout les points qui ne sont pas transparents
		// 		for (int y = 0; y < img.getHeight(); y++)
		// 		{
		// 			for (int x = img.getWidth() - 1; x >= 0; x--)
		// 			{
		// 				if ( img.getRGB(x, y) != 0 )
		// 				{
		// 					p.addPoint(x, y);
		// 					break;
		// 				}
		// 			}
		// 		}

		// 		for (int y = img.getHeight() - 1; y >= 0; y--)
		// 		{
		// 			for (int x = img.getWidth() - 1; x >= 0; x--)
		// 			{
		// 				if ( img.getRGB(x, y) != 0 )
		// 				{
		// 					p.addPoint(x, y);
		// 					break;
		// 				}
		// 			}
		// 		}
		// 	}
		// }

		/**Evement souris. */
		public void mousePressed(MouseEvent e)
		{
			int posX = e.getX();
			int posY = e.getY();

			Integer indice = trouverCarte(posX, posY);

			if (indice != null)
			{

				//PanelPioche.this.ctrl.piocher(indice);
				//Carte c = null;//PanelPioche.this.ctrl.getCarte(indice);

				System.out.println("indice = " + indice);
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

		/*public void mouseEntered(MouseEvent e)
		{
	 		int posX = e.getX();
			int posY = e.getY();
			
			if ( r.contains(x, y) )
			{
				
			}

		}*/

		/**Cherche l'ile clique. */
		private Integer trouverCarte(int x, int y)
		{
			for (int i = PanelPioche.this.ensCartePioche.size() - 1; i >= 0 ; i--) // parcours à l'envers pour correspondre au positionnement par IHM
			{
				Rectangle r = PanelPioche.this.ensCartePioche.get(i);

				if ( r.contains(x, y) )
				{
					System.out.println("trouverCarte i = " + i);
					return i;
				}
			}

			return null;
		}
	 }
}
