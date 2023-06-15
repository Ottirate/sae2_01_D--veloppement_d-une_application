package debug.scenario.ihm;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import debug.scenario.Controleur;
import debug.scenario.metier.Carte;

import java.awt.event.*;

/**
 * Frame qui contient la pioche.
 * 
 * @author Équipe 1
 * @date juin 2023
 */
public class FrameCartes extends JFrame implements ActionListener
{

	/*---------------------*/
	/*      Attributs      */
	/*---------------------*/
	
	private Controleur ctrl;

	private PanelPioche panelPioche;
	private JPanel      panelBtn;

	private JButton btnManche;


	/**
	 * Constructeur qui prend un objet {@code Controleur} en paramètre.
	 * 
	 * @param ctrl le controleur
	 */
	public FrameCartes(Controleur ctrl) 
	{
		this.ctrl = ctrl;

		/*---------------------*/
		/*  Paramètre de base  */
		/*---------------------*/

		this.setTitle("Pioche");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.setSize(1280, 350);


		/*---------------------------*/
		/*  Création des composants  */
		/*---------------------------*/

		this.btnManche   = new JButton("Manche suivante");
		this.panelPioche = new PanelPioche(ctrl);

		this.panelBtn = new JPanel();
		this.panelBtn.setOpaque(false);


		/*------------------------*/
		/*  Ajout des composants  */
		/*------------------------*/

		this.panelBtn.add(this.btnManche);

		this.add( this.panelBtn,    BorderLayout.SOUTH  );
		this.add( this.panelPioche, BorderLayout.CENTER );

		
		/*----------------------*/
		/*      Activation      */
		/*----------------------*/

		this.hideButton();
		this.btnManche.addActionListener(this);

		this.setVisible(true);
	}


	/*      Méthodes       */
	// Pioche
	public void initPioche()  { this.panelPioche.initPioche(); }
	public void bloquerPioche(boolean bloque) { this.panelPioche.bloquerPioche(bloque); }
	
	//Boutton manche
	public void showButton () { this.panelBtn.setVisible(true);  }
	public void hideButton () { this.panelBtn.setVisible(false); }
	
	// Maj
	public void maj () { this.panelPioche.repaint(); }


	/*       Action        */
	public void actionPerformed(ActionEvent e)
	{
		this.ctrl     .initialiserManche ()               ;
		this.btnManche.setText           ("Fin de partie");
	}




	/*      Scénario       */

	// Forcer la pioche
	public void forcePioche( int indice )
	{
		this.panelPioche.forcePioche( indice );
	}
}
