// Déclaration du package auquel appartient le fichier

package metier;

// Importation des classes utilisées dans ce fichier

import java.util.ArrayList;

// Initialisation de la classe Joueur

public class Region 
{
	// Déclaration des attributs de la classe Joueur

	private String nom;
	private ArrayList<Sommet> ensSommet;

	public Region ( String nom )
	{
		this.nom = nom;
		ensSommet = new ArrayList<Sommet>();
	}

	// Accesseur nécessaire à la classe Joueur

	public String getNom() { return this.nom; }

	public ArrayList<Sommet> getEnsSommet() { return ensSommet; }

	// Méthode permettant d'ajouter un sommet à la liste des sommets de la région

	public void ajouterSommet(Sommet sommet) { ensSommet.add(sommet); }
}