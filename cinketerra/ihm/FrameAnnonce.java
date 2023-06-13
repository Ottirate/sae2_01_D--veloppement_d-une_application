package cinketerra.ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FrameAnnonce extends JFrame
{
	private Image img;

	public FrameAnnonce()
	{
		// Supprime les bords de la JFrame
		this.setUndecorated(true);

		// Rend la JFrame transparente
		this.setBackground(new Color(0, 0, 0, 0));
		//this.setOpacity(0f);

		// Définit une taille pour la JFrame
		this.setSize(400, 300);

		// Centre la JFrame sur l'écran
		this.setLocationRelativeTo(null);

		// Quitte l'application lorsque la JFrame est fermée
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Ajoute des composants à la JFrame
		this.img = getToolkit().getImage( "./resources/zeppelin.png" );

		// Rend la JFrame visible
		this.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(this.img, 0, 0, null);
	}
}
