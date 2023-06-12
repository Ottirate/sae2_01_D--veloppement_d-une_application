/*
* Auteur : Équipe 1
* Date   : juin 2023
* */


/*      Paquetage      */
package cinke_terra.tests;


/*       Imports       */
import cinke_terra.Controleur;


/*       Classe        */
public class ControleurTests
{
	/* Attributs de Classe */
	private FrameTests ihm;


	/*    Constructeur     */
	public ControleurTests()
	{
		this.ihm = new FrameTests();
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