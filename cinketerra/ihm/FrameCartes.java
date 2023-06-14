/*
* Auteur : Équipe 1
* Date   : juin 2023
* */


/*      Paquetage      */
package cinketerra.ihm;


/*       Imports       */
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cinketerra.Controleur;

import java.awt.event.*;


/**
 * Frame qui contient la mappe et l'historique
 */
public class FrameCartes extends JFrame implements ActionListener
{


	/*      Attributs      */
	private Controleur ctrl;

	private PanelPioche panelPioche;
	private JPanel      panelBtn;

	private JButton btnManche;


	/*    Constructeur     */
	public FrameCartes(Controleur ctrl) 
	{
		this.ctrl = ctrl;

		//Paramètre de base
		this.setTitle("Pioche");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.setLayout(new BorderLayout());

		this.setSize(1280, 350);

		//Création des attributs
		this.btnManche   = new JButton("Manche suivante");
		this.panelPioche = new PanelPioche(ctrl);

		panelBtn = new JPanel();
		panelBtn.setOpaque(false);

		//Ajout des composants
		panelBtn.add(this.btnManche);

		this.add( this.panelBtn,    BorderLayout.SOUTH  );
		this.add( this.panelPioche, BorderLayout.CENTER );

		//Activation
		this.hideButton();
		this.btnManche.addActionListener(this);

		//Visible
		this.setVisible(true);
	}

	public void initPioche() { this.panelPioche.initPioche(); }
	public void bloquerPioche(boolean bloque) { this.panelPioche.bloquerPioche(bloque); }

	public void showButton () { this.panelBtn.setVisible(true);  }
	public void hideButton () { this.panelBtn.setVisible(false); }

	public void maj () { this.panelPioche.repaint(); }

	public void actionPerformed(ActionEvent e)
	{
		this.ctrl     .initialiserManche ()               ;
		this.btnManche.setText           ("Fin de partie");
	}


}
