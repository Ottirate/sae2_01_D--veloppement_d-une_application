package cinke_terra.ihm;

import cinke_terra.Controleur;
import cinke_terra.metier.*;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class PanelIles extends JPanel
{
	public static String NOM_CHEMIN = "../src/iles"; 

	private Controleur ctrl;

	public PanelIles (Controleur ctrl)
	{
		this.ctrl = ctrl;
	}

	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		for (Ile i : this.ctrl.getIles())
			g2.drawImage(PanelIles.NOM_CHEMIN + i.getNom(), i.getxImages(), i.getyImages());
	}
}
