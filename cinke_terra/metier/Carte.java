package cinke_terra.metier;

import java.awt.Color;

public enum Carte {
	// Cartes blanches
	B_MULTI_COLOR(null, Color.white),
	B_VERT(Color.decode("#49064C"), Color.white),
	B_ROSE(Color.decode("#9D7E89"), Color.white),
	B_JAUNE(Color.decode("#BFA759"), Color.white),
	B_GRIS(Color.decode("#8D8C70"), Color.white),

	// Cartes noires
	N_MULTI_COLOR(null, Color.black),
	N_VERT(Color.decode("#49064C"), Color.black),
	N_ROSE(Color.decode("#9D7E89"), Color.black),
	N_JAUNE(Color.decode("#BFA759"), Color.black),
	N_GRIS(Color.decode("#8D8C70"), Color.black);

	private Color color;
	private Color contour;

	private Carte(Color coul, Color contour) {
		this.color = coul;
		this.contour = contour;
	}

	public Color getColor() {
		return this.color;
	}

	public Color getContour() {
		return this.contour;
	}

}