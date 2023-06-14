package cinketerra.ihm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import cinketerra.Controleur;
import cinketerra.metier.Mappe;
import cinketerra.metier.Mouvement;

import java.awt.BorderLayout;

public class FrameGame extends JFrame 
{
	private Controleur ctrl;

	private int    id;

	private JTextArea    txtHistorique;
	private JScrollPane  spHistorique;

	private PanelIles    panelIles;
	private JPanel       panelHistorique;

	private boolean      historiqueShowed;

	public FrameGame(Controleur ctrl, int id) 
	{
		this.ctrl = ctrl;
		this.id   = id;

		// Info de base
		this.setTitle("CinkeTerra (Joueur " + id + ")" );
		this.setSize(1280, 925);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.setLayout(new BorderLayout());

		this.panelIles   = new PanelIles(ctrl,id);

		this.panelHistorique = new JPanel();
		this.panelHistorique.setBorder(new TitledBorder(new EtchedBorder(), "Journal de bord"));

		// create the middle panel components

		this.txtHistorique = new JTextArea();
		this.txtHistorique.setColumns(30);
		this.txtHistorique.setEditable(false);

		this.spHistorique = new JScrollPane(this.panelHistorique);
		this.spHistorique.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		//Add Textarea in to middle panel
		this.panelHistorique.add(this.txtHistorique);

		this.add(this.panelIles, BorderLayout.CENTER);

		this.setVisible(true);
	}

	public void newManche () { this.panelIles.newManche(); }

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
