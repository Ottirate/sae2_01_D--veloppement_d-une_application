/*      Paquetage      */
package cinke_terra.tests;


/*       Imports       */
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;


/*       Classe        */
public class PanelSelection extends JPanel implements ActionListener
{
	/* Attributs de Classe */
	private Controleur ctrl;

	private JComboBox<String> ddlstTestJeu;
	private JComboBox<String> ddlstTestCartes;
	private JComboBox<String> ddlstTestMappe;
	private JComboBox<String> ddlstTestIHM;


	/*    Constructeur     */
	public PanelSelection( Controleur ctrl )
	{
		/*     Paramétrage     */
		this.ctrl = ctrl;
		this.setLayout( new GridLayout(4,2) );
		

		/*      Créations      */
		String[] ensTestJeu  = new String[]{"Score","Relancer","Quitter","Tour impossible"};
		this.ddlstTestJeu    = new JComboBox<>();

		String[] ensTestCartes = new String[]{"Passer tour","Changer joueur","5 cartes blanches", "5 cartes noires"};
		this.ddlstTestCartes   = new JComboBox<>();

		String[] ensTestMappe = new String[]{"Cycles","Intersections","Extrémités","Sections"};
		this.ddlstTestMappe   = new JComboBox<>();

		String[] ensTestIHM = new String[]{"Redimensionnement","Selection pioche","Taille minimum","Positionnements cartes"};
		this.ddlstTestIHM   = new JComboBox<>();

		/*   Positionnement    */
		this.add( new JLabel("Jeu") );
		this.add( this.ddlstTestJeu );

		this.add( new JLabel("Cartes") );
		this.add( this.ddlstTestCartes );

		this.add( new JLabel("Mappe") );
		this.add( this.ddlstTestMappe );

		this.add( new JLabel("IHM") );
		this.add( this.ddlstTestIHM );


		/*     Activation      */
		this.ddlstTestJeu.addActionListener(this);
		this.ddlstTestCartes.addActionListener(this);
		this.ddlstTestMappe.addActionListener(this);
		this.ddlstTestIHM.addActionListener(this);

		/*      Affichage      */ 
		this.setVisible( true );
	}


	public void actionPerformed( ActionEvent e )
	{
		this.ctrl.lancerTest( ( (JComboBox<>)(e.getSource()) ).getSelectedIndex() );
	}
}