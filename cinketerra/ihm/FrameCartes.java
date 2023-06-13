package cinketerra.ihm;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cinketerra.Controleur;

import java.awt.event.*;

public class FrameCartes extends JFrame implements ActionListener
{
	private Controleur ctrl;

	private JButton btnManche;

	private PanelPioche panelPioche;

	public FrameCartes(Controleur ctrl) 
	{
		this.ctrl = ctrl;

		// Info de base
		this.setTitle("Pioche");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		this.setLayout(new BorderLayout());

		this.setSize(1280, 350);

		this.btnManche   = new JButton("Manche suivante");
		this.panelPioche = new PanelPioche(ctrl);

		JPanel panelTemp = new JPanel();
		panelTemp.add(this.btnManche);

		this.add(this.panelPioche, BorderLayout.CENTER);
		this.add(panelTemp       , BorderLayout.SOUTH );

		this.btnManche.addActionListener(this);
		this.hideButton();

		this.setVisible(true);
	}

	public void initPioche() { this.panelPioche.initPioche(); }
	public void bloquerPioche(boolean bloque) { this.panelPioche.bloquerPioche(bloque); }

	public void showButton () {this.btnManche.setVisible(true );}
	public void hideButton () {this.btnManche.setVisible(false);}

	public void maj () { this.panelPioche.repaint(); }

	public void actionPerformed(ActionEvent e)
	{
		this.ctrl     .initialiserManche ()               ;
		this.btnManche.setText           ("Fin de partie");
	}


}
