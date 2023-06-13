// Déclaration du package auquel appartient le fichier

package metier;

// Importation des classes utilisées dans ce fichier

import controleur.Controleur;

import java.util.Random;
import java.awt.Color;

// Initialisation de la classe Joueur

public class Joueur
{
	// Déclaration des attributs de la classe Joueur

	private Controleur ctrl;

	private int        nbLancer;
	private int        nbTour;
	private boolean    aFiniUnTour;
	private Color      couleur;
	private int        nbPoint;

	public Joueur ( Controleur ctrl )
	{
		Random r = new Random();

		this.ctrl = ctrl;
		
		this.nbLancer       = r.nextInt(5) + 5;
		this.nbTour         = 0;
		this.aFiniUnTour	= false;
		this.couleur        = Color.BLUE;
		this.nbPoint        = 0;
	}

	// Mutateurs et accesseurs nécessaires à la classe Joueur

	public int getNbLancer() { return this.nbLancer; }
	
	public int getNbTour() { return this.nbTour; }

	public Color getCouleur() { return this.couleur; }

	public void ajouterNbPoint(int point) {this.nbPoint += point;}

	public void setNbPoint(int point) {this.nbPoint = point;}

	public int getPoints() {return this.nbPoint;}

	// Méthode permettant de modifier le nombre de lancer restant

	public void modifierNbLancer()
	{
		this.nbLancer--;
		this.nbTour++;

		if(nbLancer == 0 && !this.aFiniUnTour)
		{
			this.aFiniUnTour = true;
			this.couleur     = Color.RED;
			Random r = new Random();
			this.nbLancer = r.nextInt(5) + 5;
			this.ctrl.resetCycle();
		}
	}
}