// Déclaration du package auquel appartient le fichier

package metier;

// Importation des classes utilisées dans ce fichier

import controleur.Controleur;

import java.util.Random;

import iut.algo.Decomposeur;
import java.awt.geom.Line2D;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Color;

// Initialisation de la classe Graphe

public class Graphe
{
	// Déclaration des attributs de la classe Graphe

	private Controleur ctrl;

	public Graphe(Controleur ctrl, String fichier)
	{
		this.ctrl = ctrl;
		this.lireFichierGraphe(fichier);
	}

	public void lireFichierGraphe(String nomFichier)
	{
		FileReader fr;
		Scanner sc;
		Decomposeur dec = null;

		ArrayList<Sommet> lstSommet = new ArrayList<>();
		ArrayList<Region> lstRegion = new ArrayList<>();
		ArrayList<Arete>  lstAretes = new ArrayList<>();

		try
		{
			fr = new FileReader(nomFichier);
			sc = new Scanner(fr);
			String ligne = sc.nextLine();

			lirePartieSommet(sc, lstSommet, dec, ligne);
			lirePartieRegion(sc, lstSommet, lstRegion, dec, ligne);
			lirePartieArete(sc, lstSommet, lstAretes, dec, ligne);
			lirePartieBonus(sc, lstSommet, lstAretes, dec, ligne);

		}
		catch (FileNotFoundException e)
		{
			System.out.println("Le fichier n'a pas été trouvé : " + nomFichier);
		}

		this.ctrl.setSommet(lstSommet);
		this.ctrl.setRegion(lstRegion);
		this.ctrl.setArr(lstAretes);
	}

	// Méthode qui lit la partie sommet du fichier txt

	private String lirePartieSommet(Scanner sc, ArrayList<Sommet> lstSommet, Decomposeur dec, String ligne)
	{
		boolean boolSommet = true;

		while (sc.hasNextLine() && boolSommet)
		{
			ligne = sc.nextLine();
			dec = new Decomposeur(ligne);

			if (!dec.getString(0).equals(","))
				lstSommet.add(new Sommet(dec.getInt(0), dec.getInt(1), dec.getInt(2)));
			else
				boolSommet = false;
		}

		return ligne;
	}

	// Méthode qui lit la partie région du fichier txt

	private String lirePartieRegion(Scanner sc, ArrayList<Sommet> lstSommet, ArrayList<Region> lstRegion,
									Decomposeur dec, String ligne)
	{
		boolean boolRegion = true;

		while (sc.hasNextLine() && boolRegion)
		{
			ligne = sc.nextLine();
			dec = new Decomposeur(ligne);
			int cpt = 0;
			boolean bOk = true;

			if (!(dec.getString(0).equals(",")))
			{
				while (bOk)
				{
					try
					{
						if (cpt == 0)
						{
							Region region = new Region(dec.getString(cpt));
							lstRegion.add(region);
						}
						else
						{
							for (int i = 0; i < lstSommet.size(); i++)
							{
								if (lstSommet.get(i).getSommet() == Integer.parseInt(dec.getString(cpt)))
								{
									lstRegion.get(lstRegion.size() - 1).ajouterSommet(lstSommet.get(i));
									lstSommet.get(i).setRegion(lstRegion.get(lstRegion.size() - 1));
								}
							}
						}
						cpt++;
					}
					catch (Exception e)
					{
						bOk = false;
					}
				}
			}
			else
				boolRegion = false;
		}

		return ligne;
	}

	// Méthode qui lit la partie arête du fichier txt

	private String lirePartieArete(Scanner sc, ArrayList<Sommet> lstSommet, ArrayList<Arete> lstAretes, Decomposeur dec, String ligne)
	{
		boolean boolAr = true;
		while (sc.hasNextLine() && boolAr)
		{
			ligne = sc.nextLine();
			dec = new Decomposeur(ligne);
			int cpt = 0;
			boolean boolArete = true;
			if(dec.getString(0).equals(","))
			{
				boolArete = false;
				boolAr = false;
			}
			while (boolArete)
			{
				try
				{
					int indiceSommet1 = -1;
					int indiceSommet2 = -1;
					for (int i = 0; i < lstSommet.size(); i++)
					{
						if (lstSommet.get(i).getSommet() == Integer.parseInt(dec.getString(cpt)))
							indiceSommet1 = i;

						if (lstSommet.get(i).getSommet() == Integer.parseInt(dec.getString(cpt + 1)))
							indiceSommet2 = i;
					}

					lstAretes.add(new Arete(lstSommet.get(indiceSommet1), lstSommet.get(indiceSommet2)));

					cpt++;
				}
				catch (Exception e)
				{
					boolArete = false;
				}
			}
		}
		return ligne;
	}

	// Méthode qui lit la partie bonus du fichier txt

	private String lirePartieBonus(Scanner sc, ArrayList<Sommet> lstSommet, ArrayList<Arete> lstAretes, Decomposeur dec, String ligne)
	{
		sc.nextLine();
		while (sc.hasNextLine())
		{
			ligne = sc.nextLine();
			dec = new Decomposeur(ligne);
			boolean som1Existe = false;
			boolean som2Existe = false;
			for (int i = 0; i < lstSommet.size(); i++)
			{
				if (lstSommet.get(i).getSommet() == Integer.parseInt(dec.getString(0)))
					som1Existe = true;

				if (lstSommet.get(i).getSommet() == Integer.parseInt(dec.getString(1)))
					som2Existe = true;
			}
			if(som1Existe && som2Existe)
			{
				for(Arete a : lstAretes)
				{
					if(a.getSommet1().getSommet() == Integer.parseInt(dec.getString(0)) && a.getSommet2().getSommet() == Integer.parseInt(dec.getString(1)))
						a.setPoints(5);
					else if(a.getSommet1().getSommet() == Integer.parseInt(dec.getString(1)) && a.getSommet2().getSommet() == Integer.parseInt(dec.getString(0)))
						a.setPoints(5);
				}
			}
			
		}

		return ligne;
	}

	

	// Méthode qui teste l'intersection entre deux arêtes

	public boolean testIntersection(int[] arete, int[] areteTest)
	{
		Line2D line1 = new Line2D.Double(arete[0], arete[1], arete[2], arete[3]);
		Line2D line2 = new Line2D.Double(areteTest[0], areteTest[1], areteTest[2], areteTest[3]);

		if (line1.intersectsLine(line2))
		{
			if (line1.getP1().equals(line2.getP1()) || line1.getP1().equals(line2.getP2()) ||
					line1.getP2().equals(line2.getP1()) || line1.getP2().equals(line2.getP2()))
				return false;

			return true;
		}

		return false;
	}
}