// Déclaration du package auquel appartient le fichier

package vue;

// Importation des classes utilisées dans ce fichier

import controleur.Controleur;
import metier.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

// Initialisation de la classe PanelCouleur

public class PanelCouleur extends JPanel implements ActionListener
{
	// Déclaration des attributs de la classe PanelCouleur

	private FrameGraphe frame;
	private Controleur ctrl;

	private JTextField txtSommet1;
	private JTextField txtSommet2;
	private JButton    btnAjouter;

	private ArrayList<Integer> lstSelectAr;
	private ArrayList<Region> lstReg;

	private boolean text = true;

	// Création du JPanel

	public PanelCouleur(FrameGraphe frame, Controleur ctrl)
	{
		this.setLayout(new GridLayout(1, 2, 5, 5));

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/

		this.frame = frame;
		this.ctrl = ctrl;

		this.txtSommet1 = new JTextField(15);
		this.txtSommet2 = new JTextField(15);

		this.btnAjouter = new JButton("Ajouter");

		this.lstSelectAr   = new ArrayList<Integer>();
		this.lstReg        = new ArrayList<Region>();

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		this.add(new JLabel("Arête 1 : "));
		this.add(this.txtSommet1);

		this.add(new JLabel("Arête 2 : "));
		this.add(this.txtSommet2);

		this.add(this.btnAjouter);

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/

		this.btnAjouter.addActionListener(this);
	}

	// Méthode appelée lorsqu'un clic est effectué sur le bouton "Ajouter"

	public void actionPerformed(ActionEvent e)
	{
		// Si le clic est effectué sur le bouton "Ajouter"

		if (e.getSource() == this.btnAjouter)
		{
			int indiceSommet1 = Integer.parseInt(this.txtSommet1.getText());
			int indiceSommet2 = Integer.parseInt(this.txtSommet2.getText());

			ArrayList<Arete> lstArres = this.ctrl.getArr();

			boolean depart  = false;
			boolean arrivee = false;
			Arete   arete   = null;

			this.ctrl.setMessage("");

			// Si les sommets sont valides et que l'arête n'est pas déjà sélectionnée et qu'il part d'un sommet sélectionné

			if(TrouverNombre(this.lstSelectAr, indiceSommet1) || TrouverNombre(this.lstSelectAr, indiceSommet2) || this.ctrl.joueur.getNbTour() == 0)
			{
				for(int i = 0; i < lstArres.size(); i++)
				{
					depart  = false;
					arrivee = false;

					if(lstArres.get(i).Sommet1Existe(indiceSommet1) || lstArres.get(i).Sommet1Existe(indiceSommet2))
						depart  = true;

					if(lstArres.get(i).Sommet2Existe(indiceSommet2) || lstArres.get(i).Sommet2Existe(indiceSommet1))
						arrivee = true;

					if(depart && arrivee)
					{
						this.lstSelectAr.add(indiceSommet1);
						this.lstSelectAr.add(indiceSommet2);
						arete = lstArres.get(i);
						i     = lstArres.size()-1;
					}
				}
			}
			else
			{
				System.out.println("Vous devez choisir une arête qui est reliée à une arête déjà sélectionnée");
				this.ctrl.setMessage("Non valide");
			}

			boolean interTest = false;

			// Si l'arête est valide et qu'il n'y a pas d'intersection avec une arête déjà coloriée

			if (arete != null)
			{
					int[] tabAr = new int[4];
					tabAr[0] = arete.getSommet1().getPosX();
					tabAr[1] = arete.getSommet1().getPosY();
					tabAr[2] = arete.getSommet2().getPosX();
					tabAr[3] = arete.getSommet2().getPosY();

				if (this.ctrl.joueur.getNbLancer() > 0 && arete.getCouleur() == Color.BLACK)
				{
					for(int i = 0; i < this.ctrl.getArr().size(); i++)
					{
						int[] tabArTemp = new int[4];
						tabArTemp[0] = this.ctrl.getArr().get(i).getSommet1().getPosX();
						tabArTemp[1] = this.ctrl.getArr().get(i).getSommet1().getPosY();
						tabArTemp[2] = this.ctrl.getArr().get(i).getSommet2().getPosX();
						tabArTemp[3] = this.ctrl.getArr().get(i).getSommet2().getPosY();

						if(this.ctrl.getArr().get(i).getCouleur() != Color.BLACK)
						{
							if(this.ctrl.Intersection(tabAr, tabArTemp))
							{
								this.ctrl.setMessage("Intersection");
								System.out.println("Colorisation impossible car il y a une intersection avec une arête déjà coloriée");
								interTest = true;
							}
						}
					}

					// Si l'arête n'est pas déjà coloriée et que cela ne formera pas de cycle

					if(!interTest && !this.ctrl.aUnCycle(arete.getSommet1(), arete.getSommet2()))
					{
						this.ctrl.ajouter(arete.getSommet1(), arete.getSommet2());
						this.ctrl.joueur.modifierNbLancer();

						Region reg = arete.getSommet2().getRegion();

						if (!this.lstReg.contains(reg))
							this.lstReg.add(reg);

						this.ctrl.setCouleurArete(arete, this.ctrl.joueur.getCouleur());
						
						int cpt = 0;
						ArrayList<Integer> lstSomCol = new ArrayList<Integer>();

						for (Sommet s : reg.getEnsSommet())
						{
							for(int i = 0; i < this.ctrl.getArr().size(); i++)
							{
								if(this.ctrl.getArr().get(i).Sommet1Existe(s.getSommet()) || this.ctrl.getArr().get(i).Sommet2Existe(s.getSommet()))
								{
									if(this.ctrl.getArr().get(i).getCouleur() != Color.BLACK)
									{	
										if(!lstSomCol.contains(s.getSommet()))
											lstSomCol.add(s.getSommet());
									}
								}
							}
						}

						cpt = lstSomCol.size();
						int points = this.lstReg.size() * cpt + arete.getPoints();

						if(points > this.ctrl.joueur.getPoints())
							this.ctrl.joueur.setNbPoint(points);
						else
							this.ctrl.joueur.ajouterNbPoint(points);

						this.frame.setLabel(this.ctrl.joueur.getPoints());

					}
					else if(this.ctrl.aUnCycle(arete.getSommet1(), arete.getSommet2()))
					{
						this.ctrl.setMessage("Cycle");
						System.out.println("Coloration impossible car cela forme un cycle");
					}
				}
				else if(this.ctrl.joueur.getNbLancer() == 0)
				{
					this.ctrl.setMessage("Fin du jeu");
					System.out.println("Fin du jeu, vous n'avez plus de lancer");
				}
			}
		}
	}

	// Méthode permettant de savoir si un sommet est présent dans la liste

	public boolean TrouverNombre(ArrayList<Integer> lst, int nb) { return lst.contains(nb); }

	// Méthode permettant de remplir les textfields avec les sommets sélectionnés avec la souris

	public void setTxt (String txt)
	{
		if (this.text)
			this.txtSommet1.setText(txt);
		else
			this.txtSommet2.setText(txt);
		this.text = !this.text;
	}
}