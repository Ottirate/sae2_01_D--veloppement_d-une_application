package cinketerra.ihm;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import cinketerra.Controleur;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelDebut extends JPanel
{
	static final String PATH = "resources/images/";
	
	// /home/etudiant/vy222194/TP/s2/s2.01_dev_application/sae2_01_D--veloppement_d-une_application/class/cinketerra/ihm/FrameCartes.class
	// /home/etudiant/vy222194/TP/s2/s2.01_dev_application/sae2_01_D--veloppement_d-une_application/class/resources/images/Cinketera.png

	private Controleur ctrl;
	private JLabel     lblTexte;
	private JLabel     lblLogo;
	private ImageIcon  imgUnJoueur;
	private ImageIcon  imgDeuxJoueurs;
	private int        redimX1 = 75;
	private int        redimY1 = 100;
	private int        redimX2 = 100;
	private int        redimY2 = 100;
	private int        posX1   = 50;
	private int        posX2   = 155;
	private int        posY1   = 335;
	private int        posY2   = 335;
	

	public PanelDebut(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.imgUnJoueur    = new ImageIcon(PanelDebut.PATH + "UnJoueur.png");
		this.imgDeuxJoueurs = new ImageIcon(PanelDebut.PATH + "DeuxJoueurs.png");

		this.setLayout(new BorderLayout());

		this.lblTexte   = new JLabel ("Choisissez le type de partie", SwingConstants.CENTER);

		Image img = new ImageIcon(PanelDebut.PATH + "Cinketera.png").getImage();
		Image scaledImg = img.getScaledInstance(300, 200, Image.SCALE_SMOOTH);

		this.lblLogo    = new JLabel(new ImageIcon(scaledImg));

		JPanel panelTmp = new JPanel();
		panelTmp.setPreferredSize(new Dimension(300, 100));
		panelTmp.setOpaque(false);

		this.add(this.lblLogo,  BorderLayout.NORTH);
		this.add(this.lblTexte, BorderLayout.CENTER);
		this.add(panelTmp,      BorderLayout.SOUTH);

		GereSouris gs = new GereSouris();
		
		this.addMouseListener      (gs);
		this.addMouseMotionListener(gs);

		this.repaint();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		//Graphics2D g2 = (Graphics2D) g;

		// Affichage du mode 1 joueur
		Image     reImage  = this.imgUnJoueur.getImage().getScaledInstance(this.redimX1, this.redimY1, Image.SCALE_SMOOTH);
		ImageIcon newImage = new ImageIcon(reImage);

		newImage.paintIcon(this, g, this.posX1, this.posY1);

		// Affichage du mode 2 joueurs
		reImage  = this.imgDeuxJoueurs.getImage().getScaledInstance(this.redimX2, this.redimY2, Image.SCALE_SMOOTH);
		newImage = new ImageIcon(reImage);

		newImage.paintIcon(this, g, this.posX2, this.posY2);

		g.drawString("1 joueur" , 60 , 455);
		g.drawString("2 joueurs", 175, 455);
	}

	/*-----------------------------------*/
	/*       GESTIONS DE LA SOURIS       */
	/*-----------------------------------*/
	public class GereSouris extends MouseAdapter
	{
		private Rectangle[] ensRect = {new Rectangle(50, 335,  75, 100),
			                           new Rectangle(155, 335, 100, 100)};

		public GereSouris()
		{
			super();
		}

		public void mouseMoved(MouseEvent e)
		{
	 		int posX = e.getX();
			int posY = e.getY();

			if (this.trouverRect(posX, posY) == null)
			{
				PanelDebut.this.setCursor( new Cursor( Cursor.DEFAULT_CURSOR ));
				PanelDebut.this.imgUnJoueur    = new ImageIcon(PanelDebut.PATH + "UnJoueur.png");
				PanelDebut.this.imgDeuxJoueurs = new ImageIcon(PanelDebut.PATH + "DeuxJoueurs.png");
				PanelDebut.this.redimX1 = 75;
				PanelDebut.this.redimY1 = 100;
				PanelDebut.this.redimX2 = 100;
				PanelDebut.this.redimY2 = 100;
				PanelDebut.this.posX1   = 50;
				PanelDebut.this.posX2   = 155;
				PanelDebut.this.posY1   = 335;
				PanelDebut.this.posY2   = 335;
				PanelDebut.this.repaint();
				return;
			}
			
			if (this.trouverRect(posX, posY) == 0)
			{
				PanelDebut.this.imgUnJoueur = new ImageIcon(PanelDebut.PATH + "UnJoueur_S.png");
				PanelDebut.this.setCursor( new Cursor( Cursor.HAND_CURSOR ));
				PanelDebut.this.redimX1 = 85;
				PanelDebut.this.redimY1 = 115;
				PanelDebut.this.posX1   = 42;
				PanelDebut.this.posY1   = 327;
			}
			

			if (this.trouverRect(posX, posY) == 1)
			{
				PanelDebut.this.imgDeuxJoueurs = new ImageIcon(PanelDebut.PATH + "DeuxJoueurs_S.png");
				PanelDebut.this.setCursor( new Cursor( Cursor.HAND_CURSOR ));
				PanelDebut.this.redimX2 = 115;
				PanelDebut.this.redimY2 = 115;
				PanelDebut.this.posX2   = 153;
				PanelDebut.this.posY2   = 327;
			}
			

			PanelDebut.this.repaint();
		}

		public void mouseClicked(MouseEvent e)
		{
			int posX = e.getX();
			int posY = e.getY();

			if (this.trouverRect(posX, posY) == null) return;

			int value = this.trouverRect(posX, posY);

			if (value == 0)
				Controleur.NB_JOUEUR = 1;

			if (value == 1)
				Controleur.NB_JOUEUR = 2;
		}

		public Integer trouverRect(int posX, int posY)
		{
			if (this.ensRect[0].contains(posX, posY))
				return 0;

			if (this.ensRect[1].contains(posX, posY))
				return 1;
			
			return null;
		}
	}
	
}