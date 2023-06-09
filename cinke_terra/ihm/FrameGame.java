package cinke_terra.ihm;

import javax.swing.JFrame;
import cinke_terra.Controleur;
import cinke_terra.metier.*;

public class FrameGame extends JFrame {
	private Controleur ctrl;

	private PanelIles panelIles;

	public FrameGame(Controleur ctrl) 
	{
		this.ctrl = ctrl;

		// Info de base
		this.setTitle("CinkeTerra");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.setSize(1280, 925);

		this.panelIles = new PanelIles(ctrl);
		this.add(this.panelIles);

		this.setVisible(true);
	}

}
