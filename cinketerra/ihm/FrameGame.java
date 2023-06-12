package cinketerra.ihm;

import javax.swing.JFrame;
import javax.swing.JLabel;

import cinketerra.Controleur;

import java.awt.BorderLayout;
import java.awt.Color;

public class FrameGame extends JFrame 
{
	@SuppressWarnings("unused")
	private Controleur ctrl;

	private JLabel lblScore;
	private int    id;

	private PanelIles   panelIles;

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
		this.lblScore    = new JLabel(this.ctrl.getScore(this.id));

		this.lblScore.setOpaque(true);
		this.lblScore.setBackground(new Color(182, 211, 250).darker());

		this.add(this.panelIles, BorderLayout.CENTER);
		this.add(this.lblScore , BorderLayout.SOUTH );

		this.setVisible(true);
	}

	public void newManche () { this.panelIles.newManche(); }

	public void maj () 
	{ 
		this.lblScore.setText(this.ctrl.getScore(this.id));
		this.panelIles.repaint(); 
	}
}
