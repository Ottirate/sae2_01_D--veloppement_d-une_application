package cinketerra.ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameAnnonce extends JFrame
{
	private Image img;
	private int x;
	private int y;

	public FrameAnnonce( String imgName )
	{
		double width  = getToolkit().getScreenSize().getWidth();

		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setSize((int) width, 233);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setFocusable(false);

		this.img  = getToolkit().getImage( String.format("./resources/images/%s.png", imgName) );
		Image img = new ImageIcon(this.img).getImage();
		this.img  = img.getScaledInstance(1440 / 2, 467 / 2, Image.SCALE_SMOOTH); // 1440 467

		this.setVisible(true);

		new Animation( this ).start();
	}

	public void paint(Graphics g) {
		super.paint(g);

		g.drawImage(this.img, this.x, 0, null);
	}

	private class Animation extends Thread
	{
		private FrameAnnonce frame;

		public Animation(FrameAnnonce frameAnnonce)
		{
			this.frame = frameAnnonce;
		}

		public void run()
		{
			int time = 7; // Temps en seconde
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
					FrameAnnonce.this.y = (int) (height / 2);

					FrameAnnonce.this.repaint();
				}

				this.frame.dispose();
			}
			catch (Exception e) {}
		}
	}
}
