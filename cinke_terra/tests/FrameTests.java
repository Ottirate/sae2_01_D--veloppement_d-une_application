/*
* Auteur : Équipe 1
* Date   : juin 2023
* */

/*      Paquetage      */
package cinke_terra.tests;


/*       Imports       */
import javax.swing.*;
import java.awt.BorderLayout;


/*       Classe        */
public class FrameTests extends JFrame
{
	/* Attributs de Classe */
	private Controleur ctrl;


	/*    Constructeur     */
	public FrameTests( Controleur ctrl )
	{
		/*      Attributs      */
		this.ctrl = ctrl;


		/*     Paramétrage     */
		this.setTitle("Choix du test");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLayout( new BorderLayout() );
		this.setSize(1280, 350);


		/*      Créations      */
		this.panelSelection = new PanelSelection(ctrl);


		/*   Positionnement    */
		this.add(this.panelSelection, BorderLayout.CENTER);


		/*      Affichage      */
		this.setVisible(true);	
	}
}
