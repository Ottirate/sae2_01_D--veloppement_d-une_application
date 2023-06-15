package debug.scenario.ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Frame qui permet l'animation du dirigeable lors d'événement
 *
 * @author Équipe 1
 * @date juin 2023
 */
public class FrameAnnonce extends JFrame
{

	/*---------------------*/
	/*      Attributs      */
	/*---------------------*/

	private Image img;
	private int x;


	/**
	 * Constructeur qui prend en paramètre le nom d'une image.
	 * 
	 * @param imgName le nom de l'image
	 */
	public FrameAnnonce( String imgName )
	{
		double width  = getToolkit().getScreenSize().getWidth();

		this.img  = getToolkit().getImage( "./resources/images/" + imgName + ".png" );
		Image img = new ImageIcon(this.img).getImage();
		this.img  = img.getScaledInstance(1440 / 2, 467 / 2, Image.SCALE_SMOOTH); 

		/*  Paramètres de base  */
		this.setSize((int) width, 233);
		this.setUndecorated(true);

		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setFocusable(false);

		this.setBackground(new Color(0, 0, 0, 0));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*  Visible + Animation  */
		this.setVisible(true);
		new Animation( this ).start();
	}


	/**
	 * Dessine l'image sur la frame.
	 * <br><br>
	 * {@inheritDoc}
	 */
	public void paint(Graphics g)
	{
		super.paint(g);

		g.drawImage(this.img, this.x, 0, null);
	}


	/**
	 * Classe qui permet l'animation du dirigeable lors d'événement.
	 */
	private class Animation extends Thread
	{
		
		/*---------------------*/
		/*      Attributs      */
		/*---------------------*/

		private FrameAnnonce frame;


		/**
		 * Constructeur qui prend un objet {@code FrameAnnonce} en paramètre.
		 * 
		 * @param frameAnnonce la frame de l'animation
		 */
		public Animation(FrameAnnonce frameAnnonce)
		{
			this.frame = frameAnnonce;
		}


		/**
		 * {@inheritDoc}
		 */
		public void run()
		{
			int time = 7;  // Temps en seconde
			int fps  = 60; // Qualité de l'animation

			double width  = getToolkit().getScreenSize().getWidth();
			//double height = getToolkit().getScreenSize().getHeight();

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
			catch (InterruptedException e) { }
		}
	}
}
