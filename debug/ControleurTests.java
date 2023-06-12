/*
* Auteur : Équipe 1
* Date   : juin 2023
* */


/*      Paquetage      */
package debug;


import debug.ihm.FrameTests;
import debug.scenario.Controleur;


/*       Classe        */
public class ControleurTests
{
	/* Attributs de Classe */
	private FrameTests ihm;


	/*    Constructeur     */
	public ControleurTests()
	{
		this.ihm = new FrameTests( this );
	}


	/*      Méthodes       */
	public void lancerTest( int indice )
	{
		new Controleur(indice);
	}


	/*      Lancement      */
	public static void main( String[] args )
	{
		new ControleurTests();
	}
}