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
	private PanelAnnonce panel;

	public FrameAnnonce()
	{
		double width  = getToolkit().getScreenSize().getWidth();

		this.setUndecorated(true);
		//this.setBackground(new Color(0, 0, 0, 0));
		this.setSize((int) width, 233);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.img = getToolkit().getImage( "./resources/zeppelin.png" );

		Image img = new ImageIcon(this.img).getImage();

		this.img   = img.getScaledInstance(1440 / 2, 467 / 2, Image.SCALE_SMOOTH); // 1440 467
		this.panel = new PanelAnnonce();

		this.add( this.panel );
		this.setVisible(true);

		new Animation( this ).start();
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
			int time = 5; // Temps en seconde

			double width  = getToolkit().getScreenSize().getWidth();
			double height = getToolkit().getScreenSize().getHeight();

			double gap  = width / (time * 10);

			try
			{
				for (int i = 0; i < (time * 10); i++)
				{
					Thread.sleep(100);

					FrameAnnonce.this.x = (int) (i * gap) - 720;
					FrameAnnonce.this.y = (int) (height / 2);

					//this.frame.setLocation( (int) (i * gap), (int) (height / 2) );
					FrameAnnonce.this.panel.repaint();
				}

				this.frame.dispose();
			}
			catch (Exception e) {}
		}
	}

	private class PanelAnnonce extends JPanel
	{
		public PanelAnnonce() {}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponents(g);

			Graphics2D g2 = (Graphics2D) g;

			System.out.println(FrameAnnonce.this.x + " " + FrameAnnonce.this.y);

// FrameAnnonce.this.x, FrameAnnonce.this.y
			g2.drawImage(FrameAnnonce.this.img, FrameAnnonce.this.x, FrameAnnonce.this.y, null);
		}
	}
}
