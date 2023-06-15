/*
* Auteur : Équipe 1
* Date   : juin 2023
* */


/*      Paquetage      */
package debug.scenario.ihm;


/*       Imports       */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import debug.scenario.Controleur;
import debug.scenario.metier.Mappe;
import debug.scenario.metier.Mouvement;

import java.awt.BorderLayout;


/**
 * Frame avec la map et l'historique
 */
public class FrameGame extends JFrame 
{


	/*      Attributs      */
	private Controleur ctrl;

	private int    id;

	private JTextArea    txtHistorique;
	private JScrollPane  spHistorique;

	private PanelIles    panelIles;
	private JPanel       panelHistorique;

	private boolean      historiqueShowed;


	/*    Constructeur     */
	public FrameGame(Controleur ctrl, int id) 
	{
		this.ctrl = ctrl;
		this.id   = id;

		//Paramètre de base
		this.setTitle("debug.scenario (Joueur " + id + ")" );
		this.setSize(1280, 925);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.setLayout(new BorderLayout());

		//Création des composants
		this.panelIles   = new PanelIles(ctrl,id);

		this.panelHistorique = new JPanel();
		this.panelHistorique.setBorder(new TitledBorder(new EtchedBorder(), "Journal de bord"));

		this.txtHistorique = new JTextArea();
		this.txtHistorique.setColumns(30);
		this.txtHistorique.setEditable(false);

		this.spHistorique = new JScrollPane(this.panelHistorique);
		this.spHistorique.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		//Ajout des composants
		this.panelHistorique.add(this.txtHistorique);
		this.add(this.panelIles, BorderLayout.CENTER);

		//Visible
		this.setVisible(true);
	}


	/*      Méthodes       */
	// Manche
	public void newManche () { this.panelIles.newManche(); }

	// Maj
	public void maj () 
	{ 
		this.panelIles.repaint();

		String historique = "";
		for (Mouvement mv : Mappe.getActions())
			historique += mv.toString() + '\n';

		this.txtHistorique.setText(historique);

		if (this.historiqueShowed)
			this.add(this.spHistorique,BorderLayout.EAST);
		else
			this.remove(this.spHistorique);


		this.revalidate(); 
	}

	//Historique
	public void hideHistorique () 
	{ 
		this.historiqueShowed = false;
		this.maj();
	}
	public void showHistorique () 
	{ 
		this.historiqueShowed = true;
		this.maj();
	}


}
