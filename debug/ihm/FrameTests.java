/*
* Auteur : Équipe 1
* Date   : juin 2023
* */

/*      Paquetage      */
package debug.ihm;


import javax.swing.*;

import debug.ControleurTests;

import java.awt.BorderLayout;


/*       Classe        */
public class FrameTests extends JFrame
{
	/* Attributs de Classe */
	@SuppressWarnings("unused")
	private ControleurTests ctrl;
	private PanelSelection panelSelection;


	/*    Constructeur     */
	public FrameTests( ControleurTests ctrl )
	{
		/*      Attributs      */
		this.ctrl = ctrl;


		/*     Paramétrage     */
		this.setTitle("Choix du test");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setLayout( new BorderLayout() );
		this.setLocationRelativeTo(null);


		/*      Créations      */
		this.panelSelection = new PanelSelection(ctrl);


		/*   Positionnement    */
		this.add(this.panelSelection, BorderLayout.CENTER);


		/*      Affichage      */
		this.pack();
		this.setVisible(true);	
	}
}
