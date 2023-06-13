// Déclaration du package auquel appartient le fichier

package metier;

// Importation des classes utilisées dans ce fichier

import java.util.ArrayList;

public class Cycle
{
	// Déclaration des attributs de la classe Cycle

	private ArrayList<Sommet> lstEnsSommet;

	public Cycle ( ) { this.lstEnsSommet = new ArrayList<Sommet>(); }

	public ArrayList<Sommet> getList() { return this.lstEnsSommet; }

	public void ajouter(Sommet sommet, Sommet sommet2)
	{
		if (!this.lstEnsSommet.contains(sommet))
			this.lstEnsSommet.add(sommet);
		if (!this.lstEnsSommet.contains(sommet2))
			this.lstEnsSommet.add(sommet2);
	}

	// Méthode permettant de savoir si un sommet est déjà présent dans le cycle

	public boolean sommetExiste(Sommet sommet, Sommet sommet2) { return (this.lstEnsSommet.contains(sommet) && 
		this.lstEnsSommet.contains(sommet2)); }
}