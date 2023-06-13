package cinketerra.ihm;

import javax.swing.ImageIcon;
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
		this.setSize(300,500);
		this.setLocationRelativeTo(null);
		this.setIconImage( new ImageIcon(PanelDebut.PATH + "Cinketera.png").getImage() );

		this.panelD = new PanelDebut(ctrl);

		this.add(panelD);

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible(true);

	}
}