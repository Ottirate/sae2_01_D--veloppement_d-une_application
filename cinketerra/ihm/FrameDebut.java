package cinketerra.ihm;

import javax.swing.JFrame;
import cinketerra.Controleur;

public class FrameDebut extends JFrame
{
	private Controleur ctrl;
	private PanelDebut panelD;
	
	public FrameDebut(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setTitle("Choix du nombre de joueur");
		this.setLocationRelativeTo(null);

		this.panelD = new PanelDebut(ctrl);

		this.add(panelD);

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible(true);

	}
}