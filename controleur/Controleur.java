// Déclaration du package auquel appartient le fichier

package controleur;

// Importation des classes utilisées dans ce fichier

import metier.*;
import vue.*;

import java.awt.*;
import java.util.*;

// Initialisation de la classe Controleur

public class Controleur
{
	// Déclaration des attributs de la classe Controleur

	private FrameGraphe  frm;
	private Graphe graphe;
	public  Joueur joueur;
	private Cycle cycle;

	private ArrayList<Sommet> lstSommet;
	private ArrayList<Region> lstRegion;
	private ArrayList<Arete>  lstArete;

	private String cheminFichier = "./metier/data/donnees.txt";

	public Controleur ( )
	{
		this.lstSommet = new ArrayList<Sommet>();
		this.lstRegion = new ArrayList<Region>();
		this.lstArete  = new ArrayList<Arete>();
		this.joueur    = new Joueur(this);
		this.graphe    = new Graphe(this, this.cheminFichier);
		this.frm       = new FrameGraphe(this);
		this.cycle     = new Cycle();
	}

	// Mutateurs et accesseurs nécessaires à la classe Controleur

	public void setSommet ( ArrayList<Sommet> lstSommet ) { this.lstSommet = lstSommet; }

	public ArrayList<Sommet> getSommet ( ) { return this.lstSommet; }

	public void setRegion ( ArrayList<Region> lstReg ) { this.lstRegion = lstReg; }

	public ArrayList<Region> getRegion ( ) { return this.lstRegion; }

	public void setArr( ArrayList<Arete> lstAre ) { this.lstArete = lstAre; }

	public ArrayList<Arete> getArr ( ) { return this.lstArete; }

	public void setTxt ( String txt ) { this.frm.setTxt(txt); }

	// Appel la méthode testIntersection de la classe Graphe

	public boolean Intersection ( int[] tab1, int[] tab2 ) { return this.graphe.testIntersection(tab1, tab2); }

	// Appel la méthode sommetExiste de la classe Cycle

	public boolean aUnCycle ( Sommet sommet, Sommet sommet2 ) { return this.cycle.sommetExiste(sommet, sommet2); }

	// Réinitialise le cycle

	public void resetCycle ( ) { this.cycle = new Cycle(); }

	// Ajoute un sommet au cycle

	public void ajouter ( Sommet sommet, Sommet sommet2 ) { this.cycle.ajouter(sommet, sommet2); }

	// Colorie l'arête passée en paramètre

	public void setCouleurArete ( Arete a, Color c )
	{
		this.frm.setCouleurArete(a, c);

		for (Arete arete : this.lstArete)
		{
			if (arete.equals(a))
				arete.setCouleur(c);
		}
	}

	// Affiche le message d'erreur passé en paramètre sur la Frame

	public void setMessage ( String message ) { this.frm.setMessage(message); }

	public static void main(String[] args) { new Controleur(); }
}