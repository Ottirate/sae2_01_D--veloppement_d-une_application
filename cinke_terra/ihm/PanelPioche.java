package cinke_terra.ihm;

//import cinke_terra.metier.Carte;
import cinke_terra.Controleur;

import java.awt.Graphics;
import java.awt.Graphics2D;

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

		this.repaint();
	}

	public void paintComponent( Graphics g )
	{
		super.paintComponent(g);

		this.g2 = (Graphics2D) g;

		//dessiner l'ensemble des cartes
		for( int cpt = 0; cpt < ctrl.getNbCarteTotal(); cpt++)
	}
}