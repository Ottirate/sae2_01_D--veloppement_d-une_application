package cinke_terra.metier;

import java.awt.Color;

public enum Couleur {
	MULTI_COLOR(Color.decode("#3D74FF")),
	VERT(Color.decode("#FF644C")),
	ROSE(Color.decode("#F5DD14")),
	JAUNE(Color.decode("#35E11D")),
	GRIS(Color.decode("#FF42B3"));

	private Color color;

	private Couleur(Color c)
	{
		this.color = c;
	}

	public Color getValue()
	{
		return this.color;
	}
}