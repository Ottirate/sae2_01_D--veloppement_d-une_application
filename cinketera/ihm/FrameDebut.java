/*
* Auteur : Équipe 1
* Date   : juin 2023
* */


/*      Paquetage      */
package cinketera.ihm;


/*       Imports       */
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import cinketera.Controleur;


/**
 * Frame avec le titre et l'option de mode
 */
public class FrameDebut extends JFrame
{


	/*      Attributs      */
	private Controleur ctrl;
	private PanelDebut panelD;


	/*    Constructeur     */
	public FrameDebut(Controleur ctrl, boolean debug)
	{
		this.ctrl = ctrl;

		//Paramètre de base
		this.setTitle    ("Choix du nombre de joueur");
		this.setIconImage(new ImageIcon(PanelDebut.PATH + "Cinketera.png").getImage());

		this.setSize              (300,500);
		this.setLocationRelativeTo(null   );

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Création des composants
		this.panelD = new PanelDebut(ctrl, debug);

		//Ajout des composants
		this.add(panelD);

		//Visible
		this.setVisible(true);
	}
}