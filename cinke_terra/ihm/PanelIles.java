package cinke_terra.ihm;

import cinke_terra.Controleur;
import cinke_terra.metier.*;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class PanelIles extends JPanel {
	public static String NOM_CHEMIN = "../src/iles";

	private Controleur ctrl;

	public PanelIles(Controleur ctrl) {
		this.ctrl = ctrl;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		for (Chemin c : this.ctrl.getChemins()) {
			// Point
			Ile i1 = c.getIleA();
			Ile ii = c.getIleB();

			// Coords
			int x1 = i1.getxPoint();
			int y1 = i1.getyPoint();

			int x2 = i2.getxPoint();
			int y2 = i2.getyPoint();

			// Ligne
			g2.setColor(new Color(255, 255, 0, 50));
			g2.setStroke(new BasicStroke(5f));
			g2.drawLine(x1, y1, x2, y2);
			
			g2.setColor(new Color(255, 0, 0, 50));
			g2.setStroke(new BasicStroke(3f));
			g2.drawLine(x1, y1, x2, y2);
		}

		// Afficher les iles
		for (Ile i : this.ctrl.getIles()) {

			// Images
			g2.drawImage(PanelIles.NOM_CHEMIN + i.getNom(), i.getxImages(), i.getyImages());

			// Noms des iles
			g2.drawString( "",  )
		}
	}
}
