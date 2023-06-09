package cinke_terra.ihm;

//import cinke_terra.metier.Carte;
import cinke_terra.Controleur;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.Image;

import java.awt.event.*;

public class PanelPioche extends JPanel
{
	private Controleur ctrl;
	private Graphics2D g2;

	private int nbCarteTotal;

//getImage( int )
//carteCachee( int )
	public PanelPioche( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.nbCarteTotal = this.ctrl.getNbCarteTotal();

		this.setLayout(new BorderLayout());

		this.add(new JLabel("Pioche : "), BorderLayout.NORTH);

		this.repaint();
	}

	public void paintComponent( Graphics g )
	{
		super.paintComponent(g);

		this.g2 = (Graphics2D) g;

		//dessiner l'ensemble des cartes
		int cptPioche=0;
		int cptDefausse=0;

		for( int cpt = 0; cpt < ctrl.getNbCarteTotal(); cpt++ )
		{
			// Images
			ImageIcon img = new ImageIcon( this.ctrl.getImage(cpt) );
			System.out.println(this.ctrl.getImage(cpt)+"");

			int larg = img.getIconWidth();
			int lon  = img.getIconHeight();

			Image ogImage = img.getImage();
			Image reImage = ogImage.getScaledInstance((int)(larg), (int)(lon), Image.SCALE_DEFAULT);

			ImageIcon newImage = new ImageIcon(reImage);
			
			// Cas des cartes de la pioche
			if( this.ctrl.carteCachee( cpt ) )
			{
				//newImage.paintIcon(this, g, 30+50, cptPioche*10+50);
				cptPioche++;
			}
			else
			{
				newImage.paintIcon(this, g, cptDefausse*10+50, 50);
				cptDefausse++;
			}
		}
	}
}