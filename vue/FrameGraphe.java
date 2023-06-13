// Déclaration du package auquel appartient le fichier

package vue;

// Importation des classes utilisées dans ce fichier

import controleur.Controleur;
import metier.*;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;

// Initialisation de la classe FrameGraphe

public class FrameGraphe extends JFrame
{
	// Déclaration des attributs de la classe FrameGraphe

	private Controleur   ctrl;

	private PanelCouleur pnl1;
	private PanelGraphe  pnl2;
	private PanelLabel   pnl3;

	// Création de la fenêtre

	public FrameGraphe(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new BorderLayout());
		this.setSize(1200, 800);

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/

		this.pnl1 = new PanelCouleur(this, this.ctrl);
		this.pnl2 = new PanelGraphe (this.ctrl);
		this.pnl3 = new PanelLabel  ( );

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/

		this.add(this.pnl2, BorderLayout.CENTER);
		this.add(this.pnl1, BorderLayout.SOUTH);
		this.add(this.pnl3, BorderLayout.EAST);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// Méthode permettant de définir la couleur d'une arête

	public void setCouleurArete ( Arete a, Color c ) { this.pnl2.setCouleurArete(a, c); }

	// Méthodes permettant de rafraichir le score et les messages du joueur

	public void setLabel ( int points ) { this.pnl3.resetMessage(points); }

	public void setMessage ( String message ) { this.pnl3.setMessage(message); }

	public void setTxt ( String txt ) { this.pnl1.setTxt(txt); }
}