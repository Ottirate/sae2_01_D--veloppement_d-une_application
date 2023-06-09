package cinke_terra.ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import cinke_terra.Controleur;
// import cinke_terra.metier.*;

public class FrameCartes extends JFrame
{
	// @SuppressWarnings("unused")
	private Controleur ctrl;

	private PanelPioche panelPioche;

	public FrameCartes(Controleur ctrl) 
	{
		this.ctrl = ctrl;

		// Info de base
		this.setTitle("Pioche");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.setLayout(new BorderLayout());

		this.setSize(1280, 300);

		this.panelPioche = new PanelPioche(ctrl);

		this.add(this.panelPioche);		
		this.setVisible(true);
	}

}
