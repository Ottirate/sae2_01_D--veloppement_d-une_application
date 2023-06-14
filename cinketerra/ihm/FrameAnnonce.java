/*
* Auteur : Équipe 1
* Date   : juin 2023
* */


/*      Paquetage      */
package cinketerra.ihm;


/*       Imports       */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


/**
 * Frame qui permet l'animation du dirigeable lors d'événement
 */
public class FrameAnnonce extends JFrame
{


	/*      Attributs      */
	private Image img;
	private int x;


	/*    Constructeur     */
	public FrameAnnonce( String imgName )
	{
		double width  = getToolkit().getScreenSize().getWidth();

		//Paramètre de base
		this.setSize((int) width, 233);
		this.setUndecorated(true);

		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setFocusable(false);

		this.setBackground(new Color(0, 0, 0, 0));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Création des attributs
		this.img  = getToolkit().getImage( String.format("./resources/images/%s.png", imgName) );
		Image img = new ImageIcon(this.img).getImage();
		this.img  = img.getScaledInstance(1440 / 2, 467 / 2, Image.SCALE_SMOOTH); 

		//Visible + Animation
		this.setVisible(true);
		new Animation( this ).start();
	}


	/*      Affichage      */
	public void paint(Graphics g)
	{
		super.paint(g);

		g.drawImage(this.img, this.x, 0, null);
	}


	/**
	* Classe Privée : 
	*     - Frame qui permet l'animation du dirigeable lors d'événement
	*/
	private class Animation extends Thread
	{
		

		/*      Attributs      */
		private FrameAnnonce frame;


		/*    Constructeur     */
		public Animation(FrameAnnonce frameAnnonce)
		{
			this.frame = frameAnnonce;
		}


		/*      Méthodes       */
		public void run()
		{
			int time = 7;  // Temps en seconde
			int fps  = 60; // Qualité de l'animation

			double width  = getToolkit().getScreenSize().getWidth();
			double height = getToolkit().getScreenSize().getHeight();

			double gap  = (width + 720 * 1.5) / (time * fps);

			try
			{
				for (int i = 0; i < (time * fps); i++)
				{
					Thread.sleep(1000 / fps);

					FrameAnnonce.this.x = (int) (i * gap) - 720;

					FrameAnnonce.this.repaint();
				}

				this.frame.dispose();
			}
			catch (Exception e) {}
		}
	}
}
