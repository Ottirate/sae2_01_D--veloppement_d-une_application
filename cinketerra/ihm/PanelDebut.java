package cinketerra.ihm;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import cinketerra.Controleur;
import java.awt.BorderLayout;
import java.awt.event.*;

public class PanelDebut extends JPanel implements ActionListener
{
	@SuppressWarnings("unused")
	private Controleur ctrl;
	private JButton    btn1joueur;
	private JButton    btn2joueur;
	private JLabel     lblTexte;
	private JLabel     lblLogo;

	public PanelDebut(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		this.btn1joueur = new JButton("1 joueur");
		this.btn2joueur = new JButton("2 joueurs");
		this.lblTexte   = new JLabel ("Choisissez le type de partie");
		this.lblLogo    = new JLabel();

		JPanel panelSouth = new JPanel();
		panelSouth.add(this.btn1joueur);
		panelSouth.add(this.btn2joueur);

		this.add(this.lblLogo,  BorderLayout.WEST);
		this.add(this.lblTexte, BorderLayout.CENTER);
		this.add(panelSouth,    BorderLayout.SOUTH);

		this.btn1joueur.addActionListener(this);
		this.btn2joueur.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btn1joueur)
			System.out.println("ratio 1");
		else
			System.out.println("apagnan");
	}
}