/*      Paquetage      */
package debug.ihm;


import javax.swing.*;

import debug.ControleurTests;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;


/*       Classe        */
public class PanelSelection extends JPanel implements ActionListener
{
	/* Attributs de Classe */
	private ControleurTests ctrl;

	private JComboBox<String> ddlstTest;
	private JButton           btnCommencer;


	/*    Constructeur     */
	public PanelSelection( ControleurTests ctrl )
	{
		/*     Paramétrage     */
		this.ctrl       = ctrl;
		
		this.setLayout(new BorderLayout());
		

		/*      Créations      */
		String[] ensTest  = new String[]{"Premières cartes noires","Premières cartes blanches","Intersections", "Cycle", "Scénario libre"};

		this.ddlstTest    = new JComboBox<String>(ensTest);
		this.btnCommencer = new JButton("Commencer");


		/*    Positionnement    */
		@SuppressWarnings("unused")
		JPanel pnlTemp = new JPanel( new FlowLayout() );

		this.add( new JLabel("Choix du scénario", SwingConstants.CENTER), BorderLayout.NORTH  );
		this.add( this.ddlstTest,                                         BorderLayout.CENTER );
		this.add( this.btnCommencer,                                      BorderLayout.SOUTH  );

		/*     Activation      */
		this.btnCommencer.addActionListener(this);
		
		/*      Affichage      */ 
		this.setVisible( true );
	}

	public void actionPerformed( ActionEvent e )
	{
		this.ctrl.lancerTest( ddlstTest.getSelectedIndex() + 1 );
	}
}