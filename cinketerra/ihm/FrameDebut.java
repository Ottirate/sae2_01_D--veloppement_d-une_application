package cinketerra.ihm;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import cinketerra.Controleur;
import javax.swing.ImageIcon;

public class FrameDebut extends JFrame
{
	@SuppressWarnings("unused")
	private Controleur ctrl;
	private PanelDebut panelD;
	
	public FrameDebut(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setTitle("Choix du nombre de joueur");
		this.setIconImage( new ImageIcon( "l'image" ).getImage() );
		this.setLocationRelativeTo(null);
		this.setIconImage( new ImageIcon(PanelDebut.PATH + "Cinketera.png").getImage() );

		this.panelD = new PanelDebut(ctrl);

		this.add(panelD);

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible(true);

	}
}