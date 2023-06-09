package cinke_terra.ihm;

import javax.swing.JFrame;
import cinke_terra.Controleur;
import cinke_terra.metier.*;

public class FrameGame extends JFrame 
{
	private Controleur ctrl;

	private PanelIles panelIles;

	public FrameGame (Controleur ctrl)
	{
		this.ctrl = ctrl;

		//Info de base
		this.setTitle("CinkeTerra");
		this.setLocation(0,0);

		this.setSize(0, 0);

		this.panelIles = new PanelIles(ctrl);
	}


}
