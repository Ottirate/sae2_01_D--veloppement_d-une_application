/*
* Auteur : Équipe 1
* Date   : juin 2023
* */


/*      Paquetage      */
package cinketerra.tests;

import cinketerra.Controleur;
import cinketerra.tests.ihm.FrameTests;

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
		new Controleur();
	}


	/*      Lancement      */
	public static void main( String[] args )
	{
		new ControleurTests();
	}
}