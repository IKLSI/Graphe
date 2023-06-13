// Déclaration du package auquel appartient le fichier

package metier;

// Importation des classes utilisées dans ce fichier

import java.awt.Color;

// Initialisation de la classe Arete

public class Arete
{
	// Déclaration des attributs de la classe Arete

	private Sommet sommet1;
	private Sommet sommet2;
	private Color couleur;
	private int points;

	// Constructeur de la classe Arete

	public Arete(Sommet sommet1, Sommet sommet2)
	{
		this.sommet1 = sommet1;
		this.sommet2 = sommet2;
		this.points = 0;

		this.couleur = Color.BLACK;
	}

	// Mutateurs et accesseurs nécessaires à la classe Arete

	public void setSommet1(Sommet sommet1) { this.sommet1 = sommet1; }

	public Sommet getSommet1() { return this.sommet1; }

	public void setSommet2(Sommet sommet2) { this.sommet2 = sommet2; }

	public Sommet getSommet2() { return this.sommet2; }

	public void setCouleur(Color couleur) { this.couleur = couleur; }

	public Color getCouleur() { return this.couleur;}

	public int getPoints() { return this.points;}

	public int setPoints(int points) { return this.points = points;}

	// Méthodes qui vérifient si un sommet existe dans l'arête

	public boolean Sommet1Existe(int sommet) { return this.sommet1.getSommet() == sommet; }

	public boolean Sommet2Existe(int sommet) { return this.sommet2.getSommet() == sommet; }

}