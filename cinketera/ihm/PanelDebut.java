/*
* Auteur : Équipe 1
* Date   : juin 2023
* */


/*      Paquetage      */
package cinketera.ihm;


/*       Imports       */
import javax.swing.*;

import cinketera.Controleur;
import cinketera.metier.Mappe;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.*;


/**
 * Panel écran titre
 */
public class PanelDebut extends JPanel implements ActionListener
{
	

	/* Attributs de Classe */
	/*      Constants      */
	static final String PATH = "./resources/images/";


	/*      Attributs      */
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

	private boolean    debug;

	private JCheckBox         cbCarteCachee;
	private JCheckBox         cbOptionPouvoir;

	private JComboBox<String> ddlstTest;
	private JButton           btnCommencer;

	private JRadioButton      rbUn;
	private JRadioButton      rbDeux;
	private ButtonGroup       btg;


	/*    Constructeur     */
	public PanelDebut(Controleur ctrl, boolean debug)
	{
		this.ctrl           = ctrl;
		this.debug          = debug;

		this.imgUnJoueur    = new ImageIcon(PanelDebut.PATH + "UnJoueur.png");
		this.imgDeuxJoueurs = new ImageIcon(PanelDebut.PATH + "DeuxJoueurs.png");

		//Paramètre de base
		this.setLayout(new BorderLayout());

		//Création des composants
		this.lblTexte   = new JLabel ("Choisissez le type de partie", SwingConstants.CENTER);

		Image img       = new ImageIcon(PanelDebut.PATH + "Cinketera.png").getImage();
		Image scaledImg = img.getScaledInstance(300, 200, Image.SCALE_SMOOTH);
		this.lblLogo    = new JLabel(new ImageIcon(scaledImg));

		this.cbOptionPouvoir       = new JCheckBox("Activer les pouvoirs", false);

		JPanel panelTmp = new JPanel();
		panelTmp.setPreferredSize(new Dimension(300, 200));
		panelTmp.setOpaque       (false                  );

		//Ajout des composants
		this.add(this.lblLogo,  BorderLayout.NORTH );
		this.add(this.lblTexte, BorderLayout.CENTER);
		this.add(panelTmp,      BorderLayout.SOUTH );


		if (debug)
		{
			JPanel panelDebug = new JPanel( new GridLayout(7, 1) );

			String[] ensTest  = new String[]
			{
				"Scénario libre",
				"Croisements & Cycles",
				"Deux joueurs & Bifurcation",
				"Journal de bord",
				"Rebond & Carte unique",
				"Score & Compte double",
				"Double passage & Chemin unique"
			};

			this.cbCarteCachee = new JCheckBox("Montrer les cartes", true);

			this.ddlstTest     = new JComboBox<String>(ensTest);

			this.btnCommencer  = new JButton("Commencer");

			this.rbUn          = new JRadioButton("Un joueur"  , true );
			this.rbDeux        = new JRadioButton("Deux joueur", false);

			this.btg           = new ButtonGroup();

			this.btg.add( this.rbUn   );
			this.btg.add( this.rbDeux );

			JPanel panelNbJoueur = new JPanel( new GridLayout(1, 2) );

			panelNbJoueur.add( this.rbUn   );
			panelNbJoueur.add( this.rbDeux );

			panelDebug.add( this.cbCarteCachee   );
			panelDebug.add( this.cbOptionPouvoir );
			panelDebug.add(      panelNbJoueur   );
			panelDebug.add( this.ddlstTest       );
			panelDebug.add( this.btnCommencer    );

			/*     Activation      */
			this.btnCommencer.addActionListener(this);
			this.ddlstTest   .addActionListener(this);

			panelTmp.add(panelDebug, BorderLayout.NORTH);
		}
		else
		{
			panelTmp.add(this.cbOptionPouvoir, BorderLayout.NORTH);
		}

		//Action
		GereSouris gs = new GereSouris();
		this.addMouseListener      (gs);
		this.addMouseMotionListener(gs);

		//Visible
		this.repaint();
	}

	public void actionPerformed( ActionEvent e )
	{
		if (e.getSource() == this.ddlstTest)
		{
			if ( this.ddlstTest.getSelectedIndex() == 0 )
			{
				this.cbOptionPouvoir.setVisible(true);
				this.rbUn           .setVisible(true);
				this.rbDeux         .setVisible(true);
			}
			else
			{
				this.cbOptionPouvoir.setVisible(false);
				this.rbUn           .setVisible(false);
				this.rbDeux         .setVisible(false);
			}
		}
		else if (e.getSource() == this.btnCommencer)
		{
			if (this.debug)
			{
				int indiceScenario = ddlstTest.getSelectedIndex();

				Controleur.cacherCarte(this.cbCarteCachee.isSelected());

				if (indiceScenario != 0)
				{
					Controleur.prendreOptionScenario(indiceScenario);

					this.ctrl.numScenario = indiceScenario;
				}
				else
				{
					Controleur.setOptionActive( PanelDebut.this.cbOptionPouvoir.isSelected() );
					Controleur.debug = false;
					Controleur.setNbJoueur( this.rbUn.isSelected() ? 1 : 2 );
				}
			}
		}
	}


	/*     Paint/Draw      */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if (!this.debug)
		{
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
	}


	/**
	* Classe Privée : 
	*     - Gestion de la souris
	*/
	public class GereSouris extends MouseAdapter
	{


		/*      Attributs      */
		private Rectangle[] ensRect = {new Rectangle(50, 335,  75, 100),
			                           new Rectangle(155, 335, 100, 100)};


		/*    Constructeur     */
		public GereSouris()
		{
			super();
		}


		/*     Activation      */
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

			if (value == 1) Controleur.setNbJoueur(2);
			else            Controleur.setNbJoueur(1); // si value == 0

			Controleur.setOptionActive( PanelDebut.this.cbOptionPouvoir.isSelected() );
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