package cinke_terra;

import cinke_terra.ihm.FrameGame;
import cinke_terra.metier.*;
import java.util.List;

public class Controleur {
	private Mappe metier;
	private FrameGame ihm;

	public Controleur() {
		this.metier = new Mappe();
		this.ihm = new FrameGame(this);
	}

	public List<Ile> getIles() {
		return this.metier.getIles();
	}

	public List<Chemin> getChemins() {
		return this.metier.getChemins();
	}

	public static void main(String[] args) {
		new Controleur();
	}
}