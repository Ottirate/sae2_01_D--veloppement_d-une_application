package cinke_terra.ihm;

import javax.swing.JFrame;
import cinke_terra.Controleur;

public class FrameGame extends JFrame 
{
	@SuppressWarnings("unused")
	private Controleur ctrl;

	private PanelIles   panelIles;

	public FrameGame(Controleur ctrl) 
	{
		this.ctrl = ctrl;

		// Info de base
		this.setTitle("CinkeTerra");
		this.setSize(1280, 925);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.panelIles   = new PanelIles(ctrl);

		this.add(this.panelIles);

		this.setVisible(true);
	}

	public void maj () { this.panelIles.repaint();}
}
